package com.laojiashop.laojia.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.OrderGoodsdetailBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.view.ExpandLayout;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 商品详情页
 */
public class OrderdetailsActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.btn_toviewmore)
    TextView btnToviewmore;
    @BindView(R.id.expandLayout)
    ExpandLayout expandLayout;
    @BindView(R.id.img_toviewmore)
    ImageView imgToviewmore;
    //用户信息商品信息
    @BindView(R.id.tv_ordergoodusername)
    TextView tvOrdergoodusername;
    @BindView(R.id.tv_ordergoodphonenum)
    TextView tvOrdergoodphonenum;
    @BindView(R.id.tv_tv_ordergoodaddress)
    TextView tvTvOrdergoodaddress;
    @BindView(R.id.img_ordergoodpic)
    ImageView imgOrdergoodpic;
    @BindView(R.id.tv_ordergoodtitle)
    TextView tvOrdergoodtitle;
    @BindView(R.id.tv_ordergoodsku)
    TextView tvOrdergoodsku;
    @BindView(R.id.tv_ordergoodspace)
    TextView tvOrdergoodspace;
    @BindView(R.id.tv_ordergoodtotal)
    TextView tvOrdergoodtotal;
    @BindView(R.id.tv_ordergoodfreight)
    TextView tvOrdergoodfreight;
    //订单信息
    @BindView(R.id.tv_ordergoodnum)
    TextView tvOrdergoodnum;
    @BindView(R.id.tv_ordergoodpaynum)
    TextView tvOrdergoodpaynum;
    @BindView(R.id.tv_ordergoodwuliu)
    TextView tvOrdergoodwuliu;
    @BindView(R.id.tv_ordergoodpaytotal)
    TextView tvOrdergoodpaytotal;
    @BindView(R.id.tv_ordergoodpayreal)
    TextView tvOrdergoodpayreal;
    @BindView(R.id.tv_ordergoodcreatetime)
    TextView tvOrdergoodcreatetime;
    @BindView(R.id.tv_ordergoodmark)
    TextView tvOrdergoodmark;
    //支付信息
    @BindView(R.id.tv_ordergoodpayway)
    TextView tvOrdergoodpayway;
    @BindView(R.id.tv_ordergoodserialnumber)
    TextView tvOrdergoodserialnumber;
    @BindView(R.id.tv_ordergoodpaymenttime)
    TextView tvOrdergoodpaymenttime;
    @BindView(R.id.tv_ordergoodgoldbuckle)
    TextView tvOrdergoodgoldbuckle;
    @BindView(R.id.tv_ordergoodpointsdeduction)
    TextView tvOrdergoodpointsdeduction;
    //物流信息
    @BindView(R.id.tv_logisticsinformationuser)
    TextView tvLogisticsinformationuser;
    @BindView(R.id.tv_logisticsinformationnum)
    TextView tvLogisticsinformationnum;
    @BindView(R.id.tv_logisticsinformationtime)
    TextView tvLogisticsinformationtime;

    @BindView(R.id.ry_center)
    RelativeLayout ryCenter;
    //支付信息
    @BindView(R.id.ly_payinfo)
    LinearLayout lyPayinfo;
    //物流信息
    @BindView(R.id.ly_logisticsinfo)
    LinearLayout lyLogisticsinfo;
    @BindView(R.id.btn_detailscancle)
    Button btnDetailscancle;
    @BindView(R.id.btn_detailsdelete)
    Button btnDetailsdelete;
    @BindView(R.id.btn_detailspay)
    Button btnDetailspay;
    @BindView(R.id.btn_detailreturngoods)
    Button btnDetailreturngoods;
    @BindView(R.id.btn_detailconfirm)
    Button btnDetailconfirm;
    @BindView(R.id.btn_detailevaluation)
    Button btnDetailevaluation;
    //商品ID
    private int goodsid;
    public static final String KEY_DATA = "KEY_DATA";

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_orderdetails);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("订单详情");
        expandLayout.initExpand(false);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
