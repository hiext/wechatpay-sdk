package com.zhengzhuanglaile.wechatpay.mch.param;

import javax.validation.constraints.NotNull;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.base.BaseCloseOrderParam;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class WechatPayCloseOrderParam extends BaseCloseOrderParam {

    @NotNull
    @SerializedName("mchid")
    private String mchid;

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
