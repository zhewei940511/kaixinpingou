package com.laojiashop.laojia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.ConfigBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.ToastUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现界面
 */
public class WithdrawalActivity extends BaseActivity {

    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tv_header_right)
    TextView tvHeaderRight;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.tv_withusercoin)
    TextView tvWithusercoin;
    @BindView(R.id.et_usercoin)
    EditText etUsercoin;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_withdrawal);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("提现");
        tvHeaderRight.setVisibility(View.VISIBLE);
        tvHeaderRight.setText("提现记录");
        Intent intent=getIntent();
        tvWithusercoin.setText(String.valueOf(intent.getDoubleExtra("coin",0)));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getConfig()).subscribe(new BaseObserver<ConfigBean>(mAt) {
            @Override
            public void onHandleSuccess(ConfigBean configBean) throws IOException {
                //设置手机样式
                WebSettings webSettings = webView.getSettings();
                webSettings.setJavaScriptEnabled(true);//允许使用js
                //不支持屏幕缩放
                webSettings.setSupportZoom(false);
                webSettings.setBuiltInZoomControls(false);
                //不显示webview缩放按钮
                webSettings.setDisplayZoomControls(false);
                webView.loadDataWithBaseURL(null, configBean.getCoin().getTx_rules(), "text/html", "utf-8", null);

            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.iv_header_back, R.id.tv_header_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_header_right:
                jumpActivity(WithdrawalRecordActivity.class);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_confirmwithdrawal)
    public void onViewClicked() {
            String num=etUsercoin.getText().toString().trim();
            if (TextUtils.isEmpty(num))
            {
                ToastUtil.showToast("请输入提现金额");
                return;
            }
            HttpRxObservable.getObservable(ApiUtils.getApiService().corewithdraw(num)).subscribe(new BaseObserver<Object>(mAt) {
                @Override
                public void onHandleSuccess(Object o) throws IOException {
                    jumpActivity(WithdrawalRecordActivity.class);
                    finish();
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    ToastUtil.showToast(e.getMessage());
                }
            });
    }
}
