package com.laojiashop.laojia.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
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
 * 商城订单界面
 */
public class ShoporderActivity extends BaseActivity {
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
        setContentView(R.layout.activity_shoporder);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("全部订单");
        //这个FixedindicatorView是平分tab的屏幕长度的
        indicator = (FixedIndicatorView) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        list = new ArrayList<Fragment>();
        Fragment meShopFragment1 = new MeShopFragment("0");
        Fragment meShopFragment2 = new MeShopFragment("1");
        Fragment meShopFragment3 = new MeShopFragment("2");
        Fragment meShopFragment4 = new MeShopFragment("3");
        Fragment meShopFragment5 = new MeShopFragment("4");
//        Fragment meShopFragment6 = new MeShopFragment("90");
//        Fragment meShopFragment7 = new MeShopFragment("92");
        list.add(meShopFragment1);
        list.add(meShopFragment2);
        list.add(meShopFragment3);
        list.add(meShopFragment4);
        list.add(meShopFragment5);
//        list.add(meShopFragment6);
//        list.add(meShopFragment7);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(adapter);
        //设置滑动时的那一项的图形和颜色变化，ColorBar对应的是下划线的形状。
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
                        tvHeaderTitle.setText("全部订单");
                        break;
                    case 1:
                        tvHeaderTitle.setText("待付款");

                        break;
                    case 2:
                        tvHeaderTitle.setText("待发货");

                        break;
                    case 3:
                        tvHeaderTitle.setText("待收货");

                        break;
                    case 4:
                        tvHeaderTitle.setText("已签收");
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
        String flag = getIntent().getStringExtra("index");
        switch (flag) {
            //全部订单
            case "1":
                viewPager.setCurrentItem(0, true);
                break;
            //待付款
            case "2":
                viewPager.setCurrentItem(1, true);
                break;
            //待发货
            case "3":
                viewPager.setCurrentItem(2, true);
                break;
            //待收货
            case "4":
                viewPager.setCurrentItem(3, true);
                break;
            //已签收
            case "5":
                viewPager.setCurrentItem(4, true);
                break;
            //已取消
//            case "6":
//                viewPager.setCurrentItem(5, true);
//                break;
//            case "7":
//                viewPager.setCurrentItem(6, true);
//                break;
        }

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
        private String[] tabNames = {"全部订单", "待付款", "待发货", "待收货", "已签收"};

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
