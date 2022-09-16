package com.zhengzhuanglaile.wechatpay.isv.model;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.base.BaseOrderInfo;

public class WechatPayIsvOrderInfo extends BaseOrderInfo {

    @SerializedName("sp_appid")
    private String            spAppid;
    /**
     * 服务商户号    sp_mchid    string[1,32]    是   body 服务商户号，由微信支付生成并下发
    示例值：1230000109
     */
    @SerializedName("sp_mchid")
    private String            spMchid;

    /**
     * 子商户应用ID  sub_appid   string[1,32]    否   body 子商户申请的公众号appid。
    示例值：wxd678efh567hg6999
     */
    @SerializedName("sub_appid")
    private String            subAppid;

    /**
     * 子商户号 sub_mchid   string[1,32]    是   body 子商户的商户号，由微信支付生成并下发。
    示例值：1900000109
     */
    @SerializedName("sub_mchid")
    private String            subMchid;

    /** payer */
    @SerializedName("payer")
    private WechatPayIsvPayer payer;

    public String getSpAppid() {
        return spAppid;
    }

    public void setSpAppid(String spAppid) {
        this.spAppid = spAppid;
    }

    public String getSpMchid() {
        return spMchid;
    }

    public void setSpMchid(String spMchid) {
        this.spMchid = spMchid;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
    }

    public WechatPayIsvPayer getPayer() {
        return payer;
    }

    public void setPayer(WechatPayIsvPayer payer) {
        this.payer = payer;
    }

}
