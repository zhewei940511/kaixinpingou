package com.laojiashop.laojia.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.SpellgrouprecordsBean;

import java.util.List;

public class SpellgrouprecordsAdapter extends BaseQuickAdapter<SpellgrouprecordsBean.JoinListBean, BaseViewHolder> {
    public SpellgrouprecordsAdapter( List<SpellgrouprecordsBean.JoinListBean> data) {
        super(R.layout.item_spellgrouprecords, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, SpellgrouprecordsBean.JoinListBean item) {
        helper.setText(R.id.tv_groupname,item.getName());
        helper.setText(R.id.tv_grouptime,item.getCreate_time());
    }
}
