package com.laojiashop.laojia.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.HotstyletorecommendAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 商品评论
 */
public class MallGoodsCommentFragment extends BaseFragment {
    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    //模拟数据
    private ArrayList<HotstyletorecommendBean> mDataList;
    private HotstyletorecommendAdapter hotstyletorecommendAdapter;

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_mall_goods_comment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        rvRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        mDataList = new ArrayList<>();
        hotstyletorecommendAdapter = new HotstyletorecommendAdapter();
        for (int i = 0; i < 6; i++) {
            HotstyletorecommendBean databean = new HotstyletorecommendBean();
            mDataList.add(databean);
        }
        hotstyletorecommendAdapter.addData(mDataList);
        hotstyletorecommendAdapter.openLoadAnimation();
        rvRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        rvRecycler.setAdapter(hotstyletorecommendAdapter);
    }

    @Override
    protected void getDataFromServer() {

    }
}
