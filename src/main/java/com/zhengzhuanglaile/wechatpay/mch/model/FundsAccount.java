package com.zhengzhuanglaile.wechatpay.mch.model;

import com.google.gson.annotations.SerializedName;

/**
 * UNSETTLED - 未结算资金，退款所使用资金对应的资金账户类型 AVAILABLE - 可用余额，退款所使用资金对应的资金账户类型 UNAVAILABLE - 不可用余额，退款所使用资金对应的资金账户类型 OPERATION -
 * 运营户，退款所使用资金对应的资金账户类型 BASIC - 基本账户（含可用余额和不可用余额），退款所使用资金对应的资金账户类型
 */
public enum FundsAccount {
                          /**
                           *
                           */
                          @SerializedName("UNSETTLED")
                          UNSETTLED,
                          /**
                           *
                           */
                          @SerializedName("AVAILABLE")
                          AVAILABLE,
                          /**
                           *
                           */
                          @SerializedName("UNAVAILABLE")
                          UNAVAILABLE,
                          /**
                           *
                           */
                          @SerializedName("OPERATION")
                          OPERATION,
                          /**
                           *
                           */
                          @SerializedName("BASIC")
                          BASIC
}
