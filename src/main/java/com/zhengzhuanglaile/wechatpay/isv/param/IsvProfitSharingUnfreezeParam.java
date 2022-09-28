package com.zhengzhuanglaile.wechatpay.isv.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.mch.param.ProfitSharingUnfreezeParam;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 解冻剩余资金参数
 * 
 * @author dengying.zhang 2022年9月16日 下午3:38:49
 * @since 1.0.0
 */
public class IsvProfitSharingUnfreezeParam extends ProfitSharingUnfreezeParam {

    /**
     * 子商户号 sub_mchid string[1,32] 是 body 子商户号，由微信支付生成并下发 示例值：1230000109
     */
    @NotNull(message = "sub_mchid 子商户号不能为空")
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
