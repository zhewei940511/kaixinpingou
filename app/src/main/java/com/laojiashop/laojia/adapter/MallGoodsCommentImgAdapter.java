package com.laojiashop.laojia.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.MallGoodsCommentBean;

import java.util.List;

public class MallGoodsCommentImgAdapter extends BaseQuickAdapter<MallGoodsCommentBean.DataBean.ImgsBean, BaseViewHolder> {
    public MallGoodsCommentImgAdapter() {
        super(R.layout.fragmentmallgoodsitemimg);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MallGoodsCommentBean.DataBean.ImgsBean item) {
        Glide.with(mContext).load(item.getUrl()).apply(new RequestOptions().placeholder(R.mipmap.default_userhead_image)).into((ImageView) helper.getView(R.id.imgbgall));
    }
}
