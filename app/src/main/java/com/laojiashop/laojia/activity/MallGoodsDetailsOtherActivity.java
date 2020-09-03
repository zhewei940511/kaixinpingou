package com.laojiashop.laojia.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.DetailsAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.DetailsBean;
import com.laojiashop.laojia.entity.GoodsDetailBean;
import com.laojiashop.laojia.entity.HomePageBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.DensityUtil;
import com.laojiashop.laojia.utils.ScreenUtil;
import com.laojiashop.laojia.utils.StatusBarUtil;
import com.stx.xhb.xbanner.XBanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MallGoodsDetailsOtherActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    LRecyclerView recyclerView;
    @BindView(R.id.left)
    TextView left;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.appraisal)
    TextView appraisal;
    @BindView(R.id.button)
    LinearLayout button;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.btn_buynow)
    Button btnBuynow;
    private XBanner bannertops;
    private float totaldy;
    private float mRecyclerFactor;
    private List<DetailsBean> list;
    private int item1 = 0;
    private int item2 = 0;
    private LinearLayoutManager manager;
    private Resources res;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_mall_goods_details_other);
    }

    @Override
    protected void initViews() {
        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
        res = getResources();
       // StatusBarUtil.setTranslucentForImageView(this, 0, titleBar);
        left.setBackgroundResource(R.mipmap.icon_goods_back);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //图片的高度-状态栏的高度
        mRecyclerFactor = (DensityUtil.dp2px(this, 180.0F) - DensityUtil.getStatusBarHeight(this));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initListener();
        list = new ArrayList<>();
        //循环添加item的个数
        for (int i = 0; i < 2; i++) {
            DetailsBean bean = new DetailsBean();
            bean.setType(i + 1);
            list.add(bean);
        }
        recyclerView.setNestedScrollingEnabled(false);
        DetailsAdapter adapter = new DetailsAdapter(mAt);
        adapter.setDataList(list);
        //轮播图
        LRecyclerViewAdapter lradapter = new LRecyclerViewAdapter(adapter);
        //加载标题图
        View headView = View.inflate(this, R.layout.details_head, null);
        //初始化对象
        bannertops = headView.findViewById(R.id.bannertop);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtil.getScreenWidth(mAt) / 2);
        bannertops.setLayoutParams(layoutParams);
        //加载数据
        initBanner(bannertops);
        //添加头部标题从这里开始处理页面
        lradapter.addHeaderView(headView);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(lradapter);
        //设置不可刷新不可加载
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadMoreEnabled(false);
        //设置页面适配器点击事件
        adapter.setListener(new DetailsAdapter.OnItemHeightListener() {
            @Override
            public void setOnItemHeightListener(int height, int type) {
                if (height != 0) {
                    if (type == 1001) {
                        item1 = (int) (height + mRecyclerFactor);
                    } else if (type == 1002) {
                        item2 = item1 + height;
                    }
                }

            }
        });


    }
    //初始化点击事件
    private void initListener() {
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item1 != 0) {
                    recyclerView.scrollBy(0, (int) -totaldy);
                }
            }
        });

        appraisal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item2 != 0) {
//                    判断滑动距离是否超过商品
                    if (totaldy > item1)
                        recyclerView.scrollBy(0, (int) -(totaldy - item1) + 20);
                    else
                        recyclerView.scrollBy(0, (int) (item1 - totaldy) + 20);

                }
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recycler, int dx, int dy) {
                super.onScrolled(recycler, dx, dy);

                totaldy += dy;
                //判断返回按钮显示的样式
                if (totaldy == 0) {
                    left.setBackgroundResource(R.mipmap.icon_goods_back);
                } else {
                    left.setBackgroundResource(R.mipmap.all_back);
                }
                if (item2 != 0 && item1 != 0) {
                    if (totaldy < item1) {
                        title.setTextColor(res.getColor(R.color.orange));
                        appraisal.setTextColor(res.getColor(R.color.black));
                    } else if (totaldy > item1 && totaldy < item2) {
                        appraisal.setTextColor(res.getColor(R.color.orange));
                        title.setTextColor(res.getColor(R.color.black));
                    }
                }
                if (totaldy <= mRecyclerFactor) {
                    float scale = (float) totaldy / mRecyclerFactor;
                    float alpha = scale * 255;

                    if (alpha < 160) {
                        button.setVisibility(View.GONE);
                        //StatusBarUtil.setTranslucentForImageView(mAt, (int) alpha, titleBar);
                    } else {
                        button.setVisibility(View.VISIBLE);
                        //StatusBarUtil.setColor(mAt, Color.argb((int) alpha, 255, 255, 255));
                    }
                    titleBar.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    titleBar.setBackgroundColor(Color.parseColor("#ffffff"));
                    button.setVisibility(View.VISIBLE);
                    //设置状态栏颜色
                    //  StatusBarUtil.setColor(mAt, Color.parseColor("#ffffff"), 0);
                    //获取系统窗口
//                    Window window = MallGoodsDetailsOtherActivity.this.getWindow();
//                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }

            }
        });
    }


    @Override
    public void getDataFromServer() {
        //数据请求
        Intent intent = getIntent();
        String goodsid = String.valueOf(intent.getIntExtra("goodid",0));
        //数据请求
        HttpRxObservable.getObservable(ApiUtils.getApiService().getgooddetailinfo(goodsid)).subscribe(new BaseObserver<GoodsDetailBean>(mAt) {
            @Override
            public void onHandleSuccess(GoodsDetailBean goodsDetailBean) throws IOException {
                //轮播图数据源
                List<GoodsDetailBean.ImgsBean> imgBeans = goodsDetailBean.getImgs();
                //刷新数据之后，需要重新设置是否支持自动轮播  哪里报错就是这个页面的问题
                bannertops.setAutoPlayAble(imgBeans.size() > 1);
                bannertops.setIsClipChildrenMode(true);
                bannertops.setBannerData(imgBeans);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

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
//                        startActivity(new Intent(getContext(), HotelActivity.class));
                        break;
                    case 2:
//                        startActivity(new Intent(getContext(),));
                        break;
                }

            }
        });
        //加载广告图片
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //就算不加载也会出问题
                GoodsDetailBean.ImgsBean banners = (GoodsDetailBean.ImgsBean) model;
                //一个占位图就够，不用写error。
                Glide.with(mAt).load(banners.getUrl()).into((ImageView) view);
            }
        });
    }
    @OnClick(R.id.btn_buynow)
    public void onViewClicked() {
       // jumpActivity(BuyNowActivity.class);
    }
}
