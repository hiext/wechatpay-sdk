package com.zhengzhuanglaile.wechatpay.base;

import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasePayOrderStatusQueryParam {

    @NotNull
    @SerializedName("out_trade_no")
    @Expose(serialize = false)
    private String outTradeNo;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

}
