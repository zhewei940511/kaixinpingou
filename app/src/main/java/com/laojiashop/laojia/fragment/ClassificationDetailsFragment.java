package com.laojiashop.laojia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.activity.MallGoodsDetailsActivity;
import com.laojiashop.laojia.adapter.ClassificationDetailsAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.ClassificationDetailsBean;
import com.laojiashop.laojia.entity.SelectEvent;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/*
分类详情子页面
 */
public class ClassificationDetailsFragment extends BaseFragment {

    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String title;
    private int page = 1;
    private int typeid;
    //声明适配器
    private ClassificationDetailsAdapter classificationDetailsAdapter;
    //数据源
    private List<ClassificationDetailsBean.DataBean> dataBeanList = new ArrayList<>();
    //排序（newest 最新 goodsSales 销量 goodsPrice 价格 goodsComment 评价）
    private String sort = "newest";
    //排序方式(asc 升序 desc 降序)//默认都是降序，除了价格会变动以外
    private String sortBy="desc" ;


    /**
     * 接口对象
     *
     * @return
     */
    public static ClassificationDetailsFragment getInstance(int typeid) {
        ClassificationDetailsFragment classificationDetailsFragment = new ClassificationDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("typeid", typeid);
        classificationDetailsFragment.setArguments(bundle);
        return classificationDetailsFragment;
    }

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_classificationdetails;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        if(!EventBus.getDefault().isRegistered(this)) {//加上判断
            EventBus.getDefault().register(this);
        }
        if (getArguments() != null) {
            typeid = getArguments().getInt("typeid");
        }
        rvRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        classificationDetailsAdapter = new ClassificationDetailsAdapter(dataBeanList);
        //无数据显示空
        classificationDetailsAdapter.setEmptyView(LayoutInflater.from(mAty).inflate(R.layout.layout_empty_view, rvRecycler, false));
        //绑定适配器
        rvRecycler.setAdapter(classificationDetailsAdapter);
        //设置头
        refreshLayout.setRefreshFooter(new BallPulseFooter(mAty).setSpinnerStyle(SpinnerStyle.Scale));
        //开启下拉刷新
        refreshLayout.setEnableRefresh(true);
        //开启上拉加载
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableScrollContentWhenLoaded(true);
        //取消内容不满一页时开启上拉加载功能
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setEnableOverScrollBounce(true);
        //下拉刷新监听
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                loadData(sort,sortBy);
                refreshLayout.finishRefresh(true);
            }
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                loadData(sort,sortBy);
                refreshLayout.finishLoadMore(true);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
       // loadData("newest", "");
        loadData(sort,sortBy);

    }

    private void loadData(String sort, String sortBy) {
        //这个地方我开始是写死的，不晓得怎么
        Map<String, Object> map = new HashMap<String, Object>();
        //这个地方的typeid是顶部的导航的id
        map.put("typeid", typeid);
        System.out.println("当前点击的产品ID" + typeid);
        //这么传有一个问题，切换的时候还是之前点击传过来那个id
        map.put("sort", sort);
        map.put("sortBy", sortBy);
        String s = JSON.toJSONString(map);
        //问题是这个s我们可以在这里输出这个s是啥因为page和第一个参数我们是正常看到的 1,来看下
        System.out.println("当前请求的json参数是" + s);
        Log.d(TAG, "getDataFromServer: " + s);
        HttpRxObservable.getObservable(ApiUtils.getApiService().getclassificainfo("mg_mall_goods_list", page, s)).subscribe(new BaseObserver<ClassificationDetailsBean>(mAty) {
            @Override
            public void onHandleSuccess(ClassificationDetailsBean classificationDetailsBean) throws IOException {
                List<ClassificationDetailsBean.DataBean> dataBeans = classificationDetailsBean.getData();
                if (page == 1) {
                    dataBeanList.clear();
                }

                dataBeanList.addAll(dataBeans);
                classificationDetailsAdapter.notifyDataSetChanged();
                classificationDetailsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(mAty, MallGoodsDetailsActivity.class);
                        intent.putExtra("goodid", dataBeans.get(position).getId());
                        // intent.setClass();
                        startActivity(intent);
                    }
                });
            }
        });

    }

    @Override
    protected void getDataFromServer() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getSelectType(SelectEvent event) {
        String filterType = event.event;
        String filetertypeway=event.eventway;
        loadData(filterType,filetertypeway);
//        if ("asc".equals(filterType) || "desc".equals(filterType)) {
//            sortBy=filterType;
//            loadData(sort, filterType);
//            //loadData(sort, filterType);
//        } else {
//            sort=filterType;
//            loadData(filterType, "desc");
//           // loadData(filterType, sortBy);
//        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {//加上判断
            EventBus.getDefault().unregister(this);
        }
    }
}
