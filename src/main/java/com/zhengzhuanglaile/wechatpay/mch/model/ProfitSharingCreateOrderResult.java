package com.zhengzhuanglaile.wechatpay.mch.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.isv.model.ProfitSharingState;
import com.zhengzhuanglaile.wechatpay.isv.model.ReceiversResult;
import com.zhengzhuanglaile.wechatpay.result.WechatPayBaseResult;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class ProfitSharingCreateOrderResult extends WechatPayBaseResult {

    /**
     * 微信支付订单号
     */
    @SerializedName("transaction_id")
    private String                transactionId;

    /**
     * 服务商系统内部的分账单号，在服务商系统内部唯一，同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@
     */
    @SerializedName("out_order_no")
    private String                outOrderNo;

    /**
     * 微信分账单号，微信支付系统返回的唯一标识
     */
    @SerializedName("order_id")
    private String                orderId;

    /**
     * 分账单状态（每个接收方的分账结果请查看receivers中的result字段），枚举值： 1、PROCESSING：处理中 2、FINISHED：分账完成
     */
    @SerializedName("state")
    private ProfitSharingState    state;

    /**
     * 分账接收方列表，可以设置出资商户作为分账接受方，最多可有50个分账接收方
     */
    @SerializedName("receivers")
    private List<ReceiversResult> receivers;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ProfitSharingState getState() {
        return state;
    }

    public void setState(ProfitSharingState state) {
        this.state = state;
    }

    public List<ReceiversResult> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<ReceiversResult> receivers) {
        this.receivers = receivers;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }
}
