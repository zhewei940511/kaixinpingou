package com.laojiashop.laojia.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hitomi.tilibrary.style.index.NumberIndexIndicator;
import com.hitomi.tilibrary.style.progress.ProgressPieIndicator;
import com.hitomi.tilibrary.transfer.TransferConfig;
import com.hitomi.tilibrary.transfer.Transferee;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.entity.MallGoodsCommentBean;
import com.laojiashop.laojia.utils.GlideEngine;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.vansz.glideimageloader.GlideImageLoader;
import com.vansz.universalimageloader.UniversalImageLoader;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;
import java.util.List;

public class MallGoodsCommentAdapter extends BaseQuickAdapter<MallGoodsCommentBean.DataBean, BaseViewHolder> {
    //图片适配器
    private MallGoodsCommentImgAdapter mallGoodsCommentImgAdapter;
    private List<MallGoodsCommentBean.DataBean> data;
    //图片查看器
    private Transferee transfer;

    public MallGoodsCommentAdapter(List<MallGoodsCommentBean.DataBean> data, Transferee transfer) {
        super(R.layout.item_fragmentmallgoodscomment, data);
        this.data = data;
        this.transfer = transfer;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MallGoodsCommentBean.DataBean item) {
        helper.setText(R.id.tv_goodscommentname, item.getUser_name())
                .setText(R.id.tv_goodscommenttime, item.getCreate_time())
                .setText(R.id.tv_skuname, item.getSku_name())
                .setText(R.id.tv_goodscommentcontent, item.getContent());
        Glide.with(mContext).load(item.getHeadimgurl()).apply(new RequestOptions().placeholder(R.mipmap.default_userhead_image)).into((ImageView) helper.getView(R.id.iv_goodscommentheadPic));
        //星级加载
        ScaleRatingBar scaleRatingBar = helper.getView(R.id.ratingBar);
        int stars = item.getStar();
        scaleRatingBar.setRating(stars);
        mallGoodsCommentImgAdapter = new MallGoodsCommentImgAdapter();
        RecyclerView recyclerView = helper.itemView.findViewById(R.id.img_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mallGoodsCommentImgAdapter.addData(item.getImgs());
        recyclerView.setAdapter(mallGoodsCommentImgAdapter);

        List<String> urlList = new ArrayList<>();
        List<MallGoodsCommentBean.DataBean.ImgsBean> imgs = item.getImgs();//是这个，我嵌套了一个rv
        for (MallGoodsCommentBean.DataBean.ImgsBean bean : imgs) {
            urlList.add(bean.getUrl());
        }
        //查看图片
        TransferConfig config = TransferConfig.build()
                .setSourceUrlList(urlList) // 资源 url 集合, String 格式
                .setProgressIndicator(new ProgressPieIndicator()) // 资源加载进度指示器, 可以实现 IProgressIndicator 扩展
                .setIndexIndicator(new NumberIndexIndicator()) // 资源数量索引指示器，可以实现 IIndexIndicator 扩展
                .setImageLoader(GlideImageLoader.with(mContext)) // 图片加载器，可以实现 ImageLoader 扩展
                .setBackgroundColor(Color.parseColor("#000000")) // 背景色
                .enableHideThumb(false) // 是否开启当 transferee 打开时，隐藏缩略图, 默认关闭
                .setOnLongClickListener(new Transferee.OnTransfereeLongClickListener() { // 长按当前页面监听器
                    @Override
                    public void onLongClick(ImageView imageView, String imageUri, int pos) {
//                        saveImageFile(imageUri); // 使用 transferee.getFile(imageUri) 获取缓存文件保存，视频不支持
                    }
                })
                .bindRecyclerView(recyclerView, R.id.img_rv);  // 绑定一个 RecyclerView， 所有绑定方法只能调用一个

        //Adapter点击事件  是这个吗嗯
        mallGoodsCommentImgAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ArrayList<LocalMedia> localMedia=new ArrayList<>();
//
//                PictureSelector.create((BaseActivity) mContext)
//                        .themeStyle(R.style.picture_default_style)
//                        .isNotPreviewDownload(true)
//                        .loadImageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
//                        .openExternalPreview(position, localMedia);
                config.setNowThumbnailIndex(position);
                transfer.apply(config).show();

            }
        });
    }
}
