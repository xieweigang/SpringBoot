package com.ucmed.sxpt.web;

import com.alibaba.fastjson.JSONObject;
import com.ucmed.sxpt.dao.PaymentOrderMapper;
import com.ucmed.sxpt.dao.PaymentRefundMapper;
import com.ucmed.sxpt.entity.PaymentOrder;
import com.ucmed.sxpt.entity.PaymentRefund;
import com.ucmed.sxpt.entity.dto.UserDto;
import com.ucmed.sxpt.util.*;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/payment")
public class FPaymentController {
    public static final Logger LOG = Logger.getLogger(FPaymentController.class);
    public static final String NOTIFY_URL = GlobalConstants.WEB_URL + "/payment/payNotify.htm"; // 支付通知地址
    public static final String RETURN_URL = GlobalConstants.WEB_URL + "/payment/payReturn.htm"; // 支付返回地址
    public static final String REFUND_URL = GlobalConstants.WEB_URL + "/api-payment/refund"; // 支付退款地址
    @Autowired
    private PaymentOrderMapper paymentOrderMapper;

    // 支付确认页面
    @RequestMapping(method = RequestMethod.GET, value = "/testPay.htm")
    public String testPay(HttpServletRequest request, HttpSession session, ModelMap map) {
        LOG.info(request.getRequestURL() + "?" + request.getQueryString());
        UserDto userDto = (UserDto) session.getAttribute("user");
        String goodsId = "1234";
        if (!StringUtils.isEmpty(request.getParameter("goodsId"))) {
            goodsId = request.getParameter("goodsId");
        }
        String goodsName = "检查费，门诊费，化验费";
        // 本地订单查询，没找到则新建订单
        PaymentOrder paymentOrder = paymentOrderMapper.selectByGoodsId(goodsId);
        if (paymentOrder == null) {
            paymentOrder = new PaymentOrder();
            paymentOrder.setOrderId(PaymentConfig.getOrderId());
            paymentOrder.setOrderType("0");
            paymentOrder.setOrderTitle("测试缴费");
            paymentOrder.setOrderAmount("2");
            paymentOrder.setOrderStatus("0");
            paymentOrder.setCardNo(userDto.getKh());
            paymentOrder.setCardType(userDto.getKlx());
            paymentOrder.setGoodsId(goodsId);
            paymentOrder.setGoodsName(goodsName);
            paymentOrder.setTradeType("2");
            paymentOrder.setCreateTime(new Date());
            paymentOrder.setUpdateTime(new Date());
            paymentOrderMapper.insert(paymentOrder);
        } else {
            paymentOrder.setUpdateTime(new Date());
            paymentOrderMapper.updateByPrimaryKey(paymentOrder);
        }
        double amount = GlobalConstants.DoubleValueOf(paymentOrder.getOrderAmount());
        map.put("orderId", paymentOrder.getOrderId());
        map.put("orderAmount", GlobalConstants.DF0_00.format(amount / 100));
        map.put("goodsName", paymentOrder.getGoodsName());
        return "payment/payView";
    }

    // 支付跳转
    @RequestMapping(method = RequestMethod.GET, value = "/payExecute.htm")
    public String payExecute(HttpServletRequest request, ModelMap map) {
        LOG.info(request.getRequestURL() + "?" + request.getQueryString());
        String orderId = request.getParameter("orderId");
        // 本地订单查询
        PaymentOrder paymentOrder = paymentOrderMapper.selectByPrimaryKey(orderId);
        if (paymentOrder == null) {
            LOG.error("订单不存在！");
            map.put("message", "订单不存在！");
            return "public/error";
        }
        // 订单已支付
        if (!paymentOrder.getOrderStatus().equals("0")) {
            LOG.error("订单已成功支付！");
            map.put("message", "订单已成功支付！");
            return "public/error";
        }
        // 订单更新时间变更
        paymentOrder.setUpdateTime(new Date());
        paymentOrderMapper.updateByPrimaryKey(paymentOrder);
        // 银联支付请求构建
        JSONObject req = PaymentConfig.getOrderRequest(orderId, paymentOrder.getOrderAmount(), NOTIFY_URL, RETURN_URL);
        // 支付跳转
        return "redirect:" + PaymentConfig.getRequestPath(PaymentConfig.PAY_URL, req);
    }

