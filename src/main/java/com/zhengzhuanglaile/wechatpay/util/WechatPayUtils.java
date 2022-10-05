package com.zhengzhuanglaile.wechatpay.util;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.zhengzhuanglaile.wechatpay.WechatPayConstant;

public class WechatPayUtils {

    /**
     * 获取正单号
     * 
     * @param msgSrcId 前缀
     * @return 商户订单号 {来源编号(3或5位)}{时间(yyyyMMddmmHHssSSS)(17位)}{7位随机}
     */
    public static String getOutOrderId(String msgSrcId) {
        if (msgSrcId == null || "".equals(msgSrcId)) {
            msgSrcId = "oms";
        }
        return msgSrcId + DateFormatUtils.format(new Date(), WechatPayConstant.DATE_FORMART_FULL_BASE)
               + RandomStringUtils.randomNumeric(7);
    }

    /**
     * 获取到退货订单号refundOrderId
     *
     * @param msgSrcId 消息来源编号 1017
     * @return 商户订单号 {来源编号(3或4位)}{时间(yyyyMMddmmHHssSSS)(17位)}{7位随机}
     */
    public static String getRefundOrderId(String msgSrcId) {
        if (msgSrcId == null || "".equals(msgSrcId)) {
            msgSrcId = "oms";
        }
        return msgSrcId + DateFormatUtils.format(new Date(), WechatPayConstant.DATE_FORMART_FULL_BASE)
               + RandomStringUtils.randomNumeric(7);
    }
}
