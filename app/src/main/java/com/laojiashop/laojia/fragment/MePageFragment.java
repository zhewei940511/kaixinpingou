package com.laojiashop.laojia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.activity.GoldcoinsActivity;
import com.laojiashop.laojia.activity.MeHappybeanActivity;
import com.laojiashop.laojia.activity.ReceivedividendsActivity;
import com.laojiashop.laojia.activity.SetActivity;
import com.laojiashop.laojia.activity.ShoporderActivity;
import com.laojiashop.laojia.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的个人中心页面
 */
public class MePageFragment extends BaseFragment {
    @BindView(R.id.img_myset)
    ImageView imgMyset;
    @BindView(R.id.ly_goldcoins)
    LinearLayout lyGoldcoins;
    @BindView(R.id.ly_receivedividends)
    LinearLayout lyReceivedividends;
    @BindView(R.id.rl_addressmanagement)
    RelativeLayout rlAddressmanagement;
    @BindView(R.id.ly_happybean)
    LinearLayout lyHappybean;
    @BindView(R.id.ly_mepagegenerationofpayment)
    LinearLayout lyMepagegenerationofpayment;
    @BindView(R.id.ly_mepagedropshipping)
    LinearLayout lyMepagedropshipping;
    @BindView(R.id.ly_mepageforthegoods)
    LinearLayout lyMepageforthegoods;
    @BindView(R.id.mepagehavethegoods)
    LinearLayout mepagehavethegoods;
    @BindView(R.id.ly_allorders)
    LinearLayout lyAllorders;

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_me;
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

    //设置图标点击事件
    @OnClick(R.id.img_myset)
    public void onClicked() {
        Intent intent = new Intent(mAty, SetActivity.class);
        startActivity(intent);
    }


    @OnClick({R.id.ly_receivedividends, R.id.rl_addressmanagement, R.id.ly_goldcoins, R.id.ly_happybean,R.id.ly_mepagegenerationofpayment, R.id.ly_mepagedropshipping, R.id.ly_mepageforthegoods, R.id.mepagehavethegoods, R.id.ly_allorders})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //我得金币点击事件
            case R.id.ly_goldcoins:
                Intent intent = new Intent(mAty, GoldcoinsActivity.class);
                startActivity(intent);
                break;
            //分红权
            case R.id.ly_receivedividends:
                Intent receivedintent = new Intent(mAty, ReceivedividendsActivity.class);
                startActivity(receivedintent);
                break;
            //我的地址
            case R.id.rl_addressmanagement:
                break;
            //开心豆
            case R.id.ly_happybean:
                Intent happybeanintent = new Intent(mAty, MeHappybeanActivity.class);
                startActivity(happybeanintent);
                break;
                //待付款
            case R.id.ly_mepagegenerationofpayment:
                Intent generationointent=new Intent(getActivity(),ShoporderActivity.class);
                generationointent.putExtra("index","2");
                startActivity(generationointent);
                break;
                //待发货
            case R.id.ly_mepagedropshipping:
                break;
                //待收货
            case R.id.ly_mepageforthegoods:
                break;
                //已收货
            case R.id.mepagehavethegoods:
                break;
                //全部订单
            case R.id.ly_allorders:
                Intent allorderintent=new Intent(getActivity(),ShoporderActivity.class);
                allorderintent.putExtra("index","1");
                startActivity(allorderintent);
               // startActivity(new Intent(getContext(), ShoporderActivity.class));
                break;
        }
    }

}
