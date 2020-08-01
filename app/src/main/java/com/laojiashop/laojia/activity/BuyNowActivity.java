package com.laojiashop.laojia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kyleduo.switchbutton.SwitchButton;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.AddressmanagementBean;
import com.laojiashop.laojia.entity.GoodsDetailBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 立即购买
 */
public class BuyNowActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.btn_sub)
    ImageView btnSub;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.btn_add)
    ImageView btnAdd;
    @BindView(R.id.order_switch)
    SwitchButton orderSwitch;
    @BindView(R.id.tv_showuse)
    TextView tvShowuse;
    public static final int REQUEST_CODE_BUYNOW = 1110;
    @BindView(R.id.rl_nothaveaddress)
    RelativeLayout rlNothaveaddress;
    @BindView(R.id.rl_havethedefaultaddress)
    RelativeLayout rlHavethedefaultaddress;
    @BindView(R.id.tv_provinces)
    TextView tvProvinces;
    @BindView(R.id.tv_detailedaddress)
    TextView tvDetailedaddress;
    @BindView(R.id.tv_usernameandphone)
    TextView tvUsernameandphone;
    //商品信息
    @BindView(R.id.tv_goodsinfotitle)
    TextView tvGoodsinfotitle;
    @BindView(R.id.tv_goodsinfospecifications)
    TextView tvGoodsinfospecifications;
    @BindView(R.id.tv_goodsinfoprice)
    TextView tvGoodsinfoprice;
    @BindView(R.id.img_goodsinfopic)
    ImageView imgGoodsinfopic;
    @BindView(R.id.ly_integralinfo)
    LinearLayout lyIntegralinfo;
    @BindView(R.id.tv_integralnum)
    TextView tvIntegralnum;
    @BindView(R.id.tv_actualamountpaidnum)
    TextView tvActualamountpaidnum;
    @BindView(R.id.btn_postorder)
    Button btnPostorder;
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    //定义接收数据
    private String goodsid, number, price, skuname, goodtitle, orderImg, skuCombination;
    private int type, freeze_coin, addressid;
    private float allprice;
    private List<GoodsDetailBean.FreightListBean> freightListBeans = new ArrayList<>();

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_buynow);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        //数据接收
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        goodsid = bundle.getString("goodid");
        type = bundle.getInt("type", 0);
        freeze_coin = bundle.getInt("freeze_coin", 0);
        number = bundle.getString("number");
        price = bundle.getString("pirce");
        skuname = bundle.getString("skuname");
        orderImg = bundle.getString("orderImg");
        goodtitle = bundle.getString("goodtitle");
        skuCombination = bundle.getString("skuCombination");
        freightListBeans = (List<GoodsDetailBean.FreightListBean>) bundle.getSerializable("freightListBeans");
        allprice = (float) (Integer.parseInt(number) * Double.parseDouble(price));
        tvActualamountpaidnum.setText("实付金额￥" + (Integer.parseInt(number) * Double.parseDouble(price)));
//        goodsid = intent.getStringExtra("goodid");
//        type = intent.getIntExtra("type", 0);
//        freeze_coin = intent.getIntExtra("freeze_coin", 0);
//        number = intent.getStringExtra("number");
//        price = intent.getStringExtra("pirce");
//        skuname = intent.getStringExtra("skuname");
//        orderImg = intent.getStringExtra("orderImg");
//        goodtitle = intent.getStringExtra("goodtitle");
        tvNum.setText(number);
        tvGoodsinfotitle.setText(goodtitle);
        tvGoodsinfospecifications.setText("规格: " + skuname);
        tvGoodsinfoprice.setText("价格:￥" + price);
        Glide.with(mAt).load(orderImg).apply(new RequestOptions()
                .placeholder(R.drawable.default_image)).into(imgGoodsinfopic);

        //判断是否有积分
        if (freeze_coin != 0) {
            lyIntegralinfo.setVisibility(View.VISIBLE);
            tvIntegralnum.setText(freeze_coin + "个");
        } else {
            lyIntegralinfo.setVisibility(View.GONE);
        }
        //设置标题
        tvHeaderTitle.setText("确认订单");
        ivHeaderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        orderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tvShowuse.setVisibility(View.VISIBLE);
                } else {
                    tvShowuse.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getaddressgetList("mg_address", 1)).subscribe(new BaseObserver<AddressmanagementBean>(mAt) {
            @Override
            public void onHandleSuccess(AddressmanagementBean addressmanagementBean) throws IOException {
                //成功  你的adapter呢
                List<AddressmanagementBean.DataBean> data = addressmanagementBean.getData();
//                for (int i = 0; i < data.size(); i++) {}


                //判断当前邮费
                for (int i = 0; i < data.size(); i++) {
                    if (freightListBeans.get(i).getId() == data.get(i).getP_code()) {
                        tvYunfei.setText("￥"+freightListBeans.get(i).getFreight());
                    }else {
                        tvYunfei.setText("包邮");
                    }
                    int is_def = data.get(i).getIs_def();
                    if (is_def == 1) {
                        rlHavethedefaultaddress.setVisibility(View.VISIBLE);
                        rlNothaveaddress.setVisibility(View.GONE);
                        tvProvinces.setText(data.get(i).getSsq());
                        tvDetailedaddress.setText(data.get(i).getAddress());
                        tvUsernameandphone.setText(data.get(i).getName() + " " + data.get(i).getPhone());
                    } else {
                        rlNothaveaddress.setVisibility(View.VISIBLE);
                        rlHavethedefaultaddress.setVisibility(View.GONE);
                    }
                }

            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.rl_nothaveaddress, R.id.rl_havethedefaultaddress, R.id.btn_sub, R.id.btn_add, R.id.order_switch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //无默认地址
            case R.id.rl_nothaveaddress:

                break;
            //有默认地址
            case R.id.rl_havethedefaultaddress:
                break;
            case R.id.btn_sub:
                number = tvNum.getText().toString();
                int i = Integer.parseInt(number);
                if (i > 1) {
                    i -= 1;
                    String addNumber = Integer.toString(i);
                    tvNum.setText(addNumber);
                }

                break;
            case R.id.btn_add:
                number = tvNum.getText().toString();
                int addi = Integer.parseInt(number);
                addi += 1;
                String addnum = Integer.toString(addi);
                tvNum.setText(addnum);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_BUYNOW) {
            String address = data.getStringExtra("address");
            if (!TextUtils.isEmpty(address)) {
                // txtSelect.setText(address);
            }
        }
    }

//    public <T> T fromJson(String json, Type typeOfT) throws JsonSyntaxException {
//        if (json == null) {
//            return null;
//        }
//        StringReader reader = new StringReader(json);
//        T target = (T) fromJson(reader, typeOfT);
//        return target;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_postorder)
    public void onViewClicked() {
        //提交订单
        HttpRxObservable.getObservable(ApiUtils.getApiService().createorder(type,
                goodsid,
                "",
                0,
                0,
                allprice,
                number,
                skuCombination,
                addressid,
                "",
                "",
                "",
                "")).subscribe(new BaseObserver<Object>(mAt) {
            @Override
            public void onHandleSuccess(Object o) throws IOException {
                showToast("" + o.toString());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);

            }
        });
    }
}
