package com.laojiashop.laojia.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.HomePageBean;
import com.laojiashop.laojia.entity.SpecialCourseSectionBean;


import java.util.List;

/**
 * 专题适配器
 */
public class SpecialAdapter extends BaseSectionQuickAdapter<SpecialCourseSectionBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public SpecialAdapter(int layoutResId, int sectionHeadResId, List<SpecialCourseSectionBean> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SpecialCourseSectionBean item) {
        helper.setText(R.id.tv_headtitle, item.header);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SpecialCourseSectionBean item) {
        HomePageBean.SpecialBean.GoodsListBean goodsListBean=item.t;
        helper.setText(R.id.tv_specialtitle,goodsListBean.getTitle())
        .setText(R.id.tv_specialcontent,goodsListBean.getStitle())
        .setText(R.id.tv_special_old_price,goodsListBean.getMarket_price())
        .setText(R.id.tv_price,goodsListBean.getPrice())
        .setText(R.id.tv_special_group_price,goodsListBean.getShow_price().min);
         Glide.with(mContext).load(goodsListBean.getPath()).apply(new RequestOptions().placeholder(R.drawable.default_image)).into((ImageView) helper.getView(R.id.img_specialimg));


    }
}
