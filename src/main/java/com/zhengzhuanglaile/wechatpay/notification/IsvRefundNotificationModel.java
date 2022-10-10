package com.zhengzhuanglaile.wechatpay.notification;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * @author angy
 */
public class IsvRefundNotificationModel extends RefundNotificationModel {

    /**
     * 服务商户号 sp_mchid string[1,32] 是 body 服务商户号，由微信支付生成并下发 示例值：1230000109
     */
    @SerializedName("sp_mchid")
    private String spMchid;

    /**
     * 子商户号 sub_mchid string[1,32] 是 body 子商户的商户号，由微信支付生成并下发。 示例值：1900000109
     */
    @SerializedName("sub_mchid")
    private String subMchid;

    public String getSpMchid() {
        return spMchid;
    }

    public void setSpMchid(String spMchid) {
        this.spMchid = spMchid;
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
