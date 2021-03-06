package com.laojiashop.laojia.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.entity.MycollectionBean;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;

public class MycollectionAdapter extends BaseQuickAdapter<MycollectionBean.DataBean, BaseViewHolder> {
    private List<String> tag=new ArrayList<>();

    public MycollectionAdapter( @Nullable List<MycollectionBean.DataBean> data) {
        super(R.layout.item_mycollection, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MycollectionBean.DataBean item) {
        Glide.with(mContext).load(item.getPath()).apply(new RequestOptions().placeholder(R.mipmap.default_userhead_image)).into((ImageView) helper.getView(R.id.img_collectionpic));
        //添加点击事件
        helper.addOnClickListener(R.id.btn_delectcollection);
        helper.setText(R.id.tv_collectiontitle,item.getGoods_name());
        if (item.getStatus()==1)
        {
            helper.setText(R.id.tv_goodsstatus,"在售");
        }
        else
        {
            helper.setText(R.id.tv_goodsstatus,"已下架");
        }
        //判断显示区间价
        helper.setText(R.id.tv_collectionprice,"￥"+item.getShow_price().getMin());
        tag=item.getTags();
        TagContainerLayout tagContainerLayout=helper.getView(R.id.tga_mycollection);
        tagContainerLayout.setTags(tag);
        helper.addOnClickListener(R.id.btn_delectcollection);
    }
}
