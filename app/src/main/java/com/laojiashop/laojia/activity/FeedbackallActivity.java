package com.laojiashop.laojia.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.fragment.FeedbackFragment;
import com.laojiashop.laojia.fragment.MeShopFragment;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 意见反馈界面
 */
public class FeedbackallActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;

    private FixedIndicatorView indicator;
    //碎片集合
    private List<Fragment> list;
    private ViewPager viewPager;
    //第三方指示器
    private IndicatorViewPager indicatorViewPager;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_feedbackall);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("待处理");
        indicator = (FixedIndicatorView) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        list = new ArrayList<Fragment>();
        Fragment meFeedbackFragment1 = new FeedbackFragment("1");
        Fragment meFeedbackFragment2 = new FeedbackFragment("2");
        Fragment meFeedbackFragment3 = new FeedbackFragment("3");
        Fragment meFeedbackFragment4 = new FeedbackFragment("4");

        list.add(meFeedbackFragment1);
        list.add(meFeedbackFragment2);
        list.add(meFeedbackFragment3);
        list.add(meFeedbackFragment4);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(adapter);
        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.parseColor("#FF666C"), 5));
        viewPager.setOffscreenPageLimit(5);//缓存的左右页面的个数都是
        viewPager.setCurrentItem(0, true);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tvHeaderTitle.setText("待处理");
                        break;
                    case 1:
                        tvHeaderTitle.setText("处理中");
                        break;
                    case 2:
                        tvHeaderTitle.setText("待反馈");
                        break;
                    case 3:
                        tvHeaderTitle.setText("已完成");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    /**
     * 指示器适配器对形象
     */
    public IndicatorViewPager.IndicatorFragmentPagerAdapter adapter = new IndicatorViewPager.IndicatorFragmentPagerAdapter(getSupportFragmentManager()) {
        private String[] tabNames = { "待处理", "处理中", "待反馈", "已完成"};

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            //此方法设置的tab的页面和显示
            if (convertView == null) {
                convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.tab,
                        container, false);
            }
            TextView tv = (TextView) convertView;
            tv.setText(tabNames[position]);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            //设置viewpager下的页面
            Fragment fragment = list.get(position);
            return fragment;
        }
    };
    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }
}
