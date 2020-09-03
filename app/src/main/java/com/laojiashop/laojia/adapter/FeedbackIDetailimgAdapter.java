package com.laojiashop.laojia.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.FeedbackDetailBean;

import java.util.List;

public class FeedbackIDetailimgAdapter extends BaseQuickAdapter<FeedbackDetailBean.ImgsBean, BaseViewHolder> {
    public FeedbackIDetailimgAdapter() {
        super(R.layout.fragmentmallgoodsitemimg);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FeedbackDetailBean.ImgsBean item) {
        Glide.with(mContext).load(item.getUrl()).into((ImageView) helper.getView(R.id.imgbgall));
    }
}
