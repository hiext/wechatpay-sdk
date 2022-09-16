package com.zhengzhuanglaile.wechatpay.isv.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.result.WechatPayBaseResult;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 请求分账返回参数
 * @author dengying.zhang 2022年9月16日 下午2:48:54
 * @since 1.0.0
 */
public class WechatPayProfitSharingCreateOrderResult extends WechatPayBaseResult {

    /**
     * 子商户号  string[1,32]    是   body 服务商户号，由微信支付生成并下发
    示例值：1230000109
     */
    @SerializedName("sub_mchid")
    private String                subMchid;

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
     * 分账单状态（每个接收方的分账结果请查看receivers中的result字段），枚举值：
     * 1、PROCESSING：处理中
     * 2、FINISHED：分账完成
     */
    @SerializedName("state")
    private ProfitSharingState    state;

    /**
     * 分账接收方列表，可以设置出资商户作为分账接受方，最多可有50个分账接收方
     */
    @SerializedName("receivers")
    private List<ReceiversResult> receivers;

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
    }

    /**
     * 微信支付订单号
     * @return
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * 微信支付订单号
     * @param transactionId
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * 商户系统内部的分账单号，在商户系统内部唯一，同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@
     * @return
     */
    public String getOutOrderNo() {
        return outOrderNo;
    }

    /**
     * 商户系统内部的分账单号，在商户系统内部唯一，同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@
     * @param outOrderNo
     */
    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    /**
     * 微信分账单号，微信支付系统返回的唯一标识
     * @return
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 微信分账单号，微信支付系统返回的唯一标识
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 分账单状态（每个接收方的分账结果请查看receivers中的result字段），枚举值：
     * 1、PROCESSING：处理中
     * 2、FINISHED：分账完成
     * @return
     */
    public ProfitSharingState getState() {
        return state;
    }

    /**
     * 
     * 分账单状态（每个接收方的分账结果请查看receivers中的result字段），枚举值：
     * 1、PROCESSING：处理中
     * 2、FINISHED：分账完成
     * @param state
    */
    public void setState(ProfitSharingState state) {
        this.state = state;
    }

    /**
     * 分账接收方列表，可以设置出资商户作为分账接受方，最多可有50个分账接收方
     * @return
     */
    public List<ReceiversResult> getReceivers() {
        return receivers;
    }

    /**
     * 分账接收方列表，可以设置出资商户作为分账接受方，最多可有50个分账接收方
     * @param
     */
    public void setReceivers(List<ReceiversResult> receivers) {
        this.receivers = receivers;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
