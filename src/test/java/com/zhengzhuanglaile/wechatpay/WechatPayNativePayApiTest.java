package com.zhengzhuanglaile.wechatpay;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhengzhuanglaile.wechatpay.isv.WechatPayIsvNativePayApi;
import com.zhengzhuanglaile.wechatpay.isv.model.WechatPayIsvOrderInfo;
import com.zhengzhuanglaile.wechatpay.isv.nativepay.param.WechatPayIsvNativePayCreateOrderParam;
import com.zhengzhuanglaile.wechatpay.isv.param.IsvPayRefundParam;
import com.zhengzhuanglaile.wechatpay.isv.nativepay.param.RefundAmountParam;
import com.zhengzhuanglaile.wechatpay.isv.param.IsvCloseOrderParam;
import com.zhengzhuanglaile.wechatpay.isv.param.IsvPayOrderStatusQueryParam;
import com.zhengzhuanglaile.wechatpay.model.Amount;
import com.zhengzhuanglaile.wechatpay.model.WechatPayConfig;
import com.zhengzhuanglaile.wechatpay.result.NativePayResult;
import com.zhengzhuanglaile.wechatpay.result.WechatPayBaseResult;

public class WechatPayNativePayApiTest {

    private static Logger                logger          = LoggerFactory.getLogger(WechatPayNativePayApiTest.class);

    private final String                 spAppid         = "wx19b0234a18f143ab";

    private final String                 spMchid         = "1627846695";

    private final String                 subMchid        = "1630412847";

    private final String                 notifyUrl       = "https://shop.zhengzhuanglaile.com/site/wechatpay/nativenotify";

    private static final WechatPayConfig wechatPayConfig = WechatPayConfig.builder().setApiV3Key(WechatPayConstant.getApiv3key()).setMerchantId(WechatPayConstant.getMerchantId()).setMerchantSerialNumber(WechatPayConstant.getMerchantSerialNumber()).setPrivateKey(WechatPayConstant.getMerchantPrivatekey()).build();

    // @Test
    public void testCreatOrder() {
        WechatPayIsvNativePayCreateOrderParam param = new WechatPayIsvNativePayCreateOrderParam();
        param.setSpAppid(spAppid);
        param.setSpMchid(spMchid);
        param.setSubMchid(subMchid);
        param.setDescription("苹果手机-开发测试");
        String outTraderNo = "1630412847001008";
        param.setOutTradeNo(outTraderNo);
        param.setNotifyUrl(notifyUrl);
        param.setAmount(new Amount(500));
        NativePayResult result = WechatPayIsvNativePayApi.createOrder(param, wechatPayConfig);
        logger.info(result.getCodeUrl());
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).plusDays(1L);
        logger.info(zonedDateTime.format(DateTimeFormatter.ofPattern(WechatPayConstant.WECHAT_PAY_DATE_FORMAT,
                                                                     Locale.CHINA)));
    }

    @Test
    public void testQueryOrderStatus() {
        IsvPayOrderStatusQueryParam csaobStatusQueryParam = new IsvPayOrderStatusQueryParam();
        String outTraderNo = "wos202209261724008748716655";
        csaobStatusQueryParam.setOutTradeNo(outTraderNo);
        csaobStatusQueryParam.setSpMchid(spMchid);
        csaobStatusQueryParam.setSubMchid(subMchid);
        WechatPayIsvOrderInfo statusQueryResult = WechatPayIsvNativePayApi.queryOrderStatus(csaobStatusQueryParam,
                                                                                            wechatPayConfig);
        logger.info(statusQueryResult.toString());
    }

    // @Test
    public void testCloseOrder() {
        IsvCloseOrderParam closeOrderParam = new IsvCloseOrderParam();
        closeOrderParam.setOutTradeNo("1630412847001008");
        closeOrderParam.setSpMchid(spMchid);
        closeOrderParam.setSubMchid(subMchid);
        WechatPayBaseResult wechatPayBaseResult = WechatPayIsvNativePayApi.closeOrder(closeOrderParam, wechatPayConfig);
        logger.info(wechatPayBaseResult.toString());
    }

    // @Test
    public void testRefund() {
        IsvPayRefundParam narivePayRefundParam = new IsvPayRefundParam();
        RefundAmountParam amountParam = new RefundAmountParam();
        amountParam.setRefund(500);
        amountParam.setTotal(500);
        narivePayRefundParam.setAmount(amountParam);
        narivePayRefundParam.setSubMchid(subMchid);
        narivePayRefundParam.setOutRefundNo("T1630412847001008");
        narivePayRefundParam.setOutTradeNo("1630412847001008");
        narivePayRefundParam.setReason("测试");
        WechatPayBaseResult wechatPayBaseResult = WechatPayIsvNativePayApi.refund(narivePayRefundParam,
                                                                                  wechatPayConfig);
        logger.info(wechatPayBaseResult.toString());
    }
}
