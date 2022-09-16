package com.zhengzhuanglaile.wechatpay.result;

import com.zhengzhuanglaile.wechatpay.model.WechatPayResultCode;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

public class WechatPayBaseResult {

    private String code;

    /**
     * 平台错误信息
     */
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMesssage() {
        return message;
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public WechatPayBaseResult(){
        super();
    }

    public WechatPayBaseResult(WechatPayResultCode err){
        super();
        this.code = err.getValue();
        this.message = err.getName();
    }

    public WechatPayBaseResult(WechatPayResultCode err, String errMsg){
        super();
        this.code = err.getValue();
        this.message = errMsg;
    }

    public void setBaseResult(WechatPayResultCode err) {
        this.code = err.getValue();
        this.message = err.getName();
    }

    public void setBaseResult(WechatPayResultCode err, String errMsg) {
        this.code = err.getValue();
        this.message = errMsg;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
