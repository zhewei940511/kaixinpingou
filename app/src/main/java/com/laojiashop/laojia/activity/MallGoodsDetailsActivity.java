package com.laojiashop.laojia.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.imagepipeline.common.SourceUriType;
import com.google.gson.Gson;
import com.laojiashop.laojia.R;

import com.laojiashop.laojia.adapter.MallBuyAdapter;
import com.laojiashop.laojia.adapter.SpecialTestAdapter;
import com.laojiashop.laojia.adapter.SpecificationsAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.CreateOrderBean;
import com.laojiashop.laojia.entity.GoodsDetailBean;
import com.laojiashop.laojia.fragment.MallGoodsCommentFragment;
import com.laojiashop.laojia.fragment.MallGoodsDetailFragment;
import com.laojiashop.laojia.http.ApiException;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.BaseResult;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.ScreenUtil;
import com.laojiashop.laojia.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.stx.xhb.xbanner.XBanner;
import com.wuhenzhizao.sku.bean.Sku;
import com.wuhenzhizao.sku.bean.SkuAttribute;
import com.wuhenzhizao.sku.view.OnSkuListener;
import com.wuhenzhizao.sku.view.SkuSelectScrollView;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
    private List<GoodsDetailBean.SkuBean.ListBean> listBeanList = new ArrayList<>();
    private List<GoodsDetailBean.SkuBean.TreeBean> treeBeanList = new ArrayList<>();
    private List<GoodsDetailBean.SkuBean.TreeBean.VBean> vBeanList = new ArrayList<>();

    //选中的文字拼接
    private ArrayList<String> strings;
    private HashMap<Object, Object> map;
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
    //定义对象接收用户选中的价格和
    private TextView ordername, showprice, mNum;
    private String skuname, goodtitle;
    private double price;
    //积分
    private int freeze_coin, is_cross_border;
    //运费末班string
//    private String freightListBeansstr;
    //运费模板
    private List<GoodsDetailBean.FreightListBean> freightListBeans = new ArrayList<>();
    //定义num数量（购买数量）
    private String number;
    LayoutInflater mInflater;

    private ArrayList<GoodsDetailBean.SkuBean.ListBean> res = new ArrayList<>();

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
//        //添加数据源
//        treeBeanList = new ArrayList<>();
//        listBeanList=new ArrayList<>();

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
        mIndicator.setScrollBar(new ColorBar(getApplicationContext(), Color.parseColor("#FF666C"), 5));
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
                //这个地方的是规格数据
                listBeanList = goodsDetailBean.getSku().getList();
                treeBeanList = goodsDetailBean.getSku().getTree();
                for (int i = 0; i < vBeanList.size(); i++) {
                    vBeanList.addAll(treeBeanList.get(i).getV());
                }

                //存入运费模板
                freightListBeans = goodsDetailBean.getFreight_list();
                //转换成String
//                Gson gson=new Gson();
//                freightListBeansstr=gson.toJson(freightListBeans);
                //System.out.println("信息"+freightListBeansstr);
