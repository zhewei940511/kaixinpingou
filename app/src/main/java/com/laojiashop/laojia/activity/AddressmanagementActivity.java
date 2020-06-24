package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.AddressmanagementAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;

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
    //模拟数据
    private ArrayList<HotstyletorecommendBean> mDataList;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_address_management);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("收货地址");
        rvAddress.setLayoutManager(new LinearLayoutManager(mAt));
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mDataList=new ArrayList<>();
        for (int i=0;i<4;i++) {
            HotstyletorecommendBean databean = new HotstyletorecommendBean();
            mDataList.add(databean);
        }
        AddressmanagementAdapter addressmanagementAdapter=new AddressmanagementAdapter(R.layout.item_address_management,mDataList);
        addressmanagementAdapter.openLoadAnimation();
        rvAddress.setAdapter(addressmanagementAdapter);
    }

    @Override
    public void getDataFromServer() {

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
                jumpActivity(NewaddressActivity.class);
                break;
        }
    }

}
