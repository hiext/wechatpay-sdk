package com.zhengzhuanglaile.wechatpay.isv.nativepay.param;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.google.gson.annotations.SerializedName;
import com.zhengzhuanglaile.wechatpay.base.BaseCreateOrderParam;

/**
 * 微信Native下单参数 <div class="table-wrp">
 * <table class="table">
 * <thead>
 * <tr>
 * <th >参数名</th>
 * <th>变量</th>
 * <th >类型[长度限制]</th>
 * <th >必填</th>
 * <th>描述</th>
 * </tr>
 * </thead> <tbody>
 * <tr>
 * <td>服务商应用ID</td>
 * <td>sp_appid</td>
 * <td>string[1,32]</td>
 * <td>是</td>
 * <td><span class="label-tips label-blue">body</span> 服务商申请的公众号appid。 <br>
 * <span class="green">示例值：wx8888888888888888 </span></td>
 * </tr>
 * <tr>
 * <td>服务商户号</td>
 * <td>sp_mchid</td>
 * <td>string[1,32]</td>
 * <td>是</td>
 * <td><span class="label-tips label-blue">body</span> 服务商户号，由微信支付生成并下发 <br>
 * <span class="green">示例值：1230000109 </span></td>
 * </tr>
 * <tr>
 * <td>子商户应用ID</td>
 * <td>sub_appid</td>
 * <td>string[1,32]</td>
 * <td>否</td>
 * <td><span class="label-tips label-blue">body</span> 子商户申请的公众号appid。 <br>
 * <span class="green">示例值：wxd678efh567hg6999 </span></td>
 * </tr>
 * <tr>
 * <td>子商户号</td>
 * <td>sub_mchid</td>
 * <td>string[1,32]</td>
 * <td>是</td>
 * <td><span class="label-tips label-blue">body</span> 子商户的商户号，由微信支付生成并下发。 <br>
 * <span class="green">示例值：1900000109 </span></td>
 * </tr>
 * <tr>
 * <td>商品描述</td>
 * <td>description</td>
 * <td>string[1,127]</td>
 * <td>是</td>
 * <td><span class="label-tips label-blue">body</span> 商品描述 <br>
 * <span class="green">示例值：Image形象店-深圳腾大-QQ公仔 </span></td>
 * </tr>
 * <tr>
 * <td>商户订单号</td>
 * <td>out_trade_no</td>
 * <td>string[6,32]</td>
 * <td>是</td>
 * <td><span class="label-tips label-blue">body</span> 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。 <br>
 * <span class="green">示例值：1217752501201407033233368018 </span></td>
 * </tr>
 * <tr>
 * <td>交易结束时间</td>
 * <td>time_expire</td>
 * <td>string[1,64]</td>
 * <td>否</td>
 * <td><span class="label-tips label-blue">body</span> 订单失效时间，遵循<a href=
 * "https://datatracker.ietf.org/doc/html/rfc3339">rfc3339</a>标准格式，格式为yyyy-MM-DDTHH:mm:ss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss表示时分秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC8小时，即北京时间）。例如：2015-05-20T13:29:35+08:00表示，北京时间2015年5月20日&nbsp;13点29分35秒。
 * <br>
 * <span class="green">示例值：2018-06-08T10:34:56+08:00 </span></td>
 * </tr>
 * <tr>
 * <td>附加数据</td>
 * <td>attach</td>
 * <td>string[1,128]</td>
 * <td>否</td>
 * <td><span class="label-tips label-blue">body</span> 附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用，实际情况下只有支付完成状态才会返回该字段。 <br>
 * <span class="green">示例值：自定义数据 </span>&nbsp;</td>
 * </tr>
 * <tr>
 * <td>通知地址</td>
 * <td>notify_url</td>
 * <td>string[1,256]</td>
 * <td>是</td>
 * <td><span class="label-tips label-blue">body</span> 通知URL必须为直接可访问的URL，不允许携带查询串，要求必须为https地址。 <br>
 * 格式：URL <br>
 * <span class="green">示例值：https://www.weixin.qq.com/wxpay/pay.php </span></td>
 * </tr>
 * <tr>
 * <td>订单优惠标记</td>
 * <td>goods_tag</td>
 * <td>string[1,32]</td>
 * <td>否</td>
 * <td><span class="label-tips label-blue">body</span> 订单优惠标记 <br>
 * <span class="green">示例值：WXG </span></td>
 * </tr>
 * <tr class="object" tabindex="7">
 * <td><span class="extend">+</span> 结算信息</td>
 * <td>settle_info</td>
 * <td>object</td>
 * <td>否</td>
 * <td><span class="label-tips label-blue">body</span> 结算信息</td>
 * </tr>
 * <tr>
 * <td class="object-sub object-sub7 hide" colspan="5">
 * <table class="table">
 * <thead>
 * <tr>
 * <th >参数名</th>
 * <th>变量</th>
 * <th >类型[长度限制]</th>
 * <th >必填</th>
 * <th>描述</th>
 * </tr>
 * </thead> <tbody>
 * <tr>
 * <td>是否指定分账</td>
 * <td>profit_sharing</td>
 * <td>boolean</td>
 * <td>否</td>
 * <td>是否指定分账，枚举值 <br>
 * true：是 <br>
 * false：否 <br>
 * <span class="green">示例值：true </span></td>
 * </tr>
 * <!--
 * <tr >
 * <td >补差金额</td>
 * <td >subsidy_amount</td>
 * <td >int64</td>
 * <td >否</td>
 * <td >SettleInfo.profit_sharing为true时，该金额才生效。 <br>
 * <span class="red">注意：单笔订单最高补差金额为5000元 </span><br>
 * <span class="green">示例值：10 </span></td>
 * </tr>
 * --> </tbody>
 * </table>
 * </td>
 * </tr>
 * <tr class="object" tabindex="1">
 * <td><span class="extend">+</span> 订单金额</td>
 * <td>amount</td>
 * <td>object</td>
 * <td>是</td>
 * <td><span class="label-tips label-blue">body</span> 订单金额信息</td>
 * </tr>
 * <tr>
 * <td class="object-sub object-sub1 hide" colspan="5">
 * <table class="table">
 * <thead>
 * <tr>
 * <th >参数名</th>
 * <th>变量</th>
 * <th >类型[长度限制]</th>
 * <th >必填</th>
 * <th>描述</th>
 * </tr>
 * </thead> <tbody>
 * <tr>
 * <td>总金额</td>
 * <td>total</td>
 * <td>int</td>
 * <td>是</td>
 * <td>订单总金额，单位为分。 <br>
 * <span class="green">示例值：100 </span></td>
 * </tr>
 * <tr>
 * <td>货币类型</td>
 * <td>currency</td>
 * <td>string[1,16]</td>
 * <td>否</td>
 * <td>CNY：人民币，境内商户号仅支持人民币。 <br>
 * <span class="green">示例值：CNY </span></td>
 * </tr>
 * </tbody>
 * </table>
 * </td>
 * </tr>
 * <!--
 * <tr class="object" tabindex="6">
 * <td><span class="extend">+</span> 支付者</td>
 * <td >payer</td>
 * <td >object</td>
 * <td >是</td>
 * <td ><span class="label-tips label-blue">body</span> 支付者信息</td>
 * </tr>
 * <tr>
 * <td class="object-sub object-sub6 hide" colspan="5">
 * <table class="table">
 * <thead>
 * <tr>
 * <th >参数名</th>
 * <th>变量</th>
 * <th >类型[长度限制]</th>
 * <th >必填</th>
 * <th>描述</th>
 * </tr>
 * </thead> <tbody>
 * <tr >
 * <td >用户服务标识</td>
 * <td >sp_openid</td>
 * <td >string[1,128]</td>
 * <td >是</td>
 * <td >用户在服务商appid下的唯一标识。 <br>
 * <span class="green">示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o </span></td>
 * </tr>
 * <tr >
 * <td >用户子标识</td>
 * <td >sub_openid</td>
 * <td >string[1,128]</td>
 * <td >否</td>
 * <td >用户在子商户appid下的唯一标识。 <br>
 * <span class="green">示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o </span></td>
 * </tr>
 * </tbody>
 * </table>
 * </td>
 * </tr>
 * -->
 * <tr class="object" tabindex="2">
 * <td><span class="extend">+</span> 优惠功能</td>
 * <td>detail</td>
 * <td>object</td>
 * <td>否</td>
 * <td><span class="label-tips label-blue">body</span> 优惠功能</td>
 * </tr>
 * <tr>
 * <td class="object-sub object-sub2 hide" colspan="5">
 * <table class="table">
 * <thead>
 * <tr>
 * <th >参数名</th>
 * <th>变量</th>
 * <th >类型[长度限制]</th>
 * <th >必填</th>
 * <th>描述</th>
 * </tr>
 * </thead> <tbody>
 * <tr>
 * <td>订单原价</td>
 * <td>cost_price</td>
 * <td>int</td>
 * <td>否</td>
 * <td>1、商户侧一张小票订单可能被分多次支付，订单原价用于记录整张小票的交易金额。 <br>
 * 2、当订单原价与支付金额不相等，则不享受优惠。 <br>
 * 3、该字段主要用于防止同一张小票分多次支付，以享受多次优惠的情况，正常支付订单不必上传此参数。 <br>
 * <span class="green">示例值：608800 </span></td>
 * </tr>
 * <tr>
 * <td>商品小票ID</td>
 * <td>invoice_id</td>
 * <td>string[1,32]</td>
 * <td>否</td>
 * <td>商家小票ID <br>
 * <span class="green">示例值：微信123 </span></td>
 * </tr>
 * <tr class="object" tabindex="3">
 * <td><span class="extend">+</span> 单品列表</td>
 * <td>goods_detail</td>
 * <td>array</td>
 * <td>否</td>
 * <td>单品列表信息 <br>
 * 条目个数限制：【1，6000】</td>
 * </tr>
 * <tr>
 * <td class="object-sub object-sub3 hide" colspan="5">
 * <table class="table">
 * <thead>
 * <tr>
 * <th >参数名</th>
 * <th>变量</th>
 * <th >类型[长度限制]</th>
 * <th >必填</th>
 * <th>描述</th>
 * </tr>
 * </thead> <tbody>
 * <tr>
 * <td>商户侧商品编码</td>
 * <td>merchant_goods_id</td>
 * <td>string[1,32]</td>
 * <td>是</td>
 * <td>由半角的大小写字母、数字、中划线、下划线中的一种或几种组成。 <br>
 * <span class="green">示例值：1246464644 </span></td>
 * </tr>
 * <tr>
 * <td>微信支付商品编码</td>
 * <td>wechatpay_goods_id</td>
 * <td>string[1,32]</td>
 * <td>否</td>
 * <td>微信支付定义的统一商品编号（没有可不传） <br>
 * <span class="green">示例值：1001 </span></td>
 * </tr>
 * <tr>
 * <td>商品名称</td>
 * <td>goods_name</td>
 * <td>string[1,256]</td>
 * <td>否</td>
 * <td>商品的实际名称 <br>
 * <span class="green">示例值：iPhoneX 256G </span></td>
 * </tr>
 * <tr>
 * <td>商品数量</td>
 * <td>quantity</td>
 * <td>int</td>
 * <td>是</td>
 * <td>用户购买的数量 <br>
 * <span class="green">示例值：1</span></td>
 * </tr>
 * <tr>
 * <td>商品单价</td>
 * <td>unit_price</td>
 * <td>int</td>
 * <td>是</td>
 * <td>商品单价，单位为分 <br>
 * <span class="green">示例值：828800 </span></td>
 * </tr>
 * </tbody>
 * </table>
 * </td>
 * </tr>
 * </tbody>
 * </table>
 * </td>
 * </tr>
 * <tr class="object" tabindex="4">
 * <td><span class="extend">+</span> 场景信息</td>
 * <td>scene_info</td>
 * <td>object</td>
 * <td>否</td>
 * <td><span class="label-tips label-blue">body</span> 支付场景描述</td>
 * </tr>
 * <tr>
 * <td class="object-sub object-sub4 hide" colspan="5">
 * <table class="table">
 * <thead>
 * <tr>
 * <th >参数名</th>
 * <th>变量</th>
 * <th >类型[长度限制]</th>
 * <th >必填</th>
 * <th>描述</th>
 * </tr>
 * </thead> <tbody>
 * <tr>
 * <td>用户终端IP</td>
 * <td>payer_client_ip</td>
 * <td>string[1,45]</td>
 * <td>是</td>
 * <td>用户的客户端IP，支持IPv4和IPv6两种格式的IP地址。 <br>
 * <span class="green">示例值：14.23.150.211 </span></td>
 * </tr>
 * <tr>
 * <td>商户端设备号</td>
 * <td>device_id</td>
 * <td>string[1,32]</td>
 * <td>否</td>
 * <td>商户端设备号（门店号或收银设备ID）。 <br>
 * <span class="green">示例值：013467007045764 </span></td>
 * </tr>
 * <tr class="object" tabindex="5">
 * <td><span class="extend">+</span> 商户门店信息</td>
 * <td>store_info</td>
 * <td>object</td>
 * <td>否</td>
 * <td>商户门店信息</td>
 * </tr>
 * <tr>
 * <td class="object-sub object-sub5 hide" colspan="5">
 * <table class="table">
 * <thead>
 * <tr>
 * <th >参数名</th>
 * <th>变量</th>
 * <th >类型[长度限制]</th>
 * <th >必填</th>
 * <th>描述</th>
 * </tr>
 * </thead> <tbody>
 * <tr>
 * <td>门店编号</td>
 * <td>id</td>
 * <td>string[1,32]</td>
 * <td>是</td>
 * <td>商户侧门店编号 <br>
 * <span class="green">示例值：0001 </span></td>
 * </tr>
 * <tr>
 * <td>门店名称</td>
 * <td>name</td>
 * <td>string[1,256]</td>
 * <td>否</td>
 * <td>商户侧门店名称 <br>
 * <span class="green">示例值：腾讯大厦分店 </span></td>
 * </tr>
 * <tr>
 * <td>地区编码</td>
 * <td>area_code</td>
 * <td>string[1,32]</td>
 * <td>否</td>
 * <td>地区编码，详细请见<a href="/wiki/doc/apiv3_partner/terms_definition/chapter1_1_3.shtml#part-5">省市区编号对照表</a>。 <br>
 * <span class="green">示例值：440305 </span></td>
 * </tr>
 * <tr>
 * <td>详细地址</td>
 * <td>address</td>
 * <td>string[1,512]</td>
 * <td>否</td>
 * <td>详细的商户门店地址 <br>
 * <span class="green">示例值：广东省深圳市南山区科技中一道10000号 </span></td>
 * </tr>
 * </tbody>
 * </table>
 * </td>
 * </tr>
 * </tbody>
 * </table>
 * </td>
 * </tr>
 * </tbody>
 * </table>
 * </div>
 * 
 * @author dengying.zhang 2022年8月18日 下午2:35:21
 * @since 1.0.0
 */
