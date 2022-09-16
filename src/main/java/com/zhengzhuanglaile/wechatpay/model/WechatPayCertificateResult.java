package com.zhengzhuanglaile.wechatpay.model;

import java.io.Serializable;
import java.util.List;

public class WechatPayCertificateResult implements Serializable {

    /**
     * 
     */
    private static final long               serialVersionUID = 5681237569604320169L;
    private List<WechatPayCertificateModel> data;

    public List<WechatPayCertificateModel> getData() {
        return data;
    }

    public void setData(List<WechatPayCertificateModel> data) {
        this.data = data;
    }

}
