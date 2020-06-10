package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.widget.Button;
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
import com.laojiashop.laojia.fragment.HappybeanPage.HasendedFragment;
import com.laojiashop.laojia.fragment.HappybeanPage.OngoingFragment;
import com.laojiashop.laojia.fragment.MyspellingPage.SpellgroupFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 我得金币界面
 */
public class GoldcoinsActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.btn_withdrawal)
    Button btnWithdrawal;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    //设置适配器
    private MyPagerAdapter myPagerAdapter;
    //标题
    private String[] mTitles = {"全部交易", "拼团奖励", "导购奖励", "培育奖励", "团购奖励"};

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_goldcoin);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("我的金币");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        fragments.add(new AlltransactionsFragment());
        fragments.add(new SpellgrouprewardsFragment());
        fragments.add(new SpellgrouprewardsFragment());
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


    //提现按钮
    @OnClick(R.id.btn_withdrawal)
    public void onViewClicked() {
        jumpActivity(WithdrawalActivity.class);
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
