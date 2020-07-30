package com.laojiashop.laojia.fragment;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.MallGoodsDetailsAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.GoodsDetailBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;

/**
 * 商品详情
 */
public class MallGoodsDetailFragment extends BaseFragment {
//    @BindView(R.id.rl_goddsdetailsimg)
//    RecyclerView rlGoddsdetailsimg;
    @BindView(R.id.wv_details)
    WebView wvDetails;
    //商品id
    private String goodId;

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_mall_goods_details;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
       // rlGoddsdetailsimg.setLayoutManager(new LinearLayoutManager(mAty));
    }

    @Override
    protected void initData() {
        Bundle bundle = getArguments();
        goodId = bundle.getString("id");
    }

    @Override
    protected void getDataFromServer() {
        //网络请求
        HttpRxObservable.getObservable(ApiUtils.getApiService().getgooddetailinfo(goodId)).subscribe(new BaseObserver<GoodsDetailBean>(mAty) {
            @Override
            public void onHandleSuccess(GoodsDetailBean goodsDetailBean) throws IOException {
//                //产品详情
//                List<GoodsDetailBean.IntroBean> intro = goodsDetailBean.getIntro();
               // initAdapter(goodsDetailBean.getIntro());
                String details=goodsDetailBean.getGoodsContentUrl();
               // String htmlData = getHtmlData(details);
               // wvDetails.loadData(details,"text/html", "UTF-8");
                wvDetails.loadUrl(details);
                wvDetails.setVerticalScrollBarEnabled(false);
                wvDetails.setHorizontalScrollBarEnabled(false);
                wvDetails.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return (event.getAction() == MotionEvent.ACTION_MOVE);
                    }
                });
            }
        });
    }

    /**
     * 拼接html字符串片段
     * @param bodyHTML
     * @return
     */
    private  String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width:100% !important; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body style:'height:auto;max-width: 100%; width:auto;'>" + bodyHTML + "</body></html>";
    }

}
