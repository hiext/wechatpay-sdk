package com.zhengzhuanglaile.wechatpay.util;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import com.wechat.pay.contrib.apache.httpclient.exception.ValidationException;
import com.zhengzhuanglaile.wechatpay.WechatPayConstant;
import com.zhengzhuanglaile.wechatpay.model.WechatPayCertificateResult;
import com.zhengzhuanglaile.wechatpay.model.WechatPayConfig;

/**
 * 证书测试
 * 
 * @author dengying.zhang 2022年8月18日 上午11:33:29
 * @since 1.0.0
 */
public class CertificatesManagerUtilTest {

    private static Logger logger = LoggerFactory.getLogger(CertificatesManagerUtilTest.class);

    @Test
    public void test() throws NotFoundException, HttpCodeException, ValidationException, IOException,
                       GeneralSecurityException {
        WechatPayConfig wechatPayConfig = WechatPayConfig.builder().setApiV3Key(WechatPayConstant.getApiv3key()).setMerchantId(WechatPayConstant.getMerchantId()).setMerchantSerialNumber(WechatPayConstant.getMerchantSerialNumber()).setPrivateKey(WechatPayConstant.getMerchantPrivatekey()).build();
        CertificatesManagerUtil certificatesManagerUtil = CertificatesManagerUtil.builder(wechatPayConfig);
        WechatPayCertificateResult certificateResult = certificatesManagerUtil.getCertificate();
        logger.info(GsonUtil.getGson().toJson(certificateResult));
        logger.info(certificateResult.getData().get(0).getEncryptCertificate().getCiphertext());
    }

}
