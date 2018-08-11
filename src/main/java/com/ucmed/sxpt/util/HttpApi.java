package com.ucmed.sxpt.util;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class HttpApi {
    private static final Logger LOG = Logger.getLogger(HttpApi.class);
    private static final String URL = "http://192.168.12.196:8081/wxserver/n_wxserver.asmx";
    private static final String CONTENT_TYPE = "text/xml";
    private static final String CHARSET = "UTF-8";
    private static HttpApi mInstance;
    // api列表
    public static final String clinicpaylist = "clinicpaylist"; // 门诊列表查询
    public static final String clinicpaydetail = "clinicpaydetail"; // 门诊费用详情
    public static final String clinicpaybudget = "clinicpaybudget"; // 门诊支付预结算
    public static final String clinicpay = "clinicpay"; // 门诊支付结算
    public static final String clinicguide = "clinicguide"; // 门诊指引单
    public static final String clinicpayconfirm = "clinicpayconfirm"; // 门诊配药确认
    public static final String beinhospitalinfo = "beinhospitalinfo"; // 住院信息查询
    public static final String beinhospitalbills = "beinhospitalbills"; // 住院信息查询
    public static final String beinhospitalpay = "beinhospitalpay"; // 住院费用预缴
    public static final String beinhospitalpayrecord = "beinhospitalpayrecord"; // 住院预缴记录查询
    public static final String beinhospitalpaybudget = "beinhospitalpaybudget"; // 住院预结算
    public static final String beinhospitalpayconfirm = "beinhospitalpayconfirm"; // 住院结算

    public static String api(String apiName, Map<String, String> params) {
        StringBuffer reqBuffer = new StringBuffer();
        reqBuffer.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
        reqBuffer.append("<soap:Body>");
        reqBuffer.append("<" + apiName + " xmlns=\"http://tempurl.org\">");
        for (Map.Entry<String, String> mapEntry : params.entrySet()) {
            reqBuffer.append("<" + mapEntry.getKey() + ">" + mapEntry.getValue() + "</" + mapEntry.getKey() + ">");
        }
        reqBuffer.append("</" + apiName + ">");
        reqBuffer.append("</soap:Body>");
        reqBuffer.append("</soap:Envelope>");
        String reqStringg = reqBuffer.toString();
        LOG.info(reqStringg);
        String resString = HttpUtil.getInstance().POST(URL, reqStringg, CONTENT_TYPE, CHARSET);
        LOG.info(resString);
        int beginIndex = resString.indexOf("{\"returnCode\"");
        int endIndex = resString.indexOf("</" + apiName + "Result>");
        return resString.substring(beginIndex, endIndex);
    }

    public static void main(String[] args) {
        Map<String, String> params = new HashMap<>();
        params.put("cardno", "010"); // 卡号
        params.put("cardtype", "3"); // 卡类型
        String resString = HttpApi.api(HttpApi.clinicpaylist, params);
        LOG.info(resString);
    }
}
