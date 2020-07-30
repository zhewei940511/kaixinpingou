package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 招募令
 */
public class RecruittoActivity extends BaseActivity {

    @BindView(R.id.btn_checkshoppers)
    ImageButton btnCheckshoppers;
    @BindView(R.id.btn_checkdealers)
    ImageButton btnCheckdealers;
    @BindView(R.id.btn_checkagent)
    ImageButton btnCheckagent;
    @BindView(R.id.tv_uploaddocuments)
    TextView tvUploaddocuments;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_recruitto);

    }

    @Override
    protected void initViews() {
        setpicnull();
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

    private void setpicnull() {
        btnCheckagent.setImageDrawable(null);
        btnCheckdealers.setImageDrawable(null);
        btnCheckshoppers.setImageDrawable(null);
    }


    @OnClick({R.id.tv_uploaddocuments,R.id.btn_checkshoppers, R.id.btn_checkdealers, R.id.btn_checkagent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //立即申请
            case R.id.tv_uploaddocuments:
                jumpActivity(UploaddocumentsActivity.class);
                break;
                //导购选择
            case R.id.btn_checkshoppers:
                setpicnull();
                btnCheckshoppers.setImageResource(R.mipmap.icon_checksure);
                break;
                //经销商选择
            case R.id.btn_checkdealers:
                setpicnull();
                btnCheckdealers.setImageResource(R.mipmap.icon_checksure);
                break;
                //代理商选择
            case R.id.btn_checkagent:
                setpicnull();
                btnCheckagent.setImageResource(R.mipmap.icon_checksure);
                break;
        }
    }

}
