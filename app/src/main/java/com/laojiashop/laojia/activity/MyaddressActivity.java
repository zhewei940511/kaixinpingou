package com.laojiashop.laojia.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 地址管理界面
 */
public class MyaddressActivity extends BaseActivity {

    @BindView(R.id.header_title_view)
    RelativeLayout mTitleView;
    @BindView(R.id.tv_header_title)
    TextView mHeaderTitle;
    private RecyclerView mRecycler;
    private Button btn_addressadd;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_myaddress);
    }

    @Override
    protected void initViews() {
        mRecycler = findViewById(R.id.rv_recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(mAt));
        btn_addressadd = findViewById(R.id.btn_addressadd);
        btn_addressadd.setOnClickListener(view ->
                startActivityForResult(new Intent(mAt, NewaddressActivity.class), 666));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        getBarDistance(mTitleView);
        mHeaderTitle.setText("我的地址");
    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
          //回调数据刷新  getDataFromServer();
        }
    }
    @OnClick(R.id.iv_header_back)
    public void clickView() {
        finish();
    }}
