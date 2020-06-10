package com.laojiashop.laojia.base;

import android.app.Application;

import com.bugtags.library.Bugtags;
import com.laojiashop.laojia.BuildConfig;
import com.laojiashop.laojia.http.ApiService;
import com.laojiashop.laojia.utils.SharedPreferencesManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.bugly.Bugly;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseApplication extends Application {
    private ApiService api;
    private static BaseApplication application;
    private static final String TAG = "FloatWindow";
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        SharedPreferencesManager.init(application);
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ApiService.class);

    }
    public ApiService API() {
        return api;
    }
//    public static Context getMyAppContext(){
//        return application;
//    }
}
