package com.zhengzhuanglaile.wechatpay.base;

import javax.validation.constraints.NotNull;

import com.zhengzhuanglaile.wechatpay.model.Amount;
import com.zhengzhuanglaile.wechatpay.model.WechatPayDiscounts;
import com.zhengzhuanglaile.wechatpay.model.WechatPaySettleInfo;
import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 微信支付下单基础信息-服务商版接口
 * @author dengying.zhang 2022年8月18日 下午3:18:29
 * @since 1.0.0
 */
public class BaseCreateOrderParam {

    /**
     * 商品描述 description string[1,127]   是   body 商品描述
    示例值：Image形象店-深圳腾大-QQ公仔
     */
    @NotNull(message = "description 不能为空")
    @Length(min = 1, max = 32)
    private String              description;

    /**
     * 商户订单号    out_trade_no    string[6,32]    是   body 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。
    示例值：1217752501201407033233368018
     */
    @NotNull(message = "out_trade_no 不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("out_trade_no")
    private String              outTradeNo;

    /**
     * 交易结束时间   time_expire string[1,64]    否   body 订单失效时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日 13点29分35秒。
    示例值：2018-06-08T10:34:56+08:00
     */
    @SerializedName("time_expire")
    private String              timeExpire;

    /**
     * 附加数据 attach  string[1,128]   否   body 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用，实际情况下只有支付完成状态才会返回该字段。
    示例值：自定义数据  
     */
    @Length(max = 32)
    private String              attach;

    /**
     * 通知地址 notify_url  string[1,256]   是   body 通知URL必须为直接可访问的URL，不允许携带查询串，要求必须为https地址。
    格式：URL
    示例值：https://www.weixin.qq.com/wxpay/pay.php
     */
    @NotNull(message = "notify_url 通知URL不能为空")
    @Length(min = 1, max = 256)
    @SerializedName("notify_url")
    private String              notifyUrl;

    /**
     * 订单优惠标记   goods_tag   string[1,32]    否   body 订单优惠标记
    示例值：WXG
     */
    @Length(max = 32)
    @SerializedName("goods_tag")
    private String              goodsTag;

    /**
     * 结算信息 settle_info object  否   body 结算信息
     */
    @SerializedName("settle_info")
    private WechatPaySettleInfo settleInfo;
    /**
     * 订单金额
     */
    @NotNull
    private Amount amount;

    /**
     * 优惠功能 detail  object  否   body 优惠功能
     */
    private WechatPayDiscounts detail;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(String timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public WechatPaySettleInfo getSettleInfo() {
        return settleInfo;
    }

    public void setSettleInfo(WechatPaySettleInfo settleInfo) {
        this.settleInfo = settleInfo;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public WechatPayDiscounts getDetail() {
        return detail;
    }

    public void setDetail(WechatPayDiscounts detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
