package com.laojiashop.laojia.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.MyTeamAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.GoldcoinsBean;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.entity.MyteamBean;
import com.laojiashop.laojia.entity.MyteamListBean;
import com.laojiashop.laojia.entity.UserInfoBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.LoginInfoUtil;;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.subsciber.IProgressDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MyTeamActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_erweimacode)
    TextView tvHeaderErweimacode;
    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.tv_totalnumberteams)
    TextView tvTotalnumberteams;
    @BindView(R.id.tv_numberteamaddedtoday)
    TextView tvNumberteamaddedtoday;
    @BindView(R.id.tv_myteamperformance)
    TextView tvMyteamperformance;
    @BindView(R.id.tv_teamnewperformancetoday)
    TextView tvTeamnewperformancetoday;
    private int page = 1;
    //适配器
    private MyTeamAdapter myTeamAdapter;
    //数据源
    private List<MyteamListBean.ListBean> listBeans = new ArrayList<>();

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_myteam);
    }

    @Override
    protected void initViews() {
        rvRecycler.setLayoutManager(new LinearLayoutManager(mAt));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        rvRecycler.setLayoutManager(new LinearLayoutManager(mAt));
        myTeamAdapter = new MyTeamAdapter(listBeans);
        //无数据显示空
        myTeamAdapter.setEmptyView(LayoutInflater.from(mAt).inflate(R.layout.layout_empty_view, rvRecycler, false));
        //绑定适配器
        rvRecycler.setAdapter(myTeamAdapter);
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
        //下拉刷新监听
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                loaddata();
                refreshLayout.finishRefresh(true);
            }
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                loaddata();
                refreshLayout.finishLoadMore(true);
            }
        });
    }

    @Override
    public void getDataFromServer() {
        //加载团队业绩列表
        loaddata();
        //请求团队业绩页面
        HttpRxObservable.getObservable(ApiUtils.getApiService().MyTeamIndex()).subscribe(new BaseObserver<MyteamBean>(mAt) {
            @Override
            public void onHandleSuccess(MyteamBean myteamBean) throws IOException {
                tvTotalnumberteams.setText(String.valueOf(myteamBean.getTotalNumberTeams()));
                tvNumberteamaddedtoday.setText(String.valueOf(myteamBean.getNumberTeamAddedToday()));
                tvMyteamperformance.setText(myteamBean.getMyTeamPerformance());
                tvTeamnewperformancetoday.setText(myteamBean.getTeamNewPerformanceToday());
            }
        });
    }

    //加载团队列表
    private void loaddata() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getMyTeamList(page)).subscribe(new BaseObserver<MyteamListBean>(mAt) {
            @Override
            public void onHandleSuccess(MyteamListBean myteamListBean) throws IOException {
                //成功
                List<MyteamListBean.ListBean> data = myteamListBean.getList();
                if (page == 1) {
                    listBeans.clear();
                }
                listBeans.addAll(data);
                myTeamAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.iv_header_back, R.id.tv_header_erweimacode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回
            case R.id.iv_header_back:
                finish();
                break;
            //二维码
            case R.id.tv_header_erweimacode:
                jumpActivity(MyPromotioncodeActivity.class);
//                finish();
                break;
        }
    }

    /**
     * 生成二維碼
     */
    private void createQRCode(String content) {
//        new Thread(() -> {
//            //生成二维码相关放在子线程里面
//            Bitmap logo = BitmapFactory.decodeResource(getResources(),R.drawable.icon_test);
//            Bitmap bitmap =  CodeUtils.createQRCode(content,600,logo);
//            runOnUiThread(()->{
//                //显示二维码
//                ivCode.setImageBitmap(bitmap);
//            });
//        }).start();

    }

}