public class WechatPayIsvNativePayCreateOrderParam extends BaseCreateOrderParam {

    /**
     * 服务商应用ID sp_appid string[1,32] 是 body 服务商申请的公众号appid。示例值：wx8888888888888888
     */
    @NotNull(message = "sp_appid 服务商应用ID不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("sp_appid")
    private String spAppid;
    /**
     * 服务商户号 sp_mchid string[1,32] 是 body 服务商户号，由微信支付生成并下发 示例值：1230000109
     */
    @NotNull(message = "sp_mchid 服务商户号不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("sp_mchid")
    private String spMchid;

    /**
     * 子商户应用ID sub_appid string[1,32] 否 body 子商户申请的公众号appid。 示例值：wxd678efh567hg6999
     */
    @Length(min = 1, max = 32)
    @SerializedName("sub_appid")
    private String subAppid;

    /**
     * 子商户号 sub_mchid string[1,32] 是 body 子商户的商户号，由微信支付生成并下发。 示例值：1900000109
     */
    @NotNull(message = "sub_mchid 不能为空")
    @Length(min = 1, max = 32)
    @SerializedName("sub_mchid")
    private String subMchid;

    public String getSpAppid() {
        return spAppid;
    }

    public void setSpAppid(String spAppid) {
        this.spAppid = spAppid;
    }

    public String getSpMchid() {
        return spMchid;
    }

    public void setSpMchid(String spMchid) {
        this.spMchid = spMchid;
    }

    public String getSubAppid() {
        return subAppid;
    }

    public void setSubAppid(String subAppid) {
        this.subAppid = subAppid;
    }

    public String getSubMchid() {
        return subMchid;
    }

    public void setSubMchid(String subMchid) {
        this.subMchid = subMchid;
    }

}
