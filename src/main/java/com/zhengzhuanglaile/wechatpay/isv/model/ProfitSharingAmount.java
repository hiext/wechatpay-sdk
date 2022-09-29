package com.zhengzhuanglaile.wechatpay.isv.model;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.result.WechatPayBaseResult;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 分账剩余待分金额
 * 
 * @author dengying.zhang
 */
public class ProfitSharingAmount extends WechatPayBaseResult {

    @SerializedName("transaction_id")
    private String  transactionId;
    @SerializedName("unsplit_amount")
    private Integer unsplitAmount;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getUnsplitAmount() {
        return unsplitAmount;
    }

    public void setUnsplitAmount(Integer unsplitAmount) {
        this.unsplitAmount = unsplitAmount;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }
}
