package com.zhengzhuanglaile.wechatpay.isv.nativepay.param;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 退款金额参数
 * 
 * @author dengying.zhang 2022年8月22日 下午5:19:37
 * @since 1.0.0
 */
public class RefundAmountParam {

    /** 退款金额 说明：退款金额，币种的最小单位，只能为整数，不能超过原订单支付金额。 */
    @SerializedName("refund")
    private Integer             refund;
    /**
     * 退款出资账户及金额 说明：退款需要从指定账户出资时，传递此参数指定出资金额（币种的最小单位，只能为整数）。
     * 同时指定多个账户出资退款的使用场景需要满足以下条件：1、未开通退款支出分离产品功能；2、订单属于分账订单，且分账处于待分账或分账中状态。
     * 参数传递需要满足条件：1、基本账户可用余额出资金额与基本账户不可用余额出资金额之和等于退款金额；2、账户类型不能重复。 上述任一条件不满足将返回错误
     */
    @SerializedName("from")
    private List<FundsFromItem> from     = new ArrayList<FundsFromItem>();
    /** 原订单金额 说明：原支付交易的订单总金额，币种的最小单位，只能为整数。 */
    @SerializedName("total")
    private Integer             total;
    /** 退款币种 说明：符合ISO 4217标准的三位字母代码，目前只支持人民币：CNY。 */
    @SerializedName("currency")
    private String              currency = "CNY";

    public Integer getRefund() {
        return refund;
    }

    public void setRefund(Integer refund) {
        this.refund = refund;
    }

    public List<FundsFromItem> getFrom() {
        return from;
    }

    public void setFrom(List<FundsFromItem> from) {
        this.from = from;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