//                map = new HashMap<>();
//                strings = new ArrayList<>();
//                for (int i = 0; i < treeBeanList.size(); i++) {
//                    strings.add(i, "");
//                }
                //轮播图数据源
                List<GoodsDetailBean.ImgsBean> imgBeans = goodsDetailBean.getImgs();
                for (int i = 0; i < goodsDetailBean.getSku().getTree().size(); i++) {
                    res.addAll(goodsDetailBean.getSku().getList());
                }
                //刷新数据之后，需要重新设置是否支持自动轮播  哪里报错就是这个页面的问题
                bannertops.setAutoPlayAble(imgBeans.size() > 1);
                bannertops.setIsClipChildrenMode(true);
                bannertops.setBannerData(imgBeans);
                //数据绑定
                tvMarketPrice.setText("￥" + goodsDetailBean.getMarket_price());
                //goodsDetailBean.getShow_price().
                //判断是否显示区间价
                if (goodsDetailBean.getShow_price().getMax().equals(goodsDetailBean.getShow_price().getMin())) {
                    tvPriceRange.setText(goodsDetailBean.getShow_price().getMin());
                } else {
                    tvPriceRange.setText(goodsDetailBean.getShow_price().getMin() + "-" + goodsDetailBean.getShow_price().getMax());
                }
                tvMarketPrice.getPaint().setAntiAlias(true);//抗锯齿
                tvMarketPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线
                goodtitle = goodsDetailBean.getTitle();
                tvGoodstitle.setText(goodtitle);//标题
                tvGoodsubtitletitle.setText(goodsDetailBean.getStitle());//副标题
                tvFreightTips.setText("运费:" + goodsDetailBean.getFreight_tips());//运费
                freeze_coin = goodsDetailBean.getFreeze_coin();
                is_cross_border = goodsDetailBean.getIs_cross_border();//是否是境外商品
                orderImg = goodsDetailBean.getPath();
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
                    //重写的一个dialog
                    //specificationsdialog();
                    showSkuDialog();
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
        ordername = contentView.findViewById(R.id.tv_ordername);
        showprice = contentView.findViewById(R.id.tv_showprice);
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
        //减
        ImageView mSub = contentView.findViewById(R.id.btn_sub);
        //加
        ImageView mAdd = contentView.findViewById(R.id.btn_add);
        mNum = contentView.findViewById(R.id.tv_num);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = mNum.getText().toString();
                int i = Integer.parseInt(number);
                i += 1;
                String addNumber = Integer.toString(i);
                mNum.setText(addNumber);
            }
        });

        mSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = mNum.getText().toString();
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
        buyAdapter.setItemClickKind(new MallBuyAdapter.ItemClickKind() {
            @Override
            public void getItemKind(String spec) {
//                //System.out.println("信息显示" + spec);
//                String skuid=spec;
                for (GoodsDetailBean.SkuBean.ListBean specPrice : listBeanList) {
                    if (spec.equals(specPrice.getSku())) {
                        price = Double.parseDouble(specPrice.getPrice());
                        skuname = specPrice.getSku_name();
                        showprice.setText("￥" + price);
                        ordername.setText("规格:" + skuname);
                    }
                }
                mbuynow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (ordername.getText().length() == 0) {
                            showToast("请选择规格");
                            return;
                        }
                        if (showprice.getText().length() == 0) {
                            showToast("请选择规格");
                            return;
                        }
                        switch (isonebuy) {
                            //普通商品type为1
                            case 0:
                                type = 1;
                                break;
                            //一人直购设置type类型为3
                            case 1:
                                type = 3;
                                break;
                        }
                        // Intent intent=new Intent(mAt,BuyNowActivity.class);
                        Intent intent = new Intent(mAt, GotoToconfirmorderActivity.class);
                        Bundle bundle = new Bundle();
//                        intent.putExtra("goodid",goodsid);
//                        intent.putExtra("type",type);
//                        intent.putExtra("freeze_coin",freeze_coin);
//                        intent.putExtra("number",mNum.getText().toString());
//                        intent.putExtra("pirce",pirce);
//                        intent.putExtra("skuname",skuname);
//                        intent.putExtra("goodtitle",goodtitle);
//                        intent.putExtra("orderImg",orderImg);
                        bundle.putString("goodid", goodsid);
                        bundle.putInt("type", type);
                        bundle.putInt("freeze_coin", freeze_coin);
                        bundle.putInt("is_cross_border", is_cross_border);
                        bundle.putString("number", mNum.getText().toString());
                        bundle.putDouble("pirce", price);
                        bundle.putString("skuname", skuname);
                        bundle.putString("goodtitle", goodtitle);
                        bundle.putString("skuCombination", spec);
                        bundle.putString("orderImg", orderImg);
                        bundle.putSerializable("freightListBeans", (Serializable) freightListBeans);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        bottomDialog.dismiss();
                    }
                });
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
        ordername = contentView.findViewById(R.id.tv_ordername);
        showprice = contentView.findViewById(R.id.tv_showprice);
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
                //发起团购
                HttpRxObservable.getObservable(ApiUtils.getApiService().createorder(2,
                        goodsid,
                        configIdx,
                        0,
                        0,
                        0,
                        0,
                        "",
                        0,
                        "",
                        "",
                        "",
                        "")).subscribe(new BaseObserver<CreateOrderBean>(mAt) {
                    @Override
                    public void onHandleSuccess(CreateOrderBean createOrderBean) throws IOException {
                        ToastUtil.showToast("发团成功");
                        MainActivity.invoke(mAt, 1);
                    }

                });
