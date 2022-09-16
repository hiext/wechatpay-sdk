package com.zhengzhuanglaile.wechatpay.isv.param;

import com.google.gson.annotations.SerializedName;

public enum ReceiversAccount {
                              /**
                               * 分账接收方类型为MERCHANT_ID时，分账接收方账号为商户号
                               */
                              @SerializedName("MERCHANT_ID")
                              MERCHANT_ID,

                              /**
                               * 分账接收方类型为PERSONAL_OPENID时，分账接收方账号为个人openid
                               */
                              @SerializedName("PERSONAL_OPENID")
                              PERSONAL_OPENID,

                              /**
                               * 分账接收方类型为PERSONAL_SUB_OPENID时，分账接收方账号为个人sub_openid
                               */
                              @SerializedName("PERSONAL_SUB_OPENID")
                              PERSONAL_SUB_OPENID,
}
