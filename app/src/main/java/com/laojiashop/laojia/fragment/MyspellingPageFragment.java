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
import com.laojiashop.laojia.fragment.MyspellingPage.IparticipateinSpellingFragment;
import com.laojiashop.laojia.fragment.MyspellingPage.IstartedSpellingFragment;
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
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private int showTag;

    public MyspellingPageFragment(int showTag) {
        this.showTag = showTag;
    }

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
        fragments.add(new IparticipateinSpellingFragment());
        fragments.add(new IstartedSpellingFragment());
        tabLayout.setViewPager(viewPager, new String[]{"我参与的", "我发起的"}, getActivity(), fragments);
        if (showTag == 1) {
            viewPager.setCurrentItem(1);
        }else {
            viewPager.setCurrentItem(0);
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getDataFromServer() {

    }
}
