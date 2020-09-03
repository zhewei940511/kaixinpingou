package com.laojiashop.laojia.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.LiveeventsBean;

import java.util.List;

public class LiveeventsAdapter extends BaseQuickAdapter<LiveeventsBean, BaseViewHolder> {
    public LiveeventsAdapter() {
        super(R.layout.item_liveevents);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LiveeventsBean item) {

    }
}
