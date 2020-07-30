package com.laojiashop.laojia.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.utils.BarUtils;
import com.laojiashop.laojia.utils.CountDownTimerUtils;
import com.laojiashop.laojia.utils.DialogUtil;
import com.laojiashop.laojia.utils.StatusBarUtil;
import com.laojiashop.laojia.view.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册界面
 */
public class RegisteredActivity extends BaseActivity {
    @BindView(R.id.rootView)
    TextView mRootView;
    @BindView(R.id.btn_getregistcode)
    Button btnGetregistcode;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    @BindView(R.id.ck_sure)
    CheckBox ckSure;
    @BindView(R.id.ly_gotologin)
    LinearLayout lyGotologin;
    @BindView(R.id.et_registphonenum)
    ClearEditText etRegistphonenum;
    @BindView(R.id.tv_registagreement)
    TextView tvRegistagreement;
    private CountDownTimerUtils countDownTimer;


    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_registered);
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

    @OnClick({R.id.ck_sure, R.id.ly_gotologin, R.id.btn_getregistcode, R.id.btn_regist})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //同意用户协议
            case R.id.ck_sure:
                //判断是否同意用户协议
                if (ckSure.isChecked()) {
                    btnRegist.setBackgroundResource(R.drawable.shape_button_main);
                    btnRegist.setEnabled(true);
                } else {
                    btnRegist.setBackgroundResource(R.drawable.shape_button_main_34);
                    btnRegist.setEnabled(false);
                }
                break;
            case R.id.ly_gotologin:
                jumpActivity(LoginActivity.class);
                finish();
                break;
            //获取验证码
            case R.id.btn_getregistcode:
                countDownTimer = new CountDownTimerUtils(btnGetregistcode, 60000, 1000);
                countDownTimer.start();
                break;
                //注册
            case R.id.btn_regist:

                break;
        }
    }
    Dialog dialog;
    @OnClick(R.id.tv_registagreement)
    public void onViewClicked() {
        dialog=DialogUtil.showProtocolDialogNoBtns(mAt);
    }
}
