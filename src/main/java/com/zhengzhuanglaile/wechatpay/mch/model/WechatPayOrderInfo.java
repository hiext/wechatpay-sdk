package com.zhengzhuanglaile.wechatpay.mch.model;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.base.BaseOrderInfo;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class WechatPayOrderInfo extends BaseOrderInfo {

    /**
     * 应用ID appid string[1,32] 是 body appid。示例值：wx8888888888888888
     */
    @SerializedName("appid")
    private String         appid;
    /**
     * 直连商户号 mchid string[1,32] 是 body 服务商户号，由微信支付生成并下发 示例值：1230000109
     */
    @SerializedName("mchid")
    private String         mchid;

    /** payer */
    @SerializedName("payer")
    private WechatPayPayer payer;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public WechatPayPayer getPayer() {
        return payer;
    }

    public void setPayer(WechatPayPayer payer) {
        this.payer = payer;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }
}
