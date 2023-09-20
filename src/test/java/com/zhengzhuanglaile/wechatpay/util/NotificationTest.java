package com.zhengzhuanglaile.wechatpay.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.Base64;

import org.junit.Before;
import org.junit.Test;

import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.ParseException;
import com.wechat.pay.contrib.apache.httpclient.exception.ValidationException;
import com.wechat.pay.contrib.apache.httpclient.notification.NotificationRequest;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;

public class NotificationTest {

    private final String               WECHAT_PAY_SERIAL    = "634E8E36F04579BFE59872861F33F26F415E4511";
    private final String               WECHAT_PAY_SIGNATURE = "BLXoCxpUEJKE1h2dBOG5M9dSNihSNujTwM8KG+vVzUg6eFg6D7jm5hMrh56LRWJGgRPBwKOcyrZVQMYs5Bowmm0gcKpGo5gKiUkf9vjFbYvIvmaN+b7wWCxCP+t/zuE8vGaIYIRoud14ljlcA1HZax+8XDjdzo05djb5h8i2Gpgk1V0dYFyYffArqFMJSXWUWzLyOcSLNJghA8R/nyh1NSuGaBFRn+pV8pzmvSxz9+JsP9dAxvlFoM9eOun7TTYWXoIW3xJPTOqMiWC1HPZXrrwBrEnR1oAGku/dU+ZuNO6X96s/Xlrf+ROwT3fUdIIAjLUkioukRHrpMUiNZxEM2g==";
    private final String               WECHAT_PAY_TIMESTAMP = "1661476763";
    private final String               WECHAT_PAY_NONCE     = "cvp6PxD6VwSi5IYc4psIrzomP06dXtvX";
    private final String               body                 = "{\"summary\":\"支付成功\",\"event_type\":\"TRANSACTION.SUCCESS\",\"create_time\":\"2022-08-25T15:15:15+08:00\",\"resource\":{\"associated_data\":\"transaction\",\"ciphertext\":\"+qOdDUHgnBip2FNr91YgDQc2vRjZLJloX1sj+hxEiGuwqhPwUAVZoH3ygjfZiPEYoCjyMPQCPw3GdljSJFlYnXCMdJzC9BCja1wPZGZ6MMnjtYAG/8dXTKqrgL2ur2H0O2eM20137Sdy146WGLUVF/n48mJpUp4l3TOukcIV8K31HmLZlL5d7yW8zyDhHpaNvgstMLLn9xUcV8XJn2GB5VDtU88wnAlxvZiv5f7h8eYeaxFQVr6IEzyi6CuRMobjI0lrqlrGoEoVjrG7ZSy6M+tWvmsEj20TQX/5uWZWoWv2oby14EQ0fHcVLONU7dLG+2xbEeeTklnGzsnrW8eWL+cTepxisw/Y9uI0bcbpiUEgBnC7vibAEGA97ZMbciedPVgrr+W5c5cZ3mDp1+DM51WDAweOFRjpFUQl6DI6lnOZHI/ADAeS7js0AacIksHGB+J63b0UeQc3mFe0Mlevxfg90C9qUjFUDJzzXJUyl95fy0l6s331Vm+0I4qnp4/3X/mE2+DZiLe51D8ZjBW/4eai3m/WvFDh4A+OkDvkGspcNu0oN1ZunagSfvxw1wh+Xn60V4fM6zub2K4NPP37rcte1XNPbRgYGcrpLw==\",\"original_type\":\"transaction\",\"nonce\":\"oKY36Ys8hQGh\",\"algorithm\":\"AEAD_AES_256_GCM\"},\"resource_type\":\"encrypt-resource\",\"id\":\"768ddba8-4a66-5f62-9230-9138ef10c565\"}";

    private static final String        privateKey           = WechatPayRsaCrypUtil.readPrivateKeyToString("\\d:\\wechatpaycert\\hanlan_key.pem");
    // 商户私钥
    private static final String        merchantId           = "1604561034";

    // 商户号
    private static final String        merchantSerialNumber = "4A7015C3F03FD80E1FD0351C1DCA489791DFD39E";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         // 商户证书序列号
    private static final String        apiV3Key             = "Hanlan100azhengzhuanglaile724365";                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 // apiV3密钥
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  // //
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  // 请求头Wechatpay-Signature
    private Verifier                   verifier;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  // 验签器
    private static CertificatesManager certificatesManager;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       // 平台证书管理器

    @Before
    public void setup() throws Exception {
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(privateKey);
        // 获取证书管理器实例
        certificatesManager = CertificatesManager.getInstance();
        // 向证书管理器增加需要自动更新平台证书的商户信息
        certificatesManager.putMerchant(merchantId,
                                        new WechatPay2Credentials(merchantId,
                                                                  new PrivateKeySigner(merchantSerialNumber,
                                                                                       merchantPrivateKey)),
                                        apiV3Key.getBytes(StandardCharsets.UTF_8));
        // 从证书管理器中获取verifier
        verifier = certificatesManager.getVerifier(merchantId);
    }

    //@Test
    public void check() throws ValidationException, ParseException, NoSuchAlgorithmException, InvalidKeyException,
                        SignatureException {
        String b = body;
        System.out.println(b);
        NotificationRequest notificationRequest = new NotificationRequest.Builder().withBody(body).withNonce(WECHAT_PAY_NONCE).withSerialNumber(WECHAT_PAY_SERIAL).withSignature(WECHAT_PAY_SIGNATURE).withTimestamp(WECHAT_PAY_TIMESTAMP).build();
        X509Certificate x509Certificate = verifier.getValidCertificate();

        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initVerify(x509Certificate);
        sign.update(b.getBytes());
        Boolean se = sign.verify(Base64.getDecoder().decode(WECHAT_PAY_SIGNATURE));
        System.out.println(se);

        Boolean che = verifier.verify(WECHAT_PAY_SERIAL, b.getBytes(), WECHAT_PAY_SIGNATURE);
        System.out.println(che);
        // NotificationHandler handler = new NotificationHandler(verifier,
        // apiV3Key.getBytes(StandardCharsets.UTF_8));
        // // 验签和解析请求体
        // Notification notification = handler.parse(notificationRequest);
    }
}
