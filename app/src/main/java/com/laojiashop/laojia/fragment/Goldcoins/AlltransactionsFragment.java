package com.laojiashop.laojia.fragment.Goldcoins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.GoldcoinsAdapter;
import com.laojiashop.laojia.adapter.IstartedSpellingAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.GoldcoinsBean;
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
 * 全部交易
 */
public class AlltransactionsFragment extends BaseFragment {
    String state;
    @BindView(R.id.rv_hotstyletorecommend)
    RecyclerView rvHotstyletorecommend;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    //适配器
    private GoldcoinsAdapter goldcoinsAdapter;
    public AlltransactionsFragment(String state) {
        this.state = state;
    }
    //数据源
    private List<GoldcoinsBean.DataBean> mDataList=new ArrayList<>();
    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_alltransactions;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        rvHotstyletorecommend.setLayoutManager(new LinearLayoutManager(mAty));
        goldcoinsAdapter = new GoldcoinsAdapter(mDataList);
        //无数据显示空
        goldcoinsAdapter.setEmptyView(LayoutInflater.from(mAty).inflate(R.layout.layout_empty_view, rvHotstyletorecommend, false));
        //绑定适配器
        rvHotstyletorecommend.setAdapter(goldcoinsAdapter);
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
        map.put("typeid", state);
        String s = JSON.toJSONString(map);
        HttpRxObservable.getObservable(ApiUtils.getApiService().getCoinList("mg_Coin",page,s)).subscribe(new BaseObserver<GoldcoinsBean>(mAty) {
            @Override
            public void onHandleSuccess(GoldcoinsBean goldcoinsBean) throws IOException {
                //成功
                List<GoldcoinsBean.DataBean> data = goldcoinsBean.getData();
                if (page == 1) {
                    mDataList.clear();
                }
                mDataList.addAll(data);
                goldcoinsAdapter.notifyDataSetChanged();
            }
        });
    }
}
