package com.zhengzhuanglaile.wechatpay;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.zhengzhuanglaile.wechatpay.util.PropertiesUtil;

public class WechatPayConstant {

    public static final String    WECHAT_PAY_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssxxx";

    public static final String    DATE_FORMART_FULL_BASE = "yyyyMMddHHmmssSSS";

    public static final String    QRCODE_PAY_ICON_WEIXIN = "weixin.png";

    public static final String    WECHAT_HTTPS           = "https://";
    public static final String    WECHAT_PAY_HOST        = "api.mch.weixin.qq.com";

    public static final String    DEFAULT_CHARTSET_NAME  = "utf-8";

    private static PropertiesUtil propertiesUtil         = new PropertiesUtil("application.properties");
    public static final String    SDK_NAME               = "wechatpay-SDK";
    public static final String    DEV_COMPANY            = "zhengzhuanglaile.com";
    /**
     * 商户号
     */
    private static final String   MERCHANT_ID            = "1230000109";
    /**
     * 商户的APIKey 32位字符串
     */
    private static final String   API_V3_KEY             = "niyaoshezhideshanghudeapiv3key12";
    /**
     * 商户的证书号
     */
    private static final String   MERCHANT_SERIAL_NUMBER = "1DDE55AD98ED71D6EDD4A4A16996DE7B47773A8C";

    /**
     * 
     */
    private static final String   MERCHANT_PRIVATE_KEY   = "./aa.pem";

    private static final boolean  DEV                    = true;

    /**
     * API V3密钥
     * 
     * @return
     */
    public static String getApiv3key() {
        if (propertiesUtil.getProperty("wechatpay.apiv3key") == null) {
            return API_V3_KEY;
        } else {
            return propertiesUtil.getProperty("wechatpay.apiv3key");
        }
    }

    /**
     * 商户号
     * 
     * @return
     */
    public static String getMerchantId() {
        if (propertiesUtil.getProperty("wechatpay.merchantId") == null) {
            return MERCHANT_ID;
        } else {
            return propertiesUtil.getProperty("wechatpay.merchantId");
        }
    }

    public static boolean getDev() {
        if (propertiesUtil.getProperty("wechatpay.dev") == null) {
            return DEV;
        } else {
            return Boolean.parseBoolean(propertiesUtil.getProperty("wechatpay.dev"));
        }
    }

    /**
     * 获取商户证书序列号
     * 
     * @return
     */
    public static String getMerchantSerialNumber() {
        if (propertiesUtil.getProperty("wechatpay.merchantSerialNumber") == null) {
            return MERCHANT_SERIAL_NUMBER;
        } else {
            return propertiesUtil.getProperty("wechatpay.merchantSerialNumber");
        }
    }

    /**
     * 获取商户的私钥
     * 
     * @return
     */
    public static String getMerchantPrivatekey() {
        String path = null;
        if (propertiesUtil.getProperty("wechatpay.merchantPrivateKeyPath") == null) {
            path = MERCHANT_PRIVATE_KEY;
        } else {
            path = propertiesUtil.getProperty("wechatpay.merchantPrivateKeyPath");
        }

        String privateKey = null;
        try (FileInputStream fileInputStream = new FileInputStream(new File(path))) {
            ByteArrayOutputStream os = new ByteArrayOutputStream(2048);
            byte[] buffer = new byte[1024];
            for (int length; (length = fileInputStream.read(buffer)) != -1;) {
                os.write(buffer, 0, length);
            }
            privateKey = os.toString("UTF-8");
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

}
