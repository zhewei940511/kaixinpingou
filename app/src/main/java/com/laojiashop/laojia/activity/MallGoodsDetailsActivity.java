package com.laojiashop.laojia.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.laojiashop.laojia.R;

import com.laojiashop.laojia.adapter.MallBuyAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.GoodsDetailBean;
import com.laojiashop.laojia.fragment.MallGoodsCommentFragment;
import com.laojiashop.laojia.fragment.MallGoodsDetailFragment;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.ScreenUtil;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.stx.xhb.xbanner.XBanner;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MallGoodsDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_market_price)
    TextView tvMarketPrice;
    @BindView(R.id.tv_price_range)
    TextView tvPriceRange;
    @BindView(R.id.tv_goodstitle)
    TextView tvGoodstitle;
    @BindView(R.id.tv_goodsubtitletitle)
    TextView tvGoodsubtitletitle;
    @BindView(R.id.tv_freight_tips)
    TextView tvFreightTips;
    //规格
    @BindView(R.id.rl_checkspecifications)
    RelativeLayout rlCheckspecifications;
    @BindView(R.id.btn_buynow)
    Button btnBuynow;
    @BindView(R.id.img_collection)
    ImageView imgCollection;
    @BindView(R.id.tv_mall_collection)
    TextView tvMallCollection;
    @BindView(R.id.mall_goods_collection)
    LinearLayout mallGoodsCollection;
    @BindView(R.id.ll_buynow)
    LinearLayout llBuynow;
    private XBanner bannertops;
    @BindView(R.id.header_title_view)
    RelativeLayout mHeaderTitleView;
    @BindView(R.id.root_view)
    RelativeLayout mRootView;
    @BindView(R.id.tv_header_title)
    TextView mHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView mImgheaderback;
    private List<Fragment> list = new ArrayList<>();
    private FixedIndicatorView mIndicator;
    private ViewPager mViewPager;
    //商品ID
    private String goodsid;
    //底部弹窗
    private Dialog bottomDialog;
    //弹窗信息
    //订单图片
    private String orderImg;
    //规格数据源
    private List<GoodsDetailBean.SkuBean.TreeBean> treeBeanList;
    //团购数据源
    private List<GoodsDetailBean.TuangouBean> tuangouBeanList;
    //是否收藏
    private boolean isCollection;
    //是否团购
    private int hastg;
    //订单类型
    private int type;
    private int isonebuy;
    //拼团人数
    private int configIdx;
    LayoutInflater mInflater;
   private ArrayList<GoodsDetailBean.SkuBean.TreeBean.VBean> res=new ArrayList<>();
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_mall_goods_details);
    }

    @Override
    protected void initViews() {
        mInflater = LayoutInflater.from(mAt);
//        int barHeight = StatusBarUtil.getStatusBarHeight(this);
//        if (barHeight > 0) {
//            //设置状态栏的高度
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mRootView.getLayoutParams();
//            layoutParams.topMargin = BarUtils.getStatusBarHeight(this) + layoutParams.topMargin;
//            mRootView.setLayoutParams(layoutParams);
//        }
        //数据请求
        Intent intent = getIntent();
        goodsid = String.valueOf(intent.getIntExtra("goodid", 0));
        getBarDistance(mHeaderTitleView);
        mHeaderTitle.setText("商品详情");
        mImgheaderback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mIndicator = findViewById(R.id.indicator);
        bannertops = findViewById(R.id.bannertop);
        mViewPager = findViewById(R.id.viewPager);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtil.getScreenWidth(mAt) / 2);
        bannertops.setLayoutParams(layoutParams);
        //加载数据
        initBanner(bannertops);
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
                GoodsDetailBean.ImgsBean banners = (GoodsDetailBean.ImgsBean) model;
                //一个占位图就够，不用写error。
                Glide.with(mAt).load(banners.getUrl()).into((ImageView) view);
            }
        });
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
        mIndicator.setScrollBar(new ColorBar(getApplicationContext(), Color.parseColor("#5ED3AE"), 5));
        mViewPager.setOffscreenPageLimit(1);//缓存

    }

    @OnClick(R.id.iv_header_back)
    public void clickBack() {
        finish();
    }

    @Override
    public void getDataFromServer() {

        //数据请求
        HttpRxObservable.getObservable(ApiUtils.getApiService().getgooddetailinfo(goodsid)).subscribe(new BaseObserver<GoodsDetailBean>(mAt) {
            @Override
            public void onHandleSuccess(GoodsDetailBean goodsDetailBean) throws IOException {
                //轮播图数据源
                List<GoodsDetailBean.ImgsBean> imgBeans = goodsDetailBean.getImgs();
                for (int i=0;i<goodsDetailBean.getSku().getTree().size();i++)
                {
                    res.addAll(goodsDetailBean.getSku().getTree().get(i).getV());
                }
                //刷新数据之后，需要重新设置是否支持自动轮播  哪里报错就是这个页面的问题
                bannertops.setAutoPlayAble(imgBeans.size() > 1);
                bannertops.setIsClipChildrenMode(true);
                bannertops.setBannerData(imgBeans);
                //数据绑定
                tvMarketPrice.setText(goodsDetailBean.getMarket_price());
                //goodsDetailBean.getShow_price().
                //判断是否显示区间价
                if (goodsDetailBean.getShow_price().getMax().equals(goodsDetailBean.getShow_price().getMin())) {
                    tvPriceRange.setText(goodsDetailBean.getShow_price().getMin());
                } else {
                    tvPriceRange.setText(goodsDetailBean.getShow_price().getMin() + "-" + goodsDetailBean.getShow_price().getMax());
                }
                tvMarketPrice.getPaint().setAntiAlias(true);//抗锯齿
                tvMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                tvGoodstitle.setText(goodsDetailBean.getTitle());//标题
                tvGoodsubtitletitle.setText(goodsDetailBean.getStitle());//副标题
                tvFreightTips.setText("运费:" + goodsDetailBean.getFreight_tips());//运费
                orderImg = goodsDetailBean.getPath();
                //添加数据源
                treeBeanList = new ArrayList<>();
                //这个地方的是规格数据
                for (GoodsDetailBean.SkuBean.TreeBean treeBean : goodsDetailBean.getSku().getTree()) {
                    treeBeanList.add(treeBean);
                }
                //循环添加团购模块
                tuangouBeanList = new ArrayList<>();
                for (GoodsDetailBean.TuangouBean tuangouBean : goodsDetailBean.getTuangou()) {
                    tuangouBeanList.add(tuangouBean);
                }
                isCollection = goodsDetailBean.isIs_act();
                //判断商品是否收藏
                if (isCollection == true) {
                    tvMallCollection.setText("已收藏");
                    imgCollection.setImageResource(R.mipmap.icon_collection);

                } else {
                    tvMallCollection.setText("收藏");
                    imgCollection.setImageResource(R.mipmap.icon_uncollection);
                }
                hastg = goodsDetailBean.getHas_tg();
                isonebuy = goodsDetailBean.getIs_one_buy();
                //判断是否是团购商品0否1是
                if (hastg == 1) {
                    btnBuynow.setText("发起团购");
                    type = 2;
                } else {
                    btnBuynow.setText("立即购买");
                    switch (isonebuy) {
                        //非直购type为1
                        case 0:
                            type = 1;
                            break;
                        //是直购设置type类型为3
                        case 1:
                            type = 3;
                            break;
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
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

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.rl_checkspecifications, R.id.btn_buynow, R.id.mall_goods_collection})
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

                    }
                });
                break;
            //选择规格
            case R.id.rl_checkspecifications:
                if (hastg == 1) {
                    //团购商品弹窗(无规格选择)
                    showtgdialog();
                } else {
                    //直购商品弹窗(可以选择规格)
                    specificationsdialog();
                }
                break;
            //直接购买
            case R.id.btn_buynow:
                //判断显示的弹窗(1是团购弹窗其他情况为直购.两个弹窗的功能样式不一样，这里分开做处理)
                if (hastg == 1) {
                    //团购商品弹窗(无规格选择)
                    showtgdialog();
                } else {
                    //直购商品弹窗(可以选择规格)
                    specificationsdialog();
                }
                break;
        }
    }

    /**
     * 直购弹窗
     */
    private void specificationsdialog() {
        bottomDialog = new Dialog(this, R.style.BottomDialog);
        Window window = bottomDialog.getWindow();
        // 把 DecorView 的默认 padding 取消，同时 DecorView 的默认大小也会取消
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        // 设置宽度
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        // 给 DecorView 设置背景颜色，很重要，不然导致 Dialog 内容显示不全，有一部分内容会充当 padding，上面例子有举出
        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);
        //判断是否是团购产品
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_buy_now, null);
        ImageView mOrderImg = contentView.findViewById(R.id.iv_online_order_image);
        TextView ordername = contentView.findViewById(R.id.tv_ordername);
        TextView showprice = contentView.findViewById(R.id.tv_showprice);
        Glide.with(mOrderImg.getContext()).load(orderImg).apply(new RequestOptions()
                .placeholder(R.drawable.default_image)).into(mOrderImg);
        ImageView mCancle = contentView.findViewById(R.id.iv_cancle);
        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });
        Button mbuynow = contentView.findViewById(R.id.btn_buynow);
        mbuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //jumpActivity(BuyNowActivity.class);
                //传值跳转
                Intent intent = new Intent(mAt, BuyNowActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);
                bottomDialog.dismiss();
            }
        });
        //减
        ImageView mSub = contentView.findViewById(R.id.btn_sub);
        //加
        ImageView mAdd = contentView.findViewById(R.id.btn_add);
        TextView mNum = contentView.findViewById(R.id.tv_num);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = mNum.getText().toString();
                int i = Integer.parseInt(number);
                i += 1;
                String addNumber = Integer.toString(i);
                mNum.setText(addNumber);
            }
        });

        mSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = mNum.getText().toString();
                int i = Integer.parseInt(number);
                if (i > 1) {
                    i -= 1;
                    String addNumber = Integer.toString(i);
                    mNum.setText(addNumber);
                }
            }
        });

        //规格适配器
        RecyclerView mBuyRecycler = contentView.findViewById(R.id.rv_buy_recyclerview);
        mBuyRecycler.setLayoutManager(new LinearLayoutManager(mBuyRecycler.getContext()));
        MallBuyAdapter buyAdapter = new MallBuyAdapter(R.layout.mall_buy_item, treeBeanList);
        mBuyRecycler.setAdapter(buyAdapter);
        //点击事件
        buyAdapter.setItemClickKind(new MallBuyAdapter.ItemClickKind() {
            @Override
            public void getItemKind(String spec) {
               // System.out.println("信息"+spec);
                ordername.setText("已选择:" + spec);
                for (GoodsDetailBean.SkuBean.TreeBean.VBean re : MallGoodsDetailsActivity.this.res) {
                    if (re.getName().equals(spec)) {
                        if (spec != null) {
                            re.getName();
                        }
                    }
                }
                StringBuffer buffer = new StringBuffer();
                for (int i = 0; i < MallGoodsDetailsActivity.this.res.size(); i++) {
                    GoodsDetailBean.SkuBean.TreeBean.VBean vBean = MallGoodsDetailsActivity.this.res.get(i);
                    if (vBean.getName().length() <= 0) {
                        showToast("请选择" + vBean.getName());
                        return;
                    } else {
                        buffer.append(vBean.getName()).append("-");
                    }
                }
                String selectKind = buffer.substring(0, buffer.length() - 1);
                System.out.println("信息获取"+selectKind);
            }
        });
        //dialog弹窗相关
        bottomDialog.setContentView(contentView);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
    }

    /**
     * 团购弹窗
     */
    private void showtgdialog() {
        bottomDialog = new Dialog(this, R.style.BottomDialog);
        Window window = bottomDialog.getWindow();
        // 把 DecorView 的默认 padding 取消，同时 DecorView 的默认大小也会取消
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        // 设置宽度
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(layoutParams);
        // 给 DecorView 设置背景颜色，很重要，不然导致 Dialog 内容显示不全，有一部分内容会充当 padding，上面例子有举出
        window.getDecorView().setBackgroundColor(Color.TRANSPARENT);
        //判断是否是团购产品
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_buy_tg, null);
        ImageView mOrderImg = contentView.findViewById(R.id.iv_online_order_image);
        TextView ordername = contentView.findViewById(R.id.tv_ordername);
        TextView showprice = contentView.findViewById(R.id.tv_showprice);
        Glide.with(mOrderImg.getContext()).load(orderImg).apply(new RequestOptions()
                .placeholder(R.drawable.default_image)).into(mOrderImg);
        ImageView mCancle = contentView.findViewById(R.id.iv_cancle);
        mCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomDialog.dismiss();
            }
        });

        //团购样式
        TagFlowLayout flowLayout = contentView.findViewById(R.id.id_flowlayouts);
        ArrayList<String> value = new ArrayList<>();
        for (GoodsDetailBean.TuangouBean tuangouBean : tuangouBeanList) {
            value.add(tuangouBean.getNumberStr());
        }
        //绑定适配器
        flowLayout.setAdapter(new TagAdapter<String>(value) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv, flowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        //监听选中事件
        flowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                if (selectPosSet.size() > 0) {
                    Integer next = selectPosSet.iterator().next();
                    String s = value.get(next);
                    configIdx = tuangouBeanList.get(next).getPrice_id();
                    ordername.setText("已选择:" + s);
                    showprice.setText("￥" + tuangouBeanList.get(next).getPrice());
                }
            }
        });
        Button mbuynow = contentView.findViewById(R.id.btn_buynow);
        mbuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //jumpActivity(BuyNowActivity.class);
                //传值
                Intent intent = new Intent(mAt, BuyNowActivity.class);
                //毕传参数
                intent.putExtra("type", type);
                intent.putExtra("goodid", goodsid);
                startActivity(intent);
                bottomDialog.dismiss();
            }
        });
        //dialog弹窗相关
        bottomDialog.setContentView(contentView);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
