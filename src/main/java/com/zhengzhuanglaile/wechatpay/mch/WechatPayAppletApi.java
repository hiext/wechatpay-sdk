package com.zhengzhuanglaile.wechatpay.mch;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.zhengzhuanglaile.wechatpay.isv.nativepay.param.WechatPayIsvNativePayCreateOrderParam;
import com.zhengzhuanglaile.wechatpay.mch.jsapi.param.WechatPayAppletCreateOrderParam;
import com.zhengzhuanglaile.wechatpay.mch.model.WechatPayOrderInfo;
import com.zhengzhuanglaile.wechatpay.mch.param.WechatPayCloseOrderParam;
import com.zhengzhuanglaile.wechatpay.mch.param.WechatPayOrderStatusQueryParam;
import com.zhengzhuanglaile.wechatpay.model.WechatPayConfig;
import com.zhengzhuanglaile.wechatpay.model.WechatPayResultCode;
import com.zhengzhuanglaile.wechatpay.result.WechatPayAppletCreateOrderResult;
import com.zhengzhuanglaile.wechatpay.result.WechatPayBaseResult;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;
import com.zhengzhuanglaile.wechatpay.util.RequestClientUtil;
import com.zhengzhuanglaile.wechatpay.util.WechatPayRsaCrypUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.hibernate.validator.internal.util.StringHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import com.zhengzhuanglaile.wechatpay.WechatPayConstant;
import com.zhengzhuanglaile.wechatpay.isv.WechatPayIsvAppletApi;

/**
 * ????????????????????????????????????
 * @author dengying.zhang 2022???8???23??? ??????1:29:24
 * @since 1.0.0
 */
public class WechatPayAppletApi {

    private static Logger       logger    = LoggerFactory.getLogger(WechatPayIsvAppletApi.class);
    private static final String JSAPI_URI = "/v3/pay/transactions/jsapi";

    private static final String QUERY_URI = "/v3/pay/transactions/out-trade-no/";
    private static Validator    validator = null;

    protected static void init() {
        // ??????????????????
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    private static void buildMiniProgramRequest(WechatPayAppletCreateOrderParam param, WechatPayConfig wechatPayConfig,
                                                WechatPayAppletCreateOrderResult result) {

        Long tims = ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).toEpochSecond();
        String appid = param.getAppid();
        // ?????????????????????
        String nonceStr = WechatPayRsaCrypUtil.getRandomString(32);
        String miniPackage = "prepay_id=" + result.getPrepayId();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(appid);
        stringBuffer.append("\n");
        stringBuffer.append(tims);
        stringBuffer.append("\n");
        stringBuffer.append(nonceStr);
        stringBuffer.append("\n");
        stringBuffer.append(miniPackage);
        stringBuffer.append("\n");
        String todoSign = stringBuffer.toString();
        logger.info(todoSign);
        String paySign = WechatPayRsaCrypUtil.sign(PemUtil.loadPrivateKey(wechatPayConfig.getPrivateKey()), todoSign);
        Map<String, String> jsonObj = new HashMap<>();
        jsonObj.put("timeStamp", tims.toString());
        jsonObj.put("nonceStr", nonceStr);
        jsonObj.put("package", miniPackage);
        jsonObj.put("signType", "RSA");
        jsonObj.put("paySign", paySign);
        result.setMiniPayRequest(jsonObj);
    }

