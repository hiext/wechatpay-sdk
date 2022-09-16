package com.zhengzhuanglaile.wechatpay.isv;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.zhengzhuanglaile.wechatpay.WechatPayConstant;
import com.zhengzhuanglaile.wechatpay.base.BaseCloseOrderParam;
import com.zhengzhuanglaile.wechatpay.isv.model.WechatPayIsvOrderInfo;
import com.zhengzhuanglaile.wechatpay.isv.nativepay.param.NativePayRefundParam;
import com.zhengzhuanglaile.wechatpay.isv.nativepay.param.WechatPayIsvNativePayCreateOrderParam;
import com.zhengzhuanglaile.wechatpay.isv.param.IsvPayOrderStatusQueryParam;
import com.zhengzhuanglaile.wechatpay.model.WechatPayConfig;
import com.zhengzhuanglaile.wechatpay.model.WechatPayResultCode;
import com.zhengzhuanglaile.wechatpay.result.NativePayResult;
import com.zhengzhuanglaile.wechatpay.result.WechatPayBaseResult;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;
import com.zhengzhuanglaile.wechatpay.util.RequestClientUtil;
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

/**
 * 微信支付c扫bAPI
 * @author dengying.zhang 2022年8月18日 下午3:28:29
 * @since 1.0.0
 */
public class WechatPayIsvNativePayApi {

    private static Logger       logger     = LoggerFactory.getLogger(WechatPayIsvNativePayApi.class);
    private static final String NATIVE_URI = "/v3/pay/partner/transactions/native";

    private static final String QUERY_URI  = "/v3/pay/partner/transactions/out-trade-no/";

    private static final String REFUND_URI = "/v3/refund/domestic/refunds";
    private static Validator    validator  = null;

