package com.zhengzhuanglaile.wechatpay.isv;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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

import com.zhengzhuanglaile.wechatpay.WechatPayConstant;
import com.zhengzhuanglaile.wechatpay.isv.nativepay.param.WechatPayIsvNativePayCreateOrderParam;
import com.zhengzhuanglaile.wechatpay.isv.param.IsvPayRefundParam;
import com.zhengzhuanglaile.wechatpay.mch.model.Refund;
import com.zhengzhuanglaile.wechatpay.mch.param.RefundQueryParam;
import com.zhengzhuanglaile.wechatpay.model.WechatPayConfig;
import com.zhengzhuanglaile.wechatpay.model.WechatPayResultCode;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;
import com.zhengzhuanglaile.wechatpay.util.RequestClientUtil;

/**
 * 服务商退款接口
 *
 * @author dengying.zhang
 */
public class IsvRefundApi {

    private static Logger       logger           = LoggerFactory.getLogger(IsvRefundApi.class);
    private static final String REFUND_URI       = "/v3/refund/domestic/refunds";

    private static final String REFUND_QUERY_URI = "/v3/refund/domestic/refunds/";
    private static Validator    validator        = null;

    protected static void init() {
        // 初始化校验器
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public static Refund createRefunds(IsvPayRefundParam param, WechatPayConfig wechatPayConfig) {

        init();
        Set<ConstraintViolation<IsvPayRefundParam>> set = validator.validate(param);
        if (set != null && set.size() > 0) {
            ArrayList<String> validateString = new ArrayList<>();
            for (ConstraintViolation<IsvPayRefundParam> constraintViolation : set) {
                validateString.add("字段：" + constraintViolation.getPropertyPath().toString() + "-"
                                   + constraintViolation.getMessage());
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
        Refund result = null;
        try {
            String res = null;
            response = httpClient.execute(httpPost);
            logger.info("=========返回数据开始==============");
            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK == statusCode) {

                res = EntityUtils.toString(response.getEntity());
                logger.info(res);
                result = GsonUtil.getGson().fromJson(res, Refund.class);
                result.setBaseResult(WechatPayResultCode.SUCCESS);
            } else if (HttpStatus.SC_ACCEPTED == statusCode) {
                CloseableHttpResponse response2 = httpClient.execute(httpPost);
                res = EntityUtils.toString(response2.getEntity());
                result = GsonUtil.getGson().fromJson(res, Refund.class);
                result.setBaseResult(WechatPayResultCode.SUCCESS);
            } else {
                res = EntityUtils.toString(response.getEntity());
                logger.error(res);
                result = GsonUtil.getGson().fromJson(res, Refund.class);
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
     * 查询单笔退款（通过商户退款单号）
     * @param param
     * @param wechatPayConfig
     * @return
     */
    public static Refund queryByOutRefundNo(RefundQueryParam param, WechatPayConfig wechatPayConfig) {
        init();
        Set<ConstraintViolation<RefundQueryParam>> set = validator.validate(param);
        if (set != null && set.size() > 0) {
            ArrayList<String> validateString = new ArrayList<>();
            for (ConstraintViolation<RefundQueryParam> constraintViolation : set) {
                validateString.add("字段：" + constraintViolation.getPropertyPath().toString() + "-"
                                   + constraintViolation.getMessage());
                logger.info("错误：" + constraintViolation.getMessage());
                logger.info("字段：" + constraintViolation.getPropertyPath().toString());
            }
            throw new IllegalArgumentException(StringHelper.join(validateString, ";"));
        }
        String uri = WechatPayConstant.WECHAT_HTTPS + WechatPayConstant.WECHAT_PAY_HOST + REFUND_QUERY_URI
                     + param.getOutRefundNo() + "?";
        String resTemp[] = { null };
        try {
            URIBuilder uriBuilder = new URIBuilder(uri);
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
            Refund refund = new Refund();
            refund.setBaseResult(WechatPayResultCode.FAIL);
            return refund;
        }
        return GsonUtil.getGson().fromJson(resTemp[0], Refund.class);
    }
}
