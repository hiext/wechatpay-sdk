package com.zhengzhuanglaile.wechatpay.model;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class WechatPaySceneInfo {

    /**
     * 用户终端IP payer_client_ip string[1,45] 是 用户的客户端IP，支持IPv4和IPv6两种格式的IP地址。 示例值：14.23.150.211
     */
    @SerializedName("payer_client_ip")
    private String                  payerClientIp;

    /**
     * 商户端设备号 device_id string[1,32] 否 商户端设备号（门店号或收银设备ID）。 示例值：013467007045764
     */
    @SerializedName("device_id")
    private String                  deviceId;

    /**
     * 商户门店信息 store_info object 否 商户门店信息
     */
    @SerializedName("store_info")
    private WechatPaySceneInfoStore storeInfo;

    public String getPayerClientIp() {
        return payerClientIp;
    }

    public void setPayerClientIp(String payerClientIp) {
        this.payerClientIp = payerClientIp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public WechatPaySceneInfoStore getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(WechatPaySceneInfoStore storeInfo) {
        this.storeInfo = storeInfo;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
