package com.zhengzhuanglaile.wechatpay.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class EncryptCertificate implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8793419053002463119L;

    private String            algorithm;
    @SerializedName("associated_data")
    private String            associatedData;
    private String            ciphertext;

    private String            nonce;

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAssociatedData() {
        return associatedData;
    }

    public void setAssociatedData(String associatedData) {
        this.associatedData = associatedData;
    }

    public String getCiphertext() {
        return ciphertext;
    }

    public void setCiphertext(String ciphertext) {
        this.ciphertext = ciphertext;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
