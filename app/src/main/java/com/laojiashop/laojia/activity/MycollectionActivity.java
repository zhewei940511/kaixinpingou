package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.MycollectionAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.MycollectionBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.ToastUtil;
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
 * 我的收藏
 */
public class MycollectionActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private MycollectionAdapter mycollectionAdapter;
    private List<MycollectionBean.DataBean> mDataList=new ArrayList<>();
    private int page = 1;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_mycollection);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("我的收藏");
        rvRecycler.setLayoutManager(new LinearLayoutManager(mAt));
        mycollectionAdapter=new MycollectionAdapter(mDataList);
        //无数据显示空
        mycollectionAdapter.setEmptyView(LayoutInflater.from(mAt).inflate(R.layout.layout_empty_view, rvRecycler, false));
        //绑定适配器
        rvRecycler.setAdapter(mycollectionAdapter);
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
        //设置点击事件
        mycollectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MycollectionBean.DataBean dataBean= (MycollectionBean.DataBean) adapter.getItem(position);
                switch (view.getId())
                {
                    //取消收藏
                    case R.id.btn_delectcollection:
                        cancleCollection(String.valueOf(dataBean.getGoods_id()));
                        break;
                }
            }
        });
    }

    private void cancleCollection(String item_id) {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getaddcollect("Collect",item_id)).subscribe(new BaseObserver<Object>(mAt) {
            @Override
            public void onHandleSuccess(Object o) throws IOException {
                ToastUtil.showToast("取消成功");
                // 刷新数据
                getDataFromServer();
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getcollectgetList("mg_collect_list",page)).subscribe(new BaseObserver<MycollectionBean>(mAt) {
            @Override
            public void onHandleSuccess(MycollectionBean mycollectionBean) throws IOException {
                //成功
                List<MycollectionBean.DataBean> data = mycollectionBean.getData();
                if (page == 1) {
                    mDataList.clear();
                }
                mDataList.addAll(data);
                mycollectionAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }

}
