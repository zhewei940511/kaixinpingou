package com.laojiashop.laojia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;
import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.AddressmanagementAdapter;
import com.laojiashop.laojia.adapter.MycollectionAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.AddressmanagementBean;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.entity.MycollectionBean;
import com.laojiashop.laojia.entity.OrderBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 地址管理界面
 */
public class AddressmanagementActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.rv_address)
    RecyclerView rvAddress;
    @BindView(R.id.btn_newaddress)
    Button btnNewaddress;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    //适配器
    private AddressmanagementAdapter addressmanagementAdapter;
    //模拟数据
    private List<AddressmanagementBean.DataBean> mDataList = new ArrayList<>();
    private int page = 1;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_address_management);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("收货地址");
        rvAddress.setLayoutManager(new LinearLayoutManager(mAt));
        addressmanagementAdapter = new AddressmanagementAdapter(mDataList);
        //无数据显示空
        addressmanagementAdapter.setEmptyView(LayoutInflater.from(mAt).inflate(R.layout.layout_empty_view, rvAddress, false));
        //设置滑动删除
        OnItemDragListener itemDragListener=new OnItemDragListener() {
            @Override
            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int i) {

            }

            @Override
            public void onItemDragMoving(RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder1, int i1) {

            }

            @Override
            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int i) {

            }
        };

        ItemDragAndSwipeCallback itemDragAndSwipeCallback=new ItemDragAndSwipeCallback(addressmanagementAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rvAddress);
        // 开启滑动删除
        addressmanagementAdapter.enableSwipeItem();
       // addressmanagementAdapter.setOnItemSwipeListener(onItemSwipeListener);
        //绑定适配器
        rvAddress.setAdapter(addressmanagementAdapter);
        //设置点击事件
        addressmanagementAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //标记是这个吗嗯，对头
                switch (view.getId()) {
                    //编辑地址事件
                    case R.id.img_editaddress:
                        AddressmanagementBean.DataBean item = addressmanagementAdapter.getItem(position);
                        //跳哪个界面，需要哪些参数名字，电话是否是默认地址
                        NewaddressActivity.invoke(mAt, 1, item);
                        break;
                }
            }
        });


        //设置头
        refreshLayout.setRefreshFooter(new BallPulseFooter(mAt).setSpinnerStyle(SpinnerStyle.Scale));
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
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        mDataList = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            HotstyletorecommendBean databean = new HotstyletorecommendBean();
//            mDataList.add(databean);
//        }
//        AddressmanagementAdapter addressmanagementAdapter = new AddressmanagementAdapter(R.layout.item_address_management, mDataList);
//        addressmanagementAdapter.openLoadAnimation();
//        rvAddress.setAdapter(addressmanagementAdapter);
    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getaddressgetList("mg_address", page)).subscribe(new BaseObserver<AddressmanagementBean>(mAt) {
            @Override
            public void onHandleSuccess(AddressmanagementBean addressmanagementBean) throws IOException {
                //成功  你的adapter呢
                List<AddressmanagementBean.DataBean> data = addressmanagementBean.getData();
                if (page == 1) {
                    mDataList.clear();
                }
                mDataList.addAll(data);
                addressmanagementAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.iv_header_back, R.id.btn_newaddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回按钮
            case R.id.iv_header_back:
                finish();
                break;
            //新增地址
            case R.id.btn_newaddress:
                // 跳转地址界面
                //jumpActivity(NewaddressActivity.class);
                NewaddressActivity.invoke(mAt, 0, null);//这个地方可以不需要动，动也可以
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //返回回调
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDataFromServer();
    }
}
