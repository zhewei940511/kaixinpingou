package com.laojiashop.laojia.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.GridImageAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.FeedbackconditionsBean;
import com.laojiashop.laojia.entity.FlieUploadBean;
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
 * 意见反馈
 */
public class FeedbackActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.picselect_recycler)
    RecyclerView picselectRecycler;
    @BindView(R.id.tv_showchoosetype)
    TextView tvShowchoosetype;
    @BindView(R.id.rl_choosetype)
    RelativeLayout rlChoosetype;
    @BindView(R.id.tv_header_right)
    TextView tvHeaderRight;
    @BindView(R.id.ly_ordernum)
    LinearLayout lyOrdernum;
    @BindView(R.id.et_feedbackphone)
    EditText etFeedbackphone;
    @BindView(R.id.et_feedbackordernum)
    EditText etFeedbackordernum;
    @BindView(R.id.et_feedbackcontext)
    EditText etFeedbackcontext;
    @BindView(R.id.btn_Submitfeedback)
    Button btnSubmitfeedback;
    private int maxSelectNum = 3;
    private FullyGridLayoutManager fullyGridLayoutManager;
    private GridImageAdapter gridImageAdapter;
    //选择器
    private OptionsPickerView optionsPickerView;
    //选择的id
    private int typeid = 0;
    //类型名字
    private String typename;
    /**
     * 模拟数据显示
     */
    private List<String> choosetype;
    //图片集合
    private List<String> urllist = new ArrayList<>();

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_feedback);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("意见反馈");
        tvHeaderRight.setVisibility(View.VISIBLE);
        tvHeaderRight.setText("我的反馈");
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
                        // 预览图片 可自定长按保存路径
//                        PictureWindowAnimationStyle animationStyle = new PictureWindowAnimationStyle();
//                        animationStyle.activityPreviewEnterAnimation = R.anim.picture_anim_up_in;
//                        animationStyle.activityPreviewExitAnimation = R.anim.picture_anim_down_out;
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
        HttpRxObservable.getObservable(ApiUtils.getApiService().getBaFeedbackTypeList("mg_feedbacktype_list", 1)).subscribe(new BaseObserver<FeedbackconditionsBean>(mAt) {
            @Override
            public void onHandleSuccess(FeedbackconditionsBean feedbackconditionsBean) throws IOException {
                choosetype = new ArrayList<>();
                for (int i = 0; i < feedbackconditionsBean.getData().size(); i++) {
                    choosetype.add(feedbackconditionsBean.getData().get(i).getTypename());
                    // typeid=feedbackconditionsBean.getData().get(i).getId();
                }
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

    @OnClick({R.id.iv_header_back, R.id.rl_choosetype, R.id.tv_header_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.rl_choosetype:
                showpick();
                break;
            case R.id.tv_header_right:
                jumpActivity(FeedbackallActivity.class);
                break;
        }
    }

    /**
     * 弹窗
     */
    private void showpick() {
        optionsPickerView = new OptionsPickerBuilder(mAt, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = choosetype.get(options1);
                //typeid=options1;
                switch (options1) {
                    //订单反馈
                    case 0:
                        lyOrdernum.setVisibility(View.VISIBLE);
                        typeid = 1;
                        break;
                    //咨询反馈
                    case 1:
                        lyOrdernum.setVisibility(View.GONE);
                        typeid = 2;
                        break;
                    //合作反馈
                    case 2:
                        lyOrdernum.setVisibility(View.GONE);
                        typeid = 3;
                        break;
                    //建议反馈
                    case 3:
                        lyOrdernum.setVisibility(View.GONE);
                        typeid = 4;
                        break;
                    default:
                        break;
                }
                typename = choosetype.get(options1);
                System.out.println("typeid" + typeid);
//                System.out.println("id1-----"+options1+"id2========="+options2+"id3++++++++++"+options3+"view++++++++"+v);
                tvShowchoosetype.setText(tx);
            }
        }).setTitleText("")//设置标题
                .setTitleBgColor(Color.parseColor("#ffffff"))//设置背景颜色
                .setOutSideCancelable(false)//点击屏幕，点在控件外部范围时，是否取消显示
                .setLineSpacingMultiplier(2)//设置行间距
                .setCancelColor(Color.parseColor("#666666"))
                .setSubmitColor(Color.parseColor("#FF666C"))
                .build();
        optionsPickerView.setPicker(choosetype);
        optionsPickerView.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 意见反馈按钮
     */
    @OnClick(R.id.btn_Submitfeedback)
    public void onViewClicked() {
        String phone = etFeedbackphone.getText().toString().trim();
        String context = etFeedbackcontext.getText().toString().trim();
        String ordernum = etFeedbackordernum.getText().toString().trim();
        switch (typeid) {
            case 0:
                ToastUtil.showToast("请选择反馈类型");
                break;
            case 1:
                if (TextUtils.isEmpty(ordernum)) {
                    ToastUtil.showToast("请输入订单号");
                    return;
                }
                break;
        }
        //typeid这个是选中类型的id,1.2.3.4.5.6.有几个，分别标一下
//        if (typeid == 0) {
//            ToastUtil.showToast("请选择反馈类型");
//            return;
//        }
        //        if (TextUtils.isEmpty(ordernum)) {
//            ToastUtil.showToast("请输入订单号");
//            return;
//        }
        if (TextUtils.isEmpty(phone)) {
            ToastUtil.showToast("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(context)) {
            ToastUtil.showToast("请输入反馈信息");
            return;
        }
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < urllist.size(); i++) {
            //循环遍历所有
            Map<String, String> map = new HashMap<>();
            map.put("url", urllist.get(i));
            list.add(map);
        }
        System.out.println("list显示" + list);
        String s = JSON.toJSONString(list);
        System.out.println("数据显示" + s);
        HttpRxObservable.getObservable(ApiUtils.getApiService().Fankuiapply("FeedBack", typeid, typename, phone, context, ordernum, s)).subscribe(new BaseObserver<Object>(mAt) {
            @Override
            public void onHandleSuccess(Object o) throws IOException {
                ToastUtil.showToast("意见反馈成功");
                jumpActivity(FeedbackallActivity.class);
                finish();
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                ToastUtil.showToast(e.getMessage());
            }
        });
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
//            for (LocalMedia media : result) {
//                Log.i(TAG, "是否压缩:" + media.isCompressed());
//                Log.i(TAG, "压缩:" + media.getCompressPath());
//                Log.i(TAG, "原图:" + media.getPath());
//                Log.i(TAG, "是否裁剪:" + media.isCut());
//                Log.i(TAG, "裁剪:" + media.getCutPath());
//                Log.i(TAG, "是否开启原图:" + media.isOriginal());
//                Log.i(TAG, "原图路径:" + media.getOriginalPath());
//                Log.i(TAG, "Android Q 特有Path:" + media.getAndroidQToPath());
//                Log.i(TAG, "宽高: " + media.getWidth() + "x" + media.getHeight());
//                Log.i(TAG, "Size: " + media.getSize());
//                // TODO 可以通过PictureSelectorExternalUtils.getExifInterface();方法获取一些额外的资源信息，如旋转角度、经纬度等信息
//            }
            if (mAdapterWeakReference.get() != null) {
                for (int i = 0; i < result.size(); i++) {
                    // String pathone=result.get(i).getAndroidQToPath();
                    String pathone = result.get(i).getPath();
                    File file = new File(pathone);
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
