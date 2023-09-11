package com.zhengzhuanglaile.wechatpay.isv.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.mch.param.ProfitSharingCreateOrderParam;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 请求分账参数
 * 
 * @author dengying.zhang 2022年9月16日 下午1:52:46
 * @since 1.0.0
 */
public class IsvProfitSharingCreateOrderParam extends ProfitSharingCreateOrderParam {

    /**
     * 子商户号 string[1,32] 是 body 子商户号 示例值：1230000109
     */
    @NotNull(message = "sub_mchid 子商户号不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("sub_mchid")
    private String subMchid;

    /**
     * 子商户应用ID sub_appid string[1,32] 否 子商户的公众账号ID，分账接收方类型包含PERSONAL_SUB_OPENID时必填 示例值：wxd678efh567hg6999
     */
    @Length(min = 1, max = 32)
    @SerializedName("sub_appid")
    private String subAppid;

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
