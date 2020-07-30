package com.laojiashop.laojia.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.utils.BarUtils;

import com.laojiashop.laojia.utils.StatusBarUtil;
import com.laojiashop.laojia.view.GoodsCustomScrollView;
import com.stx.xhb.xbanner.XBanner;
import com.willy.ratingbar.ScaleRatingBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsdetailsActivity extends BaseActivity implements GoodsCustomScrollView.ScrollViewListener, View.OnClickListener {

    @BindView(R.id.bannertop)
    XBanner bannertop;
    @BindView(R.id.ratingBar)
    ScaleRatingBar ratingBar;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.ll_goods)
    LinearLayout llGoodsContent;
    @BindView(R.id.scrollview)
    GoodsCustomScrollView scrollview;
    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.goods)
    TextView goods;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.commment)
    TextView commment;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.detail)
    TextView detail;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.title_label)
    RelativeLayout titleLabel;
    @BindView(R.id.tv_mall_home)
    TextView tvMallHome;
    @BindView(R.id.tv_mall_shopping)
    TextView tvMallShopping;
    @BindView(R.id.ll_normal)
    LinearLayout llNormal;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    //创建线程更新ui
    private Handler handlerone = new Handler();
    private Handler handlertwo = new Handler();
    private Handler handlerthree = new Handler();
    Context c;
    private int commentHeight;                            //评论版块控件的高度
    private int commentHeightQD;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_goodsdetails);
    }

    @Override
    protected void initViews() {
//        int barHeight = StatusBarUtil.getStatusBarHeight(this);
//        if (barHeight > 0) {
//            //设置状态栏的高度
//            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) rootView.getLayoutParams();
//            layoutParams.topMargin = BarUtils.getStatusBarHeight(this) + layoutParams.topMargin;
//            rootView.setLayoutParams(layoutParams);
//        }

        goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollToPosition();
            }
        });
        commment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlertwo.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollview.smoothScrollTo(0, commentHeightQD);
                    }
                });
            }
        });
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handlerthree.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollview.smoothScrollTo(0, commentHeightQD + commentHeight);
                    }
                });
            }
        });
        initListeners();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //模拟数据
        List<String> imgesUrl = new ArrayList<>();
        //  imgesUrl.add("http://img3.fengniao.com/forum/attachpics/913/114/36502745.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        bannertop.setData(imgesUrl, null);
        bannertop.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(mAt).load(imgesUrl.get(position)).into((ImageView) view);
            }
        });

    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    //对象点击事件
    @Override
    public void onClick(View view) {

    }

    //滑动到顶部
    private void scrollToPosition() {
        handlerone.post(new Runnable() {

            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    //滚动的监听事件
    @Override
    public void onScrollChanged(GoodsCustomScrollView scrollView, int x, int y, int oldx, int oldy) {
        try {
            if (y <= 0) {
                titleLabel.setBackgroundColor(Color.argb(0, 250, 250, 250));
                goods.setTextColor(Color.argb(0, 0, 0, 0));
                commment.setTextColor(Color.argb(0, 0, 0, 0));
                detail.setTextColor(Color.argb(0, 0, 0, 0));
                view1.setBackgroundColor(Color.argb(0, 0, 0, 0));
                back.setVisibility(View.GONE);
            } else if (y > 0 && y <= 400) {  //滑动距离小于商品图的高度时，设置背景和字体颜色颜色透明度渐变
                float scale = (float) y / 400;
                float alpha = (255 * scale);
                clearAndShowThis(view1);
                titleLabel.setBackgroundColor(Color.argb((int) alpha, 250, 250, 250));
                view1.setBackgroundColor(Color.argb((int) alpha, 0, 0, 0));
                goods.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                commment.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                detail.setTextColor(Color.argb((int) alpha, 0, 0, 0));
                back.setVisibility(View.VISIBLE);
            } else if (y > 400 && y < commentHeightQD) {
                //当滑动超过商品图而小于评论
                setTitleColor();
                clearAndShowThis(view1);
            } else if (y >= commentHeightQD && y < (commentHeightQD + commentHeight)) {
                //当滑动超过评论起始位置而小于评论结束位置
                setTitleColor();
                clearAndShowThis(view2);
            } else if (y >= (commentHeightQD + commentHeight)) {
                //当滑动超过评论结束位置（默认为详情）
                setTitleColor();
                clearAndShowThis(view3);
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置标题的颜色为黑色，背景为白色可见
     */
    private void setTitleColor() {
        titleLabel.setBackgroundColor(Color.argb(255, 250, 250, 250));
        goods.setTextColor(Color.argb(255, 0, 0, 0));
        commment.setTextColor(Color.argb(255, 0, 0, 0));
        detail.setTextColor(Color.argb(255, 0, 0, 0));
    }

    private void clearAndShowThis(View currentView) {
        view1.setVisibility(View.INVISIBLE);
        view2.setVisibility(View.INVISIBLE);
        view3.setVisibility(View.INVISIBLE);
        view1.setBackgroundColor(Color.argb(255, 0, 0, 0));
        view2.setBackgroundColor(Color.argb(255, 0, 0, 0));
        view3.setBackgroundColor(Color.argb(255, 0, 0, 0));
        currentView.setVisibility(View.VISIBLE);
    }

    /**
     * 获取顶部图片高度后，设置滚动监听
     */
    private void initListeners() {
        ViewTreeObserver mGoods = llGoodsContent.getViewTreeObserver();
        ViewTreeObserver mComment = llComment.getViewTreeObserver();

        mComment.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                llComment.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
                commentHeight = llComment.getHeight();
            }
        });


        mGoods.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                goods.getViewTreeObserver().removeGlobalOnLayoutListener(
                        this);
//                commentHeightQD = goods.getHeight() - ScreenUtils.getStatusHeight(c) - ScreenUtils
//                        .dip2px(mAt, 40);

                scrollview.setScrollViewListener(GoodsdetailsActivity.this);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