//                HttpRxObservable.getObservable(ApiUtils.getApiService().makeTuan(goodsid,configIdx)).subscribe(new BaseObserver<Object>(mAt) {
//                    @Override
//                    public void onHandleSuccess(Object o) throws IOException {
//                        //这里是团购弹窗的
//                        MainActivity.invoke(mAt,1);
//                    }
//                });
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
     * 直购弹窗测试
     */
    private void showSkuDialog() {
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
        View contentView = LayoutInflater.from(this).inflate(R.layout.dialog_buy_now, null);
        //规格适配器
        RecyclerView mBuyRecycler = contentView.findViewById(R.id.rv_buy_recyclerview);
        mBuyRecycler.setLayoutManager(new LinearLayoutManager(mBuyRecycler.getContext()));
        SpecificationsAdapter buyAdapter = new SpecificationsAdapter(treeBeanList);
        mBuyRecycler.setAdapter(buyAdapter);

        //适配器点击事件处理选中
//        buyAdapter.setItemClickKind(new SpecificationsAdapter.ItemClickKind() {
//            @Override
//            public void getItemKind(String spec, String kind) {
//                //System.out.println("选中的信息标题" + spec + "内容" + kind);
//                map = new HashMap<>();
//                for (GoodsDetailBean.SkuBean.TreeBean treeBean : treeBeanList) {
//                    if (treeBean.getK().equals(spec))
//                    {
//                        map.put(spec, kind);
//                    }
//                }
//                System.out.println("添加的数据"+map);
////                strings = new ArrayList<>();
////                  StringBuffer buffer = new StringBuffer();
////                  System.out.println("信息展示"+kind);
////                  for (int i=0;i<treeBeanList.size();i++)
////                  {
////                      buffer.append(kind).append("-");
////                  }
////                  System.out.println("拼接过后的值"+buffer);
////                for (GoodsDetailBean.SkuBean.TreeBean treeBean : treeBeanList) {
////                    //判断选中的key是否和集合中的key一致，如果一致就清除之前value选中的item
////                    if (treeBean.getK().equals(spec)) {
////                        //  strings.clear();
////                        if (kind != null) {
////                            strings.add(kind);
////                        }
////                    }
////                }
////                StringBuffer buffer = new StringBuffer();
////                for (int i = 0; i < treeBeanList.size(); i++) {
////                    vBeanList = treeBeanList.get(0).getV();
//////                    for (int j = 0; j < vBeanList.size(); j++) {
////                        if (vBeanList.size() <= 0) {
////                            ToastUtil.showToast("请选择" + treeBeanList.get(i).getK());
////                            return;
////                        } else {
////                            buffer.append(vBeanList.get(i).getName()).append("-");
////                        }
////                        break;
////                  //  }
////                }
////                String selectKind = buffer.substring(0, buffer.length() - 1);
////                System.out.println("信息" + selectKind);
//            }
//        });
        //dialog弹窗相关
        bottomDialog.setContentView(contentView);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.setCanceledOnTouchOutside(true);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.show();
    }
}
