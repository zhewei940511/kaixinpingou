package com.laojiashop.laojia.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.FlieUploadBean;
import com.laojiashop.laojia.entity.UserInfoBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.ActivityManage;
import com.laojiashop.laojia.utils.BitmapUtil;
import com.laojiashop.laojia.utils.GlideEngine;
import com.laojiashop.laojia.utils.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 设置界面
 */
public class SetActivity extends BaseActivity {
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.img_userinfo)
    RoundedImageView imgUserinfo;
    private static final int REQUEST_CODE_TOUXIANG_LOGO = 555;
    @BindView(R.id.rl_bindphonenumber)
    RelativeLayout rlBindphonenumber;
    @BindView(R.id.rl_changethepassword)
    RelativeLayout rlChangethepassword;
    @BindView(R.id.rl_nickname)
    RelativeLayout rlNickname;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_phonetxt)
    TextView tvPhonetxt;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_set);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("设置");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        //获取用户信息
        HttpRxObservable.getObservable(ApiUtils.getApiService().getuserinfo()).subscribe(new BaseObserver<UserInfoBean>(mAt) {
            @Override
            public void onHandleSuccess(UserInfoBean userInfoBean) throws IOException {
                Glide.with(mAt).load(userInfoBean.getHeadimgurl()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imgUserinfo);
                tvUsername.setText(userInfoBean.getName());
                if (TextUtils.isEmpty(userInfoBean.getPhone()))
                {
                    tvPhonetxt.setText("立即绑定");
                }else {
                    tvPhonetxt.setText(userInfoBean.getPhone());
                }
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.btn_logout, R.id.rl_nickname, R.id.iv_header_back, R.id.img_userinfo, R.id.rl_bindphonenumber, R.id.rl_changethepassword})
    public void onClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_logout:
                HttpRxObservable.getObservable(ApiUtils.getApiService().logOut()).subscribe(new BaseObserver<Object>(mAt) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        // jumpActivity(mAt,UsercodeloginActivity.class);
                        UsercodeloginActivity.start(mAt);
                        ActivityManage.finishAll();
                    }
                });
                break;
            //用户昵称
            case R.id.rl_nickname:
                break;
            //返回按钮
            case R.id.iv_header_back:
                finish();
                break;
            //修改头像
            case R.id.img_userinfo:
                startSelectPic(REQUEST_CODE_TOUXIANG_LOGO);
                break;
            //绑定手机号
            case R.id.rl_bindphonenumber:
                jumpActivity(BindphonenumberActivity.class);
                break;
            //修改密码
            case R.id.rl_changethepassword:
                jumpActivity(ChangethepasswordActivity.class);
                break;
        }
    }

    private void startSelectPic(int requestCode) {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(1)
                .isCamera(true)
                .loadImageEngine(GlideEngine.createGlideEngine())
                .forResult(requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) return;
        String path = PictureSelector.obtainMultipleResult(data).get(0).getAndroidQToPath();
        if (TextUtils.isEmpty(path)) {
            path = PictureSelector.obtainMultipleResult(data).get(0).getPath();
        }
        ImageView imageView = null;
        //判断头像的返回
        switch (requestCode) {
            case REQUEST_CODE_TOUXIANG_LOGO:
                imageView = imgUserinfo;
                break;
        }
        File file = new File(path);
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file_data", file.getName(), requestFile);
         //请求上传接口
        HttpRxObservable.getObservable(ApiUtils.getApiService().uploadtosave(body)).subscribe(new BaseObserver<FlieUploadBean>(mAt) {
            @Override
            public void onHandleSuccess(FlieUploadBean flieUploadBean) throws IOException {
                //上传成功调用修改用户信息接口
                HttpRxObservable.getObservable(ApiUtils.getApiService().saveUserInfo(flieUploadBean.getPath(),"")).subscribe(new BaseObserver<Object>(mAt) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        //成功加载头像
                        Glide.with(mAt).load(flieUploadBean.getPath()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imgUserinfo);
                    }
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtil.showToast("修改头像失败"+e.getMessage());
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.showToast("上传失败"+e.getMessage());
            }
        });
        //这两部是网络请求里面操作的
//        BitmapUtil.recycle(finalImageView);
//        finalImageView.setImageBitmap(BitmapFactory.decodeFile(finalPath));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
    //回调
    @Override
    protected void onResume() {
        super.onResume();
        getDataFromServer();
    }
}
