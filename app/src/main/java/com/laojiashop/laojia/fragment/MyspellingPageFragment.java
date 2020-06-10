package com.laojiashop.laojia.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.fragment.MyspellingPage.AbulkFragment;
import com.laojiashop.laojia.fragment.MyspellingPage.SpellgroupFragment;
import com.laojiashop.laojia.utils.BarUtils;
import com.laojiashop.laojia.utils.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的拼团页面
 */
public class MyspellingPageFragment extends BaseFragment {
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.title_view)
    FrameLayout mTitleView;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tv_screen)
    TextView tvScreen;
    @BindView(R.id.ll_sorting_way)
    LinearLayout llSortingWay;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private PopupWindow mPopWindow;

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_myspelling;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        int barHeight = StatusBarUtil.getStatusBarHeight(mAty);
        if (barHeight > 0) {
            //设置状态栏的高度
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mTitleView.getLayoutParams();
            layoutParams.topMargin = BarUtils.getStatusBarHeight(mAty) + layoutParams.topMargin;
            mTitleView.setLayoutParams(layoutParams);
        }
        fragments.add(new SpellgroupFragment());
        fragments.add(new AbulkFragment());
        tabLayout.setViewPager(viewPager, new String[]{"拼团", "团购"}, getActivity(), fragments);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getDataFromServer() {

    }

    @OnClick({R.id.tv_screen, R.id.ll_sorting_way})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_screen:
                break;
            case R.id.ll_sorting_way:
                if (mPopWindow != null && !mPopWindow.isShowing()) {
                    mPopWindow.showAsDropDown(llSortingWay, 0, 0);
                }
                break;
        }
    }
    //初始化popuwindow
    private void initPopuWindow(View contentView, TextView textView) {
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);

        //设置背景色
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.8f;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);

        //popupWindow获取焦点
        mPopWindow.setFocusable(true);
        mPopWindow.setAnimationStyle(R.style.popmenu_animation); //动画
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //设置popupWindow消失时的监听
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            //在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
                textView.setTextColor(getResources().getColor(R.color.color_common_font));
            }
        });
    }
}
