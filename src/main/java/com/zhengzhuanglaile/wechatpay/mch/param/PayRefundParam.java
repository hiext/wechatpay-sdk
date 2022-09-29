package com.zhengzhuanglaile.wechatpay.mch.param;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.isv.nativepay.param.RefundAmountParam;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author dengying.zhang
 */
public class PayRefundParam {


    /**
     * 商户订单号    out_trade_no    string[6,32]    是   body 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。
     示例值：1217752501201407033233368018
     */
    @NotNull(message = "out_trade_no 不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("out_trade_no")
    private String            outTradeNo;
    /**
     * 商户订单号    out_trade_no    string[6,32]    是   body 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。
     示例值：1217752501201407033233368018
     */
    @NotNull(message = "out_refund_no 不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("out_refund_no")
    private String            outRefundNo;

    @SerializedName("reason")
    private String            reason;

    @SerializedName("notify_url")
    private String            notifyUrl;

    @SerializedName("funds_account")
    private String            fundsAccount;
    @SerializedName("amount")
    private RefundAmountParam amount;
}