    // 支付订单列表页面
    @RequestMapping(method = RequestMethod.GET, value = "/payList.htm")
    public String payList(HttpServletRequest request, HttpSession session, ModelMap map) {
        LOG.info(request.getRequestURL() + "?" + request.getQueryString());
        UserDto userDto = (UserDto) session.getAttribute("user");
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("cardNo", userDto.getKh()); // 卡号
        hashMap.put("cardType", userDto.getKlx()); // 卡类型
        List<PaymentOrder> paymentOrderList = paymentOrderMapper.selectByCard(hashMap);
        map.put("payList", paymentOrderList);
        return "payment/payList";
    }

    // 支付结果通知：更新订单
    @RequestMapping(method = RequestMethod.POST, value = "/payNotify.htm")
    public void payNotify(HttpServletRequest request, HttpServletResponse response) {
        LOG.info(request.getRequestURL() + "?" + request.getQueryString());
        PaymentOrder paymentOrder = packageStore(request);
        try {
            if (paymentOrder == null) {
                response.getWriter().print("FAILED");
            } else {
                response.getWriter().print("SUCCESS");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 支付返回：更新订单，通知医院，自动退款
    @RequestMapping(method = RequestMethod.GET, value = "/payReturn.htm")
    public String payReturn(HttpServletRequest request, ModelMap map) {
        LOG.info(request.getRequestURL() + "?" + request.getQueryString());
        PaymentOrder paymentOrder = packageStore(request);
        if (paymentOrder == null) {
            LOG.error("订单不存在！");
            map.put("message", "订单不存在！");
            return "public/error";
        }
        boolean noticeStatus = false; // 通知是否成功
        // 订单类型，0测试缴费1诊间支付2住院缴费3挂号费4卡充值5其他
        if (paymentOrder.getOrderType().equals("0")) {
            noticeStatus = true;
        } else if (paymentOrder.getOrderType().equals("1")) {
            noticeStatus = ClinicController.clinicPayConfirm(paymentOrder);
        } else if (paymentOrder.getOrderType().equals("2")) {
            // TODO 住院缴费
        }
        if (noticeStatus) { // 通知成功
            paymentOrder.setOrderStatus("2");
            paymentOrder.setUpdateTime(new Date());
            paymentOrderMapper.updateByPrimaryKey(paymentOrder);
            LOG.info("订单完结，订单号：" + paymentOrder.getOrderId());
            return "payment/paySuccess";
        } else { // 通知失败
            // 发起自动退款
            JSONObject req = new JSONObject();
            req.put("uniqueId", paymentOrder.getOrderId());
            req.put("orderId", paymentOrder.getOrderId());
            req.put("refundType", "1");
            req.put("refundAmount", paymentOrder.getOrderAmount());
            req.put("sign", PaymentConfig.getSign(req));
            String resString = HttpUtil.getInstance().POST(REFUND_URL, req.toString());
            JSONObject res = JSONObject.parseObject(resString);
            LOG.error("通知医院失败，退款结果：" + res.get("returnInfo"));
            map.put("message", "通知医院失败，退款结果：" + res.get("returnInfo"));
            return "payment/payFailed";
        }
    }

    // 支付响应报文存储
    public PaymentOrder packageStore(HttpServletRequest request) {
        JSONObject req = PaymentConfig.getRequestJSON(request);
        PaymentOrder paymentOrder = paymentOrderMapper.selectByPrimaryKey(request.getParameter("merOrderId"));
        if (paymentOrder != null) {
            // 订单未支付状态下，如果支付成功，状态改为已支付
            if (request.getParameter("status").equals("TRADE_SUCCESS") && paymentOrder.getOrderStatus().equals("0")) {
                paymentOrder.setOrderStatus("1"); // 已支付
            }
            paymentOrder.setAcceptUrl(request.getRequestURI());
            paymentOrder.setNotifyId(request.getParameter("notifyId"));
            paymentOrder.setSerialId(request.getParameter("seqId"));
            paymentOrder.setSerialStatus(request.getParameter("status"));
            paymentOrder.setSerialPacket(req.toString());
            paymentOrder.setUpdateTime(new Date());
            paymentOrderMapper.updateByPrimaryKey(paymentOrder);
        }
        return paymentOrder;
    }
}
