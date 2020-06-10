package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.utils.StatusBarUtil;

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
}
