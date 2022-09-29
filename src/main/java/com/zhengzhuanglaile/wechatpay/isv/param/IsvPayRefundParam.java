package com.zhengzhuanglaile.wechatpay.isv.param;

import javax.validation.constraints.NotNull;

import com.zhengzhuanglaile.wechatpay.isv.nativepay.param.RefundAmountParam;
import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * @author dengying.zhang
 */
public class IsvPayRefundParam {

    /**
     * 子商户号 sub_mchid   string[1,32]    是   body 子商户的商户号，由微信支付生成并下发。
    示例值：1900000109
     */
    @NotNull(message = "sub_mchid 不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("sub_mchid")
    private String            subMchid;

    /**
     * 商户订单号    out_trade_no    string[6,32]    是   body 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。
    示例值：1217752501201407033233368018
     */
    @NotNull(message = "out_trade_no 不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("out_trade_no")
    private String            outTradeNo;
    /**
     * 商户订单号    out_trade_no    string[6,32]    是   body 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。
    示例值：1217752501201407033233368018
     */
    @NotNull(message = "out_refund_no 不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("out_refund_no")
    private String            outRefundNo;

    @SerializedName("reason")
    private String            reason;

    @SerializedName("notify_url")
    private String            notifyUrl;

    @SerializedName("funds_account")
    private String            fundsAccount;
    @SerializedName("amount")
    private RefundAmountParam amount;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getFundsAccount() {
        return fundsAccount;
    }

    public void setFundsAccount(String fundsAccount) {
        this.fundsAccount = fundsAccount;
    }

    public RefundAmountParam getAmount() {
        return amount;
    }

    public void setAmount(RefundAmountParam amount) {
        this.amount = amount;
    }

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
