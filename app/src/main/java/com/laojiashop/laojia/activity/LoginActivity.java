package com.laojiashop.laojia.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.GsonConverterFactory;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.model.User;
import com.laojiashop.laojia.utils.BarUtils;
import com.laojiashop.laojia.utils.LogUtil;
import com.laojiashop.laojia.utils.LoginInfoUtil;
import com.laojiashop.laojia.utils.SharedPreferencesManager;
import com.laojiashop.laojia.utils.StatusBarUtil;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.request.CustomRequest;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.rootView)
    TextView mRootView;
    @BindView(R.id.ly_jumregistered)
    LinearLayout lyJumregistered;
    @BindView(R.id.et_loginphonenum)
    EditText etLoginphonenum;
    @BindView(R.id.tv_forgetpwd)
    TextView tvForgetpwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.img_loginweixin)
    ImageView imgLoginweixin;
    @BindView(R.id.et_password)
    EditText etPassword;
    SharedPreferencesManager sharedPreferencesManager;
    @BindView(R.id.tv_usecodelogin)
    TextView tvUsecodelogin;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_login);
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
        sharedPreferencesManager = new SharedPreferencesManager(mAt);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //权限检查
        checkRxPermission();
    }

    @Override
    public void getDataFromServer() {
//            HttpRxObservable.getObservable(ApiUtils.getApiService().login("18888888888","123","3")).subscribe(new BaseObserver<User>(mAt) {
//                @Override
//                public void onHandleSuccess(User user) throws IOException {
//
//                }
//            });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    //动态申请权限
    @SuppressLint("CheckResult")
    private void checkRxPermission() {
        RxPermissions rxPermissions = new RxPermissions((Activity) mAt);
        String[] permissionsArr = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CALL_PHONE
        };
        rxPermissions
                .requestEach(
                        permissionsArr)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            LogUtil.i("testRxPermission CallBack onPermissionsGranted() : " + permission.name +
                                    " request granted , to do something...");
                            //todo somthing

                        } else if (permission.shouldShowRequestPermissionRationale) {
                            LogUtil.e("testRxPermission CallBack onPermissionsDenied() : " + permission.name + "request denied");
                            //ToastUtil.showShort(instance, "拒绝权限，等待下次询问哦");
                            alertDialog(mAt);
                            //todo request permission again
                        } else {
                            LogUtil.e("testRxPermission CallBack onPermissionsDenied() : this " + permission.name + " is denied " +
                                    "and never ask again");
                            // ToastUtil.showShort(instance, "拒绝权限，不再弹出询问框，请前往APP应用设置中打开此权限");
                            //todo nothing
                        }
                    }
                });
    }

    /**
     * 用户拒绝，并且选择不再提示,可以引导用户进入权限设置界面开启权限
     * 弹窗是否显示根据需求选择调用
     *
     * @param context
     */
    private static void alertDialog(final Activity context) {
        if (context != null) {
            new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setTitle("权限要求")
                    .setMessage("如果没有请求的权限，此应用程序可能无法正常工作。打开app设置界面修改app权限")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                            intent.setData(uri);
                            context.startActivity(intent);
                            context.finish();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .create()
                    .show();
        }
    }


    @OnClick({R.id.tv_usecodelogin,R.id.ly_jumregistered, R.id.tv_forgetpwd, R.id.btn_login, R.id.img_loginweixin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //使用验证码登录
            case R.id.tv_usecodelogin:
                jumpActivity(UsercodeloginActivity.class);
                finish();
                break;
                //注册
            case R.id.ly_jumregistered:
                jumpActivity(RegisteredActivity.class);
                //finish();
                break;
                //忘记密码
            case R.id.tv_forgetpwd:
                break;
            //登录
            case R.id.btn_login:
                String account = etLoginphonenum.getText().toString().trim();
                String pwd = etPassword.getText().toString().trim();
                if (account.isEmpty()) {
                    Toast.makeText(mAt, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText(mAt, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
//                HttpRxObservable.getObservable(ApiUtils.getApiService().login(account, pwd, "account")).subscribe(new BaseObserver<User>(mAt) {
//                    @Override
//                    public void onHandleSuccess(User user) throws IOException {
//                        //缓存数据
//                        LoginInfoUtil.saveLoginInfo(user.id, user.token);
//                        sharedPreferencesManager.put("headimgurl", user.headimgurl);
//                        sharedPreferencesManager.put("name", user.name);
//                        jumpActivity(MainActivity.class);
//                        finish();
//                    }
//                });

//                jumpActivity(MainActivity.class);
//                finish();
                break;
            case R.id.img_loginweixin:
                break;
        }
    }

}
