package com.zhengzhuanglaile.wechatpay.isv.model;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.isv.param.Receivers;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 分账接收方列表
 * 
 * @author dengying.zhang 2022年9月16日 下午2:55:34
 * @since 1.0.0
 */
public class ReceiversResult extends Receivers {

    /**
     * 分账结果
     */
    @SerializedName("result")
    private String result;

    /**
     * 分账失败原因
     */
    @SerializedName("fail_reason")
    private String failReason;

    /**
     * 分账明细单号
     */
    @SerializedName("detail_id")
    private String detailId;

    /**
     * 分账创建时间
     */
    @SerializedName("create_time")
    private String createTime;

    /**
     * 分账完成时间
     */
    @SerializedName("finish_time")
    private String finishTime;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
