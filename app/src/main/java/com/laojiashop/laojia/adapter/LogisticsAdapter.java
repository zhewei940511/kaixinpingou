package com.laojiashop.laojia.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Trace;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.LogisticsBean;

import java.util.ArrayList;
import java.util.List;

import static androidx.recyclerview.widget.RecyclerView.*;

public class LogisticsAdapter extends BaseQuickAdapter<LogisticsBean, BaseViewHolder> {

//    private LayoutInflater inflater;
//    private List<LogisticsBean> traceList = new ArrayList<>(1);
//    private static final int TYPE_TOP = 0x0000;
//    private static final int TYPE_NORMAL = 0x0001;

    public LogisticsAdapter() {
        super(R.layout.item_trace);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, LogisticsBean item) {
        int position = helper.getLayoutPosition();
        //TYPE_TOP
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
        helper.setText(R.id.tvAcceptTime, item.getAcceptTime());
        helper.setText(R.id.tvAcceptStation, item.getAcceptStation());
    }

}
