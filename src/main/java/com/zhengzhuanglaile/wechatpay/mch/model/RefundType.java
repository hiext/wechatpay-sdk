package com.zhengzhuanglaile.wechatpay.mch.model;

import com.google.gson.annotations.SerializedName;

/**
 * COUPON - 代金券，代金券类型，需要走结算资金的充值型代金券 DISCOUNT - 优惠券，优惠券类型，不走结算资金的免充值型优惠券
 */
public enum RefundType {
                        /**
                         * 代金券
                         */
                        @SerializedName("COUPON")
                        COUPON,
                        /**
                         * 优惠券
                         */
                        @SerializedName("DISCOUNT")
                        DISCOUNT
}
