package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.ReceivedividendsAdapter;
import com.laojiashop.laojia.adapter.SpellgroupFragmentAdapter;
import com.laojiashop.laojia.adapter.WithdrawalRecordAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.entity.ReceivedividendsBean;
import com.laojiashop.laojia.entity.WithdrawalRecordBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 提现记录
 */
public class WithdrawalRecordActivity extends BaseActivity {
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
    private List<WithdrawalRecordBean.DataBean> dataBeans = new ArrayList<>();
    //适配器
    private WithdrawalRecordAdapter withdrawalRecordAdapter;
    private int page = 1;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_withdrawal_record);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("提现记录");
        tvHeaderRight.setVisibility(View.VISIBLE);
        tvHeaderRight.setText("我要提现");
        rvRecycler.setLayoutManager(new LinearLayoutManager(mAt));
        rvRecycler.addItemDecoration(new DividerItemDecoration(mAt,DividerItemDecoration.VERTICAL));
        withdrawalRecordAdapter = new WithdrawalRecordAdapter(dataBeans);
        //无数据显示空
        withdrawalRecordAdapter.setEmptyView(LayoutInflater.from(mAt).inflate(R.layout.layout_empty_view, rvRecycler, false));
        //绑定适配器
        rvRecycler.setAdapter(withdrawalRecordAdapter);
        //设置头
        refreshLayout.setRefreshFooter(new BallPulseFooter(mAt).setSpinnerStyle(SpinnerStyle.Scale));
        //开启下拉刷新
        refreshLayout.setEnableRefresh(true);
        //开启上拉加载
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableScrollContentWhenLoaded(true);
        //取消内容不满一页时开启上拉加载功能
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setEnableOverScrollBounce(true);
        //下拉刷新监听
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getDataFromServer();
                refreshLayout.finishRefresh(true);
            }
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getDataFromServer();
                refreshLayout.finishLoadMore(true);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        mDataList=new ArrayList<>();
//        for (int i=0;i<4;i++) {
//            HotstyletorecommendBean databean = new HotstyletorecommendBean();
//            mDataList.add(databean);
//        }
//        SpellgroupFragmentAdapter adapter=new SpellgroupFragmentAdapter(R.layout.item_withdrawal_record,mDataList);
//        adapter.openLoadAnimation();
//        rvRecycler.addItemDecoration(new DividerItemDecoration(mAt,DividerItemDecoration.VERTICAL));
//        //刷新数据
//        //  pageLoadUtil = PageLoadUtil.get((BaseActivity) getActivity(), rvRecycler, adapter, refreshLayout);
//        rvRecycler.setAdapter(adapter);
    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getwithdrawLogList("mg_withdrawLog",page)).subscribe(new BaseObserver<WithdrawalRecordBean>(mAt) {
            @Override
            public void onHandleSuccess(WithdrawalRecordBean withdrawalRecordBean) throws IOException {
                //成功
                List<WithdrawalRecordBean.DataBean> data = withdrawalRecordBean.getData();
                if (page == 1) {
                    dataBeans.clear();
                }
                dataBeans.addAll(data);
                withdrawalRecordAdapter.notifyDataSetChanged();
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
                jumpActivity(WithdrawalActivity.class);
                finish();
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
