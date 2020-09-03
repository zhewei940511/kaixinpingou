package com.laojiashop.laojia.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ActivityUtils;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.GsonConverterFactory;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.model.User;
import com.laojiashop.laojia.utils.BarUtils;
import com.laojiashop.laojia.utils.ComParamContact;
import com.laojiashop.laojia.utils.CountDownTimerUtils;
import com.laojiashop.laojia.utils.DialogUtil;
import com.laojiashop.laojia.utils.LogUtil;
import com.laojiashop.laojia.utils.LoginInfoUtil;
import com.laojiashop.laojia.utils.StatusBarUtil;
import com.laojiashop.laojia.utils.ToastUtil;
import com.laojiashop.laojia.view.ClearEditText;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.subsciber.IProgressDialog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

import static com.laojiashop.laojia.http.BaseObserver.REQUEST_CODE_LOGIN;

/**
 * 使用验证码登录
 */
public class UsercodeloginActivity extends BaseActivity {
    @BindView(R.id.rootView)
    TextView mRootView;
    @BindView(R.id.btn_logincode)
    Button btnLogincode;
    @BindView(R.id.btn_codelogin)
    Button btnCodelogin;
    @BindView(R.id.img_codeloginweixin)
    ImageView imgCodeloginweixin;
    @BindView(R.id.ck_sure)
    CheckBox ckSure;
    @BindView(R.id.tv_registagreement)
    TextView tvRegistagreement;
    @BindView(R.id.et_codephone)
    ClearEditText etCodephone;
    @BindView(R.id.et_codecontext)
    EditText etCodecontext;
    private CountDownTimerUtils countDownTimer;
    //定义数据
    private String phone, codecontext;
    private IWXAPI api;
    Receiver receiver;
    IntentFilter intentFilter;
    private static boolean started = false;
    public static final String KEY_FOR_RESULT = "KEY_FOR_RESULT";
//    //判断是否需要重复登录
//    public static final String KEY_FOR_RESULT = "KEY_FOR_RESULT";
//    private static boolean started = false;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_usercodelogin);
    }

    @Override
    protected void initViews() {
        int barHeight = StatusBarUtil.getStatusBarHeight(mAt);
        if (barHeight > 0) {
            //设置状态栏的高度
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mRootView.getLayoutParams();
            layoutParams.topMargin = BarUtils.getStatusBarHeight(mAt) + layoutParams.topMargin;
            mRootView.setLayoutParams(layoutParams);
        }
        StatusBarUtil.setStatusBarDarkTheme(mAt, true);
        receiver = new Receiver();
        intentFilter = new IntentFilter();
        intentFilter.addAction("wxdl");
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    Dialog dialog;

    @OnClick({R.id.tv_registagreement, R.id.ck_sure, R.id.btn_logincode, R.id.btn_codelogin, R.id.img_codeloginweixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //查看用户协议
            case R.id.tv_registagreement:
                dialog = DialogUtil.showProtocolDialogNoBtns(mAt);
                break;
            //同意用户协议
            case R.id.ck_sure:
                //判断是否同意用户协议
                if (ckSure.isChecked()) {
                    btnCodelogin.setBackgroundResource(R.drawable.shape_button_main);
                    btnCodelogin.setEnabled(true);
                } else {
                    btnCodelogin.setBackgroundResource(R.drawable.shape_button_main_34);
                    btnCodelogin.setEnabled(false);
                }
                break;
            //获取验证码
            case R.id.btn_logincode:
                getusercode();
                break;
            //登录
            case R.id.btn_codelogin:
                usercodeLogin();
                break;
            //微信登录
            case R.id.img_codeloginweixin:
                if (api == null) {
                    api = WXAPIFactory.createWXAPI(UsercodeloginActivity.this, ComParamContact.APP_ID, true);
                }
                if (!api.isWXAppInstalled()) {
                    showToast("您手机尚未安装微信，请安装后再登录");
                    return;
                }
                api.registerApp(ComParamContact.APP_ID);
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_jj_login_state";
                api.sendReq(req);
                break;
        }
    }



    //登录方法
    private void usercodeLogin() {
        phone = etCodephone.getText().toString().trim();
        codecontext = etCodecontext.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("请输入正确手机号码");
            //Toast.makeText(mAt, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(codecontext)) {
            ToastUtil.showToast("验证码不能为空");
            //Toast.makeText(mAt, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpRxObservable.getObservable(ApiUtils.getApiService().login(phone, codecontext, "5", "account")).subscribe(new BaseObserver<User>(mAt) {
            @Override
            public void onHandleSuccess(User user) throws IOException {
                showToast("登录成功！");
                //登录成功保存用户信息
                LoginInfoUtil.saveLoginInfo(user.id, user.token);
                jumpActivity(MainActivity.class);
                finish();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    //验证码获取
    private void getusercode() {
        phone = etCodephone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(mAt, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
            return;
        }
        HttpRxObservable.getObservable(ApiUtils.getApiService().getcode(phone, "3")).subscribe(new BaseObserver<Object>(mAt) {
            @Override
            public void onHandleSuccess(Object o) throws IOException {
                countDownTimer = new CountDownTimerUtils(btnLogincode, 60000, 1000);
                countDownTimer.start();
                showToast("请求数据" + o.toString());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    //微信登录
    public class Receiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String code = intent.getStringExtra("code");
            HttpRxObservable.getObservable(ApiUtils.getApiService().weilogin(code, "wechat", "6")).subscribe(new BaseObserver<User>(mAt) {
                @Override
                public void onHandleSuccess(User user) throws IOException {
                    showToast("登录成功！");
                    //授权类信息存入缓存
                    LoginInfoUtil.saveLoginInfo(user.id, user.token);
                    jumpActivity(MainActivity.class);
                    finish();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        started = false;
    }
    public static void start(Activity activity) {
        start(activity, REQUEST_CODE_LOGIN);
    }
    public static void start(Activity activity, int requestCode) {
        if (started) return;
        started = true;
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_FOR_RESULT, true);
        ActivityUtils.startActivityForResult(bundle, activity, UsercodeloginActivity.class, requestCode);
    }
}
