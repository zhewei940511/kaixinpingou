package com.laojiashop.laojia.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kyleduo.switchbutton.SwitchButton;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.PayWaysAdapter;
import com.laojiashop.laojia.alipay.AuthResult;
import com.laojiashop.laojia.alipay.PayResult;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.AddressmanagementBean;
import com.laojiashop.laojia.entity.CreateOrderBean;
import com.laojiashop.laojia.entity.PayOrderInfoBean;
import com.laojiashop.laojia.entity.PayWayBean;
import com.laojiashop.laojia.entity.ScoreInfoBean;
import com.laojiashop.laojia.entity.TuanGouDetailsBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.RegexUtils;
import com.laojiashop.laojia.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 确认订单界面
 * 拼一单确认订单
 * 当前页面处理的逻辑
 * 第一步获取到页面传递的地址列表循环出运费(计算运费)
 * 第二步计算积分(判断积分是否充足)
 * 第三步运算金币使用(判断金币是否充足)
 * 第四步运算出所有金额(商品总价-(使用的积分+使用的金币))
 */
public class SpellgroupGotoToconfirmorderActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    //提交订单按钮
    @BindView(R.id.btn_postorder)
    Button btnPostorder;
    //商品信息相关
    @BindView(R.id.img_goodsinfopic)
    ImageView imgGoodsinfopic;
    @BindView(R.id.tv_goodsinfotitle)
    TextView tvGoodsinfotitle;
    @BindView(R.id.tv_goodsinfospecifications)
    TextView tvGoodsinfospecifications;
    @BindView(R.id.tv_goodsinfoprice)
    TextView tvGoodsinfoprice;
    /**
     * 当前界面需要的值定义
     */
    //默认地址的ID
    private int addressid;
    //无默认地址
    @BindView(R.id.rl_nothaveaddress)
    RelativeLayout rlNothaveaddress;
    //有默认地址
    @BindView(R.id.rl_havethedefaultaddress)
    RelativeLayout rlHavethedefaultaddress;
    //有默认地址显示详细地址(省市区/详细地址/用户电话)
    @BindView(R.id.tv_provinces)
    TextView tvProvinces;
    @BindView(R.id.tv_detailedaddress)
    TextView tvDetailedaddress;
    @BindView(R.id.tv_usernameandphone)
    TextView tvUsernameandphone;
    //价格
    @BindView(R.id.tv_actualamountpaidnum)
    TextView tvActualamountpaidnum;
    //运费计算
    private int thefreight;
    //用户名
    @BindView(R.id.et_username)
    EditText etUsername;
    //身份证号
    @BindView(R.id.et_useridcard)
    EditText etUseridcard;
    //运费
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    //金币相关
    //金币显示
    @BindView(R.id.ly_showcoin)
    LinearLayout lyShowcoin;
    //总金币
    @BindView(R.id.tv_allcoin)
    TextView tvAllcoin;
    //当前订单可使用金币
    @BindView(R.id.tv_usecoin)
    TextView tvUsecoin;
    //抵扣的金币
    @BindView(R.id.tv_deductioncoin)
    TextView tvDeductioncoin;
    //金币按钮开关
    @BindView(R.id.order_switch)
    SwitchButton orderSwitch;
    //所有金币
    private double allcoin;
    //定义变量获取最终金币数使用(金币有可能是小数需要处理)
    private double resultcoin;
    //用户能使用的积分/金币
    private double usecoin;
    //价格相关
    //商品单价
    private double price;
