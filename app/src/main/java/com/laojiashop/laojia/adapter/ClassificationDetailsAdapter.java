package com.laojiashop.laojia.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.ClassificationDetailsBean;

import java.util.List;

public class ClassificationDetailsAdapter extends BaseQuickAdapter<ClassificationDetailsBean.DataBean, BaseViewHolder> {
    public ClassificationDetailsAdapter(@Nullable List<ClassificationDetailsBean.DataBean> data) {
        super(R.layout.item_classificationdetailsrv, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ClassificationDetailsBean.DataBean item) {
        helper.setText(R.id.tv_classificatitle,item.getTitle()).setText(R.id.tv_classificacontent,item.getStitle()).setText(R.id.tv_classificaprice,item.getShow_price().getMin());
        Glide.with(mContext).load(item.getPath()).apply(new RequestOptions().placeholder(R.drawable.default_image)).into((ImageView) helper.getView(R.id.img_classificaimg));
    }
}
