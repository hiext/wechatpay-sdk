package com.zhengzhuanglaile.wechatpay.notification;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import com.wechat.pay.contrib.apache.httpclient.exception.ParseException;
import com.wechat.pay.contrib.apache.httpclient.exception.ValidationException;
import com.wechat.pay.contrib.apache.httpclient.notification.Notification;
import com.wechat.pay.contrib.apache.httpclient.notification.Notification.Resource;
import com.wechat.pay.contrib.apache.httpclient.notification.NotificationRequest;
import com.wechat.pay.contrib.apache.httpclient.notification.Request;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.zhengzhuanglaile.wechatpay.model.WechatPayConfig;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 微信通知信息 转换
 * 
 * @author dengying.zhang 2022年8月19日 下午8:33:16
 * @since 1.0.0
 */
public class WechatPayNotificationParser {

    private static final Logger logger = LoggerFactory.getLogger(WechatPayNotificationParser.class);
    private final Verifier      verifier;

    private final byte[]        apiV3Key;

    public WechatPayNotificationParser(WechatPayConfig config) throws ValidationException, HttpCodeException,
                                                               IOException, GeneralSecurityException, NotFoundException{
        super();
        String merchantId = config.getMerchantId();
        String merchantSerialNumber = config.getMerchantSerialNumber();
        String apiV3Key = config.getApiV3Key();

        String privaterKey = config.getPrivateKey();
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
        PrivateKey merchantPrivateKey = PemUtil.loadPrivateKey(config.getPrivateKey());
        // 获取证书管理器实例
        CertificatesManager certificatesManager = CertificatesManager.getInstance();

        // 向证书管理器增加需要自动更新平台证书的商户信息
        certificatesManager.putMerchant(merchantId,
                                        new WechatPay2Credentials(merchantId,
                                                                  new PrivateKeySigner(merchantSerialNumber,
                                                                                       merchantPrivateKey)),
                                        apiV3Key.getBytes(StandardCharsets.UTF_8));
        // 从证书管理器中获取verifier
        this.verifier = certificatesManager.getVerifier(merchantId);
        this.apiV3Key = apiV3Key.getBytes(StandardCharsets.UTF_8);
    }

    public static WechatPayNotificationParser builder(WechatPayConfig wechatPayConfig) {

        try {
            return new WechatPayNotificationParser(wechatPayConfig);
        } catch (ValidationException | HttpCodeException | NotFoundException | IOException
                | GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 微信支付回调消息 验签和解析请求体
     * 
     * @param notificationRequest 请求
     * @return 解密后的消息 Notification.getDecryptData();
     * @throws ParseException
     */
    public Notification parser(NotificationRequest notificationRequest) throws IOException, ParseException {
        Notification notification = null;
        try {
            validate(notificationRequest);
            notification = GsonUtil.getGson().fromJson(notificationRequest.getBody(), Notification.class);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        validateNotification(notification);
        setDecryptData(notification);
        return notification;
    }

    private void validate(Request request) throws ValidationException {
        if (request == null) {
            throw new ValidationException("request为空");
        }
        String serialNumber = request.getSerialNumber();
        byte[] message = request.getMessage();
        String signature = request.getSignature();
        if (serialNumber == null || serialNumber.isEmpty()) {
            throw new ValidationException("serialNumber为空");
        }
        if (message == null || message.length == 0) {
            throw new ValidationException("message为空");
        }
        if (signature == null || signature.isEmpty()) {
            throw new ValidationException("signature为空");
        }
        if (!verifier.verify(serialNumber, message, signature)) {
            String errorMessage = String.format("验签失败：serial=[%s] message=[%s] sign=[%s]", serialNumber,
                                                new String(message), signature);
            throw new ValidationException(errorMessage);
        }
    }

    /**
     * 校验解析后的通知结果
     *
     * @param notification 通知结果
     * @throws ParseException 参数不合法
     */
    private void validateNotification(Notification notification) throws ParseException {
        if (notification == null) {
            throw new ParseException("body解析为空");
        }
        String id = notification.getId();
        if (id == null || id.isEmpty()) {
            throw new ParseException("body不合法，id为空。body：" + notification.toString());
        }
        String createTime = notification.getCreateTime();
        if (createTime == null || createTime.isEmpty()) {
            throw new ParseException("body不合法，createTime为空。body：" + notification.toString());
        }
        String eventType = notification.getEventType();
        if (eventType == null || eventType.isEmpty()) {
            throw new ParseException("body不合法，eventType为空。body：" + notification.toString());
        }
        String summary = notification.getSummary();
        if (summary == null || summary.isEmpty()) {
            throw new ParseException("body不合法，summary为空。body：" + notification.toString());
        }
        String resourceType = notification.getResourceType();
        if (resourceType == null || resourceType.isEmpty()) {
            throw new ParseException("body不合法，resourceType为空。body：" + notification.toString());
        }
        Resource resource = notification.getResource();
        if (resource == null) {
            throw new ParseException("body不合法，resource为空。notification：" + notification.toString());
        }
        String algorithm = resource.getAlgorithm();
        if (algorithm == null || algorithm.isEmpty()) {
            throw new ParseException("body不合法，algorithm为空。body：" + notification.toString());
        }
        String originalType = resource.getOriginalType();
        if (originalType == null || originalType.isEmpty()) {
            throw new ParseException("body不合法，original_type为空。body：" + notification.toString());
        }
        String ciphertext = resource.getCiphertext();
        if (ciphertext == null || ciphertext.isEmpty()) {
            throw new ParseException("body不合法，ciphertext为空。body：" + notification.toString());
        }
        String nonce = resource.getNonce();
        if (nonce == null || nonce.isEmpty()) {
            throw new ParseException("body不合法，nonce为空。body：" + notification.toString());
        }
    }

    /**
     * 获取解密数据
     *
     * @param notification 解析body得到的通知结果
     * @throws ParseException 解析body失败
     */
    private void setDecryptData(Notification notification) throws ParseException {

        Resource resource = notification.getResource();
        String getAssociateddData = "";
        if (resource.getAssociatedData() != null) {
            getAssociateddData = resource.getAssociatedData();
        }
        byte[] associatedData = getAssociateddData.getBytes(StandardCharsets.UTF_8);
        byte[] nonce = resource.getNonce().getBytes(StandardCharsets.UTF_8);
        String ciphertext = resource.getCiphertext();
        AesUtil aesUtil = new AesUtil(apiV3Key);
        String decryptData;
        try {
            decryptData = aesUtil.decryptToString(associatedData, nonce, ciphertext);
        } catch (GeneralSecurityException e) {
            throw new ParseException("AES解密失败，resource：" + resource.toString(), e);
        }
        notification.setDecryptData(decryptData);
    }

}
