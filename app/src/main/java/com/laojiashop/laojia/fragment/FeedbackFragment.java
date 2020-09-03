package com.laojiashop.laojia.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.hitomi.tilibrary.transfer.Transferee;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.activity.FeedbackDetailActivity;
import com.laojiashop.laojia.adapter.FeedbackAdapter;
import com.laojiashop.laojia.adapter.MeShopFragmentAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.FeedbackBean;
import com.laojiashop.laojia.entity.OrderBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class FeedbackFragment extends BaseFragment {
    String state;
    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    //适配器
    private FeedbackAdapter feedbackAdapter;
    //数据源
    private List<FeedbackBean.DataBean> dataBeanList=new ArrayList<>();
    //图片查看
    private Transferee transfer;
    public FeedbackFragment(String state) {
        this.state = state;
    }
    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_feedback;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        transfer = Transferee.getDefault(mAty);
        rvRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        feedbackAdapter=new FeedbackAdapter(dataBeanList,transfer);
        //无数据显示空
        feedbackAdapter.setEmptyView(LayoutInflater.from(mAty).inflate(R.layout.layout_empty_view, rvRecycler, false));
        //绑定适配器
        rvRecycler.setAdapter(feedbackAdapter);
        //设置头
        refreshLayout.setRefreshFooter(new BallPulseFooter(mAty).setSpinnerStyle(SpinnerStyle.Scale));
        //开启下拉刷新
        refreshLayout.setEnableRefresh(true);
        //开启上拉加载
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableScrollContentWhenLoaded(true);
        //取消内容不满一页时开启上拉加载功能
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setEnableOverScrollBounce(true);
        //下拉刷新
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
        //适配器点击事件
        feedbackAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                FeedbackBean.DataBean dataBean= (FeedbackBean.DataBean) adapter.getItem(position);
                switch (view.getId())
                {
                    case R.id.ly_feedbackinfo:
                        FeedbackDetailActivity.invoke(mAty,dataBean.getId(),state);
                        break;
                }
            }
        });
    }

    @Override
    protected void getDataFromServer() {
        Map<String, Object> map = new HashMap<String, Object>();
        //这个地方的typeid是顶部的导航的id
        map.put("status",state);
        map.put("user_type",1);
        String s = JSON.toJSONString(map);
        HttpRxObservable.getObservable(ApiUtils.getApiService().getFeedBackList("mg_feedback",page,s)).subscribe(new BaseObserver<FeedbackBean>(mAty) {
            @Override
            public void onHandleSuccess(FeedbackBean feedbackBean) throws IOException {
                List<FeedbackBean.DataBean> dataBeans=feedbackBean.getData();
                if (page == 1) {
                    dataBeanList.clear();
                }
                dataBeanList.addAll(dataBeans);
                feedbackAdapter.notifyDataSetChanged();
            }
        });
    }
}
