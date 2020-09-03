package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 招募令审核中页面(待审核)
 */
public class RecruittoInthereviewActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.btn_returnhomepage)
    Button btnReturnhomepage;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_recruittointhereview);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("审核中");
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


    @OnClick({R.id.iv_header_back, R.id.btn_returnhomepage})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.btn_returnhomepage:
                jumpActivity(MainActivity.class);
                finish();
                break;
        }
    }
}
