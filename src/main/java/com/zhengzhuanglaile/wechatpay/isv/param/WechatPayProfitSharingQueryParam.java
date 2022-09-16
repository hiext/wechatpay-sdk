package com.zhengzhuanglaile.wechatpay.isv.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 查询分账结果参数
 * 发起分账请求后，可调用此接口查询分账结果
 * 注意：
 * • 发起解冻剩余资金请求后，可调用此接口查询解冻剩余资金的结果
 * @author dengying.zhang 2022年9月16日 下午3:35:36
 * @since 1.0.0
 */
public class WechatPayProfitSharingQueryParam {

    /**
     * 服务商户号    sp_mchid    string[1,32]    是   body 服务商户号，由微信支付生成并下发
    示例值：1230000109
     */
    @NotNull(message = "sp_mchid 服务商户号不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("sub_mchid")
    private String subMchid;

    /**
     * 微信支付订单号
     */
    @NotNull
    @Length(min = 1, max = 32)
    @SerializedName("transaction_id")
    private String transactionId;

    /**
     * 服务商系统内部的分账单号，在服务商系统内部唯一，同一分账单号多次请求等同一次。只能是数字、大小写字母_-|*@
     */
    @NotNull
    @Length(min = 1, max = 32)
    @SerializedName("out_order_no")
    @Expose(serialize = false)
    private String outOrderNo;

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
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

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
