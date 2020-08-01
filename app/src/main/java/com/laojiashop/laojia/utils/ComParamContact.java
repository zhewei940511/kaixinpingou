package com.laojiashop.laojia.utils;

public class ComParamContact {
    public static final String APP_ID = "wx2219207eed9eb5a8";
    public static final String AppSecret = "d5eeb1ce2ebf7d9b0b819c0d6a845bd0";
    public final static class Common {
        public final static String APPID = "appId";
        public final static String ACCESSTOKEN = "accessToken";
        public final static String TIMESTAMP = "timestamp";
        public final static String REFRESH_TOKEN = "refreshToken";
        public final static String SIGN = "sign";
          public final static String DEVICE = "3";
    }

    public final static class Code {
        //http请求成功状态码
        public final static int HTTP_SUCESS = 0;
        //token过期或者被强制登录了
        public static final int ACCESS_TOKEN = 401;
        //手机号绑定
        public static final int BIND_PHONE = 402;
        //AccessToken错误或已过期
        public static final int ACCESS_TOKEN_EXPIRED = 100010101;
        //RefreshToken错误或已过期
        public static final int REFRESH_TOKEN_EXPIRED = 100010102;
        //帐号在其它手机已登录
        public static final int OTHER_PHONE_LOGINED = 100021006;
        //timestamp过期
        public static final int TIMESTAMP_ERROR = 100010104;
        //缺少授权信息,没有accessToken,应该是没有登录
        public final static int NO_ACCESS_TOKEN = 100010100;
        //签名错误
        public final static int ERROR_SIGN = 100010105;
        //设备未绑定
        public final static int DEVICE_NO_BIND = 100022001;
    }

    public final static class Token {
        public final static String PATH = "/v1/account/token/refresh";
        public final static String AUTH_MODEL = "authModel";

    }

    public final static class UserStatus {
        public final static String PATH = "/v1/user/get";
    }

    public final static class Login {
       public final static String PATH = "/appApi/v1/User/login";
        public final static String PHONE = "phone";
        public final static String PASSWORD = "passwd";
        public  final  static String LOGINTYPE="login_type";
    }
}
