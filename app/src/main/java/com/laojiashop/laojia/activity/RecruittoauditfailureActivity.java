package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.ServiceFeeBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 审核不通过(审核失败页面)
 */
public class RecruittoauditfailureActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.tv_reasonforfailure)
    TextView tvReasonforfailure;
    @BindView(R.id.btn_gotohomepage)
    Button btnGotohomepage;
    @BindView(R.id.btn_tryone)
    Button btnTryone;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_recruittoauditfailure);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("审核失败");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getServiceFeeindex()).subscribe(new BaseObserver<ServiceFeeBean>(mAt) {
            @Override
            public void onHandleSuccess(ServiceFeeBean serviceFeeBean) throws IOException {
                tvReasonforfailure.setText(serviceFeeBean.getRecordInfo().getRemark());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.iv_header_back, R.id.btn_gotohomepage, R.id.btn_tryone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.btn_gotohomepage:
                //跳转主页
                jumpActivity(MainActivity.class);
                break;
                //申请页面
            case R.id.btn_tryone:
                jumpActivity(RecruittoActivity.class);
                break;
        }
    }
}
