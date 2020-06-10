package com.laojiashop.laojia.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;

import java.util.List;

public class FragmentmallgoodsItemAdapter extends BaseQuickAdapter<HotstyletorecommendBean, BaseViewHolder> {

    public FragmentmallgoodsItemAdapter() {
        super(R.layout.fragmentmallgoodsitemimg);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HotstyletorecommendBean item) {

    }
}
