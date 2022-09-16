package com.zhengzhuanglaile.wechatpay.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信支付返回错误码
 * 
 * @author dengying.zhang 2022年8月17日 下午3:39:09
 * @since 1.0.0
 */
public enum WechatPayResultCode {

                                 /**
                                  * 成功
                                  */
                                 SUCCESS("成功", "SUCCESS"),
                                 /**
                                  * 失败
                                  */
                                 FAIL("失败", "fail");

    private String                                  name;
    private String                                  value;

    private static Map<String, WechatPayResultCode> valueMap = new HashMap<String, WechatPayResultCode>();
    private static Map<String, WechatPayResultCode> nameMap  = new HashMap<String, WechatPayResultCode>();

    static {
        for (WechatPayResultCode status : WechatPayResultCode.values()) {
            valueMap.put(status.getValue(), status);
            nameMap.put(status.getName(), status);
        }
    }

    private WechatPayResultCode(String name, String value){
        this.name = name;
        this.value = value;
    }

    public static String getNameByValue(String value) {
        if (valueMap.get(value) == null) {
            return null;
        }
        return valueMap.get(value).name;
    }

    public static WechatPayResultCode getByValue(String value) {
        return valueMap.get(value);
    }

    public static WechatPayResultCode getByName(String name) {
        return nameMap.get(name);
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
