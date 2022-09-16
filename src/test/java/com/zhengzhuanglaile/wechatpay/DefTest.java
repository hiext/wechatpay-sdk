package com.zhengzhuanglaile.wechatpay;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DefTest {

    public static void main(String[] args) {
        Long tims = ZonedDateTime.now(ZoneId.of("Asia/Shanghai")).toEpochSecond();
        System.out.println(tims);
    }

}
