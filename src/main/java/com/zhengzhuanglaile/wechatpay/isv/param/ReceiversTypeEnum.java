package com.zhengzhuanglaile.wechatpay.isv.param;

import com.google.gson.annotations.SerializedName;

public enum ReceiversTypeEnum {
                               /**
                                * 商户号
                                */
                               @SerializedName("MERCHANT_ID")
                               MERCHANT_ID,

                               /**
                                * 个人openid（由父商户APPID转换得到）
                                */
                               @SerializedName("PERSONAL_OPENID")
                               PERSONAL_OPENID,

                               /**
                                * 个人sub_openid（由子商户APPID转换得到）
                                */
                               @SerializedName("PERSONAL_SUB_OPENID")
                               PERSONAL_SUB_OPENID,

}
