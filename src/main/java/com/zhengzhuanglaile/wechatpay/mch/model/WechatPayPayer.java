package com.zhengzhuanglaile.wechatpay.mch.model;

import com.google.gson.annotations.SerializedName;

public class WechatPayPayer {

    @SerializedName("openid")
    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public WechatPayPayer(String openid){
        super();
        this.openid = openid;
    }

}
