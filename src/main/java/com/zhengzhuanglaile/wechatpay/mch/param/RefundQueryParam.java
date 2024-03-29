package com.zhengzhuanglaile.wechatpay.mch.param;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class RefundQueryParam {

    /**
     * 商户退款单号 说明：商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @SerializedName("out_refund_no")
    @Expose(serialize = false)
    private String outRefundNo;
    /**
     * 子商户号 说明：子商户的商户号，由微信支付生成并下发。服务商模式下必须传递此参数
     */
    @SerializedName("sub_mchid")
    @Expose(serialize = false)
    private String subMchid;

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }
}
