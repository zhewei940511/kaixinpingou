package com.laojiashop.laojia.adapter;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.publish.Category;

import java.util.List;

/**
 * 商品分类左边列表适配器
 */
public class ClassificationLeftAdapter extends BaseQuickAdapter<Category, BaseViewHolder> {

    public ClassificationLeftAdapter(int layoutResId, @Nullable List<Category> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Category item) {
        if (!TextUtils.isEmpty(item.getCate_name())) {
            helper.setText(R.id.item_navigation_tv, item.getCate_name());
        }
    }
}
