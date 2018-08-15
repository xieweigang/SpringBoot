package com.ucmed.sxpt.util;

import com.alibaba.fastjson.JSONObject;
import com.ucmed.sxpt.web.FPaymentController;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 工具类，http链接池
 * Created by 谢伟刚 on 2018/05/07.
 */
public class HttpUtil {
    private static final Logger LOG = Logger.getLogger(HttpUtil.class);
    public static final int maxTotal = 200; // 最大连接数
    public static final int defaultMaxPerRoute = 100; // 每个路由的最大连接数
    public static final int connectTimeout = 20000; // 连接超时时间
    public static final int socketTimeout = 20000; // 读取超时时间
    public static final int requestTimeout = 5000; // 从池中获取连接超时时间
    public static CloseableHttpClient httpclient = null;
    private static HttpUtil mInstance;

    public static HttpUtil getInstance() {
        if (mInstance == null) {
            mInstance = new HttpUtil();
            mInstance.init();
        }
        return mInstance;
    }

    /**
     * 初始化client对象
     */
    public void init() {
        if (httpclient != null) {
            return;
        }
        // 创建连接管理器
        PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        connMgr.setMaxTotal(maxTotal);
        // 设置每个路由的最大连接数
        connMgr.setDefaultMaxPerRoute(defaultMaxPerRoute);
        // 创建httpClient
        httpclient = HttpClients.custom().setConnectionManager(connMgr).build();
    }

    /**
     * 关闭连接池
     */
    public void close() {
        if (httpclient != null) {
            try {
                httpclient.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Get方式取得URL的内容
     */
    public String GET(String url) {
        // 参数检查
        if (httpclient == null) {
            throw new RuntimeException("httpclient not init.");
        }
        if (url == null || url.trim().length() == 0) {
            throw new RuntimeException("url is blank.");
        }
        HttpGet httpGet = new HttpGet(url);
        // 设置内容
        httpGet.addHeader("accept", "*/*");
        httpGet.addHeader("connection", "Keep-Alive");
        httpGet.addHeader("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet, HttpClientContext.create());
            // 转换结果
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity);
            // 消费掉内容
            EntityUtils.consume(entity);
            return html;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            httpGet.releaseConnection();
        }
        return "";
    }

    /**
     * Post方式取得URL的内容
     */
    public String POST(String url, String content) {
        return POST(url, content, "application/json");
    }

    /**
     * Post方式取得URL的内容，charset为UTF-8
     */
    public String POST(String url, String content, String contentType) {
        return POST(url, content, contentType, "UTF-8");
    }

    /**
     * Post方式取得URL的内容，默认为"application/x-www-form-urlencoded"格式，charset为UTF-8
     */
    public String POST(String url, String content, String contentType, String charset) {
        // 参数检查
        if (httpclient == null) {
            throw new RuntimeException("httpclient not init.");
        }
        if (url == null || url.trim().length() == 0) {
            throw new RuntimeException("url is blank.");
        }
        HttpPost httpPost = new HttpPost(url);
        // 设置内容
        ContentType type = ContentType.create(contentType, Charset.forName(charset));
        StringEntity reqEntity = new StringEntity(content, type);
        httpPost.setEntity(reqEntity);

        httpPost.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE .0; Windows NT 6.1; Trident/4.0; SLCC2;)");
        httpPost.addHeader("Accept-Encoding", "*");
        // 设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout) // 连接超时时间
                .setSocketTimeout(socketTimeout) // 读取超时时间（等待数据超时时间）
                .setConnectionRequestTimeout(requestTimeout) // 从池中获取连接超时时间
                .build();
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpPost, HttpClientContext.create());
            // 转换结果
            HttpEntity entity = response.getEntity();
            String html = EntityUtils.toString(entity, charset);
            // 消费掉内容
            EntityUtils.consume(entity);
            return html;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            httpPost.releaseConnection();
        }
        return "";
    }

    /**
     * 工具类测试
     */
    public static void main(String[] args) {
        JSONObject req = new JSONObject();
        req.put("uniqueId", "319415343265998526013");
        req.put("orderId", "319415343265998526011");
        req.put("refundType", "1");
        req.put("refundAmount", "1");
        req.put("sign", PaymentConfig.getSign(req));
        String resString = HttpUtil.getInstance().POST("http://localhost:8080/payment/payRefund.htm", req.toString());
        System.out.println(resString);
    }
}