package com.zhengzhuanglaile.wechatpay.base;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.model.Amount;
import com.zhengzhuanglaile.wechatpay.model.WechatPayDiscountsGoodsDetail;
import com.zhengzhuanglaile.wechatpay.model.WechatPaySceneInfo;
import com.zhengzhuanglaile.wechatpay.result.WechatPayBaseResult;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class BaseOrderInfo extends WechatPayBaseResult {

    /** outTradeNo */
    @SerializedName("out_trade_no")
    private String outTradeNo;
    /** transactionId */
    @SerializedName("transaction_id")
    private String transactionId;

    /** tradeState */
    public enum TradeStateEnum {
                                /**
                                 * 支付成功
                                 */
                                @SerializedName("SUCCESS")
                                SUCCESS,

                                /**
                                 * 转入退款
                                 */
                                @SerializedName("REFUND")
                                REFUND,
                                /**
                                 * 未支付
                                 */
                                @SerializedName("NOTPAY")
                                NOTPAY,
                                /**
                                 * 已关闭
                                 */
                                @SerializedName("CLOSED")
                                CLOSED,
                                /**
                                 * 已撤销（仅付款码支付会返回）
                                 */
                                @SerializedName("REVOKED")
                                REVOKED,
                                /**
                                 * 用户支付中（仅付款码支付会返回）
                                 */
                                @SerializedName("USERPAYING")
                                USERPAYING,
                                /**
                                 * 支付失败（仅付款码支付会返回）
                                 */
                                @SerializedName("PAYERROR")
                                PAYERROR,
                                /**
                                 * 
                                 */
                                @SerializedName("ACCEPT")
                                ACCEPT
    }

    @SerializedName("trade_state")
    private TradeStateEnum tradeState;

    /** tradeType */
    public enum TradeTypeEnum {
                               /**
                                * 公众号支付
                                */
                               @SerializedName("JSAPI")
                               JSAPI,
                               /**
                                * 扫码支付
                                */
                               @SerializedName("NATIVE")
                               NATIVE,
                               /**
                                * APP支付
                                */
                               @SerializedName("APP")
                               APP,
                               /**
                                * 付款码支付
                                */
                               @SerializedName("MICROPAY")
                               MICROPAY,
                               /**
                                * H5支付
                                */
                               @SerializedName("MWEB")
                               MWEB,
                               /**
                                * 刷脸支付
                                */
                               @SerializedName("FACEPAY")
                               FACEPAY
    }

    @SerializedName("trade_type")
    private TradeTypeEnum                       tradeType;

    /** tradeStateDesc */
    @SerializedName("trade_state_desc")
    private String                              tradeStateDesc;
    /** bankType */
    @SerializedName("bank_type")
    private String                              bankType;
    /** attach */
    @SerializedName("attach")
    private String                              attach;
    /** successTime */
    @SerializedName("success_time")
    private String                              successTime;

    /** amount */
    @SerializedName("amount")
    private Amount                              amount;

    @SerializedName("scene_info")
    private WechatPaySceneInfo                  sceneInfo;

    /** promotionDetail */
    @SerializedName("promotion_detail")
    private List<WechatPayDiscountsGoodsDetail> promotionDetail;

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

    public TradeStateEnum getTradeState() {
        return tradeState;
    }

    public void setTradeState(TradeStateEnum tradeState) {
        this.tradeState = tradeState;
    }

    public TradeTypeEnum getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeTypeEnum tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeStateDesc() {
        return tradeStateDesc;
    }

    public void setTradeStateDesc(String tradeStateDesc) {
        this.tradeStateDesc = tradeStateDesc;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public WechatPaySceneInfo getSceneInfo() {
        return sceneInfo;
    }

    public void setSceneInfo(WechatPaySceneInfo sceneInfo) {
        this.sceneInfo = sceneInfo;
    }

    public List<WechatPayDiscountsGoodsDetail> getPromotionDetail() {
        return promotionDetail;
    }

    public void setPromotionDetail(List<WechatPayDiscountsGoodsDetail> promotionDetail) {
        this.promotionDetail = promotionDetail;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
