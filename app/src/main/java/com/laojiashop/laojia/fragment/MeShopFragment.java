package com.laojiashop.laojia.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.activity.OrderdetailsActivity;
import com.laojiashop.laojia.adapter.MeShopFragmentAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.OrderBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.ToastUtil;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.impl.ConfirmPopupView;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

import static android.content.Context.CLIPBOARD_SERVICE;

public class MeShopFragment extends BaseFragment {
    String state;
    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private ArrayList<OrderBean.DataBean> mDataList = new ArrayList<>();
    private MeShopFragmentAdapter adapter;

    public MeShopFragment(String state) {
        this.state = state;
    }

    private int goodid;
    //声明弹窗类
    private ConfirmPopupView popupView;
//
//    private ClipboardManager mClipboardManager;
//    //剪切板Data对象
//            private ClipData mClipData;
    //剪切板工具类
    private ClipboardManager clipboardManager;
    //剪切板data对象
    private ClipData clipData;
    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_shopallorder;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        clipboardManager= (ClipboardManager) mAty.getSystemService(CLIPBOARD_SERVICE);
        rvRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        adapter = new MeShopFragmentAdapter(mDataList);
        //无数据显示空
        adapter.setEmptyView(LayoutInflater.from(mAty).inflate(R.layout.layout_empty_view, rvRecycler, false));
        //绑定适配器
        rvRecycler.setAdapter(adapter);
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
        //下拉刷新
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getDataFromServer();
                refreshLayout.finishRefresh(true);
            }
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getDataFromServer();
                refreshLayout.finishLoadMore(true);
            }
        });
        //上拉加载
        //item内部按钮点击时间
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                OrderBean.DataBean dataBean = (OrderBean.DataBean) adapter.getItem(position);
                switch (view.getId()) {
                    //复制订单号
                    case R.id.img_copyorder:
                        //创建一个新的文本clip对象
                        clipData=ClipData.newPlainText("orderid",dataBean.getOrder_no());
                        //把data对象放到粘贴板中
                        clipboardManager.setPrimaryClip(clipData);
                        ToastUtil.showToast("复制成功");
                        break;
                    //商品详情
                    case R.id.btn_orderdetails:
                        OrderdetailsActivity.start((dataBean.getId()));
                        break;
                    //取消订单
                    case R.id.btn_cancelorders:
                        popupView = new XPopup.Builder(mAty)
                                .asConfirm("订单取消", "是否取消当前订单",
                                        "取消", "确定",
                                        new OnConfirmListener() {
                                            @Override
                                            public void onConfirm() {
                                                OrderBean.DataBean dataBean = (OrderBean.DataBean) adapter.getItem(position);
                                                cancle(dataBean.getId());
                                            }
                                        }, null, false);
                        popupView.show();
                        break;
                    //删除订单
                    case R.id.btn_delectorder:
                        popupView = new XPopup.Builder(mAty)
                                .asConfirm("删除提示", "是否删除当前订单",
                                        "取消", "确定",
                                        new OnConfirmListener() {
                                            @Override
                                            public void onConfirm() {
                                                OrderBean.DataBean dataBean = (OrderBean.DataBean) adapter.getItem(position);
                                                deleteOrder(dataBean.getId());
                                            }
                                        }, null, false);
                        popupView.show();
                        break;
                    //确认收货
                    case R.id.btn_confirmthegoods:
                        popupView = new XPopup.Builder(mAty)
                                .asConfirm("收货提示", "订单商品是否收到",
                                        "取消", "确定",
                                        new OnConfirmListener() {
                                            @Override
                                            public void onConfirm() {
                                                OrderBean.DataBean dataBean = (OrderBean.DataBean) adapter.getItem(position);
                                                firmreceipt(dataBean.getId());
                                            }
                                        }, null, false);
                        popupView.show();
                        break;
                }
            }
        });

    }

    @Override
    protected void getDataFromServer() {
        Map<String, Object> map = new HashMap<String, Object>();
        //这个地方的typeid是顶部的导航的id
        map.put("status", state);
        String s = JSON.toJSONString(map);
        System.out.println("当前传递的参数是" + s);
        HttpRxObservable.getObservable(ApiUtils.getApiService().getmallOrdergetList("mg_mallorder", page, s)).subscribe(new BaseObserver<OrderBean>(mAty) {
            @Override
            public void onHandleSuccess(OrderBean orderBean) throws IOException {
                List<OrderBean.DataBean> dataBeans = orderBean.getData();
                if (page == 1) {
                    mDataList.clear();
                }
                mDataList.addAll(dataBeans);
                adapter.notifyDataSetChanged();
            }
        });
    }

    //取消订单
    private void cancle(int goodid) {
        this.goodid = goodid;
        HttpRxObservable.getObservable(ApiUtils.getApiService().mallcancelorder(goodid)).subscribe(new BaseObserver<Object>(mAty) {
            @Override
            public void onHandleSuccess(Object o) throws IOException {
                getDataFromServer();
            }
        });
    }

    //确认收货
    private void firmreceipt(int goodid) {
        this.goodid = goodid;
        HttpRxObservable.getObservable(ApiUtils.getApiService().mallconfirmreceipt(goodid)).subscribe(new BaseObserver<Object>(mAty) {
            @Override
            public void onHandleSuccess(Object o) throws IOException {
                getDataFromServer();
            }
        });
    }

    //删除订单
    private void deleteOrder(int goodid) {
        this.goodid = goodid;
        HttpRxObservable.getObservable(ApiUtils.getApiService().malldeleteOrder(goodid)).subscribe(new BaseObserver<Object>(mAty) {
            @Override
            public void onHandleSuccess(Object o) throws IOException {
                getDataFromServer();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDataFromServer();
    }
}
