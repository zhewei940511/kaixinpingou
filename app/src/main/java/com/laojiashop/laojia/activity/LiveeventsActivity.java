package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.LiveeventsAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.LiveeventsBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LiveeventsActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tv_header_right)
    TextView tvHeaderRight;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    //数据源
    private List<LiveeventsBean> liveeventsBeans = new ArrayList<>();
    //适配器
    private LiveeventsAdapter liveeventsAdapter;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_liveevents);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("场次");
        tvHeaderRight.setVisibility(View.VISIBLE);
        tvHeaderRight.setText("创建直播间");
        for (int i = 0; i <= 3; i++) {
            LiveeventsBean liveeventsBean = new LiveeventsBean();
            liveeventsBeans.add(liveeventsBean);
        }
        liveeventsAdapter=new LiveeventsAdapter();
        liveeventsAdapter.addData(liveeventsBeans);
        rvRecycler.setAdapter(liveeventsAdapter);
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

    @OnClick({R.id.iv_header_back, R.id.tv_header_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_header_right:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
