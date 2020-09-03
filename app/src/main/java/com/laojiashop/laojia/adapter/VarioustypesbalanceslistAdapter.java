package com.laojiashop.laojia.adapter;

import android.opengl.Visibility;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.UserInfoBean;

import java.util.List;

public class VarioustypesbalanceslistAdapter extends BaseQuickAdapter<UserInfoBean.VariousTypesBalancesListBean, BaseViewHolder> {
    public VarioustypesbalanceslistAdapter( List<UserInfoBean.VariousTypesBalancesListBean> data) {
        super(R.layout.item_varioustypesbalanceslist, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, UserInfoBean.VariousTypesBalancesListBean item) {
        helper.setText(R.id.tv_varnum,String.valueOf(item.getValue()));
        helper.setText(R.id.tv_vartitle,item.getName());
        //添加事件
        helper.addOnClickListener(R.id.ly_allinfo);
//        //判断布局显示
//        if (Double.parseDouble(item.getValue())==0)
//        {
//            helper.setVisible(R.id.ly_allinfo, false);
//        }
//        else {
//            helper.setVisible(R.id.ly_allinfo, true);
//        }
    }
}
