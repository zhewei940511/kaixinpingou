package com.laojiashop.laojia.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.AfterSaleapplyBean;
import com.laojiashop.laojia.entity.OrderGoodsdetailBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.ToastUtil;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 申请售后
 */
public class ApplyforaftersalesActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.btn_submittheaftersale)
    Button btnSubmittheaftersale;
    @BindView(R.id.tv_showServicetypetxt)
    TextView tvShowServicetypetxt;
    @BindView(R.id.ly_chooseServicetype)
    LinearLayout lyChooseServicetype;
    @BindView(R.id.tv_showreturnReasontypetxt)
    TextView tvShowreturnReasontypetxt;
    @BindView(R.id.ly_returnReasontype)
    LinearLayout lyReturnReasontype;
    @BindView(R.id.img_ordergoodpic)
    ImageView imgOrdergoodpic;
    @BindView(R.id.tv_ordergoodtitle)
    TextView tvOrdergoodtitle;
    @BindView(R.id.tv_ordergoodsku)
    TextView tvOrdergoodsku;
    @BindView(R.id.tv_ordergoodspace)
    TextView tvOrdergoodspace;
    @BindView(R.id.picselect_recycler)
    RecyclerView picselectRecycler;
    /**
     * 模拟数据显示
     */
    private List<String> choosetype;
    //选择器
    private OptionsPickerView optionsPickerView;
    //商品ID，订单ID,商品信息
    private OrderGoodsdetailBean goodsdetailBean;
    //商品ID
    private int goodsid;
    //订单ID
    private int orderid;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_applyforaftersales);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("申请售后");
        goodsid = getIntent().getIntExtra("goods_id", 0);
        orderid = getIntent().getIntExtra("order_id", 0);
        goodsdetailBean = (OrderGoodsdetailBean) getIntent().getSerializableExtra("data");

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
//        //数据模拟
//        choosetype = new ArrayList<>();
//        choosetype.add("测试1");
//        choosetype.add("测试2");
//        choosetype.add("测试3");
//        choosetype.add("测试4");
    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().AfterSaleapplyPage(orderid, goodsid)).subscribe(new BaseObserver<AfterSaleapplyBean>(mAt) {
            @Override
            public void onHandleSuccess(AfterSaleapplyBean afterSaleapplyBean) throws IOException {
                for (int i = 0; i < afterSaleapplyBean.getGoods_info().size(); i++) {
                    Glide.with(mAt).load(afterSaleapplyBean.getGoods_info().get(i).getPath()).apply(new RequestOptions().placeholder(R.mipmap.default_userhead_image)).into(imgOrdergoodpic);
                    tvOrdergoodtitle.setText(afterSaleapplyBean.getGoods_info().get(i).getGoods_title());
                    tvOrdergoodsku.setText(afterSaleapplyBean.getGoods_info().get(i).getSku_name());
                }
                choosetype = new ArrayList<>();
                for (int i = 0; i < afterSaleapplyBean.getSale_text().size(); i++) {
                    choosetype.add(afterSaleapplyBean.getSale_text().get(i).getName());
                }

            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.showToast(e.getMessage());
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.iv_header_back, R.id.btn_submittheaftersale, R.id.ly_chooseServicetype, R.id.ly_returnReasontype})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回按钮
            case R.id.iv_header_back:
                finish();
                break;
            //跳转申请结果
            case R.id.btn_submittheaftersale:
                jumpActivity(ProcessingresultsActivity.class);
                break;
            //选择服务类型
            case R.id.ly_chooseServicetype:
                showpick();
                break;
            //选择退款原因
            case R.id.ly_returnReasontype:
                break;
        }
    }

    /**
     * 弹窗
     */
    private void showpick() {
        optionsPickerView = new OptionsPickerBuilder(mAt, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = choosetype.get(options1);
                tvShowServicetypetxt.setText(tx);
            }
        }).setTitleText("")//设置标题
                .setTitleBgColor(Color.parseColor("#ffffff"))//设置背景颜色
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .setLineSpacingMultiplier(2)//设置行间距
                .setCancelColor(Color.parseColor("#666666"))
                .setSubmitColor(Color.parseColor("#FF666C"))
                .build();
        optionsPickerView.setPicker(choosetype);
        optionsPickerView.show();
    }

    //参数传递
    public static void invoke(Activity activity, int goodid, int order_id, OrderGoodsdetailBean item) {
        Intent intent = new Intent(activity, ApplyforaftersalesActivity.class);
        intent.putExtra("goods_id", goodid);
        intent.putExtra("order_id", order_id);
        intent.putExtra("data", (Serializable) item);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
