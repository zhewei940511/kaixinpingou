package com.laojiashop.laojia.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hitomi.tilibrary.style.index.NumberIndexIndicator;
import com.hitomi.tilibrary.style.progress.ProgressPieIndicator;
import com.hitomi.tilibrary.transfer.TransferConfig;
import com.hitomi.tilibrary.transfer.Transferee;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.FeedbackBean;
import com.laojiashop.laojia.entity.MallGoodsCommentBean;
import com.vansz.glideimageloader.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

public class FeedbackAdapter extends BaseQuickAdapter<FeedbackBean.DataBean, BaseViewHolder> {
    //图片适配器
    private FeedbackImgAdapter feedbackImgAdapter;
    private List<FeedbackBean.DataBean> data;
    //图片查看器
    private Transferee transfer;
    public FeedbackAdapter(@Nullable List<FeedbackBean.DataBean> data,Transferee transfer) {
        super(R.layout.item_feedback, data);
        this.data = data;
        this.transfer = transfer;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, FeedbackBean.DataBean item) {
        helper.addOnClickListener(R.id.ly_feedbackinfo);
        helper.setText(R.id.tv_feedbackordernum,"编号:"+item.getFeed_no());
        helper.setText(R.id.tv_feedbackstatu,item.getStatus_txt());
        helper.setText(R.id.tv_feedbackcreatetime,"创建时间:"+item.getCreate_time());
        helper.setText(R.id.tv_feedbackcontext,item.getContent());
        feedbackImgAdapter = new FeedbackImgAdapter();
        RecyclerView recyclerView = helper.itemView.findViewById(R.id.img_rvs);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        feedbackImgAdapter.addData(item.getImgs());
        recyclerView.setAdapter(feedbackImgAdapter);
        List<String> urlList = new ArrayList<>();
        List<FeedbackBean.DataBean.ImgsBean> imgs = item.getImgs();
        //是这个，我嵌套了一个rv
        for (FeedbackBean.DataBean.ImgsBean bean : imgs) {
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
                .bindRecyclerView(recyclerView, R.id.img_rvs);  // 绑定一个 RecyclerView， 所有绑定方法只能调用一个
        feedbackImgAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                config.setNowThumbnailIndex(position);
                transfer.apply(config).show();
            }
        });
    }
}
