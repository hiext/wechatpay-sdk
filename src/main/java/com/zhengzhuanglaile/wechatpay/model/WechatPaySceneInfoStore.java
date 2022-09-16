package com.zhengzhuanglaile.wechatpay.model;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 微信支付场景门店信息模型
 * @author dengying.zhang 2022年8月18日 下午3:16:14
 * @since 1.0.0
 */
public class WechatPaySceneInfoStore {

    /**
     * 门店编号 id  string[1,32]    是   商户侧门店编号
    示例值：0001
     */
    private String id;

    /**
     * 门店名称 name    string[1,256]   否   商户侧门店名称
    示例值：腾讯大厦分店
     */
    private String name;

    /**
     * 地区编码 area_code   string[1,32]    否   地区编码，详细请见省市区编号对照表。
    示例值：440305
     */
    @SerializedName("area_code")
    private String areaCode;
    /**
     * 详细地址 address string[1,512]   否   详细的商户门店地址
    示例值：广东省深圳市南山区科技中一道10000号
     */
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
