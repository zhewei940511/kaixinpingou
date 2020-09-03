package com.laojiashop.laojia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kyleduo.switchbutton.SwitchButton;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.PayWaysAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.AddressmanagementBean;
import com.laojiashop.laojia.entity.CreateOrderBean;
import com.laojiashop.laojia.entity.GoodsDetailBean;
import com.laojiashop.laojia.entity.PayWayBean;
import com.laojiashop.laojia.entity.ScoreInfoBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.RegexUtils;
import com.laojiashop.laojia.utils.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 立即购买
 * 代码逻辑比较混乱使用GotoToconfirmorderActivity进行代替
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
    //    @BindView(R.id.tv_showuse)
//    TextView tvShowuse;
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
    @BindView(R.id.rv_pay_ways)
    RecyclerView rvPayWays;
    @BindView(R.id.ly_isshowuserinfo)
    LinearLayout lyIsshowuserinfo;
    //用户名
    @BindView(R.id.et_username)
    EditText etUsername;
    //身份证号
    @BindView(R.id.et_useridcard)
    EditText etUseridcard;
    //总积分
    @BindView(R.id.tv_allscore)
    TextView tvAllscore;
    //积分相关
    //积分充足
    @BindView(R.id.ly_sufficient)
    LinearLayout lySufficient;
    @BindView(R.id.tv_consumptionscore)
    TextView tvConsumptionscore;
    @BindView(R.id.tv_deductionscore)
    TextView tvDeductionscore;
    //积分不足
    @BindView(R.id.ly_insufficient)
    LinearLayout lyInsufficient;
    //积分不足
    @BindView(R.id.tv_insufficientscore)
    TextView tvInsufficientscore;
    //金币开关控制
    @BindView(R.id.ly_showcoin)
    LinearLayout lyShowcoin;
    //金币相关
    @BindView(R.id.tv_allcoin)
    TextView tvAllcoin;
    @BindView(R.id.tv_usecoin)
    TextView tvUsecoin;
    @BindView(R.id.tv_deductioncoin)
    TextView tvDeductioncoin;
    //支付方式
    @BindView(R.id.ly_payway)
    LinearLayout lyPayway;
    //定义接收数据
    private String goodsid, number, price, skuname, goodtitle, orderImg, skuCombination;
    //商品类型,抵扣积分.地址ID,是否跨境商品,所有积分,所有金币,用户使用积分,用户使用金币,allcoin,usecoin,allscore, usescore,paycore
    private int type, freeze_coin, addressid, is_cross_border;
    //allprice实际付款金额=商品价格+邮费+积分需要（如果有金币抵扣-金币抵扣的）
    private float allprice, allcoin, usecoin, allscore, usescore, paycore;
    //点击开关过后显示的价格
    private float pay;
    //支付方式
    private int payWayid;

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
        usecoin=0;
        goodsid = bundle.getString("goodid");
        type = bundle.getInt("type", 0);
        freeze_coin = bundle.getInt("freeze_coin", 0);
        number = bundle.getString("number");
        price = bundle.getString("pirce");
        skuname = bundle.getString("skuname");
        orderImg = bundle.getString("orderImg");
        goodtitle = bundle.getString("goodtitle");
        skuCombination = bundle.getString("skuCombination");
        is_cross_border = bundle.getInt("is_cross_border");
        //判断是否显示身份证信息(跨境商品需要填写身份证跨境商品1其他商品不需要)
        if (is_cross_border == 1) {
            lyIsshowuserinfo.setVisibility(View.VISIBLE);
        } else {
            lyIsshowuserinfo.setVisibility(View.GONE);
        }
        freightListBeans = (List<GoodsDetailBean.FreightListBean>) bundle.getSerializable("freightListBeans");
        // tvActualamountpaidnum.setText("实付金额￥" + (Integer.parseInt(number) * Double.parseDouble(price)+usescore));
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
       // tvHeaderTitle.setText("确认订单");
        ivHeaderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //控制是否展开金币
        orderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //开启金币抵扣
                    lyShowcoin.setVisibility(View.VISIBLE);
                    //判断开关按钮点击过后价格显示
                    if (allprice <= allcoin) {
                        pay = 0;
                        tvActualamountpaidnum.setText("实付金额￥" + 0.0);
                        usecoin=allprice;
                    } else {
                        pay = allprice - allcoin;
                        tvActualamountpaidnum.setText("实付金额￥"+String.valueOf(allprice-allcoin));
                        //如果金币不足,那么可抵扣的就等于所有金币
                        usecoin=allcoin;
                    }
                    //开启金币抵扣进行判断用户可抵扣的金币数
                    //判断支付方式显示隐藏
                    if (pay==0)
                    {
                        lyPayway.setVisibility(View.GONE);
                    }else {
                        lyPayway.setVisibility(View.VISIBLE);
                    }
                } else {
                    //关闭金币抵扣
                    lyShowcoin.setVisibility(View.GONE);
                    pay=allprice;
                    //关闭的情况下金币就为0；
                    usecoin=0;
                    tvActualamountpaidnum.setText("实付金额￥" + allprice);
                    lyPayway.setVisibility(View.VISIBLE);
                }
                System.out.println("当前pay的值是"+pay);

            }
        });

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        //获取可使用的积分和金币
        HttpRxObservable.getObservable(ApiUtils.getApiService().getScoreInfo()).subscribe(new BaseObserver<ScoreInfoBean>(mAt) {
            @Override
            public void onHandleSuccess(ScoreInfoBean scoreInfoBean) throws IOException {
                //积分
                allscore = scoreInfoBean.getScore();
                //金币
                allcoin = scoreInfoBean.getCoin();
                //设置总金币
                tvAllcoin.setText(String.valueOf(allcoin));
                //设置总积分
                tvAllscore.setText(String.valueOf(allscore) + "个");
                //判断显示积分充足的样式/积分不足显示样式50>30
                //积分充足
                if (allscore >= freeze_coin) {
                    lySufficient.setVisibility(View.VISIBLE);
                    lyInsufficient.setVisibility(View.GONE);
                    tvConsumptionscore.setText(String.valueOf(freeze_coin) + "个");
                    tvDeductionscore.setText(String.valueOf(freeze_coin) + "元");
                    //积分充足赋值商品积分
                    usescore = freeze_coin;
                    paycore = freeze_coin;
                } else {
                    //积分不足
                    lyInsufficient.setVisibility(View.VISIBLE);
                    lySufficient.setVisibility(View.GONE);
                    tvInsufficientscore.setText(String.valueOf(freeze_coin - allscore) + "元");
                    //积分不足赋值所有积分
                    usescore = freeze_coin - allscore;
                    paycore = allscore;
                    //System.out.println("需要支付的现金" + usescore);
                }
//                paycore=freeze_coin;
//                tvActualamountpaidnum.setText("实付金额￥" + (Integer.parseInt(number) * Double.parseDouble(price)+usescore));

                //获取地址列表进行判断
                HttpRxObservable.getObservable(ApiUtils.getApiService().getaddressgetList("mg_address", 1)).subscribe(new BaseObserver<AddressmanagementBean>(mAt) {
                    @Override
                    public void onHandleSuccess(AddressmanagementBean addressmanagementBean) throws IOException {

                        //成功请求地址列表，（其他地方代码太乱这里就是获取到地址列表）
                        List<AddressmanagementBean.DataBean> data = addressmanagementBean.getData();
                        int pCode = data.get(0).getP_code();
                        int is_def = data.get(0).getIs_def();
                        addressid = data.get(0).getId();
                        if (is_def == 1) {
                            rlHavethedefaultaddress.setVisibility(View.VISIBLE);
                            rlNothaveaddress.setVisibility(View.GONE);
                            tvProvinces.setText(data.get(0).getSsq());
                            tvDetailedaddress.setText(data.get(0).getAddress());
                            tvUsernameandphone.setText(data.get(0).getName() + " " + data.get(0).getPhone());
                        } else {
                            rlNothaveaddress.setVisibility(View.VISIBLE);
                            rlHavethedefaultaddress.setVisibility(View.GONE);
                        }
                        // int yunfei=0;
                        //判断当前邮费将商品详情里面的freightListBeans的id和地址列表里面的id进行匹配
                        for (int i = 0; i < freightListBeans.size(); i++) {

                            if (freightListBeans.get(i).getId() == pCode) {
                                // yunfei = freightListBeans.get(i).getFreight();
                                tvYunfei.setText("￥" + freightListBeans.get(i).getFreight());
                                allprice = (float) (Integer.parseInt(number) * Double.parseDouble(price)) + freightListBeans.get(i).getFreight() + usescore;
                                //  System.out.println("信息"+freightListBeans.get(i).getFreight());
                                tvActualamountpaidnum.setText("实际付款金额" + String.valueOf(allprice));
                                //判断金币是否充足(如果总金币大于商品价格金币充足)
                                if (allcoin >= allprice) {
                                    //金币充足情况(可使用=商品价格=抵扣)
                                    tvUsecoin.setText(String.valueOf(allprice));
                                    tvDeductioncoin.setText(String.valueOf(allprice));
                                    //抵扣的金币就等于价格
//                                    usecoin = allprice;
                                } else {
                                    //金币不足(可使用=总金币=抵扣)
                                    tvUsecoin.setText(String.valueOf(allcoin));
                                    tvDeductioncoin.setText(String.valueOf(allcoin));
                                    //usecoin = allcoin;
                                }

                                //判断第一个是否是默认地址如果为1
                                return;
                                //所有价格=商品数量*商品价格+邮费+可使用积分
//                                allprice = (float) (Integer.parseInt(number) * Double.parseDouble(price)) + freightListBeans.get(i).getFreight()+usescore;
                                // break;
                            } else {
                                //  yunfei = 0;
                                tvYunfei.setText("包邮");
                                allprice = (float) (Integer.parseInt(number) * Double.parseDouble(price)) + usescore;
                                //  System.out.println("信息"+freightListBeans.get(i).getFreight());
                                tvActualamountpaidnum.setText("实际付款金额" + String.valueOf(allprice));
                                //判断金币是否充足(如果总金币大于商品价格金币充足)
                                if (allcoin >= allprice) {
                                    //金币充足情况(可使用=商品价格=抵扣)
                                    tvUsecoin.setText(String.valueOf(allprice));
                                    tvDeductioncoin.setText(String.valueOf(allprice));
                                    //抵扣的金币就等于价格
                                    //usecoin = allprice;
                                } else {
                                    //金币不足(可使用=总金币=抵扣)
                                    tvUsecoin.setText(String.valueOf(allcoin));
                                    tvDeductioncoin.setText(String.valueOf(allcoin));
                                    //usecoin = allcoin;
                                }

//                                if (is_def == 1) {
//                                    rlHavethedefaultaddress.setVisibility(View.VISIBLE);
//                                    rlNothaveaddress.setVisibility(View.GONE);
//                                    tvProvinces.setText(data.get(0).getSsq());
//                                    tvDetailedaddress.setText(data.get(0).getAddress());
//                                    tvUsernameandphone.setText(data.get(0).getName() + " " + data.get(0).getPhone());
//                                } else {
//                                    rlNothaveaddress.setVisibility(View.VISIBLE);
//                                    rlHavethedefaultaddress.setVisibility(View.GONE);
//                                }
//                                allprice = (float) (Integer.parseInt(number) * Double.parseDouble(price))+usescore;
                            }
                            // tvActualamountpaidnum.setText("实付金额￥" + (Integer.parseInt(number) * Double.parseDouble(price)+usescore));
                        }
                        //判断支付方式显示或隐藏
//                        if (allprice==0)
//                        {
//                            lyPayway.setVisibility(View.GONE);
//                        }else
//                        {
//                            lyPayway.setVisibility(View.VISIBLE);
//                        }
                    }

                });
            }
        });
        //获取支付方式
        HttpRxObservable.getObservable(ApiUtils.getApiService().getPayWayList()).subscribe(new BaseObserver<PayWayBean>(mAt) {
            @Override
            public void onHandleSuccess(PayWayBean payWayBean) throws IOException {
                List<PayWayBean.PayWayListBean> payWayListBeans = payWayBean.getPayWayList();
                rvPayWays.setLayoutManager(new LinearLayoutManager(mAt));
                PayWaysAdapter adapter = new PayWaysAdapter(R.layout.pay_ways_item, payWayListBeans);
                //设置是否需要分割线
                //rvPayWays.addItemDecoration(new DividerItemDecoration(mAt, DividerItemDecoration.VERTICAL));
                rvPayWays.setAdapter(adapter);
                //设置点击事件
                adapter.setItemPayWays(new PayWaysAdapter.ItemPayWays() {
                    @Override
                    public void getPayWays(String payWays, int position) {
                        //获取支付ID
                        payWayid = payWayListBeans.get(position).getId();
                        System.out.println("当前选中的支付方式ID"+payWayid);
                    }
                });
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
                    tvIntegralnum.setText((freeze_coin * Integer.parseInt(addNumber) + "个"));
                    tvInsufficientscore.setText(String.valueOf((freeze_coin * Integer.parseInt(addNumber) - allscore) + "元"));
                }

                break;
            case R.id.btn_add:
                number = tvNum.getText().toString();
                int addi = Integer.parseInt(number);
                addi += 1;
                String addnum = Integer.toString(addi);
                tvNum.setText(addnum);
                tvIntegralnum.setText((freeze_coin * Integer.parseInt(addnum) + "个"));
                tvInsufficientscore.setText(String.valueOf((freeze_coin * Integer.parseInt(addnum) - allscore) + "元"));
                break;
        }
    }

    /**
     * 地址回调暂时没处理
     * @param requestCode
     * @param resultCode
     * @param data
     */
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


    @OnClick(R.id.btn_postorder)
    public void onViewClicked() {
        //判断境外商品和普通商品
        String username;
        String id_card;
        if (is_cross_border == 1) {
            //提交订单前的非空判断
            username = etUsername.getText().toString().trim();
            id_card = etUseridcard.getText().toString().trim();
            if (TextUtils.isEmpty(username)) {
                ToastUtil.showToast("请输入真实姓名");
                return;
            }
            if (!RegexUtils.checkIdCard(id_card)) {
                ToastUtil.showToast("您输入的身份证号格式不正确");
                return;
            }
        } else {
            //如果是普通商品将用户名和身份证号置成空
            username = "";
            id_card = "";
        }
        //提交订单
//        HttpRxObservable.getObservable(ApiUtils.getApiService().createorder(type,
//                goodsid,
//                "",
//                usecoin,
//                paycore,
//                pay,
//                number,
//                skuCombination,
//                addressid,
//                username,
//                id_card,
//                "",
//                "")).subscribe(new BaseObserver<CreateOrderBean>(mAt) {
////            @Override
////            public void onHandleSuccess(Object o) throws IOException {
////                ToastUtil.showToast("订单提交成功");
////                //跳转全部订单
////
////            }
//
//            @Override
//            public void onHandleSuccess(CreateOrderBean createOrderBean) throws IOException {
//                Intent allorderintent = new Intent(mAt, ShoporderActivity.class);
//                allorderintent.putExtra("index", "3");
//                startActivity(allorderintent);
//                finish();
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//
//            }
//        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
