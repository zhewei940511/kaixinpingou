package com.laojiashop.laojia.entity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.activity.ClassificationDetailsActivity;
import com.laojiashop.laojia.adapter.ClassificaInfoAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.ScreenUtil;
import com.laojiashop.laojia.utils.SharedPreferencesManager;
import com.stx.xhb.xbanner.XBanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 控制分类模块右边数据的碎片对象
 */
public class ClassificationPageRightFragment extends BaseFragment {
    @BindView(R.id.bannertop)
    XBanner bannertop;
    @BindView(R.id.rv_classifica)
    RecyclerView rvClassifica;
    @BindView(R.id.ly_topbanner)
    LinearLayout lyTopbanner;
    //接收数据集合
    private int position;
    //创建适配器对象
    private ClassificaInfoAdapter classificaInfoAdapter;
    //数据源
    private List<ClassificationPageBean.SonBean> sonBeans = new ArrayList<>();

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_classificationpageright;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ScreenUtil.getScreenWidth(mAty) / 2);
        bannertop.setLayoutParams(layoutParams);
        //加载数据
        initBanner(bannertop);
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        position = arguments.getInt("position");
    }

    @Override
    protected void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getclassificationinfo()).subscribe(new BaseObserver<List<ClassificationPageBean>>(mAty) {
            @Override
            public void onHandleSuccess(List<ClassificationPageBean> classificationPageBeans) throws IOException {
                List<ClassificationPageBean.BannerBean> bannerBeans = classificationPageBeans.get(position).getBanner();
                //判断banner是否有数据，如果没得就隐藏
                if (bannerBeans == null || bannerBeans.size() == 0) {
                    lyTopbanner.setVisibility(View.GONE);
                }
                bannertop.setAutoPlayAble(bannerBeans.size() > 1);
                //设置是否一屏显示多个
                // bannertop.setIsClipChildrenMode(true);
                bannertop.setBannerData(bannerBeans);
                //banner下的rv
                sonBeans = classificationPageBeans.get(position).getSon();
                //设置适配器相关
                rvClassifica.setLayoutManager(new GridLayoutManager(mAty, 3));
                classificaInfoAdapter = new ClassificaInfoAdapter(R.layout.item_classificainfo, sonBeans);
                rvClassifica.setAdapter(classificaInfoAdapter);
                //这里是适配器的点击事件点击就在这里
                classificaInfoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        List<ClassificationPageBean.SonBean> data = classificaInfoAdapter.getData();
                        //你这个数据封装有问题啊啥问题？你看哈
                        ClassificationDetailsActivity.invoke(getActivity(), data,position);
                    }
                });
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromServer();
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
                ClassificationPageBean.BannerBean banners = (ClassificationPageBean.BannerBean) model;
                //一个占位图就够，不用写error。
                Glide.with(mAty).load(banners.getUrl()).into((ImageView) view);
            }
        });
    }
}
