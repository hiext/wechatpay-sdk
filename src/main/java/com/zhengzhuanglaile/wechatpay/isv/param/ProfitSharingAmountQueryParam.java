package com.zhengzhuanglaile.wechatpay.isv.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * @author dengying.zhang
 */
public class ProfitSharingAmountQueryParam {

    @NotNull
    @Length(min = 1, max = 32)
    @SerializedName("transaction_id")
    private String transactionId;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }
}
