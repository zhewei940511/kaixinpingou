package com.laojiashop.laojia.utils;

import com.laojiashop.laojia.model.User;

import static com.laojiashop.laojia.model.User.KEY_TOKEN;
import static com.laojiashop.laojia.model.User.KEY_UID;

public class LoginInfoUtil {
    public static String getToken() {
        return SharedPreferencesManager.getInstance().getString(KEY_TOKEN, "");
    }

    public static String getUid() {
        return SharedPreferencesManager.getInstance().getString(KEY_UID, "");
    }

    public static void saveLoginInfo(String uid, String token) {
        SharedPreferencesManager.getInstance().setString(KEY_UID, uid);
        SharedPreferencesManager.getInstance().setString(KEY_TOKEN, token);
    }
}
