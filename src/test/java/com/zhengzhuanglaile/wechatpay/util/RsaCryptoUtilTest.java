package com.zhengzhuanglaile.wechatpay.util;

import javax.crypto.BadPaddingException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;

public class RsaCryptoUtilTest {

    private static Logger logger     = LoggerFactory.getLogger(RsaCryptoUtilTest.class);
    private final String  APPID      = "wx4f40bc48ba22a9eb";

    private final String  privateKey = WechatPayRsaCrypUtil.readPrivateKeyToString("\\d:\\wechatpaycert\\hanlan_key.pem");

    //@Test
    public void testEn() throws BadPaddingException {

        Long tims = 1661339973L;
        String appid = APPID;
        // 获取随机字符串
        String nonceStr = "Fqsdo6SiGpdcIKTZxWoxihS8k62UhCS";
        String miniPackage = "prepay_id=" + "wx241812227411747314812c65b38e6b0000";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(appid + "\n");
        stringBuffer.append(tims + "\n");
        stringBuffer.append(nonceStr + "\n");
        stringBuffer.append(miniPackage + "\n");
        String todoSign = stringBuffer.toString();
        logger.info(todoSign);
        String paySign = WechatPayRsaCrypUtil.sign(PemUtil.loadPrivateKey(privateKey), todoSign);
        logger.info(paySign);

    }

}
