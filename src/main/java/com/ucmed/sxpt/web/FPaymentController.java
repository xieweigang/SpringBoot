package com.ucmed.sxpt.web;

import com.ucmed.sxpt.dao.PaymentOrderMapper;
import com.ucmed.sxpt.entity.PaymentOrder;
import com.ucmed.sxpt.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/payment")
public class FPaymentController {
    public static final Logger LOG = Logger.getLogger(FPaymentController.class);
    public static final String NOTIFY_URL = GlobalConstants.WEB_URL + "/payment/payNotify.htm";
    public static final String RETURN_URL = GlobalConstants.WEB_URL + "/payment/payReturn.htm";
    @Autowired
    private PaymentOrderMapper paymentOrderMapper;

    // 支付订单入口页面
    @RequestMapping(method = RequestMethod.GET, value = "/testPay.htm")
    public String testPay(HttpServletRequest request, ModelMap map) {
        LOG.info("test");
        String kh = "A23455432";
        String klx = "0";
        String goodsId = "1,2,3,4";
        if (!StringUtils.isEmpty(request.getParameter("goodsId"))) {
            goodsId = request.getParameter("goodsId");
        }
        String goodsName = "检查费，门诊费，化验费";
        double amount = 0.01;
        // 本地订单查询，没找到则新建订单
        PaymentOrder paymentOrder = paymentOrderMapper.selectByGoodsId(goodsId);
        if (paymentOrder == null) {
            paymentOrder = new PaymentOrder();
            paymentOrder.setOrderId(PaymentConfig.getOrderId()); // 订单号
            paymentOrder.setOrderType("3"); // 订单类型，1诊间支付2住院缴费3测试缴费
            paymentOrder.setOrderTitle("测试缴费"); // 订单类型名称
            paymentOrder.setOrderAmount(GlobalConstants.DF0.format(amount * 100)); // 订单金额，单位：分
            paymentOrder.setOrderStatus("0"); // 订单状态，0未通知1通知成功2通知失败
            paymentOrder.setCardNo(kh); // 卡号
            paymentOrder.setCardType(klx); // 卡类型0 医保卡2 健康卡3 省内外地社保卡
            paymentOrder.setGoodsId(goodsId); // 商品单号，格式：1,2,3
            paymentOrder.setGoodsName(goodsName); // 商品名称，格式：处方,处置,检验
            paymentOrder.setTradeType("2"); // 交易类型，1支付宝2微信3银行
            paymentOrder.setCreateTime(new Date());
            paymentOrder.setUpdateTime(new Date());
            paymentOrderMapper.insert(paymentOrder);
        } else {
            paymentOrder.setUpdateTime(new Date());
            paymentOrderMapper.updateByPrimaryKey(paymentOrder);
        }
        map.put("orderId", paymentOrder.getOrderId());
        map.put("orderAmount", amount);
        map.put("goodsName", goodsName);
        return "payment/payView";
    }

    // 支付订单入口页面
    @RequestMapping(method = RequestMethod.GET, value = "/payExecute.htm")
    public String payExecute(HttpServletRequest request, ModelMap map) {
        String orderId = request.getParameter("orderId");
        // 本地订单查询
        PaymentOrder paymentOrder = paymentOrderMapper.selectByPrimaryKey(orderId);
        if (paymentOrder == null) {
            map.put("message", "订单不存在");
            return "public/failed";
        }
        // 订单更新时间变更
        paymentOrder.setUpdateTime(new Date());
        paymentOrderMapper.updateByPrimaryKey(paymentOrder);
        // 银联支付请求构建
        String payOrderUrl = PaymentConfig.gePayOrderUrl(paymentOrder.getOrderId(), paymentOrder.getOrderAmount(), NOTIFY_URL, RETURN_URL);
        // 支付跳转
        return "redirect:" + payOrderUrl;
    }

    // 支付通知，更新订单
    @RequestMapping(method = RequestMethod.POST, value = "/payNotify.htm")
    public void payNotify(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("支付结果通知");
        PaymentOrder paymentOrder = packageStore(request);
        if (paymentOrder == null) {
            WebResponse.Response(response, "FAILED");
        } else {
            WebResponse.Response(response, "SUCCESS");
        }
    }

    // 支付返回，更新订单，通知医院，状态为已通知
    @RequestMapping(method = RequestMethod.GET, value = "/payReturn.htm")
    public String payReturn(HttpServletRequest request, ModelMap map) {
        LOG.info("支付结果返回");
        PaymentOrder paymentOrder = packageStore(request);
        if (paymentOrder == null) {
            return "payment/payFailed";
        }
        // 订单类型，1诊间支付2住院缴费3就诊卡充值
        String orderStatus = "0";
        if (paymentOrder.getOrderType().equals("1")) {
            orderStatus = ClinicController.clinicPayConfirm(paymentOrder); // 交易状态,0未通知1通知成功2通知失败
        } else if (paymentOrder.getOrderType().equals("2")) {
            // TODO 住院缴费
        } else if (paymentOrder.getOrderType().equals("3")) {
            orderStatus = "1";
        }
        paymentOrder.setOrderStatus(orderStatus);
        paymentOrder.setUpdateTime(new Date());
        paymentOrderMapper.updateByPrimaryKey(paymentOrder);
        if (orderStatus.equals("1")) {
            return "payment/Success";
        } else if (orderStatus.equals("2")) {
            return "payment/payFailed";
        }
        return "public/failed";
    }

    // 支付订单列表页面
    @RequestMapping(method = RequestMethod.GET, value = "/payList.htm")
    public String payList(HttpServletRequest request, ModelMap map) {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("cardNo", request.getParameter("kh")); // 卡号
        hashMap.put("cardTpye", request.getParameter("klx")); // 卡类型
        List<PaymentOrder> paymentOrderList = paymentOrderMapper.selectByCard(hashMap);
        map.put("payList", paymentOrderList);
        return "payment/payList";
    }

    // 支付响应报文存储
    public PaymentOrder packageStore(HttpServletRequest request) {
        // 签名校验
        if (!PaymentConfig.checkSign(request)) {
            LOG.error("签名验证失败！");
            return null;
        }
        String orderId = request.getParameter("merOrderId");
        String acceptUrl = request.getRequestURI();
        String notifyId = request.getParameter("notifyId");
        String serialId = request.getParameter("seqId");
        String serialStatus = request.getParameter("status");
        String serialPacket = request.getQueryString(); // 获取参数，只支持GET请求
        LOG.info("请求地址：" + acceptUrl);
        LOG.info("请求报文：" + serialPacket);
        PaymentOrder paymentOrder = paymentOrderMapper.selectByPrimaryKey(orderId);
        if (paymentOrder != null) {
            // 已经通知医院，不再变更
            if (!paymentOrder.getOrderStatus().equals("0")) {
                return paymentOrder;
            }
            // 报文已经存储，即已经收到返回页面
            if (!StringUtils.isEmpty(paymentOrder.getSerialPacket())) {
                return paymentOrder;
            }
            paymentOrder.setAcceptUrl(acceptUrl); // 结果处理地址
            paymentOrder.setNotifyId(notifyId); // 通知唯一id
            paymentOrder.setSerialId(serialId);// 流水号
            paymentOrder.setSerialStatus(serialStatus); // 流水状态
            paymentOrder.setSerialPacket(serialPacket); // 流水报文
            paymentOrder.setUpdateTime(new Date());
            paymentOrderMapper.updateByPrimaryKey(paymentOrder);
        }
        return paymentOrder;
    }
}
