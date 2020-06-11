package com.laojiashop.laojia.fragment;

import android.os.Bundle;
import android.view.View;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseFragment;

public class MeShopFragment extends BaseFragment {
    String state;
    public MeShopFragment(String state) {
        this.state = state;
    }
    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_shopallorder;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getDataFromServer() {

    }
}
