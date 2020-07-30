package com.laojiashop.laojia.fragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.activity.AddressmanagementActivity;
import com.laojiashop.laojia.activity.FeedbackActivity;
import com.laojiashop.laojia.activity.GoldcoinsActivity;
import com.laojiashop.laojia.activity.MeHappybeanActivity;
import com.laojiashop.laojia.activity.MeHappybeanOtherActivity;
import com.laojiashop.laojia.activity.MyTeamActivity;
import com.laojiashop.laojia.activity.MycollectionActivity;
import com.laojiashop.laojia.activity.ReceivedividendsActivity;
import com.laojiashop.laojia.activity.RecruittoActivity;
import com.laojiashop.laojia.activity.SetActivity;
import com.laojiashop.laojia.activity.ShoporderActivity;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.UserInfoBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.LoginInfoUtil;
import com.laojiashop.laojia.utils.SharedPreferencesManager;
import com.makeramen.roundedimageview.RoundedImageView;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.model.CacheMode;
import com.zhouyou.http.callback.ProgressDialogCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.subsciber.IProgressDialog;

import java.io.IOException;

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
    SharedPreferencesManager sharedPreferencesManager;
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


    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
//        sharedPreferencesManager = new SharedPreferencesManager(mAty);
//        Glide.with(mAty).load(sharedPreferencesManager.getString("headimgurl", "")).into(imgUserhead);

    }


    @Override
    protected void initData() {

    }

    @Override
    protected void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getuserinfo()).subscribe(new BaseObserver<UserInfoBean>(mAty) {
            @Override
            public void onHandleSuccess(UserInfoBean userInfoBean) throws IOException {
                //头像
                Glide.with(mAty).load(userInfoBean.getHeadimgurl()).into(imgUserhead);
                tvUserinfoname.setText(userInfoBean.getName());
                tvUserinfolevel.setText(userInfoBean.getLevel_txt());
                tvUserinfophone.setText(userInfoBean.getPhone());
                tvUserinfohappybeannum.setText(String.valueOf(userInfoBean.getScore()));
                tvUserinfogoldcoinsnum.setText(String.valueOf(userInfoBean.getCoin()));
                tvUserinforeceivedividendsnum.setText(String.valueOf(userInfoBean.bonus.getNow()));
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
//        //网络请求
//        IProgressDialog mProgressDialog = new IProgressDialog() {
//            @Override
//            public Dialog getDialog() {
//                ProgressDialog dialog = new ProgressDialog(getContext());
//                dialog.setMessage("请稍后...");
//                return dialog;
//            }
//        };
//        EasyHttp.get("/appApi/v1/User/getUserInfo").headers("token", LoginInfoUtil.getToken())
//                .execute(new ProgressDialogCallBack<UserInfoBean>(mProgressDialog, true, true) {
//                    @Override
//                    public void onError(ApiException e) {
//                        super.onError(e);
//                        // showToast("登录失败！" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onSuccess(UserInfoBean userInfoBean) {
//                        //头像
//                        Glide.with(mAty).load(userInfoBean.getHeadimgurl()).into(imgUserhead);
//                        tvUserinfoname.setText(userInfoBean.getName());
//                        tvUserinfolevel.setText(userInfoBean.getLevel_txt());
//                        tvUserinfophone.setText(userInfoBean.getPhone());
//                        tvUserinfohappybeannum.setText(String.valueOf(userInfoBean.getScore()));
//                        tvUserinfogoldcoinsnum.setText(String.valueOf(userInfoBean.getCoin()));
//                        tvUserinforeceivedividendsnum.setText(String.valueOf(userInfoBean.bonus.getNow()));
//                    }
//                });
    }

    @OnClick({R.id.btn_recruitto, R.id.img_myset, R.id.rl_myteam, R.id.rl_feedback, R.id.ly_mycollection, R.id.ly_receivedividends, R.id.rl_addressmanagement, R.id.ly_goldcoins, R.id.ly_happybean, R.id.ly_mepagegenerationofpayment, R.id.ly_mepagedropshipping, R.id.ly_mepageforthegoods, R.id.mepagehavethegoods, R.id.ly_allorders})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //上传凭证
            case R.id.btn_recruitto:
                Intent rectruittointent = new Intent(mAty, RecruittoActivity.class);
                startActivity(rectruittointent);
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
            //分红权
            case R.id.ly_receivedividends:
                Intent receivedintent = new Intent(mAty, ReceivedividendsActivity.class);
                startActivity(receivedintent);
                break;
            //我的地址
            case R.id.rl_addressmanagement:
                Intent addressmanagmentintent = new Intent(mAty, AddressmanagementActivity.class);
                startActivity(addressmanagmentintent);
                break;
            //开心豆
            case R.id.ly_happybean:
                Intent happybeanintent = new Intent(mAty, MeHappybeanActivity.class);
                //Intent happybeanintent = new Intent(mAty, MeHappybeanOtherActivity.class);
                startActivity(happybeanintent);
                break;
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
}
