package com.laojiashop.laojia.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.FeedbackBean;

import java.util.List;

public class FeedbackImgAdapter extends BaseQuickAdapter<FeedbackBean.DataBean.ImgsBean, BaseViewHolder> {
    public FeedbackImgAdapter() {
        super(R.layout.fragmentmallgoodsitemimg);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FeedbackBean.DataBean.ImgsBean item) {
        Glide.with(mContext).load(item.getUrl()).into((ImageView) helper.getView(R.id.imgbgall));
    }
}
