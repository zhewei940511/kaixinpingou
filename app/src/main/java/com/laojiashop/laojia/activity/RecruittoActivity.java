package com.laojiashop.laojia.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.MembercenterAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.ServiceFeeBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.RecruittoDialogUtil;
import com.laojiashop.laojia.utils.SPUtils;
import com.laojiashop.laojia.utils.ToastUtil;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 招募令申请界面
 */
public class RecruittoActivity extends BaseActivity {

    @BindView(R.id.tv_uploaddocuments)
    TextView tvUploaddocuments;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.rv_fuwufeilist)
    RecyclerView rvFuwufeilist;
    @BindView(R.id.rd_Useragreement)
    AppCompatCheckBox rdUseragreement;
    //适配器
    private MembercenterAdapter membercenterAdapter;
    //定义变量判断用户是否同意用户协议
    private boolean isagreement = false;
    //等级
    private String levelstr;
    //用户协议
    private String rulestr;
    Dialog dialog;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_recruitto);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("服务费升级");
        rdUseragreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    isagreement = true;
                    if (TextUtils.isEmpty(levelstr))
                    {
                        ToastUtil.showToast("请先选择升级身份");
                        rdUseragreement.setChecked(false);
                        return;
                    }
                    dialog= RecruittoDialogUtil.showProtocolDialogNoBtns(mAt);
                } else {
                    isagreement = false;
                }
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getServiceFeeindex()).subscribe(new BaseObserver<ServiceFeeBean>(mAt) {
            @Override
            public void onHandleSuccess(ServiceFeeBean serviceFeeBean) throws IOException {
                List<ServiceFeeBean.LevelBean> levelBeans = serviceFeeBean.getLevel();
                //初始化
                membercenterAdapter = new MembercenterAdapter(levelBeans);
                //绑定适配器
                rvFuwufeilist.setLayoutManager(new LinearLayoutManager(mAt));
                rvFuwufeilist.setAdapter(membercenterAdapter);
                membercenterAdapter.setItemPayWays(new MembercenterAdapter.ItemWays() {
                    @Override
                    public void getWays(String level, int position) {
                        levelstr = level;
                        rulestr=getHtmlData(levelBeans.get(position).getRule());
                        SPUtils.putString(mAt,"rulestr",rulestr);
                    }

                });

            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.rd_Useragreement, R.id.iv_header_back, R.id.tv_uploaddocuments})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回按钮
            case R.id.iv_header_back:
                finish();
                break;
            //立即申请
            case R.id.tv_uploaddocuments:
                if (TextUtils.isEmpty(levelstr))
                {
                    ToastUtil.showToast("请选择升级身份");
                    return;
                }
                if (!isagreement) {
                    ToastUtil.showToast("请先仔细阅读用户协议");
                    return;
                }
                HttpRxObservable.getObservable(ApiUtils.getApiService().serviceFeeadd(levelstr)).subscribe(new BaseObserver<Object>(mAt) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        ToastUtil.showToast("申请已提交");
                        jumpActivity(UploaddocumentsActivity.class);
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtil.showToast(e.getMessage());
                    }
                });
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    private String getHtmlData(String bodyHTML) {
        String head = "<head>"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> "
                + "<style>img{max-width: 100%; width:100%; height:auto;}*{margin:0px;}</style>"
                + "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
    public static String setWebVIewImage(String star) {
        String head = "<head>"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> "
                + "<style>img{max-width: 100%; width:auto; height:auto;}</style>"
                + "<style>table{max-width: 100%; width:auto; height:auto;}</style>"
                + "</head>";
        return "<html>" + head + "<body>" + star + "</body></html>";
    }
    @OnClick(R.id.rd_Useragreement)
    public void onViewClicked() {
    }
}
