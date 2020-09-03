package com.laojiashop.laojia.activity;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.UserInfoBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.QRCodeUtil;
import com.laojiashop.laojia.view.SharePopwindow;

import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

public class MyPromotioncodeActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_checkmyteam)
    TextView tvCheckmyteam;
    @BindView(R.id.tv_mycode)
    TextView tvMycode;
    @BindView(R.id.img_promotioncode)
    ImageView imgPromotioncode;
    @BindView(R.id.btn_sharecode)
    Button btnSharecode;
    //生成的二维码
    private Bitmap qrcode_bitmap;
    //分享图片的链接
    private Uri uri;
    //分享弹窗
    private SharePopwindow sharePopwindow;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_promotioncode);
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getuserinfo()).subscribe(new BaseObserver<UserInfoBean>(mAt) {
            @Override
            public void onHandleSuccess(UserInfoBean userInfoBean) throws IOException {
                String code = userInfoBean.getMy_code();
                tvMycode.setText(code);
                //生成二维码
                qrcode_bitmap = QRCodeUtil.createQRCodeBitmap(code, 180, 180, "UTF-8", "H", "1", Color.BLACK, Color.WHITE);
                //显示
                imgPromotioncode.setImageBitmap(qrcode_bitmap);
                uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), qrcode_bitmap, null, null));
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.iv_header_back, R.id.tv_checkmyteam, R.id.btn_sharecode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.tv_checkmyteam:
                finish();
                break;
            case R.id.btn_sharecode:
                sharePopwindow = new SharePopwindow(mAt, itemsOnClick);
                sharePopwindow.showAtLocation(view,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//
//
                break;
        }
    }

    /**
     * 分享到微信
     *
     * @param uri
     */
    private void shareTowechat(Uri uri) {
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm",
                "com.tencent.mm.ui.tools.ShareImgUI");
        intent.setComponent(comp);
        intent.setAction("android.intent.action.SEND");
        intent.setType("image/*");
        //intent.setFlags(0x3000001);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(intent);
    }

    /**
     * 分享到朋友圈
     *
     * @param uri
     */
    private void shareTocircleoffriends(Uri uri) {
        Intent intent = new Intent();
        ComponentName comp = new ComponentName("com.tencent.mm",
                "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(comp);
        intent.setAction("android.intent.action.SEND");
        intent.setType("image/*");
        //intent.setFlags(0x3000001);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(intent);
    }

    //弹出窗口监听事件
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sharePopwindow.dismiss();
            switch (view.getId()) {
                //微信分享
                case R.id.ly_wechat:
                    shareTowechat(uri);
                    break;
                //朋友圈分享
                case R.id.ly_circleoffriends:
                    shareTocircleoffriends(uri);
                    break;
            }
        }
    };

}
