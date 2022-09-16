
package com.zhengzhuanglaile.wechatpay.isv.nativepay.param;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/** FundsFromItem */
public class FundsFromItem {

    /** 出资金额 说明：对应账户出资金额 */
    @SerializedName("amount")
    private Long   amount;
    /** 出资账户类型 说明：下面枚举值多选一。 枚举值： AVAILABLE : 可用余额 UNAVAILABLE : 不可用余额 */
    @SerializedName("account")
    private String account;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }
}
