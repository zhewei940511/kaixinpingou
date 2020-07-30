package com.laojiashop.laojia.adapter;

import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.HomePageBean;

import java.util.List;

public class AdnewAdapter extends BaseQuickAdapter<HomePageBean.AdNewBean,BaseViewHolder> {
    public AdnewAdapter() {
        super(R.layout.item_adnew);
    }
    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomePageBean.AdNewBean item) {
        helper.setText(R.id.nameTxt,item.getTitle());
        Glide.with(helper.itemView.getContext())
                .load(item.getPath())
                .apply(new RequestOptions().placeholder(R.drawable.ic_placeholder))
                .into((ImageView) helper.itemView.findViewById(R.id.iconImg));
    }
}
