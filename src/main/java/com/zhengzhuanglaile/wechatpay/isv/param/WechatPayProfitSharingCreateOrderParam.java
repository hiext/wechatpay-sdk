package com.zhengzhuanglaile.wechatpay.isv.param;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 请求分账参数
 * @author dengying.zhang 2022年9月16日 下午1:52:46
 * @since 1.0.0
 */
public class WechatPayProfitSharingCreateOrderParam {

    /**
     * 子商户号   string[1,32]    是   body 子商户号
    示例值：1230000109
     */
    @NotNull(message = "sub_mchid 子商户号不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("sub_mchid")
    private String          subMchid;

    /**
     * 应用ID-微信分配的公众账号ID
     */
    @NotNull(message = "微信分配的公众账号ID不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("appid")
    private String          appid;

    /**
     * 子商户应用ID  sub_appid   string[1,32]    否   子商户的公众账号ID，分账接收方类型包含PERSONAL_SUB_OPENID时必填
    示例值：wxd678efh567hg6999
     */
    @Length(min = 1, max = 32)
    @SerializedName("sub_appid")
    private String          subAppid;

    /**
     * 微信支付订单号
     */
    @NotNull
    @Length(min = 1, max = 32)
    @SerializedName("transaction_id")
    private String          transactionId;

    /**
     * 服务商系统内部的分账单号，在服务商系统内部唯一，同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@
     */
    @NotNull
    @Length(min = 1, max = 32)
    @SerializedName("out_order_no")
    private String          outOrderNo;

    /**
     * 是否解冻剩余未分资金
     * 1、如果为true，该笔订单剩余未分账的金额会解冻回分账方商户;
     * 2、如果为false，该笔订单剩余未分账的金额不会解冻回分账方商户，可以对该笔订单再次进行分账;
     */
    @NotNull
    @SerializedName("unfreeze_unsplit")
    private Boolean         unfreezeUnsplit;

    /**
     * 分账接收方列表，可以设置出资商户作为分账接受方，最多可有50个分账接收方
     */
    @SerializedName("receivers")
    private List<Receivers> receivers;

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

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

    public Boolean getUnfreezeUnsplit() {
        return unfreezeUnsplit;
    }

    public void setUnfreezeUnsplit(Boolean unfreezeUnsplit) {
        this.unfreezeUnsplit = unfreezeUnsplit;
    }

    public List<Receivers> getReceivers() {
        return receivers;
    }

    public void setReceivers(List<Receivers> receivers) {
        this.receivers = receivers;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
