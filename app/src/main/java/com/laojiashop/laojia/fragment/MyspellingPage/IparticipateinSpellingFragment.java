package com.laojiashop.laojia.fragment.MyspellingPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.IparticipateinSpellingAdapter;
import com.laojiashop.laojia.adapter.IstartedSpellingAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.MySpellingBean;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 我参与的团购
 */
public class IparticipateinSpellingFragment extends BaseFragment {
    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private IparticipateinSpellingAdapter iparticipateinSpellingAdapter;
    //数据源
    private List<MySpellingBean.DataBean> mDataList=new ArrayList<>();

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_participateinspell;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        rvRecycler.setLayoutManager(new LinearLayoutManager(mAty));
        iparticipateinSpellingAdapter=new IparticipateinSpellingAdapter(mDataList);
        //无数据显示空
        iparticipateinSpellingAdapter.setEmptyView(LayoutInflater.from(mAty).inflate(R.layout.layout_empty_view, rvRecycler, false));
        //绑定适配器
        rvRecycler.setAdapter(iparticipateinSpellingAdapter);
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
    protected void initData() {

    }

    @Override
    protected void getDataFromServer() {
        Map<String, Object> map = new HashMap<String, Object>();
        //这个地方的typeid是顶部的导航的id
        map.put("typeid",1);
        String s = JSON.toJSONString(map);
        HttpRxObservable.getObservable(ApiUtils.getApiService().getTuangouList("mg_my_tuangou",page,s)).subscribe(new BaseObserver<MySpellingBean>(mAty) {
            @Override
            public void onHandleSuccess(MySpellingBean mySpellingBean) throws IOException {
                //成功
                List<MySpellingBean.DataBean> data = mySpellingBean.getData();
                if (page == 1) {
                    mDataList.clear();
                }
                mDataList.addAll(data);
                iparticipateinSpellingAdapter.notifyDataSetChanged();
            }
        });
    }
}
