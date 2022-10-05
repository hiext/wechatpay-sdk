package com.zhengzhuanglaile.wechatpay.util;

import java.io.IOException;
import java.net.URI;
import java.util.function.Consumer;

import com.zhengzhuanglaile.wechatpay.model.WechatPayConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信支付请求工具类
 * 
 * @author dengying.zhang 2022年8月18日 下午12:18:38
 * @since 1.0.0
 */
public class RequestClientUtil {

    private static Logger       logger = LoggerFactory.getLogger(RequestClientUtil.class);
    private CloseableHttpClient httpClient;

    private RequestClientUtil(WechatPayConfig build){
        super();
        this.httpClient = CertificatesManagerUtil.builder(build).getHttpClient();

    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void get(URI uri, Consumer<CloseableHttpResponse> responseCallback) {
        logger.info("=========初始化参数==============");
        logger.info(uri.toString());
        HttpGet httpGet = new HttpGet(uri);
        try {
            doSend(httpGet, null, responseCallback);
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
    }

    public static RequestClientUtil build(WechatPayConfig build) {
        return new RequestClientUtil(build);
    }

    protected void doSend(HttpUriRequest request, HttpEntity entity,
                          Consumer<CloseableHttpResponse> responseCallback) throws IOException {
        if (entity != null && request instanceof HttpPost) {
            ((HttpPost) request).setEntity(entity);
        }
        request.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            responseCallback.accept(response);
        }
    }
}
