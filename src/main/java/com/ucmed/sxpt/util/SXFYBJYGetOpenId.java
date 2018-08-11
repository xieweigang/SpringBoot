package com.ucmed.sxpt.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取绍兴妇幼保健院openId
 *
 * @author tian
 * @date 2015年11月25日
 */
public class SXFYBJYGetOpenId {
    private static final Logger LOG = Logger.getLogger(SXFYBJYGetOpenId.class);
    public static final String appid = "wx2fa01ab1809d8f16"; // 正式
    public static final String appsecret = "efa9535c56fa3ca4a3bc75cc88474a65"; // 正式

    /**
     * 微信客户端发送https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&
     * redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=123#
     * wechat_redirect REDIRECT_URI回调地址为当前需展示界面 获取openid
     *
     * @return
     */
    public static String getOpenId(String code) {
        LOG.info("code=" + code);
        String grant_type = "authorization_code";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + appid + "&secret=" + appsecret + "&code=" + code
                + "&grant_type=" + grant_type;
        String str = HttpUtil.getInstance().GET(url);
        JSONObject reslut = JSONObject.parseObject(str);
        String openid = reslut.getString("openid");
        return openid;
    }
}
