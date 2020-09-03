package com.laojiashop.laojia.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.LogisticsAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.LogisticsBean;
import com.laojiashop.laojia.utils.CustomViewDialog;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.CenterPopupView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 售后处理结果
 */
public class ProcessingresultsActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.btn_checkthelogistics)
    Button btnCheckthelogistics;
    @BindView(R.id.btn_submitorder)
    Button btnSubmitorder;
    //弹窗
//    private CustomViewDialog customViewDialog;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_processingresults);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("申请售后");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick({R.id.btn_checkthelogistics, R.id.btn_submitorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //查看物流
            case R.id.btn_checkthelogistics:
                CustomPopup customPopup = new CustomPopup(mAt);
                new XPopup.Builder(mAt).asCustom(customPopup).show();
                break;
            //提交单号
            case R.id.btn_submitorder:
                break;
        }
    }

    /*
    就是这里
     */
    public static class CustomPopup extends CenterPopupView {

        private List<LogisticsBean> traceList = new ArrayList<>(10);
        private LogisticsAdapter adapter;

        public CustomPopup(@NonNull Context context) {
            super(context);
        }

        @Override
        protected int getImplLayoutId() {
            return R.layout.activity_logisticsdialog_view;
        }

        @Override
        protected void onCreate() {
            super.onCreate();
            RecyclerView rvLogistics = findViewById(R.id.rv_logistics);
            // 模拟一些假的数据
            traceList.add(new LogisticsBean("2016-05-25 17:48:00", "[沈阳市] [沈阳和平五部]的派件已签收 感谢使用中通快递,期待再次为您服务!"));
            traceList.add(new LogisticsBean("2016-05-25 14:13:00", "[沈阳市] [沈阳和平五部]的东北大学代理点正在派件 电话:18040xxxxxx 请保持电话畅通、耐心等待"));
            traceList.add(new LogisticsBean("2016-05-25 13:01:04", "[沈阳市] 快件到达 [沈阳和平五部]"));
            traceList.add(new LogisticsBean("2016-05-25 12:19:47", "[沈阳市] 快件离开 [沈阳中转]已发往[沈阳和平五部]"));
            traceList.add(new LogisticsBean("2016-05-25 11:12:44", "[沈阳市] 快件到达 [沈阳中转]"));
            traceList.add(new LogisticsBean("2016-05-24 03:12:12", "[嘉兴市] 快件离开 [杭州中转部]已发往[沈阳中转]"));
            traceList.add(new LogisticsBean("2016-05-23 21:06:46", "[杭州市] 快件到达 [杭州汽运部]"));
            traceList.add(new LogisticsBean("2016-05-23 18:59:41", "[杭州市] 快件离开 [杭州乔司区]已发往[沈阳]"));
            traceList.add(new LogisticsBean("2016-05-23 18:35:32", "[杭州市] [杭州乔司区]的市场部已收件 电话:18358xxxxxx"));
            adapter = new LogisticsAdapter();
            rvLogistics.setLayoutManager(new LinearLayoutManager(getContext()));
            rvLogistics.setAdapter(adapter);
            adapter.addData(traceList);
            findViewById(R.id.cancle).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }

        protected void onShow() {
            super.onShow();
        }
    }
}
