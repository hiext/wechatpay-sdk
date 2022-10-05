package com.zhengzhuanglaile.wechatpay.isv.model;

import com.google.gson.annotations.SerializedName;

/**
 * 分账单状态每个接收方的分账结果请查看receivers中的result字段
 * 
 * @author dengying.zhang 2022年9月16日 下午2:53:24
 * @since 1.0.0
 */
public enum ProfitSharingState {
                                /**
                                 * 处理中
                                 */
                                @SerializedName("PROCESSING")
                                PROCESSING,
                                /**
                                 * 分账完成
                                 */
                                @SerializedName("FINISHED")
                                FINISHED,
}
