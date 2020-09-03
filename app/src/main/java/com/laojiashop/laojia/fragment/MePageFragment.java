package com.laojiashop.laojia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.activity.AddressmanagementActivity;
import com.laojiashop.laojia.activity.FeedbackActivity;
import com.laojiashop.laojia.activity.FeedbackallActivity;
import com.laojiashop.laojia.activity.GoldcoinsActivity;
import com.laojiashop.laojia.activity.LiveeventsActivity;
import com.laojiashop.laojia.activity.MyTeamActivity;
import com.laojiashop.laojia.activity.MycollectionActivity;
import com.laojiashop.laojia.activity.ReceivedividendsActivity;
import com.laojiashop.laojia.activity.RecruittoActivity;
import com.laojiashop.laojia.activity.RecruittoInthereviewActivity;
import com.laojiashop.laojia.activity.RecruittoauditfailureActivity;
import com.laojiashop.laojia.activity.RecruittoviewthetaskActivity;
import com.laojiashop.laojia.activity.SetActivity;
import com.laojiashop.laojia.activity.ShoporderActivity;
import com.laojiashop.laojia.activity.UploaddocumentsActivity;
import com.laojiashop.laojia.adapter.MembercenterAdapter;
import com.laojiashop.laojia.adapter.VarioustypesbalanceslistAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.ServiceFeeBean;
import com.laojiashop.laojia.entity.UserInfoBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.SPUtils;
import com.laojiashop.laojia.utils.ToastUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    @BindView(R.id.ly_mycollection)
    RelativeLayout lyMycollection;
    @BindView(R.id.rl_feedback)
    RelativeLayout rlFeedback;
    @BindView(R.id.rl_myteam)
    RelativeLayout rlMyteam;
    @BindView(R.id.img_userhead)
    RoundedImageView imgUserhead;
    @BindView(R.id.btn_recruitto)
    Button btnRecruitto;
    @BindView(R.id.tv_userinfoname)
    TextView tvUserinfoname;
    @BindView(R.id.tv_userinfophone)
    TextView tvUserinfophone;
    @BindView(R.id.tv_userinfohappybeannum)
    TextView tvUserinfohappybeannum;
    @BindView(R.id.tv_userinforeceivedividendsnum)
    TextView tvUserinforeceivedividendsnum;
    @BindView(R.id.tv_userinfolevel)
    Button tvUserinfolevel;
    @BindView(R.id.tv_userinfogoldcoinsnum)
    TextView tvUserinfogoldcoinsnum;
    //列表
    @BindView(R.id.rv_varioustypesbalanceslist)
    RecyclerView rvVarioustypesbalanceslist;
    @BindView(R.id.ly_mystudio)
    RelativeLayout lyMystudio;
    //数据源
    private List<UserInfoBean.VariousTypesBalancesListBean> variousTypesBalancesListBeans = new ArrayList<>();
    //适配器
    private VarioustypesbalanceslistAdapter varioustypesbalanceslistAdapter;
    //定义变量显示文本值
    private String statusStr;
    //定义变量标识跳转界面
    private int typejump=0;
    //定义变量获取申请等级
    private int level;
    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        rvVarioustypesbalanceslist.setLayoutManager(new GridLayoutManager(mAty, 3));
    }


    @Override
    protected void initData() {

    }

    @Override
    protected void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getuserinfo()).subscribe(new BaseObserver<UserInfoBean>(mAty) {
            @Override
            public void onHandleSuccess(UserInfoBean userInfoBean) throws IOException {
//                SharedPreferencesManager.put("intro",userInfoBean.getBonus().getIntro());
                SPUtils.putString(mAty, "intro", userInfoBean.getBonus().getIntro());
                //头像
                Glide.with(mAty).load(userInfoBean.getHeadimgurl()).into(imgUserhead);
                tvUserinfoname.setText(userInfoBean.getName());
                tvUserinfolevel.setText(userInfoBean.getLevel_txt());
                tvUserinfophone.setText(userInfoBean.getPhone());
//                tvUserinfohappybeannum.setText(String.valueOf(userInfoBean.getFreeze_coin()));
//                tvUserinfogoldcoinsnum.setText(String.valueOf(userInfoBean.getCoin()));
//                tvUserinforeceivedividendsnum.setText(String.valueOf(userInfoBean.bonus.getNow()));
                variousTypesBalancesListBeans = userInfoBean.getVariousTypesBalancesList();
                varioustypesbalanceslistAdapter = new VarioustypesbalanceslistAdapter(variousTypesBalancesListBeans);
                //设置
                rvVarioustypesbalanceslist.setAdapter(varioustypesbalanceslistAdapter);
                varioustypesbalanceslistAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        List<UserInfoBean.VariousTypesBalancesListBean> data = adapter.getData();
                        int type = data.get(position).getType();
                        switch (type) {
                            //开心积分
                            case 1:
                                break;
                            //金币
                            case 2:
                                Intent intent = new Intent(mAty, GoldcoinsActivity.class);
                                startActivity(intent);
                                break;
                            //分红权
                            case 3:
                                Intent receivedintent = new Intent(mAty, ReceivedividendsActivity.class);
                                startActivity(receivedintent);
                                break;
                        }
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
        HttpRxObservable.getObservable(ApiUtils.getApiService().getServiceFeeindex()).subscribe(new BaseObserver<ServiceFeeBean>(mAty) {
            @Override
            public void onHandleSuccess(ServiceFeeBean serviceFeeBean) throws IOException {
                statusStr=serviceFeeBean.getRecordInfo().getStatusStr();
                level=serviceFeeBean.getRecordInfo().getLevel();
                if (TextUtils.isEmpty(statusStr))
                {
                    btnRecruitto.setText("立即申请");
                    typejump=0;
                }else {
                    btnRecruitto.setText(serviceFeeBean.getRecordInfo().getFront_display_status());
                    //判断状态赋值给typejump
                   // 审核状态：1 待上传凭证，2 待审核，3 审核通过，4 审核不通过
                    switch (serviceFeeBean.getRecordInfo().getStatus())
                    {
                        case 1:
                            typejump=1;
                            break;
                        case 2:
                            typejump=2;
                            break;
                        case 3:
                            typejump=3;
                            break;
                        case 4:
                            typejump=4;
                            break;
                    }
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    @OnClick({R.id.btn_recruitto, R.id.img_myset, R.id.rl_myteam, R.id.rl_feedback, R.id.ly_mycollection, R.id.ly_receivedividends, R.id.rl_addressmanagement, R.id.ly_goldcoins, R.id.ly_happybean, R.id.ly_mepagegenerationofpayment, R.id.ly_mepagedropshipping, R.id.ly_mepageforthegoods, R.id.mepagehavethegoods, R.id.ly_allorders})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //上传凭证
            case R.id.btn_recruitto:
                //根据不同的type跳转不同的页面(0跳转申请页面,1上传凭证页面2.审核中页面3.审核通过查看任务页面,4.审核不通过页面)
                switch (typejump)
                {
                    //跳转申请页面
                    case 0:
                        Intent recruittointent=new Intent(mAty,RecruittoActivity.class);
                        startActivity(recruittointent);
                        break;
                    case 1:
                        //跳转上传凭证页面
                        Intent uploadintent=new Intent(mAty, UploaddocumentsActivity.class);
                        startActivity(uploadintent);
                        break;
                    case 2:
                        //待审核页面
                        Intent recruittoInthereviewintent=new Intent(mAty, RecruittoInthereviewActivity.class);
                        startActivity(recruittoInthereviewintent);
                        break;
                    case 3:
                        //查看任务页面
//                        Intent viewthetaskintent=new Intent(mAty,RecruittoviewthetaskActivity.class);
//                        startActivity(viewthetaskintent);
                        RecruittoviewthetaskActivity.invoke(mAty,level);
                        break;
                    case 4:
                        //审核不通过
                        Intent auditfailureintent=new Intent(mAty, RecruittoauditfailureActivity.class);
                        startActivity(auditfailureintent);
                        break;
                }
//                if (recordInfoBean==null)
//                {
//                    Intent rectruittointent = new Intent(mAty, RecruittoActivity.class);
//                    startActivity(rectruittointent);
//                }else {
//                    btnRecruitto.setText("上传凭证");
//                }
                break;
            //设置
            case R.id.img_myset:
                Intent setintent = new Intent(mAty, SetActivity.class);
                startActivity(setintent);
                break;
            //我的团队
            case R.id.rl_myteam:
                Intent myteamintent = new Intent(mAty, MyTeamActivity.class);
                startActivity(myteamintent);
                break;
            //意见反馈
            case R.id.rl_feedback:
                //Intent feedbackintent = new Intent(mAty, FeedbackallActivity.class);
                Intent feedbackintent = new Intent(mAty, FeedbackActivity.class);
                startActivity(feedbackintent);
                break;
            //我的收藏
            case R.id.ly_mycollection:
                Intent mycollectionintent = new Intent(mAty, MycollectionActivity.class);
                startActivity(mycollectionintent);
                break;
            //我得金币点击事件
            case R.id.ly_goldcoins:
                Intent intent = new Intent(mAty, GoldcoinsActivity.class);
                startActivity(intent);
                break;
//            //分红权
//            case R.id.ly_receivedividends:
//                Intent receivedintent = new Intent(mAty, ReceivedividendsActivity.class);
//                startActivity(receivedintent);
//                break;
            //我的地址
            case R.id.rl_addressmanagement:
                Intent addressmanagmentintent = new Intent(mAty, AddressmanagementActivity.class);
                startActivity(addressmanagmentintent);
                break;
            //开心豆
//            case R.id.ly_happybean:
////                Intent happybeanintent = new Intent(mAty, MeHappybeanActivity.class);
////                //Intent happybeanintent = new Intent(mAty, MeHappybeanOtherActivity.class);
////                startActivity(happybeanintent);
//                break;
            //待付款
            case R.id.ly_mepagegenerationofpayment:
                Intent generationointent = new Intent(getActivity(), ShoporderActivity.class);
                generationointent.putExtra("index", "2");
                startActivity(generationointent);
                break;
            //待发货
            case R.id.ly_mepagedropshipping:
                Intent dropshippingintent = new Intent(getActivity(), ShoporderActivity.class);
                dropshippingintent.putExtra("index", "3");
                startActivity(dropshippingintent);
                break;
            //待收货
            case R.id.ly_mepageforthegoods:
                Intent forthegoodsintent = new Intent(getActivity(), ShoporderActivity.class);
                forthegoodsintent.putExtra("index", "4");
                startActivity(forthegoodsintent);
                break;
            //签收
            case R.id.mepagehavethegoods:
                Intent havethegoodsintent = new Intent(getActivity(), ShoporderActivity.class);
                havethegoodsintent.putExtra("index", "5");
                startActivity(havethegoodsintent);
                break;
            //全部订单
            case R.id.ly_allorders:
                Intent allorderintent = new Intent(getActivity(), ShoporderActivity.class);
                allorderintent.putExtra("index", "1");
                startActivity(allorderintent);
                // startActivity(new Intent(getContext(), ShoporderActivity.class));
                break;
        }
    }

    //页面刷新
    public void refresh() {
        getDataFromServer();
    }

    //直播间点击
    @OnClick(R.id.ly_mystudio)
    public void onViewClicked() {
        Intent liveintent = new Intent(mAty, LiveeventsActivity.class);
        startActivity(liveintent);
    }
}
