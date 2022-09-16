package com.zhengzhuanglaile.wechatpay.notification;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class WechatPayNotification {

    @SerializedName("id")
    private String   id;
    @SerializedName("create_time")
    private String   createTime;
    @SerializedName("event_type")
    private String   eventType;
    @SerializedName("resource_type")
    private String   resourceType;
    @SerializedName("summary")
    private String   summary;
    @SerializedName("resource")
    private Resource resource;
    private String   decryptData;

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

    public String getId() {
        return id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getEventType() {
        return eventType;
    }

    public String getDecryptData() {
        return decryptData;
    }

    public String getSummary() {
        return summary;
    }

    public String getResourceType() {
        return resourceType;
    }

    public Resource getResource() {
        return resource;
    }

    public void setDecryptData(String decryptData) {
        this.decryptData = decryptData;
    }

    public class Resource {

        @SerializedName("algorithm")
        private String algorithm;
        @SerializedName("ciphertext")
        private String ciphertext;
        @SerializedName("associated_data")
        private String associatedData;
        @SerializedName("nonce")
        private String nonce;
        @SerializedName("original_type")
        private String originalType;

        public String getAlgorithm() {
            return algorithm;
        }

        public String getCiphertext() {
            return ciphertext;
        }

        public String getAssociatedData() {
            return associatedData;
        }

        public String getNonce() {
            return nonce;
        }

        public String getOriginalType() {
            return originalType;
        }

        @Override
        public String toString() {
            return GsonUtil.getGson().toJson(this);
        }
    }

}
