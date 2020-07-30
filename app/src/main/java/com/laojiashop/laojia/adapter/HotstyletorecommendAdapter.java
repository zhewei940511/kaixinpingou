package com.laojiashop.laojia.adapter;

import android.content.Context;
import android.widget.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品评价适配器
 */
public class HotstyletorecommendAdapter extends BaseQuickAdapter<HotstyletorecommendBean, BaseViewHolder> {
    //模拟数据
    private ArrayList<HotstyletorecommendBean> mDataList;

    public HotstyletorecommendAdapter() {
        super(R.layout.item_fragmentmallgoodscomment);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HotstyletorecommendBean item) {

        //初始化内层rv适配器对象
        FragmentmallgoodsItemAdapter fragmentmallgoodsItemAdapter = new FragmentmallgoodsItemAdapter();
        mDataList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            HotstyletorecommendBean databean = new HotstyletorecommendBean();
            mDataList.add(databean);
        }
        RecyclerView recyclerView = helper.itemView.findViewById(R.id.img_rv);
        //recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        fragmentmallgoodsItemAdapter.addData(mDataList);
        recyclerView.setAdapter(fragmentmallgoodsItemAdapter);
    }
}
