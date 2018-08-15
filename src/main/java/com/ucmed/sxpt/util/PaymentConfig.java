package com.ucmed.sxpt.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

public class PaymentConfig {
    public static final Logger LOG = Logger.getLogger(PaymentConfig.class);
    public static final String TID = "88880001"; // 终端号
    public static final String MID = "898340149000005"; // 商户号
    public static final String INST_MID = "YUEDANDEFAULT"; // 业务类型
    public static final String MSG_ID = "3194"; // 来源编号
    public static final String MSG_SRC = "WWW.TEST.COM"; // 消息来源
    public static final String KEY = "fcAmtnx7MwismjWNhNKdHC44mNXtnEQeJkRrhKJwyrW2ysRR"; // 签名key，测试
    //public static final String KEY = "fcAmtnx7MwismjWNhNKdHC44mNXtnEQeJkRrhKJwyrW2ysRR"; // 签名key，生产
    public static final String PAY_URL = "https://qr-test2.chinaums.com/netpay-portal/webpay/pay.do"; // 支付下单测试环境接口地址
    //public static final String PAY_URL = "https://qr.chinaums.com/netpay-portal/webpay/pay.do"; //  支付下单生产环境接口地址
    public static final String PAY_REFUND_URL = "https://qr-test2.chinaums.com/netpay-route-server/api/"; // 退款测试环境接口地址
    //public static final String PAY_REFUND_URL = "https://qr.chinaums.com/netpay-route-server/api/"; // 退款生产环境接口地址

    // 生成签名
    public static String getSign(Map<String, String> treeMap) {
        String toSignSrc = "";
        int i = 0;
        for (Map.Entry<String, String> mapEntry : treeMap.entrySet()) {
            i++;
            if (mapEntry.getKey().equals("sign")) {
                continue;
            }
            toSignSrc += mapEntry.getKey() + "=" + mapEntry.getValue();
            if (i < treeMap.size()) {
                toSignSrc += "&";
            }
        }
        // MD5的方式签名
        String sign = MD5Encrypt.MD5(toSignSrc + KEY);
        LOG.info("签名报文：" + treeMap);
        LOG.info("签名结果：" + sign);
        return sign;
    }

    // 生成支付报文
    public static Map<String, String> getPayOrderPocket(String merOrderId, String totalAmount, String notifyUrl, String returnUrl) {
        // 请求入参处理
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("merOrderId", merOrderId); // 商户订单号
        treeMap.put("tid", TID); // 终端号
        treeMap.put("mid", MID); // 商户号
        treeMap.put("instMid", INST_MID); // 业务类型
        treeMap.put("msgId", MSG_ID); // 来源编号
        treeMap.put("msgSrc", MSG_SRC);  // 消息来源
        treeMap.put("msgType", "WXPay.jsPay");// 消息类型 WXPay.jsPay:微信公众号支付trade.jsPay:支付宝qmf.jspay:全民付qmf.webPay:无卡
        treeMap.put("totalAmount", totalAmount); /// 总金额（分）
        treeMap.put("requestTimestamp", DateUtil.dateToString4(new Date())); // 时间
        treeMap.put("notifyUrl", notifyUrl); // 支付结果通知地址
        treeMap.put("returnUrl", returnUrl); // 网页跳转地址
        LOG.info("生成支付报文：" + treeMap);
        return treeMap;
    }

    // 生成支付地址
    public static String getPayOrderUrl(Map<String, String> treeMap) {
        // 生成支付地址
        String sign = getSign(treeMap);
        // 生成支付地址
        String payOrderUrl = PAY_URL + "?";
        for (Map.Entry<String, String> mapEntry : treeMap.entrySet()) {
            String value = mapEntry.getValue();
            try {
                value = URLEncoder.encode(value, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            payOrderUrl += mapEntry.getKey() + "=" + value + "&";
        }
        payOrderUrl += "sign=" + sign;
        LOG.info("生成支付请求：" + payOrderUrl);
        return payOrderUrl;
    }

    // 生成退款报文
    public static Map<String, String> getPayRefundPocket(String merOrderId, String refundOrderId, String refundAmount) {
        // 请求入参处理
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("tid", TID); // 终端号
        treeMap.put("mid", MID); // 商户号
        treeMap.put("instMid", INST_MID); // 业务类型
        treeMap.put("msgId", MSG_ID); // 来源编号
        treeMap.put("msgSrc", MSG_SRC);  // 消息来源
        treeMap.put("msgType", "wx.refund");// 消息类型 wx.refund:退款
        treeMap.put("merOrderId", merOrderId); // 商户订单号
        treeMap.put("refundOrderId", refundOrderId); // 退款订单号
        treeMap.put("refundAmount", refundAmount); /// 总金额（分）
        treeMap.put("requestTimestamp", DateUtil.dateToString4(new Date())); // 时间
        LOG.info("生成退款报文：" + treeMap);
        return treeMap;
    }

    // 生成退款地址
    public static JSONObject getPayRefundJson(Map<String, String> treeMap) {
        String sign = getSign(treeMap);
        // 生产退款报文
        JSONObject payRefundJson = new JSONObject();
        for (Map.Entry<String, String> mapEntry : treeMap.entrySet()) {
            payRefundJson.put(mapEntry.getKey(), mapEntry.getValue());
        }
        payRefundJson.put("sign", sign);
        LOG.info("生成退款请求:" + payRefundJson);
        return payRefundJson;
    }

    // 获取支付退款结果报文
    public static Map<String, String> getPocket(HttpServletRequest request, JSONObject res) {
        Map<String, String> treeMap = new TreeMap<>();
        if (request != null) {
            Enumeration<String> paraNames = request.getParameterNames();
            for (Enumeration<String> e = paraNames; e.hasMoreElements(); ) {
                String thisName = e.nextElement().toString();
                String thisValue = request.getParameter(thisName);
                try {
                    thisValue = new String(thisValue.getBytes("iso-8859-1"), "utf-8");
                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                }
                treeMap.put(thisName, thisValue);
            }
        } else if (res != null) {
            Map<String, String> map = (Map) JSON.parse(String.valueOf(res));
            for (Map.Entry<String, String> mapEntry : map.entrySet()) {
                String thisName = mapEntry.getKey();
                String thisValue = String.valueOf(mapEntry.getValue());
                treeMap.put(thisName, thisValue);
            }
        }
        LOG.info("支付退款结果报文：" + treeMap);
        return treeMap;
    }

    // 支付退款结果报文校验
    public static boolean checkPocket(Map<String, String> treeMap) {
        String sign1 = getSign(treeMap);
        LOG.info("计算签名：" + sign1);
        String sign2 = treeMap.get("sign");
        LOG.info("原始签名：" + treeMap.get("sign"));
        if (!sign1.toUpperCase().equals(sign2.toUpperCase())) {
            LOG.error("验证签名失败!");
            return false;
        }
        return true;
    }

    // 支付订单号生成
    public static String getOrderId() {
        String orderId = MSG_ID + String.valueOf(new Date().getTime()) + "6011";
        LOG.info("生成支付订单：" + orderId);
        return orderId;
    }

    // 退款订单号生成
    public static String getRefundId() {
        String refundId = MSG_ID + String.valueOf(new Date().getTime()) + "6012";
        LOG.info("生成退款订单" + refundId);
        return refundId;
    }
}
