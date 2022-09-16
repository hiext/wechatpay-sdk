package com.zhengzhuanglaile.wechatpay.model;

import java.io.Serializable;

/**
 * 微信支付配置项-服务商相关的信息
 * 
 * @author dengying.zhang 2022年8月18日 下午1:30:22
 * @since 1.0.0
 */
public class WechatPayConfig implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 915517249341378547L;

    private String            privateKey;

    private String            merchantId;                            // 商户号
    private String            merchantSerialNumber;                  // 商户证书序列号
    private String            apiV3Key;                              // API V3密钥

    private WechatPayConfig(Builder builder){
        super();
        this.privateKey = builder.privateKey;
        this.merchantId = builder.merchantId;
        this.merchantSerialNumber = builder.merchantSerialNumber;
        this.apiV3Key = builder.apiV3Key;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public WechatPayConfig setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
        return this;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public WechatPayConfig setMerchantId(String merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    public String getMerchantSerialNumber() {
        return merchantSerialNumber;
    }

    public WechatPayConfig setMerchantSerialNumber(String merchantSerialNumber) {
        this.merchantSerialNumber = merchantSerialNumber;
        return this;
    }

    public String getApiV3Key() {
        return apiV3Key;
    }

    public WechatPayConfig setApiV3Key(String apiV3Key) {
        this.apiV3Key = apiV3Key;
        return this;
    }

    public static WechatPayConfig.Builder builder() {
        return new Builder();
    }

    public static WechatPayConfig.Builder builder(String privateKey, String merchantId, String merchantSerialNumber,
                                                  String apiV3Key) {
        return new Builder(privateKey, merchantId, merchantSerialNumber, apiV3Key);
    }

    public static class Builder {

        private String privateKey;

        private String merchantId;           // 商户号
        private String merchantSerialNumber; // 商户证书序列号
        private String apiV3Key;             // API V3密钥

        public Builder setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
            return this;
        }

        public Builder setMerchantId(String merchantId) {
            this.merchantId = merchantId;
            return this;
        }

        public Builder setMerchantSerialNumber(String merchantSerialNumber) {
            this.merchantSerialNumber = merchantSerialNumber;
            return this;
        }

        public Builder setApiV3Key(String apiV3Key) {
            this.apiV3Key = apiV3Key;
            return this;
        }

        public Builder(){
            super();
        }

        public Builder(String privateKey, String merchantId, String merchantSerialNumber, String apiV3Key){
            super();
            this.privateKey = privateKey;
            this.merchantId = merchantId;
            this.merchantSerialNumber = merchantSerialNumber;
            this.apiV3Key = apiV3Key;
        }

        public WechatPayConfig build() {
            return new WechatPayConfig(this);
        }
    }

    @Override
    public String toString() {
        return "WechatPayConfig [privateKey=" + privateKey + ", merchantId=" + merchantId + ", merchantSerialNumber="
               + merchantSerialNumber + ", apiV3Key=" + apiV3Key + "]";
    }
}
