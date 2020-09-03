package com.laojiashop.laojia.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.gyf.immersionbar.components.SimpleImmersionOwner;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.TuanGouDetailsBean;
import com.laojiashop.laojia.fragment.MallGoodsCommentFragment;
import com.laojiashop.laojia.fragment.MallGoodsDetailFragment;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.ScreenUtil;
import com.laojiashop.laojia.utils.ToastUtil;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.stx.xhb.xbanner.XBanner;
import com.tencent.mm.opensdk.utils.Log;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 拼团详情
 */
public class SpellgroupdetailsActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.tv_goodstitle)
    TextView tvGoodstitle;
    @BindView(R.id.tv_goodsubtitletitle)
    TextView tvGoodsubtitletitle;
    @BindView(R.id.img_collection)
    ImageView imgCollection;
    @BindView(R.id.tv_mall_collection)
    TextView tvMallCollection;
    @BindView(R.id.mall_goods_collection)
    LinearLayout mallGoodsCollection;
    @BindView(R.id.btn_spellasingle)
    Button btn_Spellasingle;
    @BindView(R.id.rv_showpicheadimg)
    RecyclerView rvShowpicheadimg;
    //价格
    @BindView(R.id.tv_showprice)
    TextView tvShowprice;
    @BindView(R.id.tv_showmarkprice)
    TextView tvShowmarkprice;
    //时分秒
    @BindView(R.id.tv_hoursTxt)
    TextView tvHoursTxt;
    @BindView(R.id.tv_minutesTxt)
    TextView tvMinutesTxt;
    @BindView(R.id.tv_secondsTxt)
    TextView tvSecondsTxt;
    //定义变量接收
    private String id, nstr;
    private XBanner bannertops;
    private List<Fragment> list = new ArrayList<>();
    private FixedIndicatorView mIndicator;
    private ViewPager mViewPager;
    //商品ID
    private String goodsid;
    //是否收藏
    private boolean isCollection;
    //团购头像适配器
 //   private TuangouPicAdapter tuangouPicAdapter;
    //团购头像数据源
   // private List<TuanGouDetailsBean.CountInfoBean.ListBean> listBeans;
    //转换过后的时间字符串
    private String formatLongToTimeStr;
    //时间集合
    private String[] split;
    //时间
    private long time;
     //定义变量传值
    private TuanGouDetailsBean tuanGouDetailsBeans;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_spellgroupdetails);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("拼团详情");
        id = getIntent().getStringExtra("id");
        nstr = getIntent().getStringExtra("nstr");
        goodsid = getIntent().getStringExtra("goodsid");
        mIndicator = findViewById(R.id.indicator);
        mViewPager = findViewById(R.id.viewPager);
        bannertops = findViewById(R.id.bannertop);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtil.getScreenWidth(mAt) / 2);
        bannertops.setLayoutParams(layoutParams);
        //加载数据
        initBanner(bannertops);
        //开启线程
        handler.postDelayed(runnable, 1000);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //商品介绍
        MallGoodsDetailFragment mallGoodsDetailFragment = new MallGoodsDetailFragment();
        Bundle detailBundle = new Bundle();
        detailBundle.putString("id", goodsid);
        mallGoodsDetailFragment.setArguments(detailBundle);
        list.add(mallGoodsDetailFragment);
        //商品评论
        MallGoodsCommentFragment mallGoodsCommentFragment = new MallGoodsCommentFragment();
        Bundle commentBundle = new Bundle();
        commentBundle.putString("id", goodsid);
        mallGoodsCommentFragment.setArguments(commentBundle);
        list.add(mallGoodsCommentFragment);
        IndicatorViewPager indicatorViewPager = new IndicatorViewPager(mIndicator, mViewPager);
        indicatorViewPager.setAdapter(adapter);
        //设置滑动时的那一项的图形和颜色变化，ColorBar对应的是下划线的形状。
        mIndicator.setScrollBar(new ColorBar(getApplicationContext(), Color.parseColor("#FF666C"), 5));
        mViewPager.setOffscreenPageLimit(1);//缓存
        mViewPager.setCurrentItem(0, true);
    }

    @Override
    public void getDataFromServer() {
        //获取拼团详情
        HttpRxObservable.getObservable(ApiUtils.getApiService().getTuanGoudetail(id, nstr)).subscribe(new BaseObserver<TuanGouDetailsBean>(mAt) {
            @Override
            public void onHandleSuccess(TuanGouDetailsBean tuanGouDetailsBean) throws IOException {
                tuanGouDetailsBeans=tuanGouDetailsBean;
                //轮播图数据源
                List<TuanGouDetailsBean.ImgsBean> imgBeans = tuanGouDetailsBean.getImgs();
                time = tuanGouDetailsBean.getEtime();
                //listBeans = tuanGouDetailsBean.getCount_info().getList();
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                rvShowpicheadimg.setLayoutParams(lp);
//                tuangouPicAdapter = new TuangouPicAdapter(listBeans);
//                rvShowpicheadimg.setAdapter(tuangouPicAdapter);
                //刷新数据之后，需要重新设置是否支持自动轮播  哪里报错就是这个页面的问题
                bannertops.setAutoPlayAble(imgBeans.size() > 1);
                bannertops.setIsClipChildrenMode(true);
                bannertops.setBannerData(imgBeans);
                tvGoodstitle.setText(tuanGouDetailsBean.getTitle());
                tvGoodsubtitletitle.setText(tuanGouDetailsBean.getStitle());
                tvShowprice.setText(String.valueOf(tuanGouDetailsBean.getPrice()));
                tvShowmarkprice.setText(String.valueOf(tuanGouDetailsBean.getMarket_price()) + "商城价");
                tvShowmarkprice.getPaint().setAntiAlias(true);//抗锯齿
                tvShowmarkprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                isCollection = tuanGouDetailsBean.isIs_act();
                //判断商品是否收藏
                if (isCollection == true) {
                    tvMallCollection.setText("已收藏");
                    imgCollection.setImageResource(R.mipmap.icon_collection);

                } else {
                    tvMallCollection.setText("收藏");
                    imgCollection.setImageResource(R.mipmap.icon_uncollection);
                }
            }

        });
    }

    //线程
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            //获取后台返回的时间每次减1000毫秒
            time = time - 1000;
            if (time >=0) {
                formatLongToTimeStr = getTime(time);
                split = formatLongToTimeStr.split(":");
                for (int i = 0; i < split.length; i++) {
                    if (i == 0) {
                        tvHoursTxt.setText(split[0] + "小时");
                    }
                    if (i == 1) {
                        tvMinutesTxt.setText(split[1] + "分钟");
                    }
                    if (i == 2) {
                        tvSecondsTxt.setText(split[2] + "秒");
                    }
                }
                // Log.d("xxx-------->", "转换后时间: " + formatLongToTimeStr);
                handler.postDelayed(this, 1000);
            } else {
                handler.removeCallbacksAndMessages(null);
            }

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //界面销毁回收线程
        handler.removeCallbacksAndMessages(null);
    }

    /**
     * 指示器适配器对形象
     */
    public IndicatorViewPager.IndicatorFragmentPagerAdapter adapter = new IndicatorViewPager.IndicatorFragmentPagerAdapter(getSupportFragmentManager()) {
        private String[] tabNames = {"商品介绍", "产品评价"};

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

    /**
     * 加载图片
     */
    private void initBanner(XBanner banner) {
        //设置广告图片点击事件
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                //判断是点击了那个选项
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                }

            }
        });
        //加载广告图片
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //就算不加载也会出问题
                TuanGouDetailsBean.ImgsBean banners = (TuanGouDetailsBean.ImgsBean) model;
                //一个占位图就够，不用写error。
                Glide.with(mAt).load(banners.getUrl()).into((ImageView) view);
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    //我先给你举个例子，你需要啥自己加
    public static void invoke(Activity activity, String id, String nstr, String goodsid) {
        Intent intent = new Intent(activity, SpellgroupdetailsActivity.class);
        //cao.//
        intent.putExtra("id", id);
        intent.putExtra("nstr", nstr);
        intent.putExtra("goodsid", goodsid);
        activity.startActivity(intent);
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.mall_goods_collection, R.id.btn_spellasingle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //收藏
            case R.id.mall_goods_collection:
                if (!isCollection) {
                    tvMallCollection.setText("已收藏");
                    imgCollection.setImageResource(R.mipmap.icon_collection);
                    isCollection = true;
                } else {
                    tvMallCollection.setText("收藏");
                    imgCollection.setImageResource(R.mipmap.icon_uncollection);
                    isCollection = false;
                }
                HttpRxObservable.getObservable(ApiUtils.getApiService().getaddcollect("Collect", goodsid)).subscribe(new BaseObserver<Object>(mAt) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        ToastUtil.showToast("收藏成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtil.showToast(e.getMessage());
                    }
                });
                break;
            //拼一单
            case R.id.btn_spellasingle:
                //jumpActivity(SpellgroupGotoToconfirmorderActivity.class);
                SpellgroupGotoToconfirmorderActivity.invoke(mAt,tuanGouDetailsBeans);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //时间戳转时分秒
    public String getCurrentTime(long value) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date(value * 1000L));
        Log.d("xxx-------->", "转换后时间: " + time);
        return time;
    }

    //秒数转时分秒
    public static String getTime(long second) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String hms = formatter.format(second);
        Log.d("xxx-------->", "转换后时间: " + hms);
        return hms;
    }
//    public  String formatLongToTimeStr(Long l) {
//        int hour = 0;
//        int minute = 0;
//        int second = 0;
//        second = l.intValue() ;
//        if (second > 60) {
//            minute = second / 60;         //取整
//            second = second % 60;         //取余
//        }
//
//        if (minute > 60) {
//            hour = minute / 60;
//            minute = minute % 60;
//        }
//        String strtime = hour+"："+minute+"："+second;
//        Log.d("xxx-------->", "转换后时间: " + strtime);
//        return strtime;
//    }

}
