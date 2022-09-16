package com.zhengzhuanglaile.wechatpay.mch.jsapi.param;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.mch.model.WechatPayPayer;
import com.zhengzhuanglaile.wechatpay.mch.nativepay.param.WechatPayNativePayCreateOrderParam;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class WechatPayAppletCreateOrderParam extends WechatPayNativePayCreateOrderParam {

    @SerializedName("payer")
    private WechatPayPayer payer;

    public WechatPayPayer getPayer() {
        return payer;
    }

    public void setPayer(WechatPayPayer payer) {
        this.payer = payer;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
