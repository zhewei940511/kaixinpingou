package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyfeedbackActivity extends BaseActivity {
    @BindView(R.id.header_title_view)
    RelativeLayout mTitleView;
    @BindView(R.id.tv_header_title)
    TextView mHeaderTitle;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_modifyfeedback);
    }

    @Override
    protected void initViews() {
        getBarDistance(mTitleView);
        mHeaderTitle.setText("意见反馈");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {

    }

    @OnClick(R.id.iv_header_back)
    public void clickView() {
        finish();
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
