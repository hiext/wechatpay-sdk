package com.zhengzhuanglaile.wechatpay.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

import com.zhengzhuanglaile.wechatpay.model.WechatPayCertificateResult;
import com.zhengzhuanglaile.wechatpay.model.WechatPayConfig;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import com.wechat.pay.contrib.apache.httpclient.exception.ValidationException;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.zhengzhuanglaile.wechatpay.WechatPayConstant;

/**
 * 证书管理工具类
 * 
 * @author dengying.zhang 2022年8月18日 上午10:22:11
 * @since 1.0.0
 */
public class CertificatesManagerUtil {

    private static Logger       logger = LoggerFactory.getLogger(CertificatesManagerUtil.class);
    private CloseableHttpClient httpClient;
    private CertificatesManager certificatesManager;
    private Verifier            verifier;

    private static final String URI    = WechatPayConstant.WECHAT_HTTPS + WechatPayConstant.WECHAT_PAY_HOST
                                         + "/v3/certificates";

    private CertificatesManagerUtil(WechatPayConfig build) throws NotFoundException, HttpCodeException, IOException,
                                                           GeneralSecurityException, ValidationException{

        super();
        String merchantId = build.getMerchantId();
        String merchantSerialNumber = build.getMerchantSerialNumber();
        String apiV3Key = build.getApiV3Key();
        String privaterKey = build.getPrivateKey();
        if (null == privaterKey || "".equals(privaterKey)) {
            logger.error("privateKey can not be null");
            throw new ValidationException("privateKey can not be null");

        }
        if (null == merchantId || "".equals(merchantId)) {
            logger.error("merchantId can not be null");
            throw new ValidationException("merchantId can not be null");
        }
        if (null == merchantSerialNumber || "".equals(merchantSerialNumber)) {
            logger.error("merchantSerialNumber can not be null");
            throw new ValidationException("merchantSerialNumber can not be null");
        }
        if (null == apiV3Key || "".equals(apiV3Key)) {
            logger.error("apiV3Key can not be null");
            throw new ValidationException("apiV3Key can not be null");
        }
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(build.getPrivateKey());
        // 获取证书管理器实例
        this.certificatesManager = CertificatesManager.getInstance();
        // 向证书管理器增加需要自动更新平台证书的商户信息
        this.certificatesManager.putMerchant(merchantId,
                                             new WechatPay2Credentials(merchantId,
                                                                       new PrivateKeySigner(merchantSerialNumber,
                                                                                            merchantPrivateKey)),
                                             apiV3Key.getBytes(StandardCharsets.UTF_8));
        // 从证书管理器中获取verifier
        this.verifier = certificatesManager.getVerifier(merchantId);
        // 构造httpclient
        this.httpClient = WechatPayHttpClientBuilder.create().withMerchant(merchantId, merchantSerialNumber,
                                                                           merchantPrivateKey).withValidator(new WechatPay2Validator(verifier)).build();
    }

    public Verifier getVerifier() {
        return this.verifier;
    }

    public CloseableHttpClient getHttpClient() {
        return this.httpClient;
    }

    /**
     * 获取新的证书
     * 
     * @throws IOException
     * @throws ClientProtocolException
     */
    public WechatPayCertificateResult getCertificate() {
        HttpGet httpGet = new HttpGet(CertificatesManagerUtil.URI);
        httpGet.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
        try {
            CloseableHttpResponse response = this.httpClient.execute(httpGet);
            String res = EntityUtils.toString(response.getEntity());
            return GsonUtil.getGson().fromJson(res, WechatPayCertificateResult.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.close();
        }
    }

    public void close() {
        try {
            this.httpClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static CertificatesManagerUtil builder(WechatPayConfig config) {
        try {
            return new CertificatesManagerUtil(config);
        } catch (NotFoundException | HttpCodeException | ValidationException | IOException
                | GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}
