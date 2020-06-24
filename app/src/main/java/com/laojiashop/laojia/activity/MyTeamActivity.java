package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.MyTeamAdapter;
import com.laojiashop.laojia.adapter.MycollectionAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTeamActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_erweimacode)
    TextView tvHeaderErweimacode;
    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private MyTeamAdapter myTeamAdapter;
    //数据模拟
    private ArrayList<HotstyletorecommendBean> mDataList;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_myteam);
    }

    @Override
    protected void initViews() {
        rvRecycler.setLayoutManager(new LinearLayoutManager(mAt));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mDataList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            HotstyletorecommendBean databean = new HotstyletorecommendBean();
            mDataList.add(databean);
        }
        myTeamAdapter = new MyTeamAdapter(R.layout.item_mytem,mDataList);
        rvRecycler.setAdapter(myTeamAdapter);
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

    @OnClick({R.id.iv_header_back, R.id.tv_header_erweimacode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回
            case R.id.iv_header_back:
                finish();
                break;
                //二维码
            case R.id.tv_header_erweimacode:

                break;
        }
    }
}
