package com.laojiashop.laojia.adapter;

import android.graphics.Color;
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
 * 商品分类右边适配器
 */
public class ClassificationRightAdapter extends BaseQuickAdapter<Category, BaseViewHolder> {
    public ClassificationRightAdapter(int layoutResId, @Nullable List<Category> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Category item) {
        if (!TextUtils.isEmpty(item.getCate_name()))
        {
            helper.setText(R.id.item_navigation_tv, item.getCate_name());
        }

        if (item.isSelect()) {
            helper.setTextColor(R.id.item_navigation_tv, Color.parseColor("#F44336"));
           // helper.setBackgroundRes(R.id.item_navigation_tv, R.drawable.bg_jiaonang_select_strock);
        } else {
            helper.setTextColor(R.id.item_navigation_tv, Color.parseColor("#6F6F6F"));
           // helper.setBackgroundRes(R.id.item_navigation_tv, R.drawable.bg_jiaonang_unselect_strock);
        }
    }
}
