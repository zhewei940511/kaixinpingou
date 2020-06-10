package com.laojiashop.laojia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.activity.GoodsdetailsActivity;
import com.laojiashop.laojia.activity.MallGoodsDetailsActivity;
import com.laojiashop.laojia.activity.MallGoodsDetailsOtherActivity;
import com.laojiashop.laojia.adapter.HomemallPageFragmentAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.utils.BarUtils;
import com.laojiashop.laojia.utils.StatusBarUtil;
import com.laojiashop.laojia.utils.ToastUtil;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.mtjsoft.www.gridviewpager_recycleview.GridViewPager;

/**
 * 商城页面
 */
public class HomemallPageFragment extends BaseFragment {
    @BindView(R.id.search_edittext)
    TextView searchEdittext;
    @BindView(R.id.gridviewpager)
    GridViewPager gridviewpager;
    @BindView(R.id.rv_hotstyletorecommend)
    RecyclerView rvHotstyletorecommend;
    private XBanner bannertops;
//    @BindView(R.id.root_view)
//    LinearLayout mRootView;
    @BindView(R.id.bannertop)
    XBanner mBanner;
    private Button btn_gotocheck;
    private ArrayList<HotstyletorecommendBean> mDataList;
    //定义测试数据
    private String[] testString = {"食品生鲜", "个护清洁", "营养保健", "家居生活", "拼购商品"};
    private int[] testicon = {R.drawable.icon_test, R.drawable.icon_test_one, R.drawable.icon_test_two, R.drawable.icon_test_three, R.drawable.icon_test_four};

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_homemall;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
//        int barHeight = StatusBarUtil.getStatusBarHeight(mAty);
//        if (barHeight > 0) {
//            //设置状态栏的高度
//            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mRootView.getLayoutParams();
//            layoutParams.topMargin = BarUtils.getStatusBarHeight(mAty) + layoutParams.topMargin;
//            mRootView.setLayoutParams(layoutParams);
//        }
        bannertops = view.findViewById(R.id.bannertop);
        rvHotstyletorecommend.setLayoutManager(new LinearLayoutManager(getActivity()));
        //模拟数据
        List<String> imgesUrl = new ArrayList<>();
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/99381473502384338.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160910/77991473496077677.jpg");
        imgesUrl.add("http://imageprocess.yitos.net/images/public/20160906/1291473163104906.jpg");
        bannertops.setData(imgesUrl, null);
        bannertops.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(mAty).load(imgesUrl.get(position)).into((ImageView) view);
            }
        });
        gridviewpager.setDataAllCount(testString.length).setImageTextLoaderInterface(new GridViewPager.ImageTextLoaderInterface() {
            @Override
            public void displayImageText(ImageView imageView, TextView textView, int position) {
                // 自己进行数据的绑定，灵活度更高，不受任何限制
                imageView.setImageResource(testicon[position]);
                textView.setText(testString[position]);
            }
        }).setGridItemClickListener(new GridViewPager.GridItemClickListener() {
            @Override
            public void click(int position) {
                //点击事件
                ToastUtil.showToast("你点击了" + testString[position]);
            }
        }).show();
    }

    @Override
    protected void initData() {
        mDataList=new ArrayList<>();
        for (int i=0;i<4;i++) {
            HotstyletorecommendBean databean = new HotstyletorecommendBean();
            mDataList.add(databean);
        }
        HomemallPageFragmentAdapter adapter=new HomemallPageFragmentAdapter(R.layout.item_hotstyletorecommend,mDataList);
        adapter.openLoadAnimation();
        rvHotstyletorecommend.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rvHotstyletorecommend.setAdapter(adapter);
        //点击时间
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //ToastUtil.showToast("你点击了"+adapter.getItem(position));
                Intent intent = new Intent(getContext(), MallGoodsDetailsOtherActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void getDataFromServer() {

    }

}
