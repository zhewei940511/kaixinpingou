package com.laojiashop.laojia.interceptor;

import com.blankj.utilcode.util.ActivityUtils;
import com.google.gson.Gson;
import com.laojiashop.laojia.activity.UsercodeloginActivity;
import com.laojiashop.laojia.model.User;
import com.laojiashop.laojia.utils.ActivityManage;
import com.laojiashop.laojia.utils.ComParamContact;
import com.zhouyou.http.interceptor.BaseExpiredInterceptor;
import com.zhouyou.http.model.ApiResult;

import okhttp3.Response;

/**
 * 处理token过期
 */
public class TokenInterceptor extends BaseExpiredInterceptor {
    private ApiResult apiResult;

    @Override
    public boolean isResponseExpired(Response response, String bodyString) {
        apiResult = new Gson().fromJson(bodyString, ApiResult.class);
        if (apiResult != null) {
            int code = apiResult.getCode();
            if (code == ComParamContact.Code.BIND_PHONE
                    || code == ComParamContact.Code.ACCESS_TOKEN
            ) {
                return true;
            }
        }
        return false;
    }

    User user = null;

    @Override
    public Response responseExpired(Chain chain, String bodyString) {
        switch (apiResult.getCode()) {
            case ComParamContact.Code.ACCESS_TOKEN://token过期
                //ActivityUtils.startActivities(UsercodeloginActivity.class);
                //如果token失效返回登录
                ActivityUtils.startActivity(UsercodeloginActivity.class);
                //移除所有activity
                ActivityManage.finishAll();
                break;
            case ComParamContact.Code.BIND_PHONE://绑定手机号
                break;
        }
        return null;
    }

}
