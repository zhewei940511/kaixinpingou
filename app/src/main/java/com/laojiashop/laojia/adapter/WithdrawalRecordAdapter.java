package com.laojiashop.laojia.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.OrderStatus;
import com.laojiashop.laojia.entity.WithdRecordStatus;
import com.laojiashop.laojia.entity.WithdrawalRecordBean;

import java.util.List;

public class WithdrawalRecordAdapter extends BaseQuickAdapter<WithdrawalRecordBean.DataBean, BaseViewHolder> {
    public WithdrawalRecordAdapter(List<WithdrawalRecordBean.DataBean> data) {
        super(R.layout.item_withdrawal_record, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, WithdrawalRecordBean.DataBean item) {
        helper.setText(R.id.tv_withdrawalamount,"提现金额:￥"+item.getPay());
        helper.setText(R.id.tv_withdrawalstate,item.getStatus_txt());
        helper.setText(R.id.tv_withdrawalservicefee,"服务费:￥"+item.getService());
        helper.setText(R.id.tv_withdrawalserialnumber,"流水号:"+item.getWx_payment_no());
        helper.setText(R.id.tv_withdrawalsubmittime,"提交时间:"+item.getCreate_time());
        helper.setText(R.id.tv_withdrawalclearingtime,"结算时间:"+item.getWx_payment_time());
        helper.setText(R.id.tv_withdrawalsettlementchannels,"结算渠道:微信零钱");
        //判断订单状态显示
        switch (item.getStatus())
        {
            //只有结算成功的状态才会有订单号和订单时间，其他的状态都隐藏
            //待审核
            case 1:
                helper.setBackgroundRes(R.id.tv_withdrawalstate,R.drawable.shape_withdrawalrecord_layoute4);
                helper.setGone(R.id.tv_withdrawalserialnumber,false);
                helper.setGone(R.id.tv_withdrawalclearingtime,false);
                break;
            //已驳回
            case 2:
                helper.setBackgroundRes(R.id.tv_withdrawalstate,R.drawable.shape_withdrawalrecord_layoutcc);
                helper.setGone(R.id.tv_withdrawalserialnumber,false);
                helper.setGone(R.id.tv_withdrawalclearingtime,false);
                break;
            //待打款
            case 3:
                helper.setBackgroundRes(R.id.tv_withdrawalstate,R.drawable.shape_withdrawalrecord_layoute4);
                helper.setGone(R.id.tv_withdrawalserialnumber,false);
                helper.setGone(R.id.tv_withdrawalclearingtime,false);
                break;
            //打款失败
            case 4:
                helper.setBackgroundRes(R.id.tv_withdrawalstate,R.drawable.shape_withdrawalrecord_layoutcc);
                helper.setGone(R.id.tv_withdrawalserialnumber,false);
                helper.setGone(R.id.tv_withdrawalclearingtime,false);
                break;
            //已退回
            case 5:
                helper.setBackgroundRes(R.id.tv_withdrawalstate,R.drawable.shape_withdrawalrecord_layoutcc);
                helper.setGone(R.id.tv_withdrawalserialnumber,false);
                helper.setGone(R.id.tv_withdrawalclearingtime,false);
                break;
            //结算成功
            case 9:
                helper.setBackgroundRes(R.id.tv_withdrawalstate,R.drawable.shape_withdrawalrecord_layout);
                break;
        }
    }
}
