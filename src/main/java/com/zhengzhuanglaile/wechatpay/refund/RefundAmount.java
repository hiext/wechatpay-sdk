package com.zhengzhuanglaile.wechatpay.refund;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class RefundAmount {

    /** 订单金额 说明：订单总金额，单位为分 */
    @SerializedName("total")
    private Long                total;
    /** 退款金额 说明：退款标价金额，单位为分，可以做部分退款 */
    @SerializedName("refund")
    private Long                refund;
    /** 退款出资账户及金额 说明：退款出资的账户类型及金额信息 */
    @SerializedName("from")
    private List<FundsFromItem> from;
    /** 用户支付金额 说明：现金支付金额，单位为分，只能为整数 */
    @SerializedName("payer_total")
    private Long                payerTotal;
    /** 用户退款金额 说明：退款给用户的金额，不包含所有优惠券金额 */
    @SerializedName("payer_refund")
    private Long                payerRefund;
    /** 应结退款金额 说明：去掉非充值代金券退款金额后的退款金额，单位为分，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额 */
    @SerializedName("settlement_refund")
    private Long                settlementRefund;
    /** 应结订单金额 说明：应结订单金额=订单金额-免充值代金券金额，应结订单金额<=订单金额，单位为分 */
    @SerializedName("settlement_total")
    private Long                settlementTotal;
    /** 优惠退款金额 说明：优惠退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠，单位为分 */
    @SerializedName("discount_refund")
    private Long                discountRefund;
    /** 退款币种 说明：符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY。 */
    @SerializedName("currency")
    private String              currency;
    /** 手续费退款金额 说明：手续费退款金额，单位为分 */
    @SerializedName("refund_fee")
    private Long                refundFee;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getRefund() {
        return refund;
    }

    public void setRefund(Long refund) {
        this.refund = refund;
    }

    public List<FundsFromItem> getFrom() {
        return from;
    }

    public void setFrom(List<FundsFromItem> from) {
        this.from = from;
    }

    public Long getPayerTotal() {
        return payerTotal;
    }

    public void setPayerTotal(Long payerTotal) {
        this.payerTotal = payerTotal;
    }

    public Long getPayerRefund() {
        return payerRefund;
    }

    public void setPayerRefund(Long payerRefund) {
        this.payerRefund = payerRefund;
    }

    public Long getSettlementRefund() {
        return settlementRefund;
    }

    public void setSettlementRefund(Long settlementRefund) {
        this.settlementRefund = settlementRefund;
    }

    public Long getSettlementTotal() {
        return settlementTotal;
    }

    public void setSettlementTotal(Long settlementTotal) {
        this.settlementTotal = settlementTotal;
    }

    public Long getDiscountRefund() {
        return discountRefund;
    }

    public void setDiscountRefund(Long discountRefund) {
        this.discountRefund = discountRefund;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Long refundFee) {
        this.refundFee = refundFee;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }
}
