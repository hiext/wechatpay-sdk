package com.zhengzhuanglaile.wechatpay.isv.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 解冻剩余资金参数
 * @author dengying.zhang 2022年9月16日 下午3:38:49
 * @since 1.0.0
 */
public class WechatPayProfitSharingUnfreezeParam {

    /**
     * 子商户号    sub_mchid    string[1,32]    是   body 子商户号，由微信支付生成并下发
    示例值：1230000109
     */
    @NotNull(message = "sub_mchid 子商户号不能为空")
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
    private String outOrderNo;

    /**
     * 分账描述
     */

    @NotNull
    @Length(max = 80, min = 1)
    @SerializedName("description")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
