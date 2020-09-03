package com.laojiashop.laojia.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.laojiashop.laojia.R;

public class SharePopwindow extends PopupWindow {
    private View mview;
    public SharePopwindow(Activity context, View.OnClickListener itemsonclick) {
        super(context);
        initview(context,itemsonclick);
    }

    private void initview(Activity context, View.OnClickListener itemsonclick) {
        LayoutInflater minflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        mview = minflater.inflate(R.layout.popwindow_share, null);
        LinearLayout lywechat = (LinearLayout) mview.findViewById(R.id.ly_wechat);
        LinearLayout lycircleoffriends = (LinearLayout) mview.findViewById(R.id.ly_circleoffriends);
        TextView canaletv = (TextView) mview.findViewById(R.id.share_cancle);
        canaletv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                backgroundalpha(context, 1f);
            }
        });
        //设置按钮监听
        lywechat.setOnClickListener(itemsonclick);
        lycircleoffriends.setOnClickListener(itemsonclick);
        this.setContentView(mview);
        //设置selectpicpopupwindow弹出窗体的宽
        this.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        //设置selectpicpopupwindow弹出窗体的高
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //设置selectpicpopupwindow弹出窗体可点击
        this.setFocusable(true);
        //设置popupwindow可触摸
        this.setTouchable(true);
        this.setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundalpha(context, 1f);
            }
        });

    }
    /**
     * 设置添加屏幕的背景透明度
     */
    public void backgroundalpha(Activity context, float bgalpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgalpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }


}
