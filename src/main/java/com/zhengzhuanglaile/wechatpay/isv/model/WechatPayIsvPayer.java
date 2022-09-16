package com.zhengzhuanglaile.wechatpay.isv.model;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 微信小程序支付者信息，以下参数二选一
 * @author dengying.zhang 2022年8月18日 下午3:23:28
 * @since 1.0.0
 */
public class WechatPayIsvPayer {

    /**
     * 参数名  变量  类型[长度限制]    必填  描述
    用户服务标识  sp_openid   string[1,128]   二选一 用户在服务商appid下的唯一标识。 下单前需获取到用户的Openid，Openid获取详见。
    示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o 
     */
    @SerializedName("sp_openid")
    private String spOpenid;

    /**
     * 
    用户子标识   sub_openid  string[1,128]   用户在子商户appid下的唯一标识。若传sub_openid，那sub_appid必填。下单前需获取到用户的Openid，Openid获取详见。
    示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     */
    @SerializedName("sub_openid")
    private String subOpenid;

    public String getSpOpenid() {
        return spOpenid;
    }

    public void setSpOpenid(String spOpenid) {
        this.spOpenid = spOpenid;
    }

    public String getSubOpenid() {
        return subOpenid;
    }

    public void setSubOpenid(String subOpenid) {
        this.subOpenid = subOpenid;
    }

    public WechatPayIsvPayer(String subOpenid){
        super();
        this.subOpenid = subOpenid;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
