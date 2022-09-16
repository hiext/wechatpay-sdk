package com.zhengzhuanglaile.wechatpay.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 微信支付优惠模型
 * @author dengying.zhang 2022年8月18日 下午3:13:11
 * @since 1.0.0
 */
public class WechatPayDiscounts {

    /**
     * 订单原价 cost_price  int 否   1、商户侧一张小票订单可能被分多次支付，订单原价用于记录整张小票的交易金额。
    2、当订单原价与支付金额不相等，则不享受优惠。
    3、该字段主要用于防止同一张小票分多次支付，以享受多次优惠的情况，正常支付订单不必上传此参数。
    示例值：608800
     */
    @SerializedName("cost_price")
    private int                                 costPrice;

    /**
     * 商品小票ID   invoice_id  string[1,32]    否   商家小票ID
    示例值：微信123
     */
    @SerializedName("invoice_id")
    private String                              invoiceId;

    /**
     * 单品列表 goods_detail    array   否   单品列表信息
    条目个数限制：【1，6000】
     */
    @SerializedName("goods_detail")
    private List<WechatPayDiscountsGoodsDetail> goodsDetail;

    public int getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(int costPrice) {
        this.costPrice = costPrice;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public List<WechatPayDiscountsGoodsDetail> getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(List<WechatPayDiscountsGoodsDetail> goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