    /**
     * ??????????????????????????????
     * @param param
     * @param wechatPayConfig
     * @return
     */
    public static WechatPayAppletCreateOrderResult createOrder(WechatPayAppletCreateOrderParam param,
                                                               WechatPayConfig wechatPayConfig) {
        init();
        Set<ConstraintViolation<WechatPayAppletCreateOrderParam>> set = validator.validate(param);
        if (set != null && set.size() > 0) {
            ArrayList<String> validateString = new ArrayList<>();
            for (ConstraintViolation<WechatPayAppletCreateOrderParam> constraintViolation : set) {
                validateString.add(
                    "?????????" + constraintViolation.getPropertyPath().toString() + "-" + constraintViolation.getMessage());
                logger.info("?????????" + constraintViolation.getMessage());
                logger.info("?????????" + constraintViolation.getPropertyPath().toString());
            }
            throw new IllegalArgumentException(StringHelper.join(validateString, ";"));
        }

        // ?????????????????????????????????????????????????????????24?????????????????????
        if (null == param.getTimeExpire() || "".equals(param.getTimeExpire())) {
            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).plusDays(1L);
            param.setTimeExpire(zonedDateTime
                .format(DateTimeFormatter.ofPattern(WechatPayConstant.WECHAT_PAY_DATE_FORMAT, Locale.CHINA)));
        }

