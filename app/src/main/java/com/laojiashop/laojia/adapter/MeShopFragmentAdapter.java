package com.laojiashop.laojia.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;

import java.util.List;

public class MeShopFragmentAdapter extends BaseQuickAdapter<HotstyletorecommendBean, BaseViewHolder> {
    public MeShopFragmentAdapter() {
        super(R.layout.item_meshop);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HotstyletorecommendBean item) {
            //点击事件
            helper.addOnClickListener(R.id.btn_orderdetails);
    }
}
