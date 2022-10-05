package com.zhengzhuanglaile.wechatpay.mch.param;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.isv.nativepay.param.RefundAmountParam;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author dengying.zhang
 */
public class PayRefundParam {

    @SerializedName("transaction_id")
    private String            transactionId;
    /**
     * 商户订单号 out_trade_no string[6,32] 是 body 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。 示例值：1217752501201407033233368018
     */
    @NotNull(message = "out_trade_no 不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("out_trade_no")
    private String            outTradeNo;
    /**
     * 商户订单号 out_trade_no string[6,32] 是 body 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。 示例值：1217752501201407033233368018
     */
    @NotNull(message = "out_refund_no 不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("out_refund_no")
    private String            outRefundNo;
    /**
     * 退款原因 说明：若商户传入，会在下发给用户的退款消息中体现退款原因
     */
    @SerializedName("reason")
    private String            reason;
    /**
     * 退款结果回调url 说明：异步接收微信支付退款结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效，优先回调当前传的这个地址。
     */
    @SerializedName("notify_url")
    private String            notifyUrl;

    /**
     * 退款商品 说明：指定商品退款需要传此参数，其他场景无需传递
     */
    @SerializedName("goods_detail")
    private List<GoodsDetail> goodsDetail;

    /**
     * 金额信息 说明：订单金额信息
     */
    @SerializedName("amount")
    private RefundAmountParam amount;
    /**
     * 退款资金来源 说明：若传递此参数则使用对应的资金账户退款，否则默认使用未结算资金退款（仅对老资金流商户适用） 枚举值： - AVAILABLE：可用余额账户
     */
    @SerializedName("funds_account")
    private String            fundsAccount;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public List<GoodsDetail> getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(List<GoodsDetail> goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

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
}
