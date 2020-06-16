package com.laojiashop.laojia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.activity.GoodscommentActivity;
import com.laojiashop.laojia.activity.OrderdetailsActivity;
import com.laojiashop.laojia.adapter.HomemallPageFragmentAdapter;
import com.laojiashop.laojia.adapter.MeShopFragmentAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class MeShopFragment extends BaseFragment {
    String state;
    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArrayList<HotstyletorecommendBean> mDataList;
    MeShopFragmentAdapter adapter=new MeShopFragmentAdapter();
    public MeShopFragment(String state) {
        this.state = state;
    }

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_shopallorder;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        rvRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        mDataList=new ArrayList<>();
        for (int i=0;i<6;i++) {
            HotstyletorecommendBean databean = new HotstyletorecommendBean();
            mDataList.add(databean);
        }
        adapter.addData(mDataList);
        adapter.openLoadAnimation();
        //rvRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //item内部按钮点击时间
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //判断按钮点击
                if (view.getId()==R.id.btn_orderdetails)
                {
                    Intent intent=new Intent(mAty, OrderdetailsActivity.class);
                    startActivity(intent);
                }
            }
        });
        //item点击实践
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent=new Intent(mAty, GoodscommentActivity.class);
                startActivity(intent);
            }
        });
        rvRecycler.setAdapter(adapter);
    }

    @Override
    protected void getDataFromServer() {

    }
}
