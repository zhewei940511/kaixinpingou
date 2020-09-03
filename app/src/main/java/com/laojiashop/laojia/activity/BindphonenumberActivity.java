package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.CountDownTimerUtils;
import com.laojiashop.laojia.utils.StatusBarUtil;
import com.laojiashop.laojia.utils.ToastUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindphonenumberActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.tv_phonebind)
    EditText tvPhonebind;
    @BindView(R.id.tv_codebind)
    EditText tvCodebind;
    @BindView(R.id.btn_getcode)
    Button btnGetcode;
    @BindView(R.id.btn_bindphonesure)
    Button btnBindphonesure;
    //定义变量接收
    private String phonenum,codenum;
    private  CountDownTimerUtils countDownTimer;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_bindphonenumber);
    }

    @Override
    protected void initViews() {
//        int barHeight = StatusBarUtil.getStatusBarHeight(mAt);
//        if (barHeight > 0) {
//            //设置状态栏的高度
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) rootView.getLayoutParams();
//            layoutParams.topMargin = BarUtils.getStatusBarHeight(mAt) + layoutParams.topMargin;
//            rootView.setLayoutParams(layoutParams);
//        }
        StatusBarUtil.setStatusBarDarkTheme(mAt, true);
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("");
        phonenum=tvPhonebind.getText().toString().trim();
        codenum=tvCodebind.getText().toString().trim();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.btn_getcode, R.id.btn_bindphonesure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_getcode:
                if (TextUtils.isEmpty(phonenum))
                {
                    ToastUtil.showToast("请输入手机号");
                    return;
                }
                HttpRxObservable.getObservable(ApiUtils.getApiService().getcode(phonenum, "3")).subscribe(new BaseObserver<Object>(mAt) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        countDownTimer = new CountDownTimerUtils(btnGetcode, 60000, 1000);
                        countDownTimer.start();
                        ToastUtil.showToast("请求数据" + o.toString());
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                    }
                });
                break;
            case R.id.btn_bindphonesure:
                if (TextUtils.isEmpty(phonenum))
                {
                    ToastUtil.showToast("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(codenum))
                {
                    ToastUtil.showToast("请输入手机号");
                    return;
                }
                HttpRxObservable.getObservable(ApiUtils.getApiService().bindPhoneSave(phonenum,codenum)).subscribe(new BaseObserver<Object>(mAt) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        ToastUtil.showToast("绑定成功");
                        finish();
                    }
                });
                break;
        }
    }
}
