package com.laojiashop.laojia.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.ReceivedividendsAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.ConfigBean;
import com.laojiashop.laojia.entity.ReceivedividendsBean;
import com.laojiashop.laojia.entity.UserInfoBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.ReceivedividendsDialogUtil;
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
 * 分红权
 */
public class ReceivedividendsActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.tv_header_right)
    TextView tvHeaderRight;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    //分红权信息相关
    @BindView(R.id.tv_count)
    TextView tvCount;
    @BindView(R.id.tv_now)
    TextView tvNow;
    @BindView(R.id.tv_end)
    TextView tvEnd;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.btm_exchange)
    Button btmExchange;
    //列表相关
    //分红权数据
    private List<ReceivedividendsBean.DataBean> dataBeanList = new ArrayList<>();
    private int page = 1;
    //适配器
    private ReceivedividendsAdapter receivedividendsAdapter;
    //退出分红权开关
    private String bonus_exit_bonus;
    //分红权兑换开关
    private String bonus_exchange;
    private Dialog dialog;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_receivedividends);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("董事分红");
        tvHeaderRight.setVisibility(View.VISIBLE);
        tvHeaderRight.setText("分红权是什么");
        recyclerView.setLayoutManager(new LinearLayoutManager(mAt));
        receivedividendsAdapter = new ReceivedividendsAdapter(dataBeanList);
        //无数据显示空
        receivedividendsAdapter.setEmptyView(LayoutInflater.from(mAt).inflate(R.layout.layout_empty_view, recyclerView, false));
        //绑定适配器
        recyclerView.setAdapter(receivedividendsAdapter);
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
        receivedividendsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ReceivedividendsBean.DataBean dataBean= (ReceivedividendsBean.DataBean) adapter.getItem(position);
                switch (view.getId())
                {
                    case R.id.btn_bonusexitbonuss:
                            HttpRxObservable.getObservable(ApiUtils.getApiService().exitBonus(dataBean.getId())).subscribe(new BaseObserver<Object>(mAt) {
                                @Override
                                public void onHandleSuccess(Object o) throws IOException {
                                    ToastUtil.showToast("退出成功");
                                    loaddata();
                                }
                            });
                        break;
                }
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        /**
         * 获取配置文件
         */
        HttpRxObservable.getObservable(ApiUtils.getApiService().getConfig()).subscribe(new BaseObserver<ConfigBean>(mAt) {
            @Override
            public void onHandleSuccess(ConfigBean configBean) throws IOException {
                bonus_exit_bonus = configBean.getBonus().getBonus_exit_bonus();
                bonus_exchange = configBean.getBonus().getBonus_exchange();
                receivedividendsAdapter.getValue(bonus_exit_bonus);
                receivedividendsAdapter.notifyDataSetChanged();
                //判断是否显示兑换按钮
                if (Integer.parseInt(bonus_exchange) == 1) {
                    btmExchange.setVisibility(View.VISIBLE);
                } else {
                    btmExchange.setVisibility(View.GONE);
                }
            }
        });
        //获取分红列表
        loaddata();
        /**
         * 获取分红权信息
         */
        HttpRxObservable.getObservable(ApiUtils.getApiService().getuserinfo()).subscribe(new BaseObserver<UserInfoBean>(mAt) {
            @Override
            public void onHandleSuccess(UserInfoBean userInfoBean) throws IOException {
                tvCount.setText(userInfoBean.getBonus().getCount());
                tvNow.setText(userInfoBean.getBonus().getNow());
                tvEnd.setText(userInfoBean.getBonus().getEnd());
            }
        });

    }

    //分红权列表
    private void loaddata() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getBonusList("mg_bonus", page)).subscribe(new BaseObserver<ReceivedividendsBean>(mAt) {
            @Override
            public void onHandleSuccess(ReceivedividendsBean receivedividendsBean) throws IOException {
                //成功
                List<ReceivedividendsBean.DataBean> data = receivedividendsBean.getData();
                if (page == 1) {
                    dataBeanList.clear();
                }
                dataBeanList.addAll(data);
                receivedividendsAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.iv_header_back, R.id.tv_header_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_header_right:
                dialog=ReceivedividendsDialogUtil.showProtocolDialogNoBtns(mAt);
                break;
        }
    }

    /**
     * 兑换按钮
     */
    @OnClick(R.id.btm_exchange)
    public void onViewClicked() {
        jumpActivity(ExchangeActivity.class);
    }

}
