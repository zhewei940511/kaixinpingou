package com.laojiashop.laojia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.laojiashop.laojia.entity.MyCoinInfoBean;
import com.laojiashop.laojia.fragment.Goldcoins.AlltransactionsFragment;
import com.laojiashop.laojia.fragment.Goldcoins.SpellgrouprewardsFragment;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;

import java.io.IOException;
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
    //可用金币
    @BindView(R.id.tv_available_gold_coins)
    TextView tvAvailableGoldCoins;
    //累计收益
    @BindView(R.id.tv_accumulatedearnings)
    TextView tvAccumulatedearnings;

    private ArrayList<Fragment> fragments = new ArrayList<>();
    //设置适配器
    private MyPagerAdapter myPagerAdapter;
    //标题
    private String[] mTitles = {"全部交易", "拼团奖励", "发团津贴", "培育津贴", "团队奖","消费","退款","销售补贴","管理津贴","董事分红"};
    private double coin;
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
        fragments.add(new AlltransactionsFragment("0"));
        fragments.add(new AlltransactionsFragment("1"));
        fragments.add(new AlltransactionsFragment("2"));
        fragments.add(new AlltransactionsFragment("3"));
        fragments.add(new AlltransactionsFragment("4"));
        fragments.add(new AlltransactionsFragment("5"));
        fragments.add(new AlltransactionsFragment("6"));
        fragments.add(new AlltransactionsFragment("7"));
        fragments.add(new AlltransactionsFragment("8"));
        fragments.add(new AlltransactionsFragment("9"));
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getMyCoinInfo()).subscribe(new BaseObserver<MyCoinInfoBean>(mAt) {
            @Override
            public void onHandleSuccess(MyCoinInfoBean myCoinInfoBean) throws IOException {
                tvAvailableGoldCoins.setText(String.valueOf(myCoinInfoBean.getCoin()));
                tvAccumulatedearnings.setText(myCoinInfoBean.getTotal());
                coin=myCoinInfoBean.getCoin();
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.iv_header_back, R.id.btn_withdrawal})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            //提现按钮
            case R.id.btn_withdrawal:
                //jumpActivity(WithdrawalActivity.class);
                Intent intent=new Intent(mAt,WithdrawalActivity.class);
                intent.putExtra("coin",coin);
                startActivity(intent);
                break;
        }
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
