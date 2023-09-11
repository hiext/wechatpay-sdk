package com.zhengzhuanglaile.wechatpay.isv.model;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.mch.model.ProfitSharingCreateOrderResult;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 请求分账返回参数
 * 
 * @author dengying.zhang 2022年9月16日 下午2:48:54
 * @since 1.0.0
 */
public class IsvProfitSharingCreateOrderResult extends ProfitSharingCreateOrderResult {

    /**
     * 子商户号 string[1,32] 是 body 服务商户号，由微信支付生成并下发 示例值：1230000109
     */
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
