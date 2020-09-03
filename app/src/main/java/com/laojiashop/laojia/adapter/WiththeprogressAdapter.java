package com.laojiashop.laojia.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.FeedbackDetailBean;

import java.util.List;

public class WiththeprogressAdapter extends BaseQuickAdapter<FeedbackDetailBean.LogBean, BaseViewHolder> {
    public WiththeprogressAdapter() {
        super(R.layout.item_trace);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FeedbackDetailBean.LogBean item) {
        int position = helper.getLayoutPosition();
        if (position == 0) {
            // 第一行头的竖线不显示
            helper.setVisible(R.id.tvTopLine, false);
            // 字体颜色加深
            helper.setTextColor(R.id.tvAcceptTime, 0xff555555);
            helper.setTextColor(R.id.tvAcceptStation, 0xff555555);
            helper.setBackgroundRes(R.id.tvDot, R.drawable.red_oval);

        } else {

            //TYPE_NORMAL
            helper.setVisible(R.id.tvTopLine, true);
            helper.setTextColor(R.id.tvAcceptTime, 0xff999999);
            helper.setTextColor(R.id.tvAcceptStation, 0xff999999);
            helper.setBackgroundRes(R.id.tvDot, R.drawable.gray_oval);
        }
        helper.setText(R.id.tvAcceptTime, item.getCreate_time());
        helper.setText(R.id.tvAcceptStation, item.getContent());
    }
}
