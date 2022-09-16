package com.zhengzhuanglaile.wechatpay.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * @author angy
 */
public class BaseCloseOrderParam {

    @SerializedName("out_trade_no")
    @Expose(serialize = false)
    private String outTradeNo;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
