package com.ucmed.sxpt.util;

import com.alibaba.fastjson.JSONObject;
import com.ucmed.sxpt.entity.PaymentOrder;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

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
    public static final String WX_REFUND_URL = GlobalConstants.WEB_URL + "/payment/payRefund.htm"; // 微信退款地址

    // 生成签名
    public static String getSign(Map<String, String> paramsMap) {
        String toSignSrc = "";
        int i = 0;
        for (Map.Entry<String, String> mapEntry : paramsMap.entrySet()) {
            toSignSrc += mapEntry.getKey() + "=" + mapEntry.getValue();
            i++;
            if (i < paramsMap.size()) {
                toSignSrc += "&";
            }
        }
        // MD5的方式签名
        String sign = MD5Encrypt.MD5(toSignSrc + KEY);
        return sign;
    }

    // 生成支付地址
    public static String gePayOrderUrl(String merOrderId, String totalAmount, String notifyUrl, String returnUrl) {
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
        String sign = PaymentConfig.getSign(treeMap);
        // 生成支付地址
        String payOrderUrl = PAY_URL + "?";
        for (Map.Entry<String, String> mapEntry : treeMap.entrySet()) {
            payOrderUrl += mapEntry.getKey() + "=" + mapEntry.getValue() + "&";
        }
        payOrderUrl += "sign=" + sign;
        return payOrderUrl;
    }

    // 生产退款报文
    public static JSONObject getPayRefundJson(String merOrderId, String refundOrderId, String refundAmount) {
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
        String sign = PaymentConfig.getSign(treeMap);
        // 生产退款报文
        JSONObject payRefundJson = new JSONObject();
        for (Map.Entry<String, String> mapEntry : treeMap.entrySet()) {
            payRefundJson.put(mapEntry.getKey(), mapEntry.getValue());
        }
        payRefundJson.put("sign", sign);
        return payRefundJson;
    }

    // 支付成功校验
    public static boolean checkSign(HttpServletRequest request) {
        Map<String, String> paramsMap = new TreeMap<>();
        String sign1 = "";
        //获取所有的请求参数
        Enumeration<String> paraNames = request.getParameterNames();
        for (Enumeration<String> e = paraNames; e.hasMoreElements(); ) {
            String thisName = e.nextElement().toString();
            String thisValue = request.getParameter(thisName);
            if (thisName.equals("sign")) {
                sign1 = thisValue;
            } else {
                paramsMap.put(thisName, thisValue);
            }
        }
        LOG.info("Source Sign:" + sign1);
        String sign2 = getSign(paramsMap);
        LOG.info("Caculate Sign:" + sign2);
        String message = "入参列表：";
        for (Map.Entry<String, String> mapEntry : paramsMap.entrySet()) {
            message += mapEntry.getKey() + "=" + mapEntry.getValue() + "  ";
        }
        LOG.info(message);
        if (!sign1.toUpperCase().equals(sign2.toUpperCase())) {
            LOG.error("验证签名失败!");
            return false;
        }
        return true;
    }

    // 支付方式转换
    public static String getMsgType(String orderType) {
        // WXPay.jsPay:微信公众号支付trade.jsPay:支付宝qmf.jspay:全民付qmf.webPay:无卡
        String msgType = "qmf.jspay";
        if (orderType.equals("1")) {
            msgType = "trade.jsPay";
        } else if (orderType.equals("2")) {
            msgType = "WXPay.jsPay";
        } else if (orderType.equals("3")) {
            msgType = "qmf.webPay";
        }
        return msgType;
    }

    // 支付方式转换
    public static String getOrderId() {
        return PaymentConfig.MSG_ID + String.valueOf(new Date().getTime()) + "6011";
    }

    // 支付方式转换
    public static String getRefundId() {
        return PaymentConfig.MSG_ID + String.valueOf(new Date().getTime()) + "6012";
    }
}