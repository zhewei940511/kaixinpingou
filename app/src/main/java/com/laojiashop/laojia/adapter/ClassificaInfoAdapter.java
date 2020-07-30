package com.laojiashop.laojia.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.ClassificationPageBean;

import java.util.List;

public class ClassificaInfoAdapter extends BaseQuickAdapter<ClassificationPageBean.SonBean, BaseViewHolder> {
    public ClassificaInfoAdapter(int layoutResId, @Nullable List<ClassificationPageBean.SonBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ClassificationPageBean.SonBean item) {
        helper.setText(R.id.tv_classificotent,item.getTypename());
        Glide.with(mContext).load(item.getPath()).apply(new RequestOptions().placeholder(R.mipmap.default_userhead_image)).into((ImageView) helper.getView(R.id.img_classificapic));
    }
}
