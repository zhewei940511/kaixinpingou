package com.laojiashop.laojia.fragment.HappybeanPage;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.OngoingFragmentAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class OngoingFragment extends BaseFragment {


    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    //模拟数据
    private ArrayList<HotstyletorecommendBean> mDataList;
    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_ongoing;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        rvRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        mDataList=new ArrayList<>();
        for (int i=0;i<4;i++) {
            HotstyletorecommendBean databean = new HotstyletorecommendBean();
            mDataList.add(databean);
        }
        OngoingFragmentAdapter adapter=new OngoingFragmentAdapter(R.layout.item_ongoing,mDataList);
        adapter.openLoadAnimation();
        rvRecycler.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        //刷新数据
        //  pageLoadUtil = PageLoadUtil.get((BaseActivity) getActivity(), rvRecycler, adapter, refreshLayout);

        rvRecycler.setAdapter(adapter);
    }

    @Override
    protected void getDataFromServer() {

    }

}
