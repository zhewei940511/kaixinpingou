package com.laojiashop.laojia.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.GoldcoinsBean;

import java.util.List;

/**
 * 金币明细列表
 */
public class GoldcoinsAdapter extends BaseQuickAdapter<GoldcoinsBean.DataBean, BaseViewHolder> {
    public GoldcoinsAdapter( List<GoldcoinsBean.DataBean> data) {
        super(R.layout.item_alltransactionsfragment, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoldcoinsBean.DataBean item) {
        helper.setText(R.id.tv_typetxt,item.getType_txt())
                .setText(R.id.tv_tagstr,item.getTag_str())
                .setText(R.id.tv_score,item.getCoin())
                .setText(R.id.tv_remark,item.getRemark())
                .setText(R.id.tv_createtime,item.getCreate_time());
    }
}
