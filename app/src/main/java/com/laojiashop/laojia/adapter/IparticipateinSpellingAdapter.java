package com.laojiashop.laojia.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.MySpellingBean;

import java.util.List;

public class IparticipateinSpellingAdapter extends BaseQuickAdapter<MySpellingBean.DataBean, BaseViewHolder> {
    public IparticipateinSpellingAdapter(List<MySpellingBean.DataBean> data) {
        super(R.layout.item_fragmentiparticipateinspelling, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MySpellingBean.DataBean item) {
        Glide.with(mContext).load(item.getPath()).apply(new RequestOptions().placeholder(R.mipmap.default_userhead_image)).into((ImageView) helper.getView(R.id.img_pateinspellpic));
        helper.setText(R.id.tv_pateinspellno,"团号:"+item.getId());
        helper.setText(R.id.tv_pateinspellstatus,item.getStatus_txt());
        helper.setText(R.id.tv_pateinspelltitle,item.getTitle());
        helper.setText(R.id.tv_pateinspellgroupprice,item.getPrice());
        helper.setText(R.id.tv_pateinretailprice,item.getMarket_price());
        helper.setText(R.id.tv_pateinspellstitle,item.getStitle());
    }
}
