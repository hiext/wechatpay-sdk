package com.zhengzhuanglaile.wechatpay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhengzhuanglaile.wechatpay.isv.IsvProfitSharingApi;
import com.zhengzhuanglaile.wechatpay.isv.model.ProfitSharingAmount;
import com.zhengzhuanglaile.wechatpay.isv.param.ProfitSharingAmountQueryParam;
import com.zhengzhuanglaile.wechatpay.model.WechatPayConfig;

public class Test {

    private static Logger                logger          = LoggerFactory.getLogger(WechatPayNativePayApiTest.class);

    private static final WechatPayConfig wechatPayConfig = WechatPayConfig.builder().setApiV3Key(WechatPayConstant.getApiv3key()).setMerchantId(WechatPayConstant.getMerchantId()).setMerchantSerialNumber(WechatPayConstant.getMerchantSerialNumber()).setPrivateKey(WechatPayConstant.getMerchantPrivatekey()).build();

    @org.junit.Test
    public void testQueryTransactions() {
        ProfitSharingAmountQueryParam sharingAmountQueryParam = new ProfitSharingAmountQueryParam();
        String orderId = "4200001591202209264231266457";
        sharingAmountQueryParam.setTransactionId(orderId);
        ProfitSharingAmount statusQueryResult = IsvProfitSharingApi.queryProfitSharingAmount(sharingAmountQueryParam,
                                                                                             wechatPayConfig);
        logger.info(statusQueryResult.toString());
    }
}
