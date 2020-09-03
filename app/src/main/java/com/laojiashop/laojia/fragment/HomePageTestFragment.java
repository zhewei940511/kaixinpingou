package com.laojiashop.laojia.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.SpecialTestAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.HomePageBean;
import com.laojiashop.laojia.entity.HomePageTestBean;
import com.laojiashop.laojia.entity.SpecialCourseSectionBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomePageTestFragment extends BaseFragment {
    @BindView(R.id.rv_hotstyletorecommend)
    RecyclerView rvHotstyletorecommend;
    private List<HomePageTestBean.SpecialBean> specialBeans = new ArrayList<>();
    private List<HomePageTestBean.SpecialBean.GoodsListBean> goodsListBeans=new ArrayList<>();
    //适配器
    private SpecialTestAdapter specialTestAdapter;

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_homemalltest;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
       // specialTestAdapter=new SpecialTestAdapter(goodsListBeans);
        GridLayoutManager manager = new GridLayoutManager(mAty, 4);
        rvHotstyletorecommend.setLayoutManager(manager);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().gethomepageinfos("goodsType")).subscribe(new BaseObserver<HomePageTestBean>(mAty) {
            @Override
            public void onHandleSuccess(HomePageTestBean homePageTestBean) throws IOException {
                specialBeans=homePageTestBean.getSpecial();
                for (int i=0;i<specialBeans.size();i++)
                {
                    List<HomePageTestBean.SpecialBean.GoodsListBean> goods_list = specialBeans.get(i).getGoods_list();
                    for (int j = 0; j < goods_list.size(); j++) {
                        goodsListBeans.add(goods_list.get(j));
                    }
                    specialTestAdapter=new SpecialTestAdapter(goodsListBeans);
                }
                rvHotstyletorecommend.setAdapter(specialTestAdapter);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });

    }
}
