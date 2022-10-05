package com.zhengzhuanglaile.wechatpay.mch.model;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.mch.param.GoodsDetail;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

import java.util.ArrayList;
import java.util.List;

public class RefundPromotion {

    /**
     * 券ID 说明：券或者立减优惠id
     */
    @SerializedName("promotion_id")
    private String            promotionId;
    /**
     * 优惠券面额 说明：用户享受优惠的金额（优惠券面额=微信出资金额+商家出资金额+其他出资方金额 ），单位为分
     */
    @SerializedName("amount")
    private Long              amount;
    /**
     * 优惠退款金额 说明：优惠退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为用户支付的现金，说明详见代金券或立减优惠，单位为分
     */
    @SerializedName("refund_amount")
    private Long              refundAmount;
    /**
     * 商品列表 说明：优惠商品发生退款时返回商品信息
     */
    @SerializedName("goods_detail")
    private List<GoodsDetail> goodsDetail = new ArrayList<GoodsDetail>();
    /**
     * 优惠范围 说明：枚举值： - GLOBAL- 全场代金券 - SINGLE- 单品优惠
     */
    @SerializedName("scope")
    private RefundScope       scope;
    /**
     * 优惠类型 说明：枚举值： - COUPON- 代金券，需要走结算资金的充值型代金券 - DISCOUNT- 优惠券，不走结算资金的免充值型优惠券
     */
    @SerializedName("type")
    private RefundType        type;

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Long refundAmount) {
        this.refundAmount = refundAmount;
    }

    public List<GoodsDetail> getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(List<GoodsDetail> goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public RefundScope getScope() {
        return scope;
    }

    public void setScope(RefundScope scope) {
        this.scope = scope;
    }

    public RefundType getType() {
        return type;
    }

    public void setType(RefundType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }
}
