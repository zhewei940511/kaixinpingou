package com.laojiashop.laojia.test;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.GoodsDetailBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.orhanobut.logger.Logger;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends BaseActivity {
    @BindView(R.id.tv)
    Button tv;
    private TextView goInput;
    private List<GoodsDetailBean.SkuBean.ListBean> listBeans;
    private List<GoodsDetailBean.SkuBean.TreeBean> treeBeanList;
    private TextView tv_shop_price, tv_shop_attr;
    //选中的文字拼接
    private ArrayList<String> strings;
    private HashMap<Object, Object> map;
    //商品ID
    private String goodsid;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void initViews() {
        //数据请求
        Intent intent = getIntent();
        goodsid = String.valueOf(intent.getIntExtra("goodid", 0));
        tv_shop_price=findViewById(R.id.tv_prices);

//        tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                showPopupWindow();
//            }
//        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        //数据请求
        HttpRxObservable.getObservable(ApiUtils.getApiService().getgooddetailinfo(goodsid)).subscribe(new BaseObserver<GoodsDetailBean>(mAt) {
            @Override
            public void onHandleSuccess(GoodsDetailBean goodsDetailBean) throws IOException {
                //newCourseDetailBean=goodsDetailBean;
                listBeans = goodsDetailBean.getSku().getList();
                treeBeanList = goodsDetailBean.getSku().getTree();
                map = new HashMap<>();
                strings = new ArrayList<>();
//                stringsid=new ArrayList<>();
                for (int i = 0; i < treeBeanList.size(); i++) {
                    strings.add(i, "");
                }
                Logger.i("------初始化的名称----" + strings.toString());
                RecyclerView rv_list = findViewById(R.id.rv_list);
                rv_list.setLayoutManager(new LinearLayoutManager(mAt));
                rv_list.setAdapter(new SkuAdapter(R.layout.test_activity_item, treeBeanList));

            }
        });

    }

    public class SkuAdapter extends BaseQuickAdapter<GoodsDetailBean.SkuBean.TreeBean, BaseViewHolder> {


        public SkuAdapter(int layoutResId, @Nullable List<GoodsDetailBean.SkuBean.TreeBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NonNull BaseViewHolder helper, GoodsDetailBean.SkuBean.TreeBean item) {
            final TagFlowLayout rlShopColor = helper.getView(R.id.rl_shop_section);
            TextView tv_xueduan = helper.getView(R.id.tv_xueduan);
            rlShopColor.setMaxSelectCount(1);
            final ArrayList<String> mVals = new ArrayList<>();
            final List<GoodsDetailBean.SkuBean.TreeBean.VBean> items = item.getV();
            //循环外层拿到treebean数据

            for (GoodsDetailBean.SkuBean.TreeBean.VBean info : items) {
                mVals.add(info.getName());
            }
            tv_xueduan.setText(item.getK());
            rlShopColor.setAdapter(new TagAdapter<String>(mVals) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView textView = (TextView) LayoutInflater.from(mContext).inflate(R.layout.tv, rlShopColor, false);
                    textView.setText(s);
                    return textView;
                }
            });
            rlShopColor.setOnSelectListener(selectPosSet -> {
                if (selectPosSet.isEmpty()) {
                    Logger.i("------没选----");
                } else {
                    for (Integer in : selectPosSet) {
                       // int pId = item.getV().get(in).getId();
                        //int id = items.get(in).getId();
                        //map.put(pId, pId + "-" + id);
                        attributeView(in, item);
                       // Logger.i("------类型选择学段----" + pId + "-" + id);
                    }
                }
            });
        }
    }

    private void attributeView(int in, GoodsDetailBean.SkuBean.TreeBean item) {
        Collection<Object> values = map.values();
        List<Object> valueList = new ArrayList<Object>(values);
        for (int i = 0; i < treeBeanList.size(); i++) {
            if (item.getV().get(i).getId() == treeBeanList.get(i).getV().get(i).getId()) {
                strings.set(i, String.valueOf(item.getV().get(in).getId()));
            }
        }
        Logger.i("------111----" + valueList.toString());
        Logger.i("------选择的名称----" + strings.toString());
        StringBuilder mystr = new StringBuilder();
        for (String s : strings) {
            if (!s.equals("")) {
                mystr.append(s).append("-");
            }
        }
        String selectKind = mystr.substring(0, mystr.length() - 1);
//        tv_shop_attr.setText(mystr);
        Logger.i("------要显示的东西----" + selectKind);
        if (treeBeanList.size() == valueList.size()) {
            for (GoodsDetailBean.SkuBean.ListBean specPrice : listBeans) {
                String[] specIdArray = specPrice.getCost_price().split(",");
                List<String> specIdList = Arrays.asList(specIdArray);
                Logger.i("------222----" + specIdList.toString());
                //500-502, 491-493, 503-505, 497-499, 494-495, 506-507]
                if (specIdList.size() == valueList.size() && specIdList.containsAll(valueList)) {
                   // String price = specPrice.getPrice();
                    //tv_shop_price.setText(price);
                    // goInput.setBackgroundResource(R.drawable.default_button_background_selector2);
                    Logger.i("------最终选择----" + valueList.toString());
                    Logger.i("------最终id----" + specPrice.getSku());
                }
            }
        }
    }

    private void setGoInput(int type, Dialog mBottomSheetDialog) {
        Collection<Object> values = map.values();
        List<Object> valueList = new ArrayList<Object>(values);
        if (treeBeanList.size() != valueList.size()) {
            Toast.makeText(TestActivity.this, "请选择规格", Toast.LENGTH_SHORT).show();
        } else {
            for (GoodsDetailBean.SkuBean.ListBean specPrice : listBeans) {
                String[] specIdArray = specPrice.getCost_price().split(",");
                List<String> specIdList = Arrays.asList(specIdArray);
                if (specIdList.size() == valueList.size() && specIdList.containsAll(valueList)) {
//                        goodSKUId = specPrice.getId();
                    Logger.i("------最终选择----" + valueList.toString());
                    Logger.i("------最终id----" + specPrice.getSku());
                }
            }
            if (type == 1) {
//                    buyCourseHaveAttr();
            }
            mBottomSheetDialog.dismiss();
        }
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
