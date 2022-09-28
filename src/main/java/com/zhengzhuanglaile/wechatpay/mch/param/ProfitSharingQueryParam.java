package com.zhengzhuanglaile.wechatpay.mch.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class ProfitSharingQueryParam {

    /**
     * 微信支付订单号
     */
    @NotNull
    @Length(min = 1, max = 32)
    @SerializedName("transaction_id")
    private String transactionId;

    /**
     * 系统内部的分账单号，在服务商系统内部唯一，同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@
     */
    @NotNull
    @Length(min = 1, max = 32)
    @SerializedName("out_order_no")
    @Expose(serialize = false)
    private String outOrderNo;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }
}
