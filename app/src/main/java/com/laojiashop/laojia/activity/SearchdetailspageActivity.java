package com.laojiashop.laojia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.ClassificationDetailsAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.ClassificationDetailsBean;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.view.CustomDrawerPopupView;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.enums.PopupPosition;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private CustomDrawerPopupView customDrawerPopupView;
    private ArrayList<HotstyletorecommendBean> mDataList;
    private boolean isClick = true;
    //定义接收对象
    private String keyword;
    private int page = 1;
    private ClassificationDetailsAdapter classificationDetailsAdapter;
    //数据源
    private List<ClassificationDetailsBean.DataBean> dataBeanList = new ArrayList<>();
    //排序（newest 最新 goodsSales 销量 goodsPrice 价格 goodsComment 评价）
    private String sort="";
    //排序方式(asc 升序 desc 降序)//默认都是降序，除了价格会变动以外
    private String sortBy="" ;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_search_details_page);
    }

    @Override
    protected void initViews() {
        searchdetailRecycler.setLayoutManager(new LinearLayoutManager(mAt));
//        customDrawerPopupView = new CustomDrawerPopupView(mAt);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        keyword = intent.getStringExtra("keyword");
        classificationDetailsAdapter = new ClassificationDetailsAdapter(dataBeanList);
        //无数据显示空
        classificationDetailsAdapter.setEmptyView(LayoutInflater.from(mAt).inflate(R.layout.layout_empty_view, searchdetailRecycler, false));
        //绑定适配器
        searchdetailRecycler.setAdapter(classificationDetailsAdapter);
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

    }

    @Override
    public void getDataFromServer() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyword",keyword);
        map.put("sort", sort);
        map.put("sortBy", sortBy);
        String s = JSON.toJSONString(map);
        HttpRxObservable.getObservable(ApiUtils.getApiService().getclassificainfo("mg_mall_goods_list",page,s)).subscribe(new BaseObserver<ClassificationDetailsBean>(mAt) {
            @Override
            public void onHandleSuccess(ClassificationDetailsBean classificationDetailsBean) throws IOException {
                List<ClassificationDetailsBean.DataBean> dataBeans = classificationDetailsBean.getData();
                if (page == 1) {
                    dataBeanList.clear();
                }
                dataBeanList.addAll(dataBeans);
                classificationDetailsAdapter.notifyDataSetChanged();
                classificationDetailsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        Intent intent = new Intent(mAt, MallGoodsDetailsActivity.class);
                        intent.putExtra("goodid", dataBeans.get(position).getId());
                        // intent.setClass();
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
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
            //排序（newest 最新 goodsSales 销量 goodsPrice 价格 goodsComment 评价）
            //排序方式(asc 升序 desc 降序)//默认都是降序，除了价格会变动以外
            case R.id.search_edittext:
                break;
            case R.id.ly_searchcomprehensive:
                selectstatu();
                lySearchcomprehensive.setSelected(true);
                vwSearchcomprehensive.setVisibility(View.VISIBLE);
                imgUp.setVisibility(View.VISIBLE);
                imgDown.setVisibility(View.VISIBLE);
                isClick = true;
                sort="";
                sortBy="";
                getDataFromServer();
                break;
            case R.id.ly_searchsales:
                selectstatu();
                sort="goodsSales";
                sortBy="desc";
                getDataFromServer();
                lySearchsales.setSelected(true);
                vwSearchsales.setVisibility(View.VISIBLE);
                imgUp.setVisibility(View.VISIBLE);
                imgDown.setVisibility(View.VISIBLE);
                isClick = true;
                break;
            case R.id.ly_searchprice:
                selectstatu();
                sort="goodsPrice";
                lySearchprice.setSelected(true);
                vwSearchprice.setVisibility(View.VISIBLE);
                if (isClick) {
                    imgUp.setVisibility(View.VISIBLE);
                    imgDown.setVisibility(View.GONE);
                    //请求升序的数据
                    isClick = false;
                    sortBy="asc";
                    getDataFromServer();
                } else {
                    //请求降序的数据
                    imgUp.setVisibility(View.GONE);
                    imgDown.setVisibility(View.VISIBLE);
                    isClick = true;
                    sortBy="desc";
                    getDataFromServer();
                }
                break;
            case R.id.ly_searchscreening:
                selectstatu();
                lySearchscreening.setSelected(true);
                vwSearchscreening.setVisibility(View.VISIBLE);
                imgUp.setVisibility(View.VISIBLE);
                imgDown.setVisibility(View.VISIBLE);
                isClick = true;
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
