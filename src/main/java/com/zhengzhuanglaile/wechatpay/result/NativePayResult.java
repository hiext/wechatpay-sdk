package com.zhengzhuanglaile.wechatpay.result;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

/**
 * 微信支付原生支付下单的返回值，返回url地址
 * @author dengying.zhang 2022年8月18日 下午3:32:24
 * @since 1.0.0
 */
public class NativePayResult extends WechatPayBaseResult implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6488851899273828027L;
    @SerializedName("code_url")
    private String            codeUrl;

    public String getCodeUrl() {
        return codeUrl;
    }

    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