//    //定义变量计算商品价格（运费价格）
    private double goodprice;
    //定义变量获取用户最后需要支付的金额是需要当成参数请求确认订单接口的
    private double actualpaymentprice;
    //支付方式相关
    //支付方式布局(要控制显示和隐藏使用)
    @BindView(R.id.ly_payway)
    LinearLayout lyPayway;
    //支付方式列表
    @BindView(R.id.rv_pay_ways)
    RecyclerView rvPayWays;
    //身份证显示信息
    @BindView(R.id.ly_isshowuserinfo)
    LinearLayout lyIsshowuserinfo;
    private TuanGouDetailsBean tuanGouDetailsBean;
    private List<TuanGouDetailsBean.FreightListBean> freightListBeans;
    //地址列表
    private List<AddressmanagementBean.DataBean> dataBeans;
    //支付方式对应的ID
    private String payWayid;
    private final static int WXPAY = 1;
    private final static int ALIPAY = 2;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    //回调startActivityForResult回调
    private final int SELECTORADDRESS = 1;
    //支付信息
    private String orderinfo;
    //跨境商品
    private int is_cross_border;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_spellgroupgototoconfirmorder);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        //设置标题
        tvHeaderTitle.setText("确认订单");
        tuanGouDetailsBean = (TuanGouDetailsBean) getIntent().getSerializableExtra("data");
        //设置商品标题
        tvGoodsinfotitle.setText(tuanGouDetailsBean.getTitle());
        //商品规格
        tvGoodsinfospecifications.setText("规格: " + tuanGouDetailsBean.getSku_info().getSku_name());
        //商品单价
        price=Double.parseDouble(tuanGouDetailsBean.getPrice());
        tvGoodsinfoprice.setText("价格:￥" + price);
        //获取运费模板
        freightListBeans = tuanGouDetailsBean.getFreight_list();
        is_cross_border=tuanGouDetailsBean.getIs_cross_border();
        //判断是否显示身份信息
        if (is_cross_border==1)
        {
            lyIsshowuserinfo.setVisibility(View.VISIBLE);
        }
        else {
            lyIsshowuserinfo.setVisibility(View.GONE);
        }
        //商品图片
        Glide.with(mAt).load(tuanGouDetailsBean.getPath()).apply(new RequestOptions()
                .placeholder(R.drawable.default_image)).into(imgGoodsinfopic);
        System.out.println("接收的数据内容" + tuanGouDetailsBean);
        //金币按钮开关并且判断支付方式显示还是隐藏
        orderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b)
                {
                    //金币不足的情况下使用的金币等于当前账号所有的金币总数
                    //开启金币抵扣
                    lyShowcoin.setVisibility(View.VISIBLE);
                    if (goodprice <= allcoin) {
                        //金币充足情况下(不需要现金支付为0)
                        actualpaymentprice = 0;
                        //最后使用的金币
                        usecoin = goodprice;
                    } else {
                        //金币不足的情况下(实际支付的金额就是商品价格-我所有的金币)
                        actualpaymentprice = goodprice - allcoin;
                        //金币不足的情况下用户使用的抵扣金币等于当前账号所有金币
                        usecoin = allcoin;
                    }
                }else {
                    //关闭金币抵扣
                    lyShowcoin.setVisibility(View.GONE);
                    //关闭金币抵扣这种情况下(实际支付就等于商品价格)
                    actualpaymentprice = goodprice;
                    //关闭金币抵扣用使用的金币数为0
                    usecoin = 0;
                }
                //判断最终支付价格是否为0判断是否显示支付宝微信支付
                if (actualpaymentprice == 0) {
                    lyPayway.setVisibility(View.GONE);
                } else {
                    lyPayway.setVisibility(View.VISIBLE);
                }
                //设置金币开关显示的价格
                tvActualamountpaidnum.setText("实付金额:￥" + actualpaymentprice);
            }
        });

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        //获取地址列表判断是否有默认地址
        HttpRxObservable.getObservable(ApiUtils.getApiService().getaddressgetList("mg_address", 1)).subscribe(new BaseObserver<AddressmanagementBean>(mAt) {
            @Override
            public void onHandleSuccess(AddressmanagementBean addressmanagementBean) throws IOException {
                //获取到地址列表
              //  List<AddressmanagementBean.DataBean> data = addressmanagementBean.getData();
                dataBeans=addressmanagementBean.getData();
                //获取到默认地址的pcode
                int pCode = dataBeans.get(0).getP_code();
                //获取到默认地址
                int is_def = dataBeans.get(0).getIs_def();
                //地址ID
                addressid = dataBeans.get(0).getId();
                if (is_def == 1) {
                    rlHavethedefaultaddress.setVisibility(View.VISIBLE);
                    rlNothaveaddress.setVisibility(View.GONE);
                    tvProvinces.setText(dataBeans.get(0).getSsq());
                    tvDetailedaddress.setText(dataBeans.get(0).getAddress());
                    tvUsernameandphone.setText(dataBeans.get(0).getName() + " " + dataBeans.get(0).getPhone());
                } else {
                    rlNothaveaddress.setVisibility(View.VISIBLE);
                    rlHavethedefaultaddress.setVisibility(View.GONE);
                }
                //计算运费
                //判断当前邮费将商品详情里面的freightListBeans的id和地址列表里面的id进行匹配
                if (freightListBeans.size() == 0) {
                    thefreight = 0;
                    tvYunfei.setText("包邮");
                } else {
                    for (int i = 0; i < freightListBeans.size(); i++) {
                        //如果当前选中给的省市区ID能匹配上地址列表的ID就取出freightListBeans.get(i).getFreight()当邮费否则就是包邮运费为0
                        if (freightListBeans.get(i).getId() == pCode) {
                            thefreight = freightListBeans.get(i).getFreight();
                            tvYunfei.setText("￥" + String.valueOf(thefreight));
                            break;
                        } else {
                            thefreight = 0;
                            tvYunfei.setText("包邮");
                        }

                    }
                }
                //获取到用户当前账户的所有金币和积分
                HttpRxObservable.getObservable(ApiUtils.getApiService().getScoreInfo()).subscribe(new BaseObserver<ScoreInfoBean>(mAt) {
                    @Override
                    public void onHandleSuccess(ScoreInfoBean scoreInfoBean) throws IOException {
                        //所有金币有可能会有小数的情况需要做四舍五入的处理
                        allcoin = scoreInfoBean.getCoin();
                        //保留小数过后的金币总数
                        BigDecimal b = new BigDecimal(allcoin);
                        resultcoin = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        //设置总金币
                        tvAllcoin.setText(String.valueOf(resultcoin));
                        //含运费的商品价格
                        goodprice=price+thefreight;
                        //判断当前金币是否大于商品价格(商品最后价格=单个商品价格*商品数量+邮费+可抵扣的积分)
                        if (resultcoin >= goodprice) {
                            //金币充足情况(可使用=商品价格=抵扣)
                            tvUsecoin.setText(String.valueOf(goodprice));
                            tvDeductioncoin.setText(String.valueOf(goodprice));
                        } else {
                            //金币不足(可使用=总金币=抵扣)
                            tvUsecoin.setText(String.valueOf(resultcoin));
                            tvDeductioncoin.setText(String.valueOf(resultcoin));

                        }
                        System.out.println("抵扣的金币数" + usecoin);
                        actualpaymentprice = goodprice;
                        tvActualamountpaidnum.setText("实付金额:￥" + goodprice);
                        //判断支付方式显示或隐藏(这里没必要判断,因为没得商品初始化就为0)
                        //最终判断是需要到金币开关判断
                        if (goodprice == 0) {
                            lyPayway.setVisibility(View.GONE);
                        } else {
                            lyPayway.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }

        });
        //获取支付方式
        HttpRxObservable.getObservable(ApiUtils.getApiService().getPayWayList()).subscribe(new BaseObserver<PayWayBean>(mAt) {
            @Override
            public void onHandleSuccess(PayWayBean payWayBean) throws IOException {
                //获取支付ID
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
                        payWayid = String.valueOf(payWayListBeans.get(position).getId());
                        System.out.println("选中的支付ID" + payWayid);
                    }
                });
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.rl_nothaveaddress, R.id.rl_havethedefaultaddress,R.id.iv_header_back, R.id.btn_postorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //无默认地址
            case R.id.rl_nothaveaddress:
//                Intent notintent = new Intent(mAt, AddressmanagementActivity.class);
//                startActivityForResult(notintent, SELECTORADDRESS);
                break;
            //有地址的点击事件
            case R.id.rl_havethedefaultaddress:
                Intent haveintent = new Intent(mAt, AddressmanagementActivity.class);
                startActivityForResult(haveintent, SELECTORADDRESS);
                break;
            //返回按钮点击事件
            case R.id.iv_header_back:
                finish();
                break;
            //提交订单
            case R.id.btn_postorder:
                postorder();
                break;
        }
    }
    //创建订单
    private void postorder()
    {
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
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tid",tuanGouDetailsBean.getId());
        map.put("freight",thefreight);
        map.put("total",goodprice);
        map.put("coin",usecoin);
        map.put("discounts","");
        map.put("pay",actualpaymentprice);
        map.put("mark","测试");
        map.put("identity_card",id_card);
        map.put("name",username);
        map.put("goods_pay",price);
        Map<String,Object> mapaddress=new HashMap<>();
        mapaddress.put("id",addressid);
        mapaddress.put("user_id",dataBeans.get(0).getUser_id());
        mapaddress.put("name",dataBeans.get(0).getName());
        mapaddress.put("phone",dataBeans.get(0).getPhone());
        mapaddress.put("code",dataBeans.get(0).getCode());
        mapaddress.put("province",dataBeans.get(0).getProvince());
        mapaddress.put("city",dataBeans.get(0).getCity());
        mapaddress.put("area",dataBeans.get(0).getArea());
        mapaddress.put("address",dataBeans.get(0).getAddress());
        mapaddress.put("is_def",dataBeans.get(0).getIs_def());
        mapaddress.put("create_time",dataBeans.get(0).getCreate_time());
        mapaddress.put("post_code",dataBeans.get(0).getPost_code());
        mapaddress.put("p_code",dataBeans.get(0).getP_code());
        mapaddress.put("c_code",dataBeans.get(0).getCode());
        mapaddress.put("a_code",dataBeans.get(0).getArea());
        mapaddress.put("ssq",dataBeans.get(0).getSsq());
        map.put("address",mapaddress);
        //转换成string字符串
        String s = JSON.toJSONString(map);
        System.out.println("信息"+s);
        HttpRxObservable.getObservable(ApiUtils.getApiService().corejoinTuanGou(s)).subscribe(new BaseObserver<CreateOrderBean>(mAt) {
            @Override
            public void onHandleSuccess(CreateOrderBean createOrderBean) throws IOException {
                //订单状态是否继续支付
                int pay_status = createOrderBean.getPay_status();
                //支付的订单号订单号
                final String order_no = (String) createOrderBean.getOrder_no();
                if (pay_status == 90) {
                    //需要继续支付的情况调用支付宝或者微信
                    if (TextUtils.isEmpty(payWayid)) {
                        ToastUtil.showToast("请选择支付方式");
                        return;
                    }
                    int id = Integer.parseInt(payWayid);
                    switch (id) {
                        //微信支付
                        case WXPAY:
                            HttpRxObservable.getObservable(ApiUtils.getApiService().toPay(order_no, id)).subscribe(new BaseObserver<PayOrderInfoBean>(mAt) {
                                @Override
                                public void onHandleSuccess(PayOrderInfoBean payOrderInfoBean) throws IOException {
                                    startWXPay(payOrderInfoBean);
                                }
                            });
                            break;
                        //支付宝支付
                        case ALIPAY:
                            HttpRxObservable.getObservable(ApiUtils.getApiService().toPay(order_no, id)).subscribe(new BaseObserver<PayOrderInfoBean>(mAt) {
                                @Override
                                public void onHandleSuccess(PayOrderInfoBean payOrderInfoBean) throws IOException {
                                    //获取订单信息
                                    orderinfo = payOrderInfoBean.getCallAlipay();
                                    Runnable payRunnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            // 构造AuthTask 对象
                                            PayTask payTask = new PayTask(mAt);
                                            // 调用授权接口，获取授权结果
                                            Map<String, String> result = payTask.payV2(orderinfo, true);
                                            Message msg = new Message();
                                            msg.what = SDK_PAY_FLAG;
                                            msg.obj = result;
                                            mHandler.sendMessage(msg);
                                        }
                                    };
                                    // 必须异步调用
                                    Thread payThread = new Thread(payRunnable);
                                    payThread.start();
                                }
                            });
                            break;
                    }

                } else {
                    //不需要继续支付的情况下跳转
                    Intent allorderintent = new Intent(mAt, ShoporderActivity.class);
                    allorderintent.putExtra("index", "1");
                    startActivity(allorderintent);
                    finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.showToast(e.getMessage());
            }
        });
    }
    //参数跳转
    public static void invoke(Activity activity, TuanGouDetailsBean item) {
        Intent intent = new Intent(activity, SpellgroupGotoToconfirmorderActivity.class);
        intent.putExtra("data", (Serializable) item);
        activity.startActivity(intent);
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtil.showToast(getString(R.string.pay_success) + payResult);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtil.showToast(getString(R.string.pay_failed) + payResult);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        ToastUtil.showToast(getString(R.string.auth_success) + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        ToastUtil.showToast(getString(R.string.auth_failed) + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };
    private void startWXPay(PayOrderInfoBean wxPayBean) {
        final IWXAPI msgApi = WXAPIFactory.createWXAPI(mAt, wxPayBean.getAppid());
        //将该app注册到微信
        msgApi.registerApp(wxPayBean.getAppid());
//        创建支付请求对象
        PayReq req = new PayReq();
        req.appId = wxPayBean.getAppid();
        req.partnerId = wxPayBean.getPartnerid();
        req.prepayId = wxPayBean.getPrepayid();
        req.packageValue = "Sign=WXPay";
        req.nonceStr = wxPayBean.getNoncestr();
        req.timeStamp = wxPayBean.getTimestamp();
        req.sign = wxPayBean.getSign();
        msgApi.sendReq(req);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECTORADDRESS) {
                AddressmanagementBean.DataBean bean = (AddressmanagementBean.DataBean) data.getSerializableExtra("data");
                // ToastUtil.showToast("数据显示" + bean.getPhone());
                tvProvinces.setText(bean.getSsq());
                tvDetailedaddress.setText(bean.getAddress());
                tvUsernameandphone.setText(bean.getName() + " " + bean.getPhone());
                addressid = bean.getId();
                int pCode = bean.getP_code();
                for (int i = 0; i < freightListBeans.size(); i++) {
                    //如果当前选中给的省市区ID能匹配上地址列表的ID就取出freightListBeans.get(i).getFreight()当邮费否则就是包邮运费为0
                    if (freightListBeans.get(i).getId() == pCode) {
                        thefreight = freightListBeans.get(i).getFreight();
                        tvYunfei.setText("￥" + String.valueOf(thefreight));
                        break;
                    } else {
                        thefreight = 0;
                        tvYunfei.setText("包邮");

                    }
                }
                goodprice = price + thefreight;
                actualpaymentprice = goodprice;
                tvActualamountpaidnum.setText("实付金额:" + goodprice);

            }
        }

    }
}
