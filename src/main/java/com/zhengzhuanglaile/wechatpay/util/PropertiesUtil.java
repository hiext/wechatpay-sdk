package com.zhengzhuanglaile.wechatpay.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {

    private Logger     logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private Properties properties;

    public PropertiesUtil(String fileName){

        if (fileName == null || "".equals(fileName)) {
            return;
        }
        properties = new Properties();
        try {
            properties.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName)));
        } catch (IOException e) {
            logger.error("配置文件读取异常：", e);
            return;
        }
    }

    /**
     *  通过properties的key获取value
     * @param key
     * @return
     */
    public String getProperty(String key) {
        String value = properties.getProperty(key.trim());
        if (key == null || "".equals(key)) {
            return null;
        }
        return value.trim();
    }

    /**
     * 通过properties的key获取value 可传递默认值
     */
    public String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key);
        if (value == null || "".equals(value)) {
            return defaultValue;
        }
        return value;
    }
}
