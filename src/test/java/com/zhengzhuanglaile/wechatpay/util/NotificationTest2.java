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

public class NotificationTest2 {

    private final String               WECHAT_PAY_SERIAL    = "634E8E36F04579BFE59872861F33F26F415E4511";
    private final String               WECHAT_PAY_SIGNATURE = "bGIZMOv8Rr+25elG7JaCg2oQ/Kvg7JlqpTvxkUOoEcGoUlvWOFhDaIZ8R8A57Y+iUw+YOGpxfDiIIUO0V4UBy//jV5VWM1XelMoNDfp3Vfk7p9m6NiDrFRyzlAUj4yluMF6/MUYvPDNAhzN0qwyzmkZgzejBnvsD8Xlw+wDu5E7cBPUrKrQkF9HbXUSO+OTQF8W64y7l8+hhK75rMmYbRFhQO2dnrLgygjiyz0fgX+IVOyJS0VZdiL26hBoX5c1eJoiUGjY8Em4oHwsglh8rafnrxfH927+nhFExD2wx9HUMyuMoT4x4x/f6/nKIjCfyWQ4KTopgVQg8h9JgcWXm8A==";
    private final String               WECHAT_PAY_TIMESTAMP = "1661479083";
    private final String               WECHAT_PAY_NONCE     = "jtt0OYLXptMmJoi8VKfwvRkOhe8T76Dn";
    private final String               body                 = "{\"summary\":\"支付成功\",\"event_type\":\"TRANSACTION.SUCCESS\",\"create_time\":\"2022-08-26T09:57:46+08:00\",\"resource\":{\"associated_data\":\"transaction\",\"ciphertext\":\"1ITcd9YqzlGo3UEWEhj9hwdzAAuSUoNYu87aOh17acflk9IkrQ2I1abC/Uu4gdwXl5eF98b4uHWfznhp4bslRtS4Z4mQ6rP3Z3gpNwiMIEOtVdiVXJnmP4LSzTdH1225YE6GCvwIIfPhwR5vyff7gSCsO8LQDE25j+l6L16nIuVz18cqkAVP8vB+zkMoHYsjqiGxIQyQzxRKgfN3GEJi87X+6h0x9M6vDpjf8JaEnog/vWDqzRDQdrifxyynrZQm7IB/sj20uTqYq/EyI83sMUZOodTXH7rgAO+oLg+715IbsX5DA7PJhAdAudsr2kdQnaa0VTXO0I++3hPG/UbHCmhkVJ6UrOEeOCC+HJdRiOEbkqoWFoiUwAxBqsS8WfFYjLohq7Sv2n9qogo7W1VAZa6q1+jcL6D/GE44IG3U3QRlCBXMEcIT35iCGJLqCIjckD/vOsL9hU3wCMAMRlB6J+nqB+pbx9wP3UR0EsGamqGpCwGEy96FIfuVlf3iKwLErOhyCGeVSEZRP/MR1PMq0FGobPxOAA2TGVkIG3vwmMUzcetG8pSTv11m+jr9Wyhdtw5JOdKGT2fxpX8LJcboq+k6zJJKUInt3d01KQ==\",\"original_type\":\"transaction\",\"nonce\":\"Bt23HZB23vg8\",\"algorithm\":\"AEAD_AES_256_GCM\"},\"resource_type\":\"encrypt-resource\",\"id\":\"18fb5036-3291-535d-a750-3c3d24a94b66\"}";

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
        NotificationRequest notificationRequest = new NotificationRequest.Builder().withBody(b).withNonce(WECHAT_PAY_NONCE).withSerialNumber(WECHAT_PAY_SERIAL).withSignature(WECHAT_PAY_SIGNATURE).withTimestamp(WECHAT_PAY_TIMESTAMP).build();
        // NotificationHandler handler = new NotificationHandler(verifier, apiV3Key.getBytes(StandardCharsets.UTF_8));
        // // 验签和解析请求体
        // Notification notification = handler.parse(notificationRequest);
        X509Certificate x509Certificate = verifier.getValidCertificate();

        String src = WECHAT_PAY_TIMESTAMP + "\n" + WECHAT_PAY_NONCE + "\n" + b + "\n";

        Signature sign = Signature.getInstance("SHA256withRSA");
        sign.initVerify(x509Certificate);
        sign.update(src.getBytes());
        Boolean se = sign.verify(Base64.getDecoder().decode(WECHAT_PAY_SIGNATURE));
        System.out.println(se);

        Boolean che = verifier.verify(WECHAT_PAY_SERIAL, b.getBytes(), WECHAT_PAY_SIGNATURE);
        System.out.println(che);

    }
}
