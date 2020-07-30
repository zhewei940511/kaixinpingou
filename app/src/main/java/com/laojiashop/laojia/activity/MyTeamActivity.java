package com.laojiashop.laojia.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.MyTeamAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.entity.MyteamBean;
import com.laojiashop.laojia.entity.MyteamListBean;
import com.laojiashop.laojia.entity.UserInfoBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.LoginInfoUtil;;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
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
    MyTeamAdapter myTeamAdapter=new MyTeamAdapter();

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
        //你的RV呢，走个几把毛
//        mDataList = new ArrayList<>();
//        for (int i = 0; i < 4; i++) {
//            HotstyletorecommendBean databean = new HotstyletorecommendBean();
//            mDataList.add(databean);
//        }
//        myTeamAdapter = new MyTeamAdapter(R.layout.item_mytem, mDataList);
        rvRecycler.setAdapter(myTeamAdapter);
    }

    @Override
    public void getDataFromServer() {
        /**
         * 团队信息接口
         */
//        HttpRxObservable.getObservable(ApiUtils.getApiService().myteam()).subscribe(new BaseObserver<MyteamBean.DataBean>(mAt) {
//            @Override
//            public void onHandleSuccess(MyteamBean.DataBean myteamBean) throws IOException {
//                tvMyteamperformance.setText(myteamBean.getMyTeamPerformance()+"W");
//                tvNumberteamaddedtoday.setText(myteamBean.getNumberTeamAddedToday()+"");
//                tvTeamnewperformancetoday.setText(myteamBean.getTeamNewPerformanceToday()+"W");
//                tvTotalnumberteams.setText(myteamBean.getTotalNumberTeams()+"");
//            }
//        });
//        IProgressDialog mProgressDialog = new IProgressDialog() {
//            @Override
//            public Dialog getDialog() {
//                ProgressDialog dialog = new ProgressDialog(mAt);
//                dialog.setMessage("请稍后...");
//                return dialog;
//            }
//        };
//                EasyHttp.get("/appApi/v1/MyTeam/index").headers("token", LoginInfoUtil.getToken())
//                .execute(new ProgressDialogCallBack<MyteamBean>(mProgressDialog, true, true) {
//
//                    @Override
//                    public void onError(ApiException e) {
//                        super.onError(e);
//                    }
//
//                    @Override
//                    public void onSuccess(MyteamBean myteamBean) {
//                        showToast(""+myteamBean.getTotalNumberTeams());
//                       // ToastUtil.showToast(""+myteamBean.getTotalNumberTeams());
//                    }
//                });

//        });
        //就是这里，下面这个是吧嗯 让你后台改一下这个吧
       // 这个data默认返回的的是“”，你是用list接收的，要么，返回null，要么返回【】，只能这样。
//            HttpRxObservable.getObservable(ApiUtils.getApiService().MyTeamList(1)).subscribe(new BaseObserver<MyteamListBean>(mAt) {
//                @Override
//                public void onHandleSuccess(MyteamListBean listBeans) throws IOException {
//                             myTeamAdapter.addData(listBeans);
//                             //我这边太卡了，你初始化的RV呢，你说不需要啊我是黄油刀操作的
//                }
//
//                @Override
//                public void onHandleError(ApiException apiExc) {
//                    super.onHandleError(apiExc);
//                }
//            });
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
//                    HttpRxObservable.getObservable(ApiUtils.getApiService().makerelationbycode("18888888880")).subscribe(new BaseObserver<Object>(mAt) {
//                        @Override
//                        public void onHandleSuccess(Object o) throws IOException {
//
//                        }
//
//                        @Override
//                        public void onHandleError(ApiException apiExc) {
//                            super.onHandleError(apiExc);
//                        }
//                    });
//                HttpRxObservable.getObservable(ApiUtils.getApiService().happybean(200)).subscribe(new BaseObserver<Object>(mAt) {
//                    @Override
//                    public void onHandleSuccess(Object o) throws IOException {
//
//                    }
//
//                    @Override
//                    public void onHandleError(ApiException apiExc) {
//                        super.onHandleError(apiExc);
//                    }
//                });
                break;
        }
    }
}
