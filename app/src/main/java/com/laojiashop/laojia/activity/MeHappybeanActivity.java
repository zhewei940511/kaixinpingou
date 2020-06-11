package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.fragment.Goldcoins.AlltransactionsFragment;
import com.laojiashop.laojia.fragment.Goldcoins.SpellgrouprewardsFragment;
import com.laojiashop.laojia.fragment.Happybean.HappbeanAlltransactionsFragment;
import com.laojiashop.laojia.fragment.Happybean.HappybeanSpellgrouprewardsFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我的开心豆界面
 */
public class MeHappybeanActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tv_header_right)
    TextView tvHeaderRight;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    private MyPagerAdapter myPagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    //标题
    private String[] mTitles = {"全部交易", "奖励", "流转", "购入"};

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_happybean);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("我的开心豆");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        fragments.add(new HappbeanAlltransactionsFragment());
        fragments.add(new HappybeanSpellgrouprewardsFragment());
        fragments.add(new SpellgrouprewardsFragment());
        fragments.add(new SpellgrouprewardsFragment());
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }

    //vp适配器
    private class MyPagerAdapter extends FragmentPagerAdapter {

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
