package com.zhengzhuanglaile.wechatpay.mch.nativepay.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.base.BaseCreateOrderParam;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 商户直连方式 创建订单
 * 
 * @author dengying.zhang 2022年8月23日 下午2:30:51
 * @since 1.0.0
 */
public class WechatPayNativePayCreateOrderParam extends BaseCreateOrderParam {

    /**
     * 应用ID appid string[1,32] 是 body appid。示例值：wx8888888888888888
     */
    @NotNull(message = "appid 商户appid不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("appid")
    private String appid;
    /**
     * 直连商户号 mchid string[1,32] 是 body 服务商户号，由微信支付生成并下发 示例值：1230000109
     */
    @NotNull(message = "mchid 服务商户号不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("mchid")
    private String mchid;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