//        OrderBean orderBean= (OrderBean) getIntent().getSerializableExtra(KEY_DATA);
        goodsid = getIntent().getIntExtra(KEY_DATA, 0);
        HttpRxObservable.getObservable(ApiUtils.getApiService().mallOrderdetail(goodsid)).subscribe(new BaseObserver<OrderGoodsdetailBean>(mAt) {
            @Override
            public void onHandleSuccess(OrderGoodsdetailBean orderGoodsdetailBean) throws IOException {
                //判断订单状态
                switch (orderGoodsdetailBean.getStatus()) {
                    //代支付
                    case 1:
                        //隐藏物流信息和支付信息
                        lyPayinfo.setVisibility(View.GONE);
                        lyLogisticsinfo.setVisibility(View.GONE);
                        btnDetailscancle.setVisibility(View.VISIBLE);
                        btnDetailspay.setVisibility(View.VISIBLE);
                        break;
                    //待发货
                    case 2:
                        lyPayinfo.setVisibility(View.VISIBLE);
                        lyLogisticsinfo.setVisibility(View.GONE);
                        btnDetailreturngoods.setVisibility(View.VISIBLE);
                        break;
                    //待收货
                    case 3:
                        lyPayinfo.setVisibility(View.VISIBLE);
                        lyLogisticsinfo.setVisibility(View.VISIBLE);
                        btnDetailreturngoods.setVisibility(View.VISIBLE);
                        btnDetailreturngoods.setBackgroundResource(R.drawable.shape_button_black_cc);
                        btnDetailreturngoods.setTextColor(Color.parseColor("#969696"));
                        btnDetailconfirm.setVisibility(View.VISIBLE);
                        break;
                    //已签收
                    case 4:
                        lyPayinfo.setVisibility(View.VISIBLE);
                        lyLogisticsinfo.setVisibility(View.VISIBLE);
                        btnDetailevaluation.setVisibility(View.VISIBLE);
                        break;
                        //过期订单
                    case 91:
                        lyPayinfo.setVisibility(View.GONE);
                        lyLogisticsinfo.setVisibility(View.GONE);
                        btnDetailsdelete.setVisibility(View.VISIBLE);
                        break;
                        //已取消
                    case 90:
                        lyPayinfo.setVisibility(View.VISIBLE);
                        lyLogisticsinfo.setVisibility(View.VISIBLE);
                        btnDetailsdelete.setVisibility(View.VISIBLE);
                        break;
                }

                tvOrdergoodusername.setText(orderGoodsdetailBean.getAddress().getName());
                tvOrdergoodphonenum.setText(orderGoodsdetailBean.getAddress().getPhone());
                tvTvOrdergoodaddress.setText(orderGoodsdetailBean.getAddress().getSsq() + "/" + orderGoodsdetailBean.getAddress().getAddress());
                //tvTvOrdergoodaddress.setText(orderGoodsdetailBean.getAddress().getSsq()+"/"+orderGoodsdetailBean.getAddress().getAddress());
                for (int i = 0; i < orderGoodsdetailBean.getGoods_info().size(); i++) {
                    Glide.with(mAt).load(orderGoodsdetailBean.getGoods_info().get(i).getPath()).apply(new RequestOptions().placeholder(R.mipmap.default_userhead_image)).into(imgOrdergoodpic);
                    tvOrdergoodtitle.setText(orderGoodsdetailBean.getGoods_info().get(i).getGoods_title());
                    tvOrdergoodsku.setText(orderGoodsdetailBean.getGoods_info().get(i).getSku_name());
                }
                tvOrdergoodtotal.setText("￥" + orderGoodsdetailBean.getTotal());
                tvOrdergoodfreight.setText("(含运费" + orderGoodsdetailBean.getFreight() + ")");
                //订单信息
                tvOrdergoodnum.setText(orderGoodsdetailBean.getOrder_no());
                tvOrdergoodpaynum.setText("￥" + orderGoodsdetailBean.getGoods_pay());
                tvOrdergoodwuliu.setText("￥" + orderGoodsdetailBean.getFreight());
                tvOrdergoodpaytotal.setText("￥" + orderGoodsdetailBean.getTotal());
                tvOrdergoodpayreal.setText("￥" + orderGoodsdetailBean.getPay());
                tvOrdergoodmark.setText(orderGoodsdetailBean.getMark());
                //支付信息
                tvOrdergoodpayway.setText(orderGoodsdetailBean.getPay_way_txt());
                tvOrdergoodserialnumber.setText(orderGoodsdetailBean.getPayment_no());
                tvOrdergoodpaymenttime.setText(orderGoodsdetailBean.getPay_time());
                tvOrdergoodgoldbuckle.setText(orderGoodsdetailBean.getCoin());
                tvOrdergoodpointsdeduction.setText(orderGoodsdetailBean.getScore());
                //物流信息
                tvLogisticsinformationuser.setText(orderGoodsdetailBean.getExpress_title());
                tvLogisticsinformationnum.setText(orderGoodsdetailBean.getExpress_no());
                tvLogisticsinformationtime.setText(orderGoodsdetailBean.getSend_time());
                //判断是否显示物流信息和支付信息

//                if (OrderStatus.DAI_FU_KUAN.status.equals(orderGoodsdetailBean.getStatus()))
//                {
//                    //隐藏支付信息物流信息
//                    lyPayinfo.setVisibility(View.GONE);
//                    lyPayinfo.setVisibility(View.GONE);
//                }
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.btn_detailevaluation,R.id.btn_detailscancle, R.id.btn_detailsdelete, R.id.btn_detailspay, R.id.btn_detailreturngoods, R.id.btn_detailconfirm, R.id.iv_header_back, R.id.btn_toviewmore})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //订单评价
            case R.id.btn_detailevaluation:
                Intent intent=new Intent(mAt, GoodscommentActivity.class);
                startActivity(intent);
                break;
            //取消订单
            case R.id.btn_detailscancle:
                HttpRxObservable.getObservable(ApiUtils.getApiService().mallcancelorder(goodsid)).subscribe(new BaseObserver<Object>(mAt) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        showToast("订单取消成功");
                        finish();
                    }
                });
                break;
            //删除订单
            case R.id.btn_detailsdelete:
                HttpRxObservable.getObservable(ApiUtils.getApiService().malldeleteOrder(goodsid)).subscribe(new BaseObserver<Object>(mAt) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        showToast("订单删除成功");
                        finish();
                    }
                });
                break;
            //支付订单(暂不支持微信)
            case R.id.btn_detailspay:
                showToast("微信支付完善中...");
                break;
            //退换货(暂不支持该功能)
            case R.id.btn_detailreturngoods:
                showToast("退货功能完善中");
                break;
            //确认收货
            case R.id.btn_detailconfirm:
                HttpRxObservable.getObservable(ApiUtils.getApiService().mallconfirmreceipt(goodsid)).subscribe(new BaseObserver<Object>(mAt) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        showToast("订单操作成功");
                        finish();
                    }
                });
                break;
            //返回按钮
            case R.id.iv_header_back:
                finish();
                break;
            //订单信息查看更多
            case R.id.btn_toviewmore:
                if (expandLayout.isExpand()) {
                    imgToviewmore.setImageResource(R.mipmap.icon_pickup);
                } else {
                    imgToviewmore.setImageResource(R.mipmap.icon_unfold);
                }
                expandLayout.toggleExpand();
                break;
        }
    }

    public static void start(int goodsid) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_DATA, goodsid);
        ActivityUtils.startActivity(bundle, OrderdetailsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick()
    public void onViewClicked() {
    }
}
