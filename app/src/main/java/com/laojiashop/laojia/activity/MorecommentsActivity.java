package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.HotstyletorecommendAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.utils.BarUtils;
import com.laojiashop.laojia.utils.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 查看更多评论
 */
public class MorecommentsActivity extends BaseActivity {
    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;

    //模拟数据
    private ArrayList<HotstyletorecommendBean> mDataList;
    private HotstyletorecommendAdapter hotstyletorecommendAdapter;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_more_comment);
    }

    @Override
    protected void initViews() {
//        int barHeight = StatusBarUtil.getStatusBarHeight(this);
//        if (barHeight > 0) {
//            //设置状态栏的高度
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mRootView.getLayoutParams();
//            layoutParams.topMargin = BarUtils.getStatusBarHeight(this) + layoutParams.topMargin;
//            mRootView.setLayoutParams(layoutParams);
//        }
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("商品评价");
        ivHeaderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        rvRecycler.setLayoutManager(new LinearLayoutManager(mAt));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mDataList = new ArrayList<>();
        hotstyletorecommendAdapter = new HotstyletorecommendAdapter();
        for (int i = 0; i < 6; i++) {
            HotstyletorecommendBean databean = new HotstyletorecommendBean();
            mDataList.add(databean);
        }
        hotstyletorecommendAdapter.addData(mDataList);
        hotstyletorecommendAdapter.openLoadAnimation();
        rvRecycler.addItemDecoration(new DividerItemDecoration(mAt, DividerItemDecoration.VERTICAL));
        rvRecycler.setAdapter(hotstyletorecommendAdapter);
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
}
