package com.laojiashop.laojia.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.ClassificationLeftAdapter;
import com.laojiashop.laojia.adapter.ClassificationRightAdapter;
import com.laojiashop.laojia.adapter.NavigationAdapter;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.http.ApiException;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.listener.ITabView;
import com.laojiashop.laojia.listener.TabAdapter;
import com.laojiashop.laojia.publish.Category;
import com.laojiashop.laojia.utils.DensityUtil;
import com.laojiashop.laojia.view.AuctionListItemDecoration;
import com.laojiashop.laojia.view.TabView;
import com.laojiashop.laojia.view.VerticalTabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

/**
 * 我的分类页面
 */
public class ClassificationPageFragment extends BaseFragment {
    @BindView(R.id.search_edittext)
    TextView searchEdittext;
    @BindView(R.id.root_view)
    RelativeLayout rootView;
    @BindView(R.id.navigation_tab_layout)
    VerticalTabLayout navigationTabLayout;
    @BindView(R.id.global_filter_ll_rv)
    RecyclerView globalFilterLlRv;
    @BindView(R.id.navigation_RecyclerView)
    RecyclerView navigationRecyclerView;
    @BindView(R.id.global_filter_grid_rv)
    RecyclerView globalFilterGridRv;
    @BindView(R.id.global_filter_view)
    RelativeLayout globalFilterView;
    @BindView(R.id.normal_view)
    LinearLayout normalView;
    @BindView(R.id.cover_view)
    AppCompatImageView mCoverView;
    @BindView(R.id.arrow)
    AppCompatImageView mArrowIcon;
    private LinearLayoutManager mManager;
    private ClassificationLeftAdapter mNavigationListAdapter;
    private ClassificationRightAdapter mNavigationGridAdapter;
    private NavigationAdapter mNavigationAdapter;

    boolean isOpen = false;
    private int mCurrentTabIndex;
    private boolean isClickTab;
    private boolean isScrollToTop;

