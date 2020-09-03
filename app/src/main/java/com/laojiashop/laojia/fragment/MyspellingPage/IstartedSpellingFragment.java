package com.laojiashop.laojia.fragment.MyspellingPage;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.activity.SpellgroupdetailsActivity;
import com.laojiashop.laojia.adapter.IstartedSpellingAdapter;
import com.laojiashop.laojia.adapter.MycollectionAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.MySpellingBean;
import com.laojiashop.laojia.entity.MycollectionBean;
import com.laojiashop.laojia.entity.OrderBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.CustomViewDialog;
import com.laojiashop.laojia.utils.ToastUtil;
import com.laojiashop.laojia.utils.WxShareUtil;
import com.laojiashop.laojia.view.SpellView;
import com.laojiashop.laojia.wxapi.WechatShareManager;
import com.lxj.xpopup.XPopup;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 我发起的团购
 */
public class IstartedSpellingFragment extends BaseFragment {
    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    //适配器
    private IstartedSpellingAdapter istartedSpellingAdapter;
    //数据源
    private List<MySpellingBean.DataBean> mDataList = new ArrayList<>();
//    //分享类
//    private WechatShareManager mShareManager;

    private  CustomViewDialog customViewDialog;
    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_startspell;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        rvRecycler.setLayoutManager(new LinearLayoutManager(mAty));
        istartedSpellingAdapter = new IstartedSpellingAdapter(mDataList);
        //无数据显示空
        istartedSpellingAdapter.setEmptyView(LayoutInflater.from(mAty).inflate(R.layout.layout_empty_view, rvRecycler, false));
        //绑定适配器
        rvRecycler.setAdapter(istartedSpellingAdapter);
        //设置头
        refreshLayout.setRefreshFooter(new BallPulseFooter(mAty).setSpinnerStyle(SpinnerStyle.Scale));
        //开启下拉刷新
        refreshLayout.setEnableRefresh(true);
        //开启上拉加载
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setEnableScrollContentWhenLoaded(true);
        //取消内容不满一页时开启上拉加载功能
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);
        refreshLayout.setEnableOverScrollBounce(true);
        //下拉刷新监听
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 1;
                getDataFromServer();
                refreshLayout.finishRefresh(true);
            }
        });
        //上拉加载
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                getDataFromServer();
                refreshLayout.finishLoadMore(true);
            }
        });

        //适配器中按钮点击事件
        istartedSpellingAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                MySpellingBean.DataBean dataBeandataBeans = (MySpellingBean.DataBean) adapter.getItem(position);
                int id = dataBeandataBeans.getId();
                switch (view.getId()) {
                    //整个Item点击事件
                    case R.id.ly_spellgroupdetails:
                        SpellgroupdetailsActivity.invoke(mAty, String.valueOf(dataBeandataBeans.getId()), dataBeandataBeans.getCtime(), String.valueOf(dataBeandataBeans.getGoods_id()));
                        break;
                    //转给朋友
                    case R.id.btn_toafriend:
                        //                        WechatShareManager.ShareContentText mShareContentText = (WechatShareManager.ShareContentText) mShareManager.getShareContentText("微信文本分享");
//                        mShareManager.shareByWebchat(mShareContentText, WechatShareManager.WECHAT_SHARE_TYPE_FRENDS);
//                        WechatShareManager.ShareContentWebpage shareContentWebpage= (WechatShareManager.ShareContentWebpage) mShareManager.getShareContentWebpag("开心拼购","商品分享",dataBeandataBeans.getShare_url(),R.drawable.pic);
//                        mShareManager.shareByWebchat(shareContentWebpage, WechatShareManager.WECHAT_SHARE_TYPE_TALK);
//                        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                        StrictMode.setThreadPolicy(policy);
//                        WxShareUtil.WxUrlShare(mAty, dataBeandataBeans.getShare_url(), "开心拼购", dataBeandataBeans.getStitle(), dataBeandataBeans.getPath(),WxShareUtil.WECHAT_FRIEND);
                        customViewDialog = new CustomViewDialog(mAty, R.layout.activity_shared_dialog_view,
                                new int[]{R.id.cancle, R.id.ly_shareweixin, R.id.ly_sharefriendcircle});
                        customViewDialog.setOnCenterItemClickListener(new CustomViewDialog.OnCenterItemClickListener() {
                            @Override
                            public void OnCenterItemClick(CustomViewDialog dialog, View view) {
                                switch (view.getId()) {
                                    case R.id.cancle:
                                        dialog.dismiss();
                                        break;
                                        //分享到微信
                                    case R.id.ly_shareweixin:
                                        StrictMode.ThreadPolicy policyweixin = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                        StrictMode.setThreadPolicy(policyweixin);
                                        WxShareUtil.WxUrlShare(mAty, dataBeandataBeans.getShare_url(), "开心拼购", dataBeandataBeans.getStitle(), dataBeandataBeans.getPath(), WxShareUtil.WECHAT_FRIEND);
                                        break;
                                        //分享到朋友圈
                                    case R.id.ly_sharefriendcircle:
                                        StrictMode.ThreadPolicy policycircle = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                                        StrictMode.setThreadPolicy(policycircle);
                                        WxShareUtil.WxUrlShare(mAty, dataBeandataBeans.getShare_url(), "开心拼购", dataBeandataBeans.getStitle(), dataBeandataBeans.getPath(), WxShareUtil.WECHAT_MOMENT);
                                        break;
                                }
                            }
                        });
                        customViewDialog.show();
                        break;
                    //统计
                    case R.id.btn_statistical:
                        //弹出底部自定义弹窗
                        new XPopup.Builder(getContext())
                                .moveUpToKeyboard(false) //如果不加这个，评论弹窗会移动到软键盘上面
                                .enableDrag(true)
                                //.isDestroyOnDismiss(true) //对于只使用一次的弹窗，推荐设置这个
//                        .isThreeDrag(true) //是否开启三阶拖拽，如果设置enableDrag(false)则无效
                                .asCustom(new SpellView(getContext(), id)/*.enableDrag(false)*/)
                                .show();
                        break;
                    //关闭
                    case R.id.btn_shutdown:
                        HttpRxObservable.getObservable(ApiUtils.getApiService().cancelPintuan(id)).subscribe(new BaseObserver<Object>(mAty) {
                            @Override
                            public void onHandleSuccess(Object o) throws IOException {
                                ToastUtil.showToast("关闭成功");
                                //刷新界面
                                getDataFromServer();
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                                ToastUtil.showToast("关闭失败");
                            }
                        });
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onStop() {
        super.onStop();
        if (customViewDialog!=null)
        {
            customViewDialog.dismiss();
        }
        //ToastUtil.showToast("测试+onStop");
    }


    @Override
    protected void getDataFromServer() {
        Map<String, Object> map = new HashMap<String, Object>();
        //这个地方的typeid是顶部的导航的id
        map.put("typeid", 2);
        String s = JSON.toJSONString(map);
        HttpRxObservable.getObservable(ApiUtils.getApiService().getTuangouList("mg_my_tuangou", page, s)).subscribe(new BaseObserver<MySpellingBean>(mAty) {
            @Override
            public void onHandleSuccess(MySpellingBean mySpellingBean) throws IOException {
                //成功
                List<MySpellingBean.DataBean> data = mySpellingBean.getData();
                if (page == 1) {
                    mDataList.clear();
                }
                mDataList.addAll(data);
                istartedSpellingAdapter.notifyDataSetChanged();
            }
        });
    }
}
