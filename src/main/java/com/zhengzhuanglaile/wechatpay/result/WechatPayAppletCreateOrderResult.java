package com.zhengzhuanglaile.wechatpay.result;

import java.util.Map;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 小程序支付创建预付订单的返回值
 * 
 * @author dengying.zhang 2022年8月20日 上午11:40:49
 * @since 1.0.0
 */
public class WechatPayAppletCreateOrderResult extends WechatPayBaseResult {

    @SerializedName("prepay_id")
    private String              prepayId;

    private Map<String, String> miniPayRequest;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public Map<String, String> getMiniPayRequest() {
        return miniPayRequest;
    }

    public void setMiniPayRequest(Map<String, String> miniPayRequest) {
        this.miniPayRequest = miniPayRequest;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
