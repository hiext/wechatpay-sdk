package com.zhengzhuanglaile.wechatpay.isv.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.mch.param.ProfitSharingQueryParam;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 查询分账结果参数 发起分账请求后，可调用此接口查询分账结果 注意： • 发起解冻剩余资金请求后，可调用此接口查询解冻剩余资金的结果
 * 
 * @author dengying.zhang 2022年9月16日 下午3:35:36
 * @since 1.0.0
 */
public class IsvProfitSharingQueryParam extends ProfitSharingQueryParam {

    /**
     * 服务商户号 sp_mchid string[1,32] 是 body 服务商户号，由微信支付生成并下发 示例值：1230000109
     */
    @NotNull(message = "sp_mchid 服务商户号不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("sub_mchid")
    private String subMchid;

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
