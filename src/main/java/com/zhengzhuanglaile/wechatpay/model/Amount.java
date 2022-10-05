package com.zhengzhuanglaile.wechatpay.model;

/**
 * 微信支付订单金额模型
 * 
 * @author dengying.zhang 2022年8月18日 下午2:57:07
 * @since 1.0.0
 */
public class Amount {

    /**
     * 总金额 total int 是 订单总金额，单位为分。 示例值：100
     */
    private int    total;

    /**
     * 货币类型 currency string[1,16] 否 CNY：人民币，境内商户号仅支持人民币。 示例值：CNY
     */
    private String currency = "CNY";

    public int getTotal() {
        return total;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getCurrency() {
        return currency;
    }

    public Amount(int total){
        super();
        this.total = total;
    }

}
