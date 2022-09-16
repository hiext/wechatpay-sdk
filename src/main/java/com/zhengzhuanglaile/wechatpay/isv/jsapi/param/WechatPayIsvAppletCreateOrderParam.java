package com.zhengzhuanglaile.wechatpay.isv.jsapi.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.base.BaseCreateOrderParam;
import com.zhengzhuanglaile.wechatpay.isv.model.WechatPayIsvPayer;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 小程序下单参数
 * @author dengying.zhang 2022年8月18日 下午3:20:13
 * @since 1.0.0
 */
public class WechatPayIsvAppletCreateOrderParam extends BaseCreateOrderParam {

    /**
     * 服务商应用ID sp_appid string[1,32] 是 body 服务商申请的公众号appid。示例值：wx8888888888888888
     */
    @NotNull(message = "sp_appid 服务商应用ID不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("sp_appid")
    private String            spAppid;
    /**
     * 服务商户号    sp_mchid    string[1,32]    是   body 服务商户号，由微信支付生成并下发
    示例值：1230000109
     */
    @NotNull(message = "sp_mchid 服务商户号不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("sp_mchid")
    private String            spMchid;

    /**
     * 子商户应用ID  sub_appid   string[1,32]    否   body 子商户申请的公众号appid。
    示例值：wxd678efh567hg6999
     */
    @Length(min = 1, max = 32)
    @SerializedName("sub_appid")
    private String            subAppid;

    /**
     * 子商户号 sub_mchid   string[1,32]    是   body 子商户的商户号，由微信支付生成并下发。
    示例值：1900000109
     */
    @NotNull(message = "sub_mchid 不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("sub_mchid")
    private String            subMchid;
    private WechatPayIsvPayer payer;

    public WechatPayIsvPayer getPayer() {
        return payer;
    }

    public void setPayer(WechatPayIsvPayer payer) {
        this.payer = payer;
    }

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

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
