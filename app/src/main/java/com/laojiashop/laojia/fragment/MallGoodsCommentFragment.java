package com.laojiashop.laojia.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.hitomi.tilibrary.transfer.TransferConfig;
import com.hitomi.tilibrary.transfer.Transferee;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.HappbeanAlltransactionsAdapter;
import com.laojiashop.laojia.adapter.HotstyletorecommendAdapter;
import com.laojiashop.laojia.adapter.MallGoodsCommentAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.entity.MallGoodsCommentBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 商品评论
 */
public class MallGoodsCommentFragment extends BaseFragment {
    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    //商品id
    private String goodId;
    //模拟数据
    private List<MallGoodsCommentBean.DataBean> mDataList = new ArrayList<>();
    private MallGoodsCommentAdapter mallGoodsCommentAdapter;
    private int page = 1;
    private Transferee transfer;

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_mall_goods_comment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        transfer = Transferee.getDefault(mAty);
        rvRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        goodId = bundle.getString("id");
        mDataList = new ArrayList<>();
        rvRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        //初始化适配器
        mallGoodsCommentAdapter = new MallGoodsCommentAdapter(mDataList, transfer);
        //无数据显示空
        mallGoodsCommentAdapter.setEmptyView(LayoutInflater.from(mAty).inflate(R.layout.layout_empty_view, rvRecycler, false));
        //绑定适配器
        rvRecycler.setAdapter(mallGoodsCommentAdapter);
        //关闭下拉刷新
        refreshLayout.setEnableRefresh(false);
        //开启上拉加载
        refreshLayout.setEnableLoadMore(true);
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

    //试一下
    @Override
    public void onDestroy() {
        super.onDestroy();
        transfer.destroy();
    }

    @Override
    protected void getDataFromServer() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goods_id", goodId);
//        JSONObject jsonObj=new JSONObject(map);
        String s = JSON.toJSONString(map);
        HttpRxObservable.getObservable(ApiUtils.getApiService().getgoodsevaluationlist("mg_comment_list", page, s)).subscribe(new BaseObserver<MallGoodsCommentBean>(mAty) {
            @Override
            public void onHandleSuccess(MallGoodsCommentBean mallGoodsCommentBean) throws IOException {
                List<MallGoodsCommentBean.DataBean> dataBeans = mallGoodsCommentBean.getData();
                if (page == 1) {
                    mDataList.clear();
                }
                mDataList.addAll(dataBeans);
                mallGoodsCommentAdapter.notifyDataSetChanged();
            }
        });
    }
}
