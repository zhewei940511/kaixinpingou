package com.laojiashop.laojia.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.GridImageAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.FlieUploadBean;
import com.laojiashop.laojia.entity.ServiceFeeBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.GlideEngine;
import com.laojiashop.laojia.utils.ToastUtil;
import com.laojiashop.laojia.view.FullyGridLayoutManager;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.decoration.GridSpacingItemDecoration;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;
import com.luck.picture.lib.tools.ScreenUtils;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 上传凭证
 */
public class UploaddocumentsActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.picselect_recycler)
    RecyclerView picselectRecycler;
    @BindView(R.id.tv_levelStr)
    TextView tvLevelStr;
    @BindView(R.id.tv_cycle)
    TextView tvCycle;
    @BindView(R.id.tv_performance)
    TextView tvPerformance;
    @BindView(R.id.btn_upload)
    Button btnUpload;

    private int maxSelectNum = 6;
    private FullyGridLayoutManager fullyGridLayoutManager;
    private GridImageAdapter gridImageAdapter;
    //图片集合
    private List<String> urllist = new ArrayList<>();
    //请求参数声明
    private String  record_id;
    private int level;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_uploaddocuments);
    }


    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("上传凭证");
        fullyGridLayoutManager = new FullyGridLayoutManager(this,
                3, GridLayoutManager.VERTICAL, false);
        gridImageAdapter = new GridImageAdapter(mAt, onAddPicClickListener);
        picselectRecycler.setLayoutManager(fullyGridLayoutManager);
        picselectRecycler.addItemDecoration(new GridSpacingItemDecoration(3,
                ScreenUtils.dip2px(this, 8), false));
        gridImageAdapter.setSelectMax(maxSelectNum);
        picselectRecycler.setAdapter(gridImageAdapter);
        gridImageAdapter.setOnItemClickListener((v, position) -> {
            List<LocalMedia> selectList = gridImageAdapter.getData();
            if (selectList.size() > 0) {
                LocalMedia media = selectList.get(position);
                String mimeType = media.getMimeType();
                int mediaType = PictureMimeType.getMimeType(mimeType);
                switch (mediaType) {
                    case PictureConfig.TYPE_VIDEO:
                        // 预览视频
                        PictureSelector.create(mAt)
                                .themeStyle(R.style.picture_default_style)
                                //.setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题
                                .externalPictureVideo(TextUtils.isEmpty(media.getAndroidQToPath()) ? media.getPath() : media.getAndroidQToPath());
                        break;
                    case PictureConfig.TYPE_AUDIO:
                        // 预览音频
                        PictureSelector.create(mAt)
                                .externalPictureAudio(PictureMimeType.isContent(media.getPath()) ? media.getAndroidQToPath() : media.getPath());
                        break;
                    default:
                        PictureSelector.create(mAt)
                                .themeStyle(R.style.picture_default_style) // xml设置主题
                                //  .setPictureStyle(mPictureParameterStyle)// 动态自定义相册主题
                                //.setPictureWindowAnimationStyle(animationStyle)// 自定义页面启动动画
                                .setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)// 设置相册Activity方向，不设置默认使用系统
                                .isNotPreviewDownload(true)// 预览图片长按是否可以下载
                                //.bindCustomPlayVideoCallback(new MyVideoSelectedPlayCallback(getContext()))// 自定义播放回调控制，用户可以使用自己的视频播放界面
                                .imageEngine(GlideEngine.createGlideEngine())// 外部传入图片加载引擎，必传项
                                .openExternalPreview(position, selectList);
                        break;
                }
            }
        });
    }


    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getServiceFeeindex()).subscribe(new BaseObserver<ServiceFeeBean>(mAt) {
            @Override
            public void onHandleSuccess(ServiceFeeBean serviceFeeBean) throws IOException {
                tvLevelStr.setText(serviceFeeBean.getRecordInfo().getLevelStr());
                tvCycle.setText(serviceFeeBean.getRecordInfo().getAssessment_cycle() + "天");
                tvPerformance.setText(String.valueOf(serviceFeeBean.getRecordInfo().getNeed_achieve_performance()));
                level = serviceFeeBean.getRecordInfo().getLevel();
                record_id = String.valueOf(serviceFeeBean.getRecordInfo().getId());
            }
        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            //参数很多，根据需要添加
            PictureSelector.create(mAt)
                    .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                    .maxSelectNum(maxSelectNum)// 最大图片选择数量
                    .imageEngine(GlideEngine.createGlideEngine())
                    .minSelectNum(1)// 最小选择数量
                    .imageSpanCount(3)// 每行显示个数
                    .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选PictureConfig.MULTIPLE : PictureConfig.SINGLE
                    .previewImage(true)// 是否可预览图片
                    .isCamera(true)// 是否显示拍照按钮
                    .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                    //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                    .enableCrop(false)// 是否裁剪
                    .compress(true)// 是否压缩
                    //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                    .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                    .withAspectRatio(1, 1)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                    .selectionData(gridImageAdapter.getData())// 是否传入已选图片
                    //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                    //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                    //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                    //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                    //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                    .rotateEnabled(false) // 裁剪是否可旋转图片
                    //.scaleEnabled()// 裁剪是否可放大缩小图片
                    //.recordVideoSecond()//录制视频秒数 默认60s
                    .forResult(new MyResultCallback(gridImageAdapter));//结果回调onActivityResult code
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_header_back, R.id.btn_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                break;
            case R.id.btn_upload:
                if (urllist == null) {
                    ToastUtil.showToast("请选择图片");
                    return;
                }
                List<Map<String, String>> list = new ArrayList<>();
                for (int i = 0; i < urllist.size(); i++) {
                    //循环遍历所有
                    Map<String, String> map = new HashMap<>();
                    map.put("url", urllist.get(i));
                    list.add(map);
                }
                System.out.println("list显示" + urllist);
                String s = JSON.toJSONString(urllist);
                System.out.println("数据显示" + s);
                HttpRxObservable.getObservable(ApiUtils.getApiService().serviceFeeupdate(level, record_id, s)).subscribe(new BaseObserver<Object>(mAt) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        ToastUtil.showToast("您的凭证上传成功");
                        jumpActivity(RecruittoInthereviewActivity.class);
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        ToastUtil.showToast(e.getMessage());
                    }
                });
                break;
        }
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 返回结果回调
     */
    private class MyResultCallback implements OnResultCallbackListener<LocalMedia> {
        private WeakReference<GridImageAdapter> mAdapterWeakReference;

        public MyResultCallback(GridImageAdapter adapter) {
            super();
            this.mAdapterWeakReference = new WeakReference<>(adapter);
        }

        @Override
        public void onResult(List<LocalMedia> result) {
            if (mAdapterWeakReference.get() != null) {
                for (int i = 0; i < result.size(); i++) {
//                    String path = result.get(i).getPath();
                    String path = result.get(i).getAndroidQToPath();
                    File file = new File(path);
                    RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), file);
                    MultipartBody.Part body = MultipartBody.Part.createFormData("file_data", file.getName(), requestFile);
                    HttpRxObservable.getObservable(ApiUtils.getApiService().uploadtosave(body)).subscribe(new BaseObserver<FlieUploadBean>(mAt) {
                        @Override
                        public void onHandleSuccess(FlieUploadBean flieUploadBean) throws IOException {
                            ToastUtil.showToast("上传成功");
                            urllist.add(flieUploadBean.getPath());
                            System.out.println("图片集合" + urllist);
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            ToastUtil.showToast("上传失败");
                        }
                    });
                }
                //加载显示图片
                mAdapterWeakReference.get().setList(result);
                mAdapterWeakReference.get().notifyDataSetChanged();
            }
        }

        @Override
        public void onCancel() {
            Log.i(TAG, "PictureSelector Cancel");
        }
    }

}
