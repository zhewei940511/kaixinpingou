package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.HotstyletorecommendAdapter;
import com.laojiashop.laojia.adapter.MycollectionAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.utils.ToastUtil;

import java.util.ArrayList;

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
    private MycollectionAdapter mycollectionAdapter;
    private ArrayList<HotstyletorecommendBean> mDataList;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_mycollection);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("我的收藏");
        rvRecycler.setLayoutManager(new LinearLayoutManager(mAt));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mDataList = new ArrayList<>();
        mycollectionAdapter = new MycollectionAdapter();
        for (int i = 0; i < 4; i++) {
            HotstyletorecommendBean databean = new HotstyletorecommendBean();
            mDataList.add(databean);
        }
        mycollectionAdapter.addData(mDataList);
        mycollectionAdapter.openLoadAnimation();
        mycollectionAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                switch (view.getId())
                {
                    case R.id.btn_delectcollection:
                        ToastUtil.showToast("你点击了"+adapter.getItem(position)+"正在删除请稍后.....");
                        break;
                }
            }
        });
        rvRecycler.addItemDecoration(new DividerItemDecoration(mAt, DividerItemDecoration.VERTICAL));
        rvRecycler.setAdapter(mycollectionAdapter);
    }

    @Override
    public void getDataFromServer() {

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
