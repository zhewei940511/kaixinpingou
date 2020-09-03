package com.laojiashop.laojia.entity;

import com.google.gson.annotations.SerializedName;

public class PayOrderInfoBean {


    /**
     * callAlipay : <form id='alipaysubmit' name='alipaysubmit' action='https://openapi.alipay.com/gateway.do?charset=UTF-8' method='POST'><input type='hidden' name='method' value='alipay.trade.wap.pay'/><input type='hidden' name='app_id' value='2021001183631558'/><input type='hidden' name='timestamp' value='2020-07-30 16:13:14'/><input type='hidden' name='format' value='json'/><input type='hidden' name='version' value='1.0'/><input type='hidden' name='alipay_sdk' value='alipay-easysdk-php-2.0.0'/><input type='hidden' name='charset' value='UTF-8'/><input type='hidden' name='sign_type' value='RSA2'/><input type='hidden' name='app_cert_sn' value='6b119b608944f38ac00da6c01f09aedd'/><input type='hidden' name='alipay_root_cert_sn' value='687b59193f3f462dd5336e5abf83c5d8_02941eef3187dddf3d3b83462e1dfcf6'/><input type='hidden' name='biz_content' value='{"subject":"支付宝支付[拼团]订单：2007159555768283621","out_trade_no":"2007159555768283621","total_amount":115,"quit_url":"http:\/\/weixin.demo.laojia99.com\/","product_code":"QUICK_WAP_WAY"}'/><input type='hidden' name='return_url' value='http://weixin.demo.laojia99.com/'/><input type='hidden' name='notify_url' value='http://sys.demo.laojia99.com/appApi/v1/Pay/alipayNotify'/><input type='hidden' name='sign' value='LwZ7mNEga4QrGEwYnuNQQo6omTRGp0lx6d17FcBvpXncQUWdMXQROOkP103I3Ue4g mowXCIWk9HVK8PLjtLHyJTp3s4nArnlTmdD4hpq4 MXIh/RlAD18Fv nPHfcj9FnB XQc6KHYemM1JmLiiqIbB5zL4boqOpEpmOMi63pk0qX/svPJSHVZAcERoj6FaZvrewsiRjTewtStBugcYW1GTW07eT2YC0woh6PenmVwYJfBcyahoiiK8cLGW0MlLpuBa4qQlio4GbYDROwJzy2zm3l8dAgUoj9oL1l0u L99bxYa2ge1yujdETDapPMPpv e6QI1dnO/1IdnhKSVbA=='/><input type='submit' value='ok' style='display:none;''></form><script>document.forms['alipaysubmit'].submit();</script>
     * appid : wx2219207eed9eb5a8
     * partnerid : 1575802031
     * prepayid : wx12151323083006666a7524be18ad320000
     * package : Sign=WXPay
     * noncestr : sbi2ylsmgtaw4r2x4smrh5ir6qpttmgk
     * timestamp : 1597216404
     * sign : CDEFC2C7084263F1F977E08BB2EF56DBFD6ECBB5B0E277FFA29BED1477AEF004
     */

    private String callAlipay;
    private String appid;
    private String partnerid;
    private String prepayid;
    @SerializedName("package")
    private String packageX;
    private String noncestr;
    private String timestamp;
    private String sign;

    public String getCallAlipay() {
        return callAlipay;
    }

    public void setCallAlipay(String callAlipay) {
        this.callAlipay = callAlipay;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }
    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }



    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
