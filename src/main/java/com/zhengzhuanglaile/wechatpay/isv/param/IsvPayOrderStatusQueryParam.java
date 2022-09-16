package com.zhengzhuanglaile.wechatpay.isv.param;

import javax.validation.constraints.NotNull;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.base.BasePayOrderStatusQueryParam;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 商户订单号查询参数
 * @author dengying.zhang 2022年8月18日 下午4:50:31
 * @since 1.0.0
 */
public class IsvPayOrderStatusQueryParam extends BasePayOrderStatusQueryParam {

    @NotNull
    @SerializedName("sp_mchid")
    private String spMchid;
    @NotNull
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
