package com.ucmed.sxpt.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ucmed.sxpt.dao.PaymentOrderMapper;
import com.ucmed.sxpt.entity.PaymentOrder;
import com.ucmed.sxpt.entity.dto.UserDto;
import com.ucmed.sxpt.util.GlobalConstants;
import com.ucmed.sxpt.util.HttpApi;
import com.ucmed.sxpt.util.PaymentConfig;
import com.ucmed.sxpt.util.WebResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/clinic")
public class ClinicController {
    public static final Logger LOG = Logger.getLogger(FPaymentController.class);
    @Autowired
    private PaymentOrderMapper paymentOrderMapper;

    // 门诊缴费列表
    @RequestMapping(method = RequestMethod.GET, value = "/clinicPayList.htm")
    public String clinicPayList(HttpSession session, ModelMap map) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("cardno", userDto.getKh()); // 卡号
        hashMap.put("cardtype", userDto.getKlx()); // 卡类型
        String resString = HttpApi.api(HttpApi.clinicpaylist, hashMap);
        JSONObject res = JSONObject.parseObject(resString);
        // 接口调用失败，返回错误页面
        if (!res.getString("returnCode").equals("0")) {
            map.put("message", "暂无相关信息！");
            return "public/failed";
        }
        // 重新构建页面所需参数
        JSONArray clinics = res.getJSONArray("clinics");
        JSONArray hasPayList = new JSONArray();
        JSONArray notPayList = new JSONArray();
        double notPayTotal = 0;
        for (int i = 0; i < clinics.size(); i++) {
            JSONObject clinic = clinics.getJSONObject(i);
            if (clinic.getString("isPay").equals("N")) {
                notPayList.add(clinic);
                String clinicCharge = clinic.getString("clinicCharge");
                notPayTotal += GlobalConstants.DoubleValueOf(clinicCharge);
            } else {
                hasPayList.add(clinic);
            }
        }
        map.put("clinics", clinics);
        map.put("hasPayList", hasPayList);
        map.put("notPayList", notPayList);
        map.put("notPayTotal", GlobalConstants.DF0_00.format(notPayTotal));
        return "clinic/clinicPayList";
    }

    // 门诊缴费详情
    @RequestMapping(method = RequestMethod.GET, value = "/clinicPayDetail.htm")
    public String clinicPayDetail(HttpServletRequest request, HttpSession session, ModelMap map) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        String clinicString = request.getParameter("clinic");
        JSONObject clinic = JSONObject.parseObject(clinicString);
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("cardno", userDto.getKh()); // 卡号
        hashMap.put("cardtype", userDto.getKlx()); // 卡类型
        hashMap.put("clinicchargeid", clinic.getString("clinicChargeId")); // 诊间支付单id
        String resString = HttpApi.api(HttpApi.clinicpaydetail, hashMap);
        JSONObject res = JSONObject.parseObject(resString);
        // 接口调用失败，返回错误页面
        if (!res.getString("returnCode").equals("0")) {
            map.put("message", "暂无相关信息！");
            return "public/failed";
        }
        if (clinic.getString("isPay").equals("P")) {
            clinic.put("isPayName", "已支付");
        } else {
            clinic.put("isPayName", "待支付");
        }
        if (StringUtils.isEmpty(clinic.getString("isConfirm"))) {
            clinic.put("isConfirm", "N");
        }
        map.put("clinic", clinic);
        map.put("items", res.getJSONArray("items"));
        return "clinic/clinicPayDetail";
    }

    // 门诊指引单
    @RequestMapping(method = RequestMethod.GET, value = "/clinicGuide.htm")
    public String clinicGuide(HttpServletRequest request, HttpSession session, ModelMap map) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("cardno", userDto.getKh()); // 卡号
        hashMap.put("cardtype", userDto.getKlx()); // 卡类型
        hashMap.put("clinicchargeid", request.getParameter("chargeId")); // 诊间支付单id
        String resString = HttpApi.api(HttpApi.clinicguide, hashMap);
        JSONObject res = JSONObject.parseObject(resString);
        // 接口调用失败，返回错误页面
        if (!res.getString("returnCode").equals("0")) {
            map.put("message", "暂无相关信息！");
            return "public/failed";
        }
        map.put("guide", res);
        return "clinic/clinicGuide";
    }

    // 医技护配药确认
    @RequestMapping(method = RequestMethod.POST, value = "/clinicDoctorConfirm.htm")
    public void clinicDoctorConfirm(HttpServletRequest request, HttpServletResponse response) {
        // 门诊支付结算
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("clinicchargeid", request.getParameter("chargeId")); // 诊间支付单id（支持多单号，以逗号分隔，比如：123,125,126）
        hashMap.put("confirmnumber", request.getParameter("doctorId")); // 确认人工号（药师工号）
        String resString = HttpApi.api(HttpApi.clinicpayconfirm, hashMap);
        WebResponse.JsonResponse(response, resString);
    }

    // 门诊缴费预结算，记录订单，并让患者支付
    @RequestMapping(method = RequestMethod.GET, value = "/clinicPayBudget.htm")
    public String clinicPayBudget(HttpServletRequest request, HttpSession session, ModelMap map) {
        UserDto userDto = (UserDto) session.getAttribute("user");
        String orderAmount = request.getParameter("orderAmount");
        String goodsId = request.getParameter("goodsId");
        String goodsName = request.getParameter("goodsName");
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("clinicchargeid", goodsId); // 诊间支付单id
        hashMap.put("cardno", userDto.getKh()); // 卡号
        hashMap.put("cardtype", userDto.getKlx()); // 卡类型
        String resString = HttpApi.api(HttpApi.clinicpaybudget, hashMap);
        JSONObject res = JSONObject.parseObject(resString);
        // 接口调用失败，返回错误页面
        if (!res.getString("returnCode").equals("0")) {
            map.put("message", "预结算失败");
            return "public/failed";
        }
        double fee1 = GlobalConstants.DoubleValueOf(res.getString("fee"));
        double fee2 = GlobalConstants.DoubleValueOf(orderAmount);
        if (fee1 != fee2) {
            map.put("message", "结算金额有出入");
            return "public/failed";
        }
        // 本地订单记录
        PaymentOrder paymentOrder = paymentOrderMapper.selectByGoodsId(goodsId);
        if (paymentOrder == null) {
            paymentOrder = new PaymentOrder();
            paymentOrder.setOrderId(PaymentConfig.getOrderId()); // 订单号
            paymentOrder.setOrderType("1"); // 订单类型，1诊间支付2住院缴费3测试缴费
            paymentOrder.setOrderTitle("诊间支付"); // 订单类型名称
            paymentOrder.setOrderAmount(GlobalConstants.DF0.format(fee1 * 100)); // 订单金额，单位：分
            paymentOrder.setOrderStatus("0"); // 订单状态，0未通知1通知成功2通知失败
            paymentOrder.setCardNo(userDto.getKh()); // 卡号
            paymentOrder.setCardType(userDto.getKlx()); // 卡类型0 医保卡2 健康卡3 省内外地社保卡
            paymentOrder.setGoodsId(goodsId); // 商品单号，格式：1,2,3
            paymentOrder.setGoodsName(goodsName); // 商品名称，格式：处方,处置,检验
            paymentOrder.setCreateTime(new Date());
            paymentOrder.setUpdateTime(new Date());
            paymentOrderMapper.insert(paymentOrder);
        }
        double amount = GlobalConstants.DoubleValueOf(paymentOrder.getOrderAmount());
        String viewAmount = GlobalConstants.DF0_00.format(amount / 100);
        map.put("orderId", paymentOrder.getOrderId());
        map.put("orderAmount", viewAmount);
        map.put("goodsName", paymentOrder.getGoodsName());
        return "payment/payView";
    }

    // 门诊支付结算
    public static String clinicPayConfirm(PaymentOrder paymentOrder) {
        // 门诊支付结算
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("tradetype", paymentOrder.getTradeType()); // 交易类型1：支付宝2：微信3：银行
        hashMap.put("serialnumber", paymentOrder.getSerialId()); // 支付流水号
        hashMap.put("outtradeno", paymentOrder.getOrderId()); // 业务订单号
        hashMap.put("cardno", paymentOrder.getCardNo()); // 卡号
        hashMap.put("cardtype", paymentOrder.getCardType()); // 卡类型0 医保卡2 健康卡3 省内外地社保卡
        hashMap.put("clinicchargeid", paymentOrder.getGoodsId()); // 诊间支付单id（支持多单号，以逗号分隔，比如：123,125,126）
        hashMap.put("fee", paymentOrder.getOrderAmount()); // 金额：单位分
        hashMap.put("packet", paymentOrder.getSerialPacket()); // 支付通知报文
        String resString = HttpApi.api(HttpApi.clinicpay, hashMap);
        JSONObject res = JSONObject.parseObject(resString);
        // 交易状态,0未通知1通知成功2通知失败
        if (res.getString("returnCode").equals("0")) {
            return "1";
        } else {
            return "2";
        }
    }
}
