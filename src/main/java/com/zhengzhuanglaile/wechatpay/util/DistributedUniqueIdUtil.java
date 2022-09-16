package com.zhengzhuanglaile.wechatpay.util;

public class DistributedUniqueIdUtil {

    private static volatile SnowFlake               snowFlake               = null;

    private static volatile DistributedUniqueIdUtil distributedUniqueIdUtil = null;

    private DistributedUniqueIdUtil(){
        DistributedUniqueIdUtil.snowFlake = new SnowFlake(2, 3);
    }

    public static long getNextId() {
        if (distributedUniqueIdUtil == null) {
            synchronized (DistributedUniqueIdUtil.class) {
                // 第二次校验singleton是否为空
                if (distributedUniqueIdUtil == null) {
                    distributedUniqueIdUtil = new DistributedUniqueIdUtil();
                }
            }
        }
        return snowFlake.nextId();
    }
}
