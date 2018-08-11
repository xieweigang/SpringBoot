package com.ucmed.sxpt.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;


/**
 * 获取卓健测试号下的相关微信号
 *
 * @author Gerrard
 * @date 2015年1月19日
 */
public class ZJCSGetOpenId {
    private static final Logger LOG = Logger.getLogger(ZJCSGetOpenId.class);
    public static final String appid = "wx43936f9d6d08e1f1";
    public static final String appsecret = "5e013844e7d14d494179fc976d06516f";

    /**
     * 微信客户端发送https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&
     * redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_base&state=123#
     * wechat_redirect REDIRECT_URI回调地址为当前需展示界面 获取openid
     *
     * @return
     */
    public static String getOpenId(String code) {
        LOG.info("getopenid");
        LOG.info("code=" + code);
        String grant_type = "authorization_code";
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + appid + "&secret=" + appsecret + "&code=" + code
                + "&grant_type=" + grant_type;
        String str = HttpUtil.getInstance().GET(url);
        JSONObject reslut = JSONObject.parseObject(str);
        String openid = reslut.getString("openid");
        LOG.info("openid=" + openid);
        return openid;
    }
}
