package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseListOtherActivity;
import com.laojiashop.laojia.entity.MeHappybean;
import com.laojiashop.laojia.fragment.Goldcoins.SpellgrouprewardsFragment;
import com.laojiashop.laojia.fragment.Happybean.HappbeanAlltransactionsFragment;
import com.laojiashop.laojia.fragment.Happybean.HappybeanSpellgrouprewardsFragment;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeHappybeanOtherActivity extends BaseListOtherActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tv_header_right)
    TextView tvHeaderRight;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.tv_happybeanavailable)
    TextView tvHappybeanavailable;
    @BindView(R.id.tv_happybeantotal)
    TextView tvHappybeantotal;
    @BindView(R.id.tv_happybeanhassend)
    TextView tvHappybeanhassend;
    @BindView(R.id.tv_happybeannotsend)
    TextView tvHappybeannotsend;
    @BindView(R.id.tabLayout)
    SlidingTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private MyPagerAdapter myPagerAdapter;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    //标题
    private String[] mTitles = {"全部交易", "奖励", "流转", "购入"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViews();
        getDataFromServer();
    }
    private void initViews()
    {
        tvHeaderTitle.setText("我的开心豆");
        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        fragments.add(new HappbeanAlltransactionsFragment());
        fragments.add(new HappybeanSpellgrouprewardsFragment());
        fragments.add(new SpellgrouprewardsFragment());
        fragments.add(new SpellgrouprewardsFragment());
        viewPager.setAdapter(myPagerAdapter);
        tabLayout.setViewPager(viewPager);
    }
    private  void  getDataFromServer()
    {
        HttpRxObservable.getObservable(ApiUtils.getApiService().gethappyinfo()).subscribe(new BaseObserver<MeHappybean>(mActivity) {
            @Override
            public void onHandleSuccess(MeHappybean meHappybean) throws IOException {
                tvHappybeantotal.setText(meHappybean.getTotal());
                tvHappybeanhassend.setText(meHappybean.getHas_send());
                tvHappybeannotsend.setText(meHappybean.getNot_send());
                tvHappybeanavailable.setText(meHappybean.getScore());
            }
        });
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_happybean;
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
