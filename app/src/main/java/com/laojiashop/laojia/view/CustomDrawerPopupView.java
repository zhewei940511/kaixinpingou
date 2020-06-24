package com.laojiashop.laojia.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.utils.BarUtils;
import com.lxj.xpopup.core.DrawerPopupView;

import butterknife.BindView;

/**
 * 重写布局
 */
public class CustomDrawerPopupView extends DrawerPopupView {

    public CustomDrawerPopupView(@NonNull Context context) {
        super(context);
    }

    /**
     * 加载布局
     *
     * @return
     */
    @Override
    protected int getImplLayoutId() {
        return R.layout.layout_customdrawerpopupview;
    }

    /**
     * 初始化
     */
    @Override
    protected void onCreate() {
        super.onCreate();
    }

    /**
     * 展示
     */
    @Override
    protected void onShow() {
        super.onShow();
    }

    /**
     * 消失
     */
    @Override
    protected void onDismiss() {
        super.onDismiss();
    }

}
