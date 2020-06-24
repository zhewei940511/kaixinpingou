package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.HomemallPageFragmentAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.view.CustomDrawerPopupView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupPosition;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchdetailspageActivity extends BaseActivity {
    @BindView(R.id.search_edittext)
    EditText searchEdittext;
    @BindView(R.id.vw_searchcomprehensive)
    View vwSearchcomprehensive;
    @BindView(R.id.ly_searchcomprehensive)
    LinearLayout lySearchcomprehensive;
    @BindView(R.id.vw_searchsales)
    View vwSearchsales;
    @BindView(R.id.ly_searchsales)
    LinearLayout lySearchsales;
    @BindView(R.id.vw_searchprice)
    View vwSearchprice;
    @BindView(R.id.ly_searchprice)
    LinearLayout lySearchprice;
    @BindView(R.id.vw_searchscreening)
    View vwSearchscreening;
    @BindView(R.id.ly_searchscreening)
    LinearLayout lySearchscreening;
    @BindView(R.id.searchdetail_recycler)
    RecyclerView searchdetailRecycler;
    @BindView(R.id.img_searchback)
    ImageView imgSearchback;
    @BindView(R.id.img_up)
    ImageView imgUp;
    @BindView(R.id.img_down)
    ImageView imgDown;
    private CustomDrawerPopupView customDrawerPopupView;

    private ArrayList<HotstyletorecommendBean> mDataList;

    private boolean isClick = true;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_search_details_page);
    }

    @Override
    protected void initViews() {
        searchdetailRecycler.setLayoutManager(new LinearLayoutManager(mAt));
        customDrawerPopupView = new CustomDrawerPopupView(mAt);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        tgafristlist=new ArrayList<>();
//        tgafristlist.add("软包抽纸");
//        tgafristlist.add("盒装抽纸");
//        tgafristlist.add("卷纸");
//        tgaFrist.setTags(tgafristlist);
        mDataList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            HotstyletorecommendBean databean = new HotstyletorecommendBean();
            mDataList.add(databean);
        }
        HomemallPageFragmentAdapter adapter = new HomemallPageFragmentAdapter(R.layout.item_hotstyletorecommend, mDataList);
        adapter.openLoadAnimation();
        searchdetailRecycler.addItemDecoration(new DividerItemDecoration(mAt, DividerItemDecoration.VERTICAL));
        searchdetailRecycler.setAdapter(adapter);
    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    //重置所有状态
    private void selectstatu() {
        //默认都补选中
        lySearchcomprehensive.setSelected(false);
        lySearchsales.setSelected(false);
        lySearchprice.setSelected(false);
        lySearchscreening.setSelected(false);
        //设置视图不显示
        vwSearchcomprehensive.setVisibility(View.GONE);
        vwSearchprice.setVisibility(View.GONE);
        vwSearchsales.setVisibility(View.GONE);
        vwSearchscreening.setVisibility(View.GONE);
    }

    @OnClick({R.id.search_edittext, R.id.ly_searchcomprehensive, R.id.ly_searchsales, R.id.ly_searchprice, R.id.ly_searchscreening})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_edittext:
                break;
            case R.id.ly_searchcomprehensive:
                selectstatu();
                lySearchcomprehensive.setSelected(true);
                vwSearchcomprehensive.setVisibility(View.VISIBLE);
                imgUp.setVisibility(View.VISIBLE);
                imgDown.setVisibility(View.VISIBLE);
                isClick=true;
                break;
            case R.id.ly_searchsales:
                selectstatu();
                lySearchsales.setSelected(true);
                vwSearchsales.setVisibility(View.VISIBLE);
                imgUp.setVisibility(View.VISIBLE);
                imgDown.setVisibility(View.VISIBLE);
                isClick=true;
                break;
            case R.id.ly_searchprice:
                selectstatu();
                lySearchprice.setSelected(true);
                vwSearchprice.setVisibility(View.VISIBLE);
                if (isClick) {
                    imgUp.setVisibility(View.VISIBLE);
                    imgDown.setVisibility(View.GONE);
                    //请求升序的数据
                    isClick = false;
                } else {
                    //请求降序的数据
                    imgUp.setVisibility(View.GONE);
                    imgDown.setVisibility(View.VISIBLE);
                    isClick = true;
                }
                break;
            case R.id.ly_searchscreening:
                selectstatu();
                lySearchscreening.setSelected(true);
                vwSearchscreening.setVisibility(View.VISIBLE);
                imgUp.setVisibility(View.VISIBLE);
                imgDown.setVisibility(View.VISIBLE);
                isClick=true;
                //显示右边弹窗
                new XPopup.Builder(mAt)
                        .popupPosition(PopupPosition.Right)//右边
                        .hasStatusBarShadow(true) //启用状态栏阴影
                        .asCustom(customDrawerPopupView)
                        .show();

                break;
        }
    }


    @OnClick(R.id.img_searchback)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
