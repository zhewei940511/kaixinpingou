package com.laojiashop.laojia.base;

import android.app.Application;

import com.laojiashop.laojia.BuildConfig;
import com.laojiashop.laojia.activity.LoginActivity;
import com.laojiashop.laojia.activity.UsercodeloginActivity;
import com.laojiashop.laojia.http.ApiService;
import com.laojiashop.laojia.http.RequestUtil;
import com.laojiashop.laojia.utils.LoginInfoUtil;
import com.laojiashop.laojia.utils.SharedPreferencesManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseAllApplication extends Application {
    private ApiService api;
    private static BaseAllApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        SharedPreferencesManager.init(application);
        initHttp();
        Logger.addLogAdapter(new AndroidLogAdapter());
        RequestUtil.onLoginRequest = activity -> {
          UsercodeloginActivity.start(activity);
            LoginInfoUtil.saveLoginInfo("","");
        };
    }
    void initHttp()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(ApiService.class);
    }
    public ApiService API() {
        return api;
    }
}
