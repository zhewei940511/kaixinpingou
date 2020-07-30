package com.laojiashop.laojia.base;

import android.app.Application;
import android.content.Context;

import com.bugtags.library.Bugtags;
import com.laojiashop.laojia.BuildConfig;
import com.laojiashop.laojia.http.GsonConverterFactory;
import com.laojiashop.laojia.interceptor.CustomSignInterceptor;
import com.laojiashop.laojia.interceptor.TokenInterceptor;
import com.laojiashop.laojia.utils.ComParamContact;
import com.laojiashop.laojia.utils.LoginInfoUtil;
import com.laojiashop.laojia.utils.SharedPreferencesManager;
import com.tencent.bugly.Bugly;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.model.HttpHeaders;
import com.zhouyou.http.utils.HttpLog;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import retrofit2.Retrofit;

/**
 * 代码太乱准备弃用
 * 搭配RXEasyHttp使用的
 */
public class BaseApplication extends Application {
   // private ApiService api;
//    //String Url = "http://sys.demo.laojia99.com";
    String Url="http://192.168.1.19:88";
    private static BaseApplication application;
    private static final String TAG = "FloatWindow";
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        SharedPreferencesManager.init(this);
        EasyHttp.init(this);//默认初始化,必须调用
        initHttp();
       // Logger.addLogAdapter(new AndroidLogAdapter());
//        RequestUtil.onLoginRequest = activity -> {
//            LoginActivity.start(activity);
//            LoginInfoUtil.saveLoginInfo("","");
//        };
        Bugly.init(getApplicationContext(), "887710c819", BuildConfig.DEBUG);
        Bugtags.start("949e93fbe5e61f0509b72cfa58a5b4a6", this, Bugtags.BTGInvocationEventBubble);
    }
    public static BaseApplication getInstance() {
        return application;
    }

    void initHttp()
    {
   /*
        初始化网络请求
         */
        HttpHeaders headers = new HttpHeaders();
        headers.put("device", ComParamContact.Common.DEVICE);
       // headers.put("token", LoginInfoUtil.getToken());//token在每个接口都要使用所以不能再这里做全局设置
        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        EasyHttp.getInstance()
                //可以全局统一设置全局URL
                .setBaseUrl(Url)//设置全局URL  url只能是域名 或者域名+端口号
                .debug("RxEasyHttp", BuildConfig.DEBUG)
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 1000)
                .setConnectTimeout(60 * 1000)
                .setRetryCount(3)//默认网络不好自动重试3次
                .setRetryDelay(500)//每次延时500ms重试
                .setRetryIncreaseDelay(500)//每次延时叠加500ms
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
//                .setCacheMaxSize(50 * 1024 * 1024)//设置缓存大小为50M
//                .setCacheVersion(1)//缓存版本为1
                .setHostnameVerifier(new UnSafeHostnameVerifier(Url))//全局访问规则
                .setCertificates()//信任所有证书
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create(new Gson()))//本框架没有采用Retrofit的Gson转化，所以不用配置
                .addCommonHeaders(headers)//设置全局公共头
                .addInterceptor(new TokenInterceptor())//处理token过期拦截
                //           .addCommonParams(params)//设置全局公共参数
                .addInterceptor(new CustomSignInterceptor());//添加参数签名拦截器
//        .addInterceptor(new HeTInterceptor());//处理自己业务的拦截器
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BuildConfig.API_HOST)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        api = retrofit.create(ApiService.class);

    }
    public class UnSafeHostnameVerifier implements HostnameVerifier {
        private String host;

        public UnSafeHostnameVerifier(String host) {
            this.host = host;
            HttpLog.i("###############　UnSafeHostnameVerifier " + host);
        }

        @Override
        public boolean verify(String hostname, SSLSession session) {
            HttpLog.i("############### verify " + hostname + " " + this.host);
            if (this.host == null || "".equals(this.host) || !this.host.contains(hostname))
                return false;
            return true;
        }
    }
    public static Context getAppContext() {
        if (application == null)
            return null;
        return application.getApplicationContext();
    }
   // public ApiService API() {
//        return api;
//    }
    public static Context getMyAppContext(){
        return application;
    }
}
