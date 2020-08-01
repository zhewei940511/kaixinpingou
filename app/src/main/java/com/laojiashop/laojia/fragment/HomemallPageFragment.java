package com.laojiashop.laojia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.activity.GotoSearchActivity;
import com.laojiashop.laojia.activity.MallGoodsDetailsActivity;
import com.laojiashop.laojia.activity.MallGoodsDetailsOtherActivity;
import com.laojiashop.laojia.adapter.AdnewAdapter;
import com.laojiashop.laojia.adapter.SpecialAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.HomePageBean;
import com.laojiashop.laojia.entity.SpecialCourseSectionBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.test.TestActivity;
import com.laojiashop.laojia.utils.ScreenUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 商城页面
 */
public class HomemallPageFragment extends BaseFragment {
    // 搜索框
    @BindView(R.id.search_edittext)
    TextView searchEdittext;
    //首页列表对象
    @BindView(R.id.rv_hotstyletorecommend)
    RecyclerView rvHotstyletorecommend;
    @BindView(R.id.rl_gosearch)
    RelativeLayout rlGosearch;
    @BindView(R.id.adnewrecyclerView)
    RecyclerView adnewrecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private XBanner bannertops;
    //分类模块适配器
    private AdnewAdapter adnewAdapter = new AdnewAdapter();
    //底部模块适配器
    private SpecialAdapter specialAdapter;
    //数据
    private List<SpecialCourseSectionBean> sectionBeansdata = new ArrayList<>();

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_homemall;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        //轮播图
        bannertops = view.findViewById(R.id.homepagebannertop);
        rvHotstyletorecommend.setLayoutManager(new LinearLayoutManager(mAty));
        //初始化适配器  你看到你这个地方有错误没，还没呢
        specialAdapter = new SpecialAdapter(R.layout.item_specialcontent, R.layout.item_specialhead, sectionBeansdata);
        rvHotstyletorecommend.setAdapter(specialAdapter);
    }

    @Override
    protected void initData() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtil.getScreenWidth(mAty) / 2);
        bannertops.setLayoutParams(layoutParams);
        initBanner(bannertops);
    }

    @Override
    protected void getDataFromServer() {
        //获取数据   哪个是网络请求就是这里
        HttpRxObservable.getObservable(ApiUtils.getApiService().gethomepageinfo("goodsType")).subscribe(new BaseObserver<HomePageBean>(mAty) {
            @Override
            public void onHandleSuccess(HomePageBean homePageBean) throws IOException {
                //轮播图数据源
                List<HomePageBean.BannerBean> banner = homePageBean.getBanner();
                //刷新数据之后，需要重新设置是否支持自动轮播  哪里报错就是这个页面的问题
                bannertops.setAutoPlayAble(banner.size() > 1);
                bannertops.setIsClipChildrenMode(true);
                bannertops.setBannerData(banner);
                //获取adnew
                List<HomePageBean.AdNewBean> adNewBeans = homePageBean.getAd_new();
                adnewAdapter.setNewData(adNewBeans);
                adnewrecyclerView.setAdapter(adnewAdapter);
                //获取底部列表数据
                List<HomePageBean.SpecialBean> special = homePageBean.getSpecial();
                //循环数据
                for (int i = 0; i < special.size(); i++) {
                    List<HomePageBean.SpecialBean.GoodsListBean> goods_list = special.get(i).goods_list;
                    sectionBeansdata.add(new SpecialCourseSectionBean(true, special.get(i).title));
                    for (int j = 0; j < goods_list.size(); j++) {
                        sectionBeansdata.add(new SpecialCourseSectionBean(goods_list.get(j)));
                    }
                }
                //还是重复数据呢，没看你修改哪里呀?
                //specialAdapter.addData(sectionBeansdata);
                specialAdapter.setNewData(sectionBeansdata);
                specialAdapter.notifyDataSetChanged();
                //适配器点击事件
                specialAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        HomePageBean.SpecialBean.GoodsListBean goodsListBean = sectionBeansdata.get(position).t;
                        //商品详情
                         //Intent intent=new Intent(mAty, TestActivity.class);
                        Intent intent = new Intent(mAty, MallGoodsDetailsActivity.class);
                        intent.putExtra("goodid", goodsListBean.getId());
                        // intent.setClass();
                        startActivity(intent);
                        //showToast("测试数据你点击了"+goodsListBean.getId());
                        //ToastUtil.showToast("你点击了数据测试"+goodsListBean.getId());

                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }


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
                HomePageBean.BannerBean banners = (HomePageBean.BannerBean) model;
                //一个占位图就够，不用写error。
                Glide.with(mAty).load(banners.getPath()).into((ImageView) view);
            }
        });
    }

    @OnClick(R.id.rl_gosearch)
    public void onViewClicked() {
        Intent intent = new Intent(mAty, GotoSearchActivity.class);
        startActivity(intent);
    }

    //点击事件
//    @Override
//    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//        HomePageBean.SpecialBean.GoodsListBean goodsListBean= (HomePageBean.SpecialBean.GoodsListBean) adapter.getItem(position);
//        ToastUtil.showToast("你点击了"+goodsListBean.getId());
//    }

    //    //页面刷新
//    public void refresh() {
//        getDataFromServer();
//    }

}