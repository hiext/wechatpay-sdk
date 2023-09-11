package com.zhengzhuanglaile.wechatpay.isv.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.util.GsonUtil;

/**
 * 分账接收方 模型
 * 
 * @author dengying.zhang 2022年9月16日 下午2:44:17
 * @since 1.0.0
 */
public class Receivers {

    /**
     * 分账接收方类型 1、MERCHANT_ID：商户号 2、PERSONAL_OPENID：个人openid（由父商户APPID转换得到） 3、PERSONAL_SUB_OPENID:
     * 个人sub_openid（由子商户APPID转换得到）
     */
    @NotNull
    @SerializedName("type")
    private ReceiversTypeEnum type;

    /**
     * 分账接收方账号 1、分账接收方类型为MERCHANT_ID时，分账接收方账号为商户号 2、分账接收方类型为PERSONAL_OPENID时，分账接收方账号为个人openid
     * 3、分账接收方类型为PERSONAL_SUB_OPENID时，分账接收方账号为个人sub_openid
     */
    @NotNull
    @SerializedName("account")
    private ReceiversAccount  account;

    /**
     * 可选项，在接收方类型为个人的时可选填，若有值，会检查与 name 是否实名匹配，不匹配会拒绝分账请求
     * 1、分账接收方类型是PERSONAL_OPENID或PERSONAL_SUB_OPENID时，是个人姓名的密文（选传，传则校验） 此字段的加密方法详见：敏感信息加密说明 2、使用微信支付平台证书中的公钥
     * 3、使用RSAES-OAEP算法进行加密 4、将请求中HTTP头部的Wechatpay-Serial设置为证书序列号
     */
    @Length(max = 102, min = 1)
    private String            name;

    /**
     * 分账金额
     */
    @NotNull
    @SerializedName("amount")
    private Integer           amount;

    /**
     * 分账描述
     */
    @NotNull
    @Length(min = 1, max = 80)
    @SerializedName("description")
    private String            description;

    /**
     * 分账接收方类型 1、MERCHANT_ID：商户号 2、PERSONAL_OPENID：个人openid（由父商户APPID转换得到） 3、PERSONAL_SUB_OPENID:
     * 个人sub_openid（由子商户APPID转换得到）
     * 
     * @return
     */
    public ReceiversTypeEnum getType() {
        return type;
    }

    /**
     * 分账接收方类型 1、MERCHANT_ID：商户号 2、PERSONAL_OPENID：个人openid（由父商户APPID转换得到） 3、PERSONAL_SUB_OPENID:
     * 个人sub_openid（由子商户APPID转换得到）
     * 
     * @param type
     */
    public void setType(ReceiversTypeEnum type) {
        this.type = type;
    }

    /**
     * 分账接收方账号 1、分账接收方类型为MERCHANT_ID时，分账接收方账号为商户号 2、分账接收方类型为PERSONAL_OPENID时，分账接收方账号为个人openid
     * 3、分账接收方类型为PERSONAL_SUB_OPENID时，分账接收方账号为个人sub_openid
     *
     * @return
     */
    public ReceiversAccount getAccount() {
        return account;
    }

    /**
     * 分账接收方账号 1、分账接收方类型为MERCHANT_ID时，分账接收方账号为商户号 2、分账接收方类型为PERSONAL_OPENID时，分账接收方账号为个人openid
     * 3、分账接收方类型为PERSONAL_SUB_OPENID时，分账接收方账号为个人sub_openid
     * 
     * @param account
     */
    public void setAccount(ReceiversAccount account) {
        this.account = account;
    }

    /**
     * 可选项，在接收方类型为个人的时可选填，若有值，会检查与 name 是否实名匹配，不匹配会拒绝分账请求
     * 1、分账接收方类型是PERSONAL_OPENID或PERSONAL_SUB_OPENID时，是个人姓名的密文（选传，传则校验） 此字段的加密方法详见：敏感信息加密说明 2、使用微信支付平台证书中的公钥
     * 3、使用RSAES-OAEP算法进行加密 4、将请求中HTTP头部的Wechatpay-Serial设置为证书序列号
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 可选项，在接收方类型为个人的时可选填，若有值，会检查与 name 是否实名匹配，不匹配会拒绝分账请求
     * 1、分账接收方类型是PERSONAL_OPENID或PERSONAL_SUB_OPENID时，是个人姓名的密文（选传，传则校验） 此字段的加密方法详见：敏感信息加密说明 2、使用微信支付平台证书中的公钥
     * 3、使用RSAES-OAEP算法进行加密 4、将请求中HTTP头部的Wechatpay-Serial设置为证书序列号
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 分账金额
     * 
     * @return
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 分账金额
     * 
     * @param amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 分账描述
     * 
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 分账描述
     * 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return GsonUtil.getGson().toJson(this);
    }

}
