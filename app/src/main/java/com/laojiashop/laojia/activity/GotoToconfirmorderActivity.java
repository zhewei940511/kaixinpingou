package com.laojiashop.laojia.activity;

import android.annotation.SuppressLint;
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
import com.laojiashop.laojia.entity.GoodsDetailBean;
import com.laojiashop.laojia.entity.PayOrderInfoBean;
import com.laojiashop.laojia.entity.PayWayBean;
import com.laojiashop.laojia.entity.ScoreInfoBean;
import com.laojiashop.laojia.http.ApiException;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.RegexUtils;
import com.laojiashop.laojia.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 确认订单界面
 * 商品购买确认订单
 * 当前页面处理的逻辑
 * 第一步获取到页面传递的地址列表循环出运费(计算运费)
 * 第二步计算积分(判断积分是否充足)
 * 第三步运算金币使用(判断金币是否充足)
 * 第四步运算出所有金额(商品总价-(使用的积分+使用的金币))
 */
public class GotoToconfirmorderActivity extends BaseActivity {
    //返回图标按钮
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    //提交订单按钮
    @BindView(R.id.btn_postorder)
    Button btnPostorder;
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
    //运费
    @BindView(R.id.tv_yunfei)
    TextView tvYunfei;
    //积分相关
    @BindView(R.id.ly_integralinfo)
    LinearLayout lyIntegralinfo;
    @BindView(R.id.tv_integralnum)
    TextView tvIntegralnum;
    //总积分
    @BindView(R.id.tv_allscore)
    TextView tvAllscore;
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
    //金币相关
    //金币显示
    @BindView(R.id.ly_showcoin)
    LinearLayout lyShowcoin;
    //金币相关
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
    //身份证显示信息
    @BindView(R.id.ly_isshowuserinfo)
    LinearLayout lyIsshowuserinfo;
    //价格
    @BindView(R.id.tv_actualamountpaidnum)
    TextView tvActualamountpaidnum;
    //商品数量相关
    @BindView(R.id.btn_sub)
    ImageView btnSub;
    @BindView(R.id.tv_nums)
    TextView tvNums;
    @BindView(R.id.btn_add)
    ImageView btnAdd;
    //商品信息相关
    //商品标题
    @BindView(R.id.tv_goodsinfotitle)
    TextView tvGoodsinfotitle;
    //规格
    @BindView(R.id.tv_goodsinfospecifications)
    TextView tvGoodsinfospecifications;
    //商品单价
    @BindView(R.id.tv_goodsinfoprice)
    TextView tvGoodsinfoprice;
    //商品图片
    @BindView(R.id.img_goodsinfopic)
    ImageView imgGoodsinfopic;
    //用户名
    @BindView(R.id.et_username)
    EditText etUsername;
    //身份证号
    @BindView(R.id.et_useridcard)
    EditText etUseridcard;
    //支付方式相关
    //支付方式布局(要控制显示和隐藏使用)
    @BindView(R.id.ly_payway)
    LinearLayout lyPayway;
    //支付方式列表
    @BindView(R.id.rv_pay_ways)
    RecyclerView rvPayWays;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    /**
     * 定义变量接收界面传递的值(freeze_coin当前商品可使用的积分整形,freeze_coin是否是境外商品1:是0：不是)
     * 境外商品需要身份证显示商品ID
     */
    private int freeze_coin, is_cross_border;
    //商品类型下单类型：1 普通商品，2 发起拼团，3 一人直购
    private int type;
    /**
     * 商品信息相关
     */
    //商品数量(整形)
    private int number;
    //商品价格
    private double price;
    //规格名称商品ID
    private String skuname, goodsid;
    //商品标题
    private String goodtitle;
    //商品图片
    private String orderImg;
    //sku组合
    private String skuCombination;
    //商品详情里的地址(接收)需要接收的变量定义完成
    private List<GoodsDetailBean.FreightListBean> freightListBeans = new ArrayList<>();
    /**
     * 当前界面需要的值定义
     */
    //默认地址的ID
    private int addressid;
    //运费计算
    private int thefreight;
    //定义用户当前账号所有金币和积分allscore所有积分allcoin所有金币
    private double allscore;
    //定义变量获取最终积分数使用(金币有可能是小数需要处理)
    private double resultscore;
    //所有金币
    private double allcoin;
    //定义变量获取最终金币数使用(金币有可能是小数需要处理)
    private double resultcoin;
    //用户能使用的积分/金币
    private int usescore;
    private double usecoin;
    //定义变量计算商品价格
    private double goodprice;
    //定义变量获取用户最后需要支付的金额是需要当成参数请求确认订单接口的
    private double actualpaymentprice;
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

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_buynow);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        //设置标题
        tvHeaderTitle.setText("确认订单");
        //接收界面传递的参数
        //数据接收
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //商品ID
        goodsid = bundle.getString("goodid");
        //订单类型
        type = bundle.getInt("type", 0);
        //当前商品积分
        freeze_coin = bundle.getInt("freeze_coin", 0);
        //商品数量
        number = Integer.parseInt(bundle.getString("number"));
        //商品价格
        price = bundle.getDouble("pirce");
        //规格名称
        skuname = bundle.getString("skuname");
        //商品图片
        orderImg = bundle.getString("orderImg");
        //商品标题
        goodtitle = bundle.getString("goodtitle");
        //sku组合
        skuCombination = bundle.getString("skuCombination");
        //获取地址运费列表
        freightListBeans = (List<GoodsDetailBean.FreightListBean>) bundle.getSerializable("freightListBeans");
        //是否是跨境商品(数据接收完成)
        is_cross_border = bundle.getInt("is_cross_border");
        //判断跨境商品显示和吟唱身份信息
        if (is_cross_border == 1) {
            lyIsshowuserinfo.setVisibility(View.VISIBLE);
        } else {
            lyIsshowuserinfo.setVisibility(View.GONE);
        }
        //判断是否有积分商品可以使用的积分不为就显示否则隐藏
        if (freeze_coin != 0) {
            lyIntegralinfo.setVisibility(View.VISIBLE);
            tvIntegralnum.setText(freeze_coin + "个");
        } else {
            lyIntegralinfo.setVisibility(View.GONE);
        }
        //设置商品数量
        tvNums.setText(String.valueOf(number));
        //设置商品标题
        tvGoodsinfotitle.setText(goodtitle);
        //商品规格
        tvGoodsinfospecifications.setText("规格: " + skuname);
        //商品单价
        tvGoodsinfoprice.setText("价格:￥" + price);
        //商品图片
        Glide.with(mAt).load(orderImg).apply(new RequestOptions()
                .placeholder(R.drawable.default_image)).into(imgGoodsinfopic);
        System.out.println("接收的数据内容" + "商品ID" + goodsid + "订单类型" + type + "商品积分" + freeze_coin + "商品数量" + number + "商品价格" + price + "规格名称" + skuname + "sku组合" + skuCombination + "商品标题" + goodtitle + "商品图片" + orderImg);
        //金币按钮开关并且判断支付方式显示还是隐藏
        orderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //金币不足的情况下使用的金币等于当前账号所有的金币总数
                    //开启金币抵扣
                    lyShowcoin.setVisibility(View.VISIBLE);
                    //开启金币抵扣判断金币是否充足
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
                } else {
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
                tvActualamountpaidnum.setText("实付金额:" + actualpaymentprice);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
    }

    @Override
    public void getDataFromServer() {

        //获取地址列表判断是否有默认地址(先获取邮费计算出总价，在获取积分接口，顺序不能乱，会出现价格初始化为0)
        HttpRxObservable.getObservable(ApiUtils.getApiService().getaddressgetList("mg_address", 1)).subscribe(new BaseObserver<AddressmanagementBean>(mAt) {
            @Override
            public void onHandleSuccess(AddressmanagementBean addressmanagementBean) throws IOException {
                //获取到地址列表
                List<AddressmanagementBean.DataBean> data = addressmanagementBean.getData();
                //判断地址是否有值如果地址列表没值就不显示地址(如果地址列表不为空，第一项为默认收货地址)
                if (data.size() == 0) {
                    rlNothaveaddress.setVisibility(View.VISIBLE);
                    rlHavethedefaultaddress.setVisibility(View.GONE);
                    //如果没得地址地址ID为0
                    addressid = 0;
                } else {
                    //获取到默认地址的pcode
                    int pCode = data.get(0).getP_code();
                    //获取到默认地址
                    int is_def = data.get(0).getIs_def();
                    //地址ID
                    addressid = data.get(0).getId();
                    //默认第一个地址为默认地址
//                    if (is_def == 1) {
                    rlHavethedefaultaddress.setVisibility(View.VISIBLE);
                    rlNothaveaddress.setVisibility(View.GONE);
                    tvProvinces.setText(data.get(0).getSsq());
                    tvDetailedaddress.setText(data.get(0).getAddress());
                    tvUsernameandphone.setText(data.get(0).getName() + " " + data.get(0).getPhone());
//                    } else {
//                        rlNothaveaddress.setVisibility(View.VISIBLE);
//                        rlHavethedefaultaddress.setVisibility(View.GONE);
//                    }
                    //计算运费
                    //判断当前邮费将商品详情里面的freightListBeans的id和地址列表里面的id进行匹配
                    //如果freightListBeans长度为0的时候包邮
                    //不为0的时候，如果地址列表匹配不到运费模板也是包邮
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
                }
//                //获取到默认地址的pcode
//                int pCode = data.get(0).getP_code();
//                //获取到默认地址
//                int is_def = data.get(0).getIs_def();
//                //地址ID
//                addressid = data.get(0).getId();
//                if (is_def == 1) {
//                    rlHavethedefaultaddress.setVisibility(View.VISIBLE);
//                    rlNothaveaddress.setVisibility(View.GONE);
//                    tvProvinces.setText(data.get(0).getSsq());
//                    tvDetailedaddress.setText(data.get(0).getAddress());
//                    tvUsernameandphone.setText(data.get(0).getName() + " " + data.get(0).getPhone());
//                } else {
//                    rlNothaveaddress.setVisibility(View.VISIBLE);
//                    rlHavethedefaultaddress.setVisibility(View.GONE);
//                }
//                //计算运费
//                //判断当前邮费将商品详情里面的freightListBeans的id和地址列表里面的id进行匹配
//                for (int i = 0; i < freightListBeans.size(); i++) {
//                    //如果当前选中给的省市区ID能匹配上地址列表的ID就取出freightListBeans.get(i).getFreight()当邮费否则就是包邮运费为0
//                    if (freightListBeans.get(i).getId() == pCode) {
//                        thefreight = freightListBeans.get(i).getFreight();
//                        tvYunfei.setText("￥" + String.valueOf(thefreight));
//                        break;
//                    } else {
//                        thefreight = 0;
//                        tvYunfei.setText("包邮");
//                    }
//
//                }
                //获取到用户当前账户的所有金币和积分
                HttpRxObservable.getObservable(ApiUtils.getApiService().getScoreInfo()).subscribe(new BaseObserver<ScoreInfoBean>(mAt) {
                    @Override
                    public void onHandleSuccess(ScoreInfoBean scoreInfoBean) throws IOException {
                        allscore = scoreInfoBean.getScore();
                        BigDecimal bs = new BigDecimal(allscore);
                        resultscore = bs.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        //所有金币有可能会有小数的情况需要做四舍五入的处理
                        allcoin = scoreInfoBean.getCoin();
                        //保留小数过后的金币总数
                        BigDecimal b = new BigDecimal(allcoin);
                        resultcoin = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                        //设置总积分
                        tvAllscore.setText(resultscore + "个");
                        //设置总金币
                        tvAllcoin.setText(String.valueOf(resultcoin));
                        //判断积分是否充足
                        //积分充足(如果我的账户积分大于等于商品可使用的积分)
                        if (resultscore >= freeze_coin) {
                            lySufficient.setVisibility(View.VISIBLE);
                            lyInsufficient.setVisibility(View.GONE);
                            tvConsumptionscore.setText(String.valueOf(freeze_coin) + "个");
                            tvDeductionscore.setText(String.valueOf(freeze_coin) + "元");
                            usescore = freeze_coin;
                            //获取商品最后价格=商品数量*单价+邮费(不需要计算积分)
                            goodprice = number * price + thefreight;
                        } else {
                            //积分不足
                            lyInsufficient.setVisibility(View.VISIBLE);
                            lySufficient.setVisibility(View.GONE);
                            tvInsufficientscore.setText(String.valueOf(freeze_coin - resultscore) + "元");
                            //积分不足的情况下可抵扣的金额=商品需要的积分-所有积分
                            usescore = (int) (freeze_coin - resultscore);
                            //获取商品最后价格=商品数量*单价+邮费+积分
                            goodprice = number * price + thefreight + usescore;
                        }
                        System.out.println("商品价格" + goodprice);
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
                        tvActualamountpaidnum.setText("实付金额:" + goodprice);
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

    @OnClick({R.id.rl_nothaveaddress, R.id.rl_havethedefaultaddress, R.id.iv_header_back, R.id.btn_postorder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //无默认地址
            case R.id.rl_nothaveaddress:
                Intent notintent = new Intent(mAt, AddressmanagementActivity.class);
                startActivityForResult(notintent, SELECTORADDRESS);
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
    private void postorder() {
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
        if (addressid == 0) {
            ToastUtil.showToast("请选择一个默认的收货地址");
            return;
        }
        //提交订单
        /**
         * 参数:订单类型,商品ID。团购配置,金币抵扣,积分抵扣,
         * 实际支付价格,商品数量,sku组合.地址ID.用户名,身份证ID
         * 备注,直播ID
         */
        HttpRxObservable.getObservable(ApiUtils.getApiService().createorder(type,
                goodsid,
                0,
                usecoin,
                usescore,
                actualpaymentprice,
                number,
                skuCombination,
                addressid,
                username,
                id_card, "", "")).subscribe(new BaseObserver<CreateOrderBean>(mAt) {
            @Override
            public void onHandleSuccess(CreateOrderBean createOrderBean) throws IOException {
                //ToastUtil.showToast("订单创建成功");
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
            public void onHandleError(ApiException apiExc) {
                super.onHandleError(apiExc);
                ToastUtil.showToast(apiExc.getErrorCode());
            }
        });
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
                //获取到用户当前账户的所有金币和积分
//                HttpRxObservable.getObservable(ApiUtils.getApiService().getScoreInfo()).subscribe(new BaseObserver<ScoreInfoBean>(mAt) {
//                    @Override
//                    public void onHandleSuccess(ScoreInfoBean scoreInfoBean) throws IOException {
//                        allscore = scoreInfoBean.getScore();
//                        BigDecimal bs = new BigDecimal(allscore);
//                        resultscore = bs.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//                        //所有金币有可能会有小数的情况需要做四舍五入的处理
//                        allcoin = scoreInfoBean.getCoin();
//                        //保留小数过后的金币总数
//                        BigDecimal b = new BigDecimal(allcoin);
//                        resultcoin = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//                        //设置总积分
//                        tvAllscore.setText(resultscore + "个");
//                        //设置总金币
//                        tvAllcoin.setText(String.valueOf(resultcoin));
//                        //判断积分是否充足
//                        //积分充足(如果我的账户积分大于等于商品可使用的积分)
//                        if (resultscore >= freeze_coin) {
//                            lySufficient.setVisibility(View.VISIBLE);
//                            lyInsufficient.setVisibility(View.GONE);
//                            tvConsumptionscore.setText(String.valueOf(freeze_coin) + "个");
//                            tvDeductionscore.setText(String.valueOf(freeze_coin) + "元");
//                            usescore = freeze_coin;
//                            //获取商品最后价格=商品数量*单价+邮费(不需要计算积分)
//                            goodprice = number * price + thefreight;
//                        } else {
//                            //积分不足
//                            lyInsufficient.setVisibility(View.VISIBLE);
//                            lySufficient.setVisibility(View.GONE);
//                            tvInsufficientscore.setText(String.valueOf(freeze_coin - resultscore) + "元");
//                            //积分不足的情况下可抵扣的金额=商品需要的积分-所有积分
//                            usescore = (int) (freeze_coin - resultscore);
//                            //获取商品最后价格=商品数量*单价+邮费+积分
//                            goodprice = number * price + thefreight + usescore;
//                        }
//                        System.out.println("商品价格" + goodprice);
//                        //判断当前金币是否大于商品价格(商品最后价格=单个商品价格*商品数量+邮费+可抵扣的积分)
//                        if (resultcoin >= goodprice) {
//                            //金币充足情况(可使用=商品价格=抵扣)
//                            tvUsecoin.setText(String.valueOf(goodprice));
//                            tvDeductioncoin.setText(String.valueOf(goodprice));
//                        } else {
//                            //金币不足(可使用=总金币=抵扣)
//                            tvUsecoin.setText(String.valueOf(resultcoin));
//                            tvDeductioncoin.setText(String.valueOf(resultcoin));
//
//                        }
//                        System.out.println("抵扣的金币数" + usecoin);
//                        actualpaymentprice = goodprice;
//                        tvActualamountpaidnum.setText("实付金额:" + goodprice);
//                        //判断支付方式显示或隐藏(这里没必要判断,因为没得商品初始化就为0)
//                        //最终判断是需要到金币开关判断
//                        if (goodprice == 0) {
//                            lyPayway.setVisibility(View.GONE);
//                        } else {
//                            lyPayway.setVisibility(View.VISIBLE);
//                        }
//                    }
//                });
                goodprice = number * price + thefreight;
                actualpaymentprice = goodprice;
                tvActualamountpaidnum.setText("实付金额:" + goodprice);

            }
        }

    }
}
