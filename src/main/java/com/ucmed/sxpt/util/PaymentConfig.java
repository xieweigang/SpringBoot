package com.ucmed.sxpt.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

public class PaymentConfig {
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
    public static String getSign(JSONObject req) {
        Map<String, Object> map = (Map) JSON.parse(String.valueOf(req));
        Map<String, String> treeMap = new TreeMap<>();
        for (Map.Entry<String, Object> mapEntry : map.entrySet()) {
            treeMap.put(String.valueOf(mapEntry.getKey()), String.valueOf(mapEntry.getValue()));
        }
        String signString = "";
        int i = 0;
        for (Map.Entry<String, String> mapEntry : treeMap.entrySet()) {
            if (i++ > 0) {
                signString += "&";
            }
            signString += mapEntry.getKey() + "=" + mapEntry.getValue();
        }
        // MD5的方式签名
        String sign = MD5Encrypt.MD5(signString + KEY);
        return sign;
    }

    // JSON报文转URL
    public static String getRequestPath(String requestURL, JSONObject req) {
        Map<String, Object> map = (Map) JSON.parse(String.valueOf(req));
        String requestPath = requestURL + "?";
        int i = 0;
        try {
            for (Map.Entry<String, Object> mapEntry : map.entrySet()) {
                if (i++ > 0) {
                    requestPath += "&";
                }
                String thisValue = String.valueOf(mapEntry.getValue());
                thisValue = URLEncoder.encode(thisValue, "UTF-8");
                requestPath += mapEntry.getKey() + "=" + thisValue;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return requestPath;
    }

    // 生成支付报文
    public static JSONObject getOrderRequest(String merOrderId, String totalAmount, String notifyUrl, String returnUrl) {
        // 请求入参处理
        JSONObject req = new JSONObject();
        req.put("merOrderId", merOrderId); // 商户订单号
        req.put("tid", TID); // 终端号
        req.put("mid", MID); // 商户号
        req.put("instMid", INST_MID); // 业务类型
        req.put("msgId", MSG_ID); // 来源编号
        req.put("msgSrc", MSG_SRC);  // 消息来源
        req.put("msgType", "WXPay.jsPay");// 消息类型 WXPay.jsPay:微信公众号支付trade.jsPay:支付宝qmf.jspay:全民付qmf.webPay:无卡
        req.put("totalAmount", totalAmount); /// 总金额（分）
        req.put("requestTimestamp", DateUtil.dateToString4(new Date())); // 时间
        req.put("notifyUrl", notifyUrl); // 支付结果通知地址
        req.put("returnUrl", returnUrl); // 网页跳转地址
        req.put("sign", getSign(req));
        return req;
    }

    // 生成退款报文
    public static JSONObject getRefundRequest(String merOrderId, String refundOrderId, String refundAmount) {
        // 请求入参处理
        JSONObject req = new JSONObject();
        req.put("tid", TID); // 终端号
        req.put("mid", MID); // 商户号
        req.put("instMid", INST_MID); // 业务类型
        req.put("msgId", MSG_ID); // 来源编号
        req.put("msgSrc", MSG_SRC);  // 消息来源
        req.put("msgType", "wx.refund");// 消息类型 wx.refund:退款
        req.put("merOrderId", merOrderId); // 商户订单号
        req.put("refundOrderId", refundOrderId); // 退款订单号
        req.put("refundAmount", refundAmount); /// 总金额（分）
        req.put("requestTimestamp", DateUtil.dateToString4(new Date())); // 时间
        req.put("sign", getSign(req));
        return req;
    }

    // 生成request请求的JSON报文
    public static JSONObject getRequestJSON(HttpServletRequest request) {
        JSONObject req = new JSONObject();
        Enumeration<String> paraNames = request.getParameterNames();
        try {
            for (Enumeration<String> e = paraNames; e.hasMoreElements(); ) {
                String thisKey = e.nextElement().toString();
                String thisValue = request.getParameter(thisKey);
                thisValue = new String(thisValue.getBytes("iso-8859-1"), "utf-8");
                req.put(thisKey, thisValue);
            }
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        return req;
    }

    // 支付订单号生成
    public static String getOrderId() {
        String orderId = MSG_ID + String.valueOf(new Date().getTime()) + "6011";
        return orderId;
    }

    // 退款订单号生成
    public static String getRefundId() {
        String refundId = MSG_ID + String.valueOf(new Date().getTime()) + "6012";
        return refundId;
    }

    /**
     * 工具类测试
     */
    public static void main(String[] args) {
        String url = "http://localhost:8080/api/getPayOrder?kh=010&klx=2";
        String resString = HttpUtil.getInstance().GET(url);
        System.out.println(resString);
    }
}
