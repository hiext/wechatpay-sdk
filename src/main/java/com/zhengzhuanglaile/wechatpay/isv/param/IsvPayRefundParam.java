package com.zhengzhuanglaile.wechatpay.isv.param;

import javax.validation.constraints.NotNull;

import com.zhengzhuanglaile.wechatpay.mch.param.PayRefundParam;
import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * @author dengying.zhang
 */
public class IsvPayRefundParam extends PayRefundParam {

    /**
     * 子商户号 sub_mchid string[1,32] 是 body 子商户的商户号，由微信支付生成并下发。 示例值：1900000109
     */
    @NotNull(message = "sub_mchid 不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("sub_mchid")
    private String subMchid;

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
