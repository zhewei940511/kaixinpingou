package com.laojiashop.laojia.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.AddressmanagementBean;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;

import java.util.List;

public class AddressmanagementAdapter extends BaseQuickAdapter<AddressmanagementBean.DataBean, BaseViewHolder> {
    public AddressmanagementAdapter(@Nullable List<AddressmanagementBean.DataBean> data) {
        super(R.layout.item_address_management, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, AddressmanagementBean.DataBean item) {
        helper.addOnClickListener(R.id.img_editaddress);
        helper.addOnClickListener(R.id.tv_delectaddress);
        helper.addOnClickListener(R.id.content);
       // helper.addOnClickListener(R.id.ly_addressinfo);
        helper.setText(R.id.tv_addressusername,item.getName())
                .setText(R.id.tv_addressuserphone,item.getPhone())
                .setText(R.id.tv_addressusercontent,item.getSsq());
        if (item.getIs_def()==1)
        {
            helper.getView(R.id.img_selecticon).setVisibility(View.VISIBLE);
        }else
        {
            helper.getView(R.id.img_selecticon).setVisibility(View.GONE);
        }

    }

}
