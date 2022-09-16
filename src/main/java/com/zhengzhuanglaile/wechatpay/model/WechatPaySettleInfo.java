package com.zhengzhuanglaile.wechatpay.model;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 结算信息
 * @author dengying.zhang 2022年8月18日 下午3:01:31
 * @since 1.0.0
 */
public class WechatPaySettleInfo {

    /**
     * 是否指定分账
     */
    @SerializedName("profit_sharing")
    private boolean profitSharing = false;

    public boolean isProfitSharing() {
        return profitSharing;
    }

    public void setProfitSharing(boolean profitSharing) {
        this.profitSharing = profitSharing;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
