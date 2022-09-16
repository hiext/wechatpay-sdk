package com.zhengzhuanglaile.wechatpay;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhengzhuanglaile.wechatpay.isv.WechatPayIsvAppletApi;
import com.zhengzhuanglaile.wechatpay.isv.jsapi.param.WechatPayIsvAppletCreateOrderParam;
import com.zhengzhuanglaile.wechatpay.isv.model.WechatPayIsvOrderInfo;
import com.zhengzhuanglaile.wechatpay.isv.model.WechatPayIsvPayer;
import com.zhengzhuanglaile.wechatpay.isv.param.IsvCloseOrderParam;
import com.zhengzhuanglaile.wechatpay.isv.param.IsvPayOrderStatusQueryParam;
import com.zhengzhuanglaile.wechatpay.model.Amount;
import com.zhengzhuanglaile.wechatpay.model.WechatPayConfig;
import com.zhengzhuanglaile.wechatpay.result.WechatPayAppletCreateOrderResult;
import com.zhengzhuanglaile.wechatpay.result.WechatPayBaseResult;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 
 * @author dengying.zhang 2022年8月20日 下午3:47:37
 * @since 1.0.0
 */
public class WechatPayAppletPayApiTest {

    private static final Logger          logger          = LoggerFactory.getLogger(WechatPayNativePayApiTest.class);

    private final String                 spMchid         = "1627846695";

    private final String                 subMchid        = "1630124950";

    private static final WechatPayConfig wechatPayConfig = WechatPayConfig.builder()
        .setApiV3Key(WechatPayConstant.getApiv3key())
        .setMerchantId(WechatPayConstant.getMerchantId())
        .setMerchantSerialNumber(WechatPayConstant.getMerchantSerialNumber())
        .setPrivateKey(WechatPayConstant.getMerchantPrivatekey())
        .build();

    // @Test
    public void testCreatOrder() {
        WechatPayIsvAppletCreateOrderParam param = new WechatPayIsvAppletCreateOrderParam();
        String spAppid = "wx19b0234a18f143ab";
        param.setSpAppid(spAppid);
        /*
         * 整装来了小程序
         */
        String subAppId = "wx4f40bc48ba22a9eb";
        param.setSubAppid(subAppId);
        param.setSpMchid(spMchid);
        param.setSubMchid(subMchid);
        param.setDescription("苹果手机-开发测试");
        String subOpenId = "oTXBV5J6yK_7uPrZDANseEIxXZOI";
        param.setPayer(new WechatPayIsvPayer(subOpenId));

        String outTraderNo = "1630412847001007";
        param.setOutTradeNo(outTraderNo);
        String notifyUrl = "https://shop.zhengzhuanglaile.com/site/wechatpay/appletnotify";
        param.setNotifyUrl(notifyUrl);
        param.setAmount(new Amount(1));
        WechatPayAppletCreateOrderResult result = WechatPayIsvAppletApi.createOrder(param, wechatPayConfig);
        logger.info(result.getPrepayId());
        logger.info(GsonUtil.getGson().toJson(result));
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).plusDays(1L);
        logger.info(
            zonedDateTime.format(DateTimeFormatter.ofPattern(WechatPayConstant.WECHAT_PAY_DATE_FORMAT, Locale.CHINA)));
    }

    @Test
    public void testQueryOrderStatus() {
        IsvPayOrderStatusQueryParam csaobStatusQueryParam = new IsvPayOrderStatusQueryParam();
        String outTraderNo = "1630412847001006";
        csaobStatusQueryParam.setOutTradeNo(outTraderNo);
        csaobStatusQueryParam.setSpMchid(spMchid);
        csaobStatusQueryParam.setSubMchid(subMchid);
        WechatPayIsvOrderInfo statusQueryResult = WechatPayIsvAppletApi.queryOrderStatus(csaobStatusQueryParam,
            wechatPayConfig);
        logger.info(statusQueryResult.toString());
    }

    // @Test
    public void testCloseOrder() {
        IsvCloseOrderParam closeOrderParam = new IsvCloseOrderParam();
        closeOrderParam.setOutTradeNo("1630412847001007");
        closeOrderParam.setSpMchid(spMchid);
        closeOrderParam.setSubMchid(subMchid);
        WechatPayBaseResult wechatPayBaseResult = WechatPayIsvAppletApi.closeOrder(closeOrderParam, wechatPayConfig);
        logger.info(wechatPayBaseResult.toString());
    }

}
