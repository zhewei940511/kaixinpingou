package com.laojiashop.laojia.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.ReceivedividendsBean;

import java.util.List;

public class ReceivedividendsAdapter extends BaseQuickAdapter<ReceivedividendsBean.DataBean, BaseViewHolder> {
    private String bonus_exit_bonuss;

    public ReceivedividendsAdapter(List<ReceivedividendsBean.DataBean> data) {
        super(R.layout.item_receivedividends, data);

    }

    public void getValue(String bonus_exit_bonus){
        this.bonus_exit_bonuss = bonus_exit_bonus;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, ReceivedividendsBean.DataBean item) {
        helper.setText(R.id.tv_receivedividends_orderid, "订单编号:" + item.getId());
        helper.addOnClickListener(R.id.btn_bonusexitbonuss);
        helper.setText(R.id.tv_receivedividends_orderstatustxt, item.getStatus_txt());
        helper.setText(R.id.tv_receivedividends_ordernum, "数量:" + String.valueOf(item.getNum()));
        helper.setText(R.id.tv_receivedividends_orderscore, "消耗开心豆:" + String.valueOf(item.getScore()));
        helper.setText(R.id.tv_receivedividends_ordernexttime, "下次分红时间:" + item.getNtime());
        helper.setText(R.id.tv_receivedividends_ordercreatetime, "兑换时间:" + item.getCreate_time());
        //判断退出分红权开关按钮
        if (Integer.parseInt(bonus_exit_bonuss) == 1) {
            //判断当前状态是否显示
            if (item.getStatus()==1)
            {
                helper.setVisible(R.id.btn_bonusexitbonuss, true);
            }else {
                helper.setGone(R.id.btn_bonusexitbonuss, false);
            }
        } else {
            helper.setGone(R.id.btn_bonusexitbonuss, false);
        }
    }
}
