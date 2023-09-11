package com.zhengzhuanglaile.wechatpay.notification;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.refund.RefundAmount;
import com.zhengzhuanglaile.wechatpay.refund.RefundStatus;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class RefundNotificationModel {

    /**
     * 直连商户号
     */
    @SerializedName("mchid")
    private String       mchid;
    /**
     * 商户订单号
     */
    @SerializedName("out_trade_no")
    private String       outTradeNo;

    /**
     * 微信支付订单号
     */
    @SerializedName("transaction_id")
    private String       transactionId;

    /**
     * 商户退款单号
     */
    @SerializedName("out_refund_no")
    private String       outRefundNo;
    /**
     * 微信支付退款单号
     */
    @SerializedName("refund_id")
    private String       refundId;
    /**
     * 退款状态 说明：退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。 枚举值： - SUCCESS—退款成功 -
     * CLOSED—退款关闭 - PROCESSING—退款处理中 - ABNORMAL—退款异常
     */
    @SerializedName("refund_status")
    private RefundStatus refundStatus;
    /**
     * 退款成功时间
     * 说明：退款成功时间，退款状态status为SUCCESS（退款成功）时，返回该字段。遵循rfc3339标准格式，格式为YYYY-MM-DDTHH:mm:ss+TIMEZONE，YYYY-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC
     * 8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日13点29分35秒。
     */
    @SerializedName("success_time")
    private String       successTime;

    /**
     * 退款入账账户 说明：取当前退款单的退款入账方，有以下几种情况： 1）退回银行卡：{银行名称}{卡类型}{卡尾号} 2）退回支付用户零钱:支付用户零钱 3）退还商户:商户基本账户商户结算银行账户
     * 4）退回支付用户零钱通:支付用户零钱通
     */
    @SerializedName("user_received_account")
    private String       userReceivedAccount;

    /**
     * 金额信息 说明：金额详细信息
     */
    @SerializedName("amount")
    private RefundAmount amount;

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public RefundStatus getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(RefundStatus refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }

    public String getUserReceivedAccount() {
        return userReceivedAccount;
    }

    public void setUserReceivedAccount(String userReceivedAccount) {
        this.userReceivedAccount = userReceivedAccount;
    }

    public RefundAmount getAmount() {
        return amount;
    }

    public void setAmount(RefundAmount amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }
}
