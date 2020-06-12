package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.view.ExpandLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情页
 */
public class OrderdetailsActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.btn_toviewmore)
    TextView btnToviewmore;
    @BindView(R.id.expandLayout)
    ExpandLayout expandLayout;
    @BindView(R.id.img_toviewmore)
    ImageView imgToviewmore;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_orderdetails);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("订单详情");
        expandLayout.initExpand(false);
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

    @OnClick({R.id.iv_header_back, R.id.btn_toviewmore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.btn_toviewmore:
                if (expandLayout.isExpand()) {
                    imgToviewmore.setImageResource(R.mipmap.icon_pickup);
                } else {
                    imgToviewmore.setImageResource(R.mipmap.icon_unfold);
                }
                expandLayout.toggleExpand();
                break;
        }
    }


}