    protected static void init() {
        // 初始化校验器
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    /**
     * 微信支付创建支付订单
     * @param param
     * @param wechatPayConfig
     * @return
     */
    public static NativePayResult createOrder(WechatPayIsvNativePayCreateOrderParam param,
                                              WechatPayConfig wechatPayConfig) {
        init();
        Set<ConstraintViolation<WechatPayIsvNativePayCreateOrderParam>> set = validator.validate(param);
        if (set != null && set.size() > 0) {
            ArrayList<String> validateString = new ArrayList<>();
            for (ConstraintViolation<WechatPayIsvNativePayCreateOrderParam> constraintViolation : set) {
                validateString.add(
                    "字段：" + constraintViolation.getPropertyPath().toString() + "-" + constraintViolation.getMessage());
                logger.info("错误：" + constraintViolation.getMessage());
                logger.info("字段：" + constraintViolation.getPropertyPath().toString());
            }
            throw new IllegalArgumentException(StringHelper.join(validateString, ";"));
        }

        // 如果订单创建没有设置过期时间，设置默认24小时后关闭订单
        if (null == param.getTimeExpire() || "".equals(param.getTimeExpire())) {
            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).plusDays(1L);
            param.setTimeExpire(zonedDateTime
                .format(DateTimeFormatter.ofPattern(WechatPayConstant.WECHAT_PAY_DATE_FORMAT, Locale.CHINA)));
        }

        RequestClientUtil requestClientUtil = RequestClientUtil.build(wechatPayConfig);
        CloseableHttpClient httpClient = requestClientUtil.getHttpClient();
        String uri = WechatPayConstant.WECHAT_HTTPS + WechatPayConstant.WECHAT_PAY_HOST + NATIVE_URI;
        logger.info("=========初始化参数==============");
        HttpPost httpPost = new HttpPost(uri);
        StringEntity entity = new StringEntity(GsonUtil.getGson().toJson(param),
            Charset.forName(WechatPayConstant.DEFAULT_CHARTSET_NAME));
        entity.setContentType(ContentType.APPLICATION_JSON.toString());
        logger.info("=========参数:" + WechatPayIsvNativePayCreateOrderParam.class + "==============");
        httpPost.setEntity(entity);
        logger.info("=========入参开始==============");
        logger.info(GsonUtil.getGson().toJson(param));
        logger.info("=========入参end==============");
        logger.info(uri);
        httpPost.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
        CloseableHttpResponse response = null;
        NativePayResult result = null;
        try {
            String res = null;
            response = httpClient.execute(httpPost);
            logger.info("=========返回数据开始==============");
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {

                res = EntityUtils.toString(response.getEntity());
                result = GsonUtil.getGson().fromJson(res, NativePayResult.class);
                result.setBaseResult(WechatPayResultCode.SUCCESS);
            } else if (202 == statusCode) {
                CloseableHttpResponse response2 = httpClient.execute(httpPost);
                res = EntityUtils.toString(response2.getEntity());
                result = GsonUtil.getGson().fromJson(res, NativePayResult.class);
                result.setBaseResult(WechatPayResultCode.SUCCESS);
            } else {
                res = EntityUtils.toString(response.getEntity());
                logger.error(res);
                result = GsonUtil.getGson().fromJson(res, NativePayResult.class);
                result.setBaseResult(WechatPayResultCode.FAIL, res);
            }
            logger.info(res);
            logger.info("=========返回数据结束==============");
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
     * 根据商户的订单号查询微信支付订单状态
     * @param param
     * @param wechatPayConfig
     * @return
     */
    public static WechatPayIsvOrderInfo queryOrderStatus(IsvPayOrderStatusQueryParam param,
                                                         WechatPayConfig wechatPayConfig) {
        init();
        Set<ConstraintViolation<IsvPayOrderStatusQueryParam>> set = validator.validate(param);
        if (set != null && set.size() > 0) {
            ArrayList<String> validateString = new ArrayList<>();
            for (ConstraintViolation<IsvPayOrderStatusQueryParam> constraintViolation : set) {
                validateString.add(
                    "字段：" + constraintViolation.getPropertyPath().toString() + "-" + constraintViolation.getMessage());
                logger.info("错误：" + constraintViolation.getMessage());
                logger.info("字段：" + constraintViolation.getPropertyPath().toString());
            }
            throw new IllegalArgumentException(StringHelper.join(validateString, ";"));
        }
        String uri = WechatPayConstant.WECHAT_HTTPS + WechatPayConstant.WECHAT_PAY_HOST + QUERY_URI
                     + param.getOutTradeNo() + "?";
        String resTemp[] = { null };
        try {
            URIBuilder uriBuilder = new URIBuilder(uri);
            uriBuilder.setParameter("sp_mchid", param.getSpMchid());
            uriBuilder.setParameter("sub_mchid", param.getSubMchid());
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
                logger.info("=========返回数据开始==============");
                logger.info(resTemp[0]);
                logger.info("=========返回数据结束==============");
            });
        } catch (URISyntaxException e) {
            logger.error(e.toString());
            e.printStackTrace();
        }
        if (null == resTemp[0] || "".equals(resTemp[0])) {
            WechatPayIsvOrderInfo csaobStatusQueryResult = new WechatPayIsvOrderInfo();
            csaobStatusQueryResult.setBaseResult(WechatPayResultCode.FAIL);
            return csaobStatusQueryResult;
        }
        return GsonUtil.getGson().fromJson(resTemp[0], WechatPayIsvOrderInfo.class);
    }