    //数据模拟
    private List<Category> mAllCategoryList = new ArrayList<>();

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_classification;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        initRecyclerView();
    }

    @Override
    protected void initData() {
    }

    private void initRecyclerView() {
        mNavigationAdapter = new NavigationAdapter(R.layout.item_navigation, null);
        mManager=new LinearLayoutManager(mAty);
        navigationRecyclerView.setLayoutManager(mManager);
        navigationRecyclerView.setHasFixedSize(true);

        mNavigationListAdapter = new ClassificationLeftAdapter(R.layout.item_list_categary, null);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mAty, LinearLayoutManager.HORIZONTAL, false);
        globalFilterLlRv.setLayoutManager(linearLayoutManager);
        globalFilterLlRv.setHasFixedSize(true);
        globalFilterLlRv.setAdapter(mNavigationListAdapter);

        mNavigationGridAdapter = new ClassificationRightAdapter(R.layout.item_grid_categary, null);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mAty, 3);
        globalFilterGridRv.setLayoutManager(gridLayoutManager);
        globalFilterGridRv.addItemDecoration(new AuctionListItemDecoration.Builder(mAty)
                .setHorizontalSpan(R.dimen.dp_13)
                .setVerticalSpan(R.dimen.dp_8)
                .setShowLastLine(true)
                .build());
        globalFilterGridRv.setHasFixedSize(true);
        globalFilterGridRv.setAdapter(mNavigationGridAdapter);
        //滑动绑定
        navigationRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (isClickTab) {
                        isClickTab = false;
                        return;
                    }
                    int firstPosition = mManager.findFirstVisibleItemPosition();
                    int lastPosition = mManager.findLastVisibleItemPosition();
                    if (firstPosition <= mNavigationListAdapter.getData().size() - 1) {
                        mCurrentTabIndex = 0;
                        isScrollToTop = false;
                        updateGlobalFilterView(true);

                        for (int i = 0; i < mNavigationListAdapter.getData().size(); i++) {
                            if (firstPosition == i) {
                                mNavigationListAdapter.getData().get(i).setSelect(true);

                                mNavigationGridAdapter.getData().get(i).setSelect(true);

                            } else {
                                mNavigationListAdapter.getData().get(i).setSelect(false);
                                mNavigationGridAdapter.getData().get(i).setSelect(false);
                            }
                        }
                        mNavigationListAdapter.replaceData(mNavigationListAdapter.getData());
                        mNavigationGridAdapter.replaceData(mNavigationGridAdapter.getData());

                        linearLayoutManager.scrollToPositionWithOffset(firstPosition, 0);
                        gridLayoutManager.scrollToPositionWithOffset(firstPosition, 0);

                    } else {
                        if (lastPosition == mNavigationAdapter.getData().size() - 1) {
                            mCurrentTabIndex = navigationTabLayout.getTabCount() - 1;
                            isScrollToTop = false;
                            updateGlobalFilterView(false);
                        } else {
                            mCurrentTabIndex = firstPosition - mNavigationListAdapter.getData().size() + 1;
                            isScrollToTop = true;
                            updateGlobalFilterView(false);
                        }
                    }

                    if (navigationTabLayout == null) {
                        return;
                    }
                    navigationTabLayout.setTabSelected(mCurrentTabIndex);
                }
            }
        });
        globalFilterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOpen = !isOpen;
                updateGlobalFilterView(true);
            }
        });

        navigationTabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tabView, int position) {
                isClickTab = true;
                navigationRecyclerView.stopScroll();
                if (position == 0) {
                    mCurrentTabIndex = 0;
                    updateGlobalFilterView(true);
                    if (isScrollToTop) {
                        mManager.scrollToPositionWithOffset(mCurrentTabIndex, 0);
                    }
                } else {
                    updateGlobalFilterView(false);
                    mCurrentTabIndex = position + mNavigationListAdapter.getData().size() - 1;
                    mManager.scrollToPositionWithOffset(mCurrentTabIndex, 0);
                }
            }

            @Override
            public void onTabReselected(TabView tabView, int i) {
            }
        });
        mNavigationListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < mNavigationListAdapter.getData().size(); i++) {
                    if (position == i) {
                        mNavigationListAdapter.getData().get(i).setSelect(true);
                        mNavigationGridAdapter.getData().get(i).setSelect(true);
                    } else {
                        mNavigationListAdapter.getData().get(i).setSelect(false);
                        mNavigationGridAdapter.getData().get(i).setSelect(false);
                    }
                }
                mNavigationListAdapter.replaceData(mNavigationListAdapter.getData());
                mNavigationGridAdapter.replaceData(mNavigationGridAdapter.getData());

                mManager.scrollToPositionWithOffset(position, 0);
            }
        });

        mNavigationGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < mNavigationListAdapter.getData().size(); i++) {
                    if (position == i) {
                        mNavigationListAdapter.getData().get(i).setSelect(true);
                        mNavigationGridAdapter.getData().get(i).setSelect(true);
                    } else {
                        mNavigationListAdapter.getData().get(i).setSelect(false);
                        mNavigationGridAdapter.getData().get(i).setSelect(false);
                    }
                }
                mNavigationListAdapter.replaceData(mNavigationListAdapter.getData());
                mNavigationGridAdapter.replaceData(mNavigationGridAdapter.getData());

                mManager.scrollToPositionWithOffset(position, 0);
                isOpen = !isOpen;
                updateGlobalFilterView(true);
            }
        });

        mArrowIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOpen = !isOpen;
                updateGlobalFilterView(true);
            }
        });

    }


    private void updateGlobalFilterView(boolean needShow) {
        if (needShow) {
            mArrowIcon.setVisibility(View.VISIBLE);
            if (isOpen) {
                mArrowIcon.setImageResource(R.mipmap.next_shanghua);
                globalFilterView.setVisibility(View.VISIBLE);
                mCoverView.setVisibility(View.GONE);
                globalFilterLlRv.setVisibility(View.GONE);
            } else {
                mArrowIcon.setImageResource(R.mipmap.next_xiala);
                mCoverView.setVisibility(View.VISIBLE);
                globalFilterLlRv.setVisibility(View.VISIBLE);
                globalFilterView.setVisibility(View.GONE);
            }

        } else {
            mArrowIcon.setVisibility(View.GONE);
            globalFilterView.setVisibility(View.GONE);
            mCoverView.setVisibility(View.GONE);
            globalFilterLlRv.setVisibility(View.GONE);
        }

    }
    public void showNavigationListData(List<Category> navigationDataList) {
        navigationTabLayout.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return navigationDataList == null ? 0 : navigationDataList.size();
            }

            @Override
            public ITabView.TabIcon getIcon(int i) {
                return new TabView.TabIcon.Builder()
                        .setIcon(R.mipmap.navigation_select, R.mipmap.navigation_unselect)
                        .setIconGravity(Gravity.FILL)
                        .setIconMargin(DensityUtil.dp2px(mAty,13))
                        .build();
            }

            @Override
            public ITabView.TabTitle getTitle(int i) {
                return new TabView.TabTitle.Builder()
                        .setContent(navigationDataList.get(i).getCate_name() + "  ")
                        .setTextColor(Color.parseColor("#3B3B3B"),
                                Color.parseColor("#6F6F6F"))
                        .setTextSize(14)
                        .build();
            }

            @Override
            public int getBackground(int position) {
                return -1;
            }

        });

        List<Category> mGlobalCategoryList = navigationDataList.get(0).getChildren();

        mGlobalCategoryList.get(0).setSelect(true);

        mNavigationListAdapter.replaceData(mGlobalCategoryList);
        mNavigationGridAdapter.replaceData(mGlobalCategoryList);


        mAllCategoryList = mGlobalCategoryList;

        navigationDataList.remove(0);

        mAllCategoryList.addAll(navigationDataList);

        mNavigationAdapter.replaceData(mAllCategoryList);

        updateGlobalFilterView(true);


      //  showNormal();
    }
    @Override
    protected void getDataFromServer() {
        //请求商品分类
        //商品评论
//        HttpRxObservable.getObservable(ApiUtils.getApiService().getMallGoodsCate("1"))
//                .subscribe(new BaseObserver<List<Category>>(mAty) {
//                    @Override
//                    public void onHandleSuccess(List<Category> mallCommentBeans) throws IOException {
//
//                        showNavigationListData(mallCommentBeans);
//                    }
//
//                    @Override
//                    public void onHandleError(ApiException apiExc) {
//                        super.onHandleError(apiExc);
//                    }
//
//                });
//        HttpRxObservable.getObservable(ApiUtils.getApiService().getMallGoodsCate("").subscribe(new BaseObserver<List<Category>>(mAty) {
//            @Override
//            public void onHandleSuccess(List<Category> categories) throws IOException {
//
//            }
//        }));
//        addSubscribe(Observable.zip(mDataManager.getCategoryList("1"), mDataManager.getCategoryList("2"), (normalResponse, globalResponse) -> {
//            List<Category> normalList = normalResponse.getResponseData();
//            List<Category> globalList = globalResponse.getResponseData();
//
//            normalList.add(0, globalList.get(0));
//
//            return normalList;
//        }).compose(RxUtils.rxSchedulerHelper()).subscribeWith(new BaseObserver<List<Category>>(mView, true) {
//            @Override
//            public void onNext(List<Category> personData) {
//                mView.showNavigationListData(personData);
//            }
//        }));

    }
}