        RequestClientUtil requestClientUtil = RequestClientUtil.build(wechatPayConfig);
        CloseableHttpClient httpClient = requestClientUtil.getHttpClient();
        String uri = WechatPayConstant.WECHAT_HTTPS + WechatPayConstant.WECHAT_PAY_HOST + JSAPI_URI;
        logger.info("=========???????????????==============");
        HttpPost httpPost = new HttpPost(uri);
        StringEntity entity = new StringEntity(GsonUtil.getGson().toJson(param),
            Charset.forName(WechatPayConstant.DEFAULT_CHARTSET_NAME));
        entity.setContentType(ContentType.APPLICATION_JSON.toString());
        logger.info("=========??????:" + WechatPayIsvNativePayCreateOrderParam.class + "==============");
        httpPost.setEntity(entity);
        logger.info("=========????????????==============");
        logger.info(GsonUtil.getGson().toJson(param));
        logger.info("=========??????end==============");
        logger.info(uri);
        httpPost.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
        CloseableHttpResponse response = null;
        WechatPayAppletCreateOrderResult result = null;
        try {
            String res = null;
            response = httpClient.execute(httpPost);
            logger.info("=========??????????????????==============");
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {

                res = EntityUtils.toString(response.getEntity());
                result = GsonUtil.getGson().fromJson(res, WechatPayAppletCreateOrderResult.class);
                result.setBaseResult(WechatPayResultCode.SUCCESS);
                buildMiniProgramRequest(param, wechatPayConfig, result);
            } else if (202 == statusCode) {
                CloseableHttpResponse response2 = httpClient.execute(httpPost);
                res = EntityUtils.toString(response2.getEntity());
                result = GsonUtil.getGson().fromJson(res, WechatPayAppletCreateOrderResult.class);
                result.setBaseResult(WechatPayResultCode.SUCCESS);
            } else {
                res = EntityUtils.toString(response.getEntity());
                logger.error(res);
                result = GsonUtil.getGson().fromJson(res, WechatPayAppletCreateOrderResult.class);
                result.setBaseResult(WechatPayResultCode.FAIL, res);
            }
            logger.info(res);
            logger.info("=========??????????????????==============");
        } catch (ClientProtocolException e) {
            logger.error(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

    /**
     * ??????????????????????????????????????????????????????
     * @param param
     * @param wechatPayConfig
     * @return
     */
    public static WechatPayOrderInfo queryOrderStatus(WechatPayOrderStatusQueryParam param,
                                                      WechatPayConfig wechatPayConfig) {
        init();
        Set<ConstraintViolation<WechatPayOrderStatusQueryParam>> set = validator.validate(param);
        if (set != null && set.size() > 0) {
            ArrayList<String> validateString = new ArrayList<>();
            for (ConstraintViolation<WechatPayOrderStatusQueryParam> constraintViolation : set) {
                validateString.add(
                    "?????????" + constraintViolation.getPropertyPath().toString() + "-" + constraintViolation.getMessage());
                logger.info("?????????" + constraintViolation.getMessage());
                logger.info("?????????" + constraintViolation.getPropertyPath().toString());
            }
            throw new IllegalArgumentException(StringHelper.join(validateString, ";"));
        }
        String uri = WechatPayConstant.WECHAT_HTTPS + WechatPayConstant.WECHAT_PAY_HOST + QUERY_URI
                     + param.getOutTradeNo() + "?";
        String resTemp[] = { null };
        try {
            URIBuilder uriBuilder = new URIBuilder(uri);
            uriBuilder.setParameter("mchid", param.getMchid());
            RequestClientUtil.build(wechatPayConfig).get(uriBuilder.build(), (response) -> {
                if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                    HttpEntity entity = response.getEntity();
                    try {
                        String res = EntityUtils.toString(entity);
                        resTemp[0] = res;
                    } catch (ParseException | IOException e) {
                        logger.error(e.toString());
                        e.printStackTrace();
                    }
                }
                logger.info("=========??????????????????==============");
                logger.info(resTemp[0]);
                logger.info("=========??????????????????==============");
            });
        } catch (URISyntaxException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        if (null == resTemp[0] || "".equals(resTemp[0])) {
            WechatPayOrderInfo csaobStatusQueryResult = new WechatPayOrderInfo();
            csaobStatusQueryResult.setBaseResult(WechatPayResultCode.FAIL);
            return csaobStatusQueryResult;
        }
        return GsonUtil.getGson().fromJson(resTemp[0], WechatPayOrderInfo.class);
    }

    /**
     * ??????????????????
     * @param param
     * @param wechatPayConfig
     * @return
     */
    public static WechatPayBaseResult closeOrder(WechatPayCloseOrderParam param, WechatPayConfig wechatPayConfig) {
        init();
        Set<ConstraintViolation<WechatPayCloseOrderParam>> set = validator.validate(param);
        if (set != null && set.size() > 0) {
            ArrayList<String> validateString = new ArrayList<>();
            for (ConstraintViolation<WechatPayCloseOrderParam> constraintViolation : set) {
                validateString.add(
                    "?????????" + constraintViolation.getPropertyPath().toString() + "-" + constraintViolation.getMessage());
                logger.info("?????????" + constraintViolation.getMessage());
                logger.info("?????????" + constraintViolation.getPropertyPath().toString());
            }
            throw new IllegalArgumentException(StringHelper.join(validateString, ";"));
        }
        String uri = WechatPayConstant.WECHAT_HTTPS + WechatPayConstant.WECHAT_PAY_HOST + QUERY_URI
                     + param.getOutTradeNo() + "/close";
        RequestClientUtil requestClientUtil = RequestClientUtil.build(wechatPayConfig);
        CloseableHttpClient httpClient = requestClientUtil.getHttpClient();
        HttpPost httpPost = new HttpPost(uri);
        StringEntity entity = new StringEntity(GsonUtil.getGson().toJson(param),
            Charset.forName(WechatPayConstant.DEFAULT_CHARTSET_NAME));
        entity.setContentType(ContentType.APPLICATION_JSON.toString());
        httpPost.setEntity(entity);
        httpPost.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
        CloseableHttpResponse response = null;
        WechatPayBaseResult result = null;
        logger.info("=========???????????????==============");
        logger.info("=========??????:" + param.getClass() + "==============");
        logger.info("=========????????????==============");
        logger.info(GsonUtil.getGson().toJson(param));
        logger.info("=========??????end==============");
        logger.info(uri);
        try {
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode || 204 == statusCode) {
                result = new WechatPayBaseResult(WechatPayResultCode.SUCCESS);
                result.setBaseResult(WechatPayResultCode.SUCCESS);
            } else {
                logger.info("=========??????????????????==============");
                if (null != response.getEntity()) {
                    String res = EntityUtils.toString(response.getEntity());
                    logger.info(res);
                    result = GsonUtil.getGson().fromJson(res, WechatPayBaseResult.class);
                }
                logger.info("=========??????????????????==============");
            }
        } catch (ClientProtocolException e) {
            logger.error(e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            logger.error(e.toString());
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return result;
    }

}
