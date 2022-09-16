package com.zhengzhuanglaile.wechatpay.isv.nativepay.param;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 退款金额参数
 * @author dengying.zhang 2022年8月22日 下午5:19:37
 * @since 1.0.0
 */
public class RefundAmountParam {

    @SerializedName("refund")
    private Integer             refund;

    @SerializedName("from")
    private List<FundsFromItem> from     = new ArrayList<FundsFromItem>();

    @SerializedName("total")
    private Integer             total;

    @SerializedName("currency")
    private String              currency = "CNY";

    public Integer getRefund() {
        return refund;
    }

    public void setRefund(Integer refund) {
        this.refund = refund;
    }

    public List<FundsFromItem> getFrom() {
        return from;
    }

    public void setFrom(List<FundsFromItem> from) {
        this.from = from;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
