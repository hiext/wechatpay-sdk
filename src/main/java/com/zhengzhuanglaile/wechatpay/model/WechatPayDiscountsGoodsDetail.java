package com.zhengzhuanglaile.wechatpay.model;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 优惠商品
 * @author dengying.zhang 2022年8月18日 下午3:12:43
 * @since 1.0.0
 */
public class WechatPayDiscountsGoodsDetail {

    /**
     * 商户侧商品编码  merchant_goods_id   string[1,32]    是   由半角的大小写字母、数字、中划线、下划线中的一种或几种组成。
    示例值：1246464644
     */
    @SerializedName("merchant_goods_id")
    private String merchantGoodsId;

    /**
     * 微信支付商品编码 wechatpay_goods_id  string[1,32]    否   微信支付定义的统一商品编号（没有可不传）
    示例值：1001
     */
    @SerializedName("wechatpay_goods_id")
    private String wechatpayGoodsId;

    /**
     * 商品名称 goods_name  string[1,256]   否   商品的实际名称
    示例值：iPhoneX 256G
     */
    @SerializedName("goods_name")
    private String goodsName;

    /**
     * 商品数量 quantity    int 是   用户购买的数量
    示例值：1
     */
    private int    quantity;

    /**
     * 商品单价 unit_price  int 是   商品单价，单位为分
    示例值：828800
     */
    @SerializedName("unit_price")
    private int    unitPrice;

    public String getMerchantGoodsId() {
        return merchantGoodsId;
    }

    public void setMerchantGoodsId(String merchantGoodsId) {
        this.merchantGoodsId = merchantGoodsId;
    }

    public String getWechatpayGoodsId() {
        return wechatpayGoodsId;
    }

    public void setWechatpayGoodsId(String wechatpayGoodsId) {
        this.wechatpayGoodsId = wechatpayGoodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
