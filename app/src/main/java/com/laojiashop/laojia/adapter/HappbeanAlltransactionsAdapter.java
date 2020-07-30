package com.laojiashop.laojia.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.MeHappyListBean;

import java.util.ArrayList;
import java.util.List;

public class HappbeanAlltransactionsAdapter extends BaseQuickAdapter<MeHappyListBean.DataBean, BaseViewHolder> {
    public HappbeanAlltransactionsAdapter(ArrayList<MeHappyListBean.DataBean> dataList) {
        super(R.layout.item_alltransactionsfragment, dataList);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MeHappyListBean.DataBean item) {
        helper.setText(R.id.tv_typetxt, item.getType_txt());
        helper.setText(R.id.tv_tagstr,item.getTag_str());
        helper.setText(R.id.tv_score,item.getScore());
        helper.setText(R.id.tv_ordernum,item.getOrder_no());
        helper.setText(R.id.tv_createtime,item.getCreate_time());
    }
}
