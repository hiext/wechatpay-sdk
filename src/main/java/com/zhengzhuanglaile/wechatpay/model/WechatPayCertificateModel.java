package com.zhengzhuanglaile.wechatpay.model;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class WechatPayCertificateModel implements Serializable {

    /**
     * 
     */
    private static final long  serialVersionUID = 6694195921498895081L;
    @SerializedName("effective_time")
    private Date               effectiveTime;
    @SerializedName("encrypt_certificate")
    private EncryptCertificate encryptCertificate;
    @SerializedName("expire_time")
    private Date               expireTime;
    @SerializedName("serial_no")
    private String             serialNo;

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public EncryptCertificate getEncryptCertificate() {
        return encryptCertificate;
    }

    public void setEncryptCertificate(EncryptCertificate encryptCertificate) {
        this.encryptCertificate = encryptCertificate;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
