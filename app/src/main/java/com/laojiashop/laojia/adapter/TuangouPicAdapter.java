package com.laojiashop.laojia.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.TuanGouDetailsBean;

import java.util.List;

//public class TuangouPicAdapter extends BaseQuickAdapter<TuanGouDetailsBean.CountInfoBean, BaseViewHolder> {
//    public TuangouPicAdapter( @Nullable List<TuanGouDetailsBean.CountInfoBean.ListBean> data) {
//        super(R.layout.item_tuangoupic, data);
//    }
//
//    @Override
//    protected void convert(@NonNull BaseViewHolder helper, TuanGouDetailsBean.CountInfoBean.ListBean item) {
//
//        Glide.with(helper.itemView.getContext())
//                .load(item.getHeadimgurl())
//                .apply(new RequestOptions().placeholder(R.drawable.ic_placeholder))
//                .into((ImageView) helper.itemView.findViewById(R.id.user_photo));
//    }
//}
