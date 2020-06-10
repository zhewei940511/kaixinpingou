package com.laojiashop.laojia.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.fragment.HappybeanPage.HasendedFragment;
import com.laojiashop.laojia.fragment.HappybeanPage.OngoingFragment;
import com.laojiashop.laojia.fragment.MyspellingPage.SpellgroupFragment;
import com.laojiashop.laojia.utils.BarUtils;
import com.laojiashop.laojia.utils.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 开心豆界面
 */
@SuppressLint("ValidFragment")
public class HappybeanPageFragment extends BaseFragment {
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayouts;
    @BindView(R.id.title_view)
    FrameLayout mTitleView;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private ArrayList<Fragment> fragments=new ArrayList<>();
    //设置适配器
    private MyPagerAdapter mAdapter;
    //标题
    private String[] mTitles = {"进行中", "已结束"};
    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_happybean;
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
        mAdapter=new MyPagerAdapter(getChildFragmentManager());
        fragments.add(new OngoingFragment());
        fragments.add(new HasendedFragment());
        viewPager.setAdapter(mAdapter);
        tabLayouts.setViewPager(viewPager);
        //tabLayouts.setViewPager(viewPager, new String[]{"进行中", "已结束"}, getActivity(), fragments);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getDataFromServer() {

    }
    //vp适配器
    private  class  MyPagerAdapter extends FragmentPagerAdapter
    {

        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
