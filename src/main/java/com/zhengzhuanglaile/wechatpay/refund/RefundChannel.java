package com.zhengzhuanglaile.wechatpay.refund;

import com.google.gson.annotations.SerializedName;

/**
 * ORIGINAL - 原路退款，退款渠道 BALANCE - 退回到余额，退款渠道 OTHER_BALANCE - 原账户异常退到其他余额账户，退款渠道 OTHER_BANKCARD - 原银行卡异常退到其他银行卡，退款渠道
 */
public enum RefundChannel {
                           /**
                            * 原路退款
                            */
                           @SerializedName("ORIGINAL")
                           ORIGINAL,

                           /**
                            * 退回到余额
                            */
                           @SerializedName("BALANCE")
                           BALANCE,

                           /**
                            * 原账户异常退到其他余额账户
                            */
                           @SerializedName("OTHER_BALANCE")
                           OTHER_BALANCE,

                           /**
                            * 原银行卡异常退到其他银行卡
                            */
                           @SerializedName("OTHER_BANKCARD")
                           OTHER_BANKCARD
}
