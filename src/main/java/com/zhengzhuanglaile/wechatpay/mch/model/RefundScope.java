package com.zhengzhuanglaile.wechatpay.mch.model;

import com.google.gson.annotations.SerializedName;

/**
 * GLOBAL - 全场代金券，全场优惠类型 SINGLE - 单品优惠，单品优惠类型
 */
public enum RefundScope {
                         /**
                          * 全场代金券
                          */
                         @SerializedName("GLOBAL")
                         GLOBAL,
                         /**
                          * 单品优惠
                          */
                         @SerializedName("SINGLE")
                         SINGLE
}
