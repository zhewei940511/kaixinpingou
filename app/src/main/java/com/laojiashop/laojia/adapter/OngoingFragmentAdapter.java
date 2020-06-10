package com.laojiashop.laojia.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;

import java.util.List;

public class OngoingFragmentAdapter extends BaseQuickAdapter<HotstyletorecommendBean, BaseViewHolder> {
    public OngoingFragmentAdapter(int layoutResId, @Nullable List<HotstyletorecommendBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HotstyletorecommendBean item) {

    }
}
