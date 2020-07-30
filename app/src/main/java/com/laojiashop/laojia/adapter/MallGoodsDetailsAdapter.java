package com.laojiashop.laojia.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.GoodsDetailBean;

import java.util.List;

public class MallGoodsDetailsAdapter extends BaseQuickAdapter<GoodsDetailBean.IntroBean, BaseViewHolder> {
    public MallGoodsDetailsAdapter(int layoutResId, @Nullable List<GoodsDetailBean.IntroBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsDetailBean.IntroBean item) {
        Glide.with(mContext).load(item.getUrl())
                .transition(new DrawableTransitionOptions().crossFade())
                .into((ImageView) helper.getView(R.id.img_goodsdetails));

    }
}