    /**
     * 关闭微信订单
     * @param param
     * @param wechatPayConfig
     * @return
     */
    public static WechatPayBaseResult closeOrder(BaseCloseOrderParam param, WechatPayConfig wechatPayConfig) {
        init();
        Set<ConstraintViolation<BaseCloseOrderParam>> set = validator.validate(param);
        if (set != null && set.size() > 0) {
            ArrayList<String> validateString = new ArrayList<>();
            for (ConstraintViolation<BaseCloseOrderParam> constraintViolation : set) {
                validateString.add(
                    "字段：" + constraintViolation.getPropertyPath().toString() + "-" + constraintViolation.getMessage());
                logger.info("错误：" + constraintViolation.getMessage());
                logger.info("字段：" + constraintViolation.getPropertyPath().toString());
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
        logger.info("=========初始化参数==============");
        logger.info("=========参数:" + param.getClass() + "==============");
        logger.info("=========入参开始==============");
        logger.info(GsonUtil.getGson().toJson(param));
        logger.info("=========入参end==============");
        logger.info(uri);
        try {
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode || 204 == statusCode) {
                result = new WechatPayBaseResult(WechatPayResultCode.SUCCESS);
            } else {
                logger.info("=========返回数据开始==============");
                if (null != response.getEntity()) {
                    String res = EntityUtils.toString(response.getEntity());
                    logger.info(res);
                    result = GsonUtil.getGson().fromJson(res, WechatPayBaseResult.class);
                }
                logger.info("=========返回数据结束==============");
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

    public static WechatPayBaseResult refund(NativePayRefundParam param, WechatPayConfig wechatPayConfig) {

        init();
        Set<ConstraintViolation<NativePayRefundParam>> set = validator.validate(param);
        if (set != null && set.size() > 0) {
            ArrayList<String> validateString = new ArrayList<>();
            for (ConstraintViolation<NativePayRefundParam> constraintViolation : set) {
                validateString.add(
                    "字段：" + constraintViolation.getPropertyPath().toString() + "-" + constraintViolation.getMessage());
                logger.info("错误：" + constraintViolation.getMessage());
                logger.info("字段：" + constraintViolation.getPropertyPath().toString());
            }
            throw new IllegalArgumentException(StringHelper.join(validateString, ";"));
        }

        RequestClientUtil requestClientUtil = RequestClientUtil.build(wechatPayConfig);
        CloseableHttpClient httpClient = requestClientUtil.getHttpClient();
        String uri = WechatPayConstant.WECHAT_HTTPS + WechatPayConstant.WECHAT_PAY_HOST + REFUND_URI;
        logger.info("=========初始化参数==============");
        HttpPost httpPost = new HttpPost(uri);
        StringEntity entity = new StringEntity(GsonUtil.getGson().toJson(param),
            Charset.forName(WechatPayConstant.DEFAULT_CHARTSET_NAME));
        entity.setContentType(ContentType.APPLICATION_JSON.toString());
        logger.info("=========参数:" + WechatPayIsvNativePayCreateOrderParam.class + "==============");
        httpPost.setEntity(entity);
        logger.info("=========入参开始==============");
        logger.info(GsonUtil.getGson().toJson(param));
        logger.info("=========入参end==============");
        logger.info(uri);
        httpPost.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.toString());
        CloseableHttpResponse response = null;
        NativePayResult result = null;
        try {
            String res = null;
            response = httpClient.execute(httpPost);
            logger.info("=========返回数据开始==============");
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {

                res = EntityUtils.toString(response.getEntity());
                logger.info(res);
                result = GsonUtil.getGson().fromJson(res, NativePayResult.class);
                result.setBaseResult(WechatPayResultCode.SUCCESS);
            } else if (202 == statusCode) {
                CloseableHttpResponse response2 = httpClient.execute(httpPost);
                res = EntityUtils.toString(response2.getEntity());
                result = GsonUtil.getGson().fromJson(res, NativePayResult.class);
                result.setBaseResult(WechatPayResultCode.SUCCESS);
            } else {
                res = EntityUtils.toString(response.getEntity());
                logger.error(res);
                result = GsonUtil.getGson().fromJson(res, NativePayResult.class);
                result.setBaseResult(WechatPayResultCode.FAIL, res);
            }
            logger.info(res);
            logger.info("=========返回数据结束==============");
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
