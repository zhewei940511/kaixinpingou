package com.laojiashop.laojia.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.utils.BitmapUtil;
import com.laojiashop.laojia.utils.GlideEngine;
import com.laojiashop.laojia.utils.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.iv_header_back, R.id.img_userinfo,R.id.rl_bindphonenumber,R.id.rl_changethepassword})
    public void onClicked(View view) {
        switch (view.getId()) {
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
        switch (requestCode)
        {
            case  REQUEST_CODE_TOUXIANG_LOGO:
                imageView=imgUserinfo;
                break;
        }
        File file = new File(path);
        ImageView finalImageView = imageView;
        String finalPath = path;
        //这两部是网络请求里面操作的
        BitmapUtil.recycle(finalImageView);
        finalImageView.setImageBitmap(BitmapFactory.decodeFile(finalPath));
    }

}
