package com.laojiashop.laojia.fragment.Goldcoins;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.HomemallPageFragmentAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 全部交易
 */
public class AlltransactionsFragment extends BaseFragment {

    @BindView(R.id.rv_hotstyletorecommend)
    RecyclerView rvHotstyletorecommend;
    //数据模拟
    private ArrayList<HotstyletorecommendBean> mDataList;

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_alltransactions;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        rvHotstyletorecommend.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        mDataList=new ArrayList<>();
        for (int i=0;i<6;i++) {
            HotstyletorecommendBean databean = new HotstyletorecommendBean();
            mDataList.add(databean);
        }
        HomemallPageFragmentAdapter adapter=new HomemallPageFragmentAdapter(R.layout.item_hotstyletorecommend,mDataList);
        adapter.openLoadAnimation();
        rvHotstyletorecommend.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rvHotstyletorecommend.setAdapter(adapter);
    }

    @Override
    protected void getDataFromServer() {

    }
}
