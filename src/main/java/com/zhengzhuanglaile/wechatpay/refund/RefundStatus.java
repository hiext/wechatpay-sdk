package com.zhengzhuanglaile.wechatpay.refund;

import com.google.gson.annotations.SerializedName;

/**
 * SUCCESS - 退款成功，退款状态 CLOSED - 退款关闭，退款状态 PROCESSING - 退款处理中，退款状态 ABNORMAL - 退款异常，退款状态
 */
public enum RefundStatus {
                          /**
                           *
                           */
                          @SerializedName("SUCCESS")
                          SUCCESS,
                          /**
                           *
                           */
                          @SerializedName("CLOSED")
                          CLOSED,
                          /**
                           *
                           */
                          @SerializedName("PROCESSING")
                          PROCESSING,
                          /**
                           *
                           */
                          @SerializedName("ABNORMAL")
                          ABNORMAL
}
