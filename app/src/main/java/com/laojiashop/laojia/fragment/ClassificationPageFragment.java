package com.laojiashop.laojia.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseFragment;
import com.laojiashop.laojia.entity.ClassificationPageBean;
import com.laojiashop.laojia.entity.ClassificationPageRightFragment;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.SharedPreferencesManager;
import com.laojiashop.laojia.view.VerticalPager;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

/**
 * 这个碎片就是分类的
 */
public class ClassificationPageFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    VerticalTabLayout tablayout;
    @BindView(R.id.viewpagers)
    VerticalPager mViewpager;
    private MyFragmentViewpager vpsp;
    //轮播图数据源
    private List<ClassificationPageBean.BannerBean> bannerBeans=new ArrayList<>();
    //标题集合
    private List<String> typename = new ArrayList<>();

    @Override
    protected int getContentViewRes() {
        return R.layout.fragment_classification;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void getDataFromServer() {
        //网络请求
        HttpRxObservable.getObservable(ApiUtils.getApiService().getclassificationinfo()).subscribe(new BaseObserver<List<ClassificationPageBean>>(mAty) {
            @Override
            public void onHandleSuccess(List<ClassificationPageBean> classificationPageBean) throws IOException {

                for (int i = 0; i < classificationPageBean.size(); i++) {
                    typename.add(classificationPageBean.get(i).getTypename());
                    bannerBeans.addAll(classificationPageBean.get(i).getBanner());
                }

               // SharedPreferencesManager.put("classinfo",classificationPageBean);就是这里
                //这个地方要注意，如果是activity，用getSupportFragmentManager，Fragment的话就使用getChildFragmentManager。好像又是数据格式的问题？
                vpsp = new MyFragmentViewpager(getChildFragmentManager());
                mViewpager.setAdapter(vpsp);
                //进行关联
                tablayout.setupWithViewPager(mViewpager);
                //设置样式
                tablayout.setTabAdapter(new TabAdapter() {
                    @Override
                    public int getCount() {
                        return typename.size();
                    }

                    @Override
                    public ITabView.TabBadge getBadge(int position) {
                        return null;
                    }

                    @Override
                    public ITabView.TabIcon getIcon(int position) {
                        return null;
                    }

                    @Override
                    public ITabView.TabTitle getTitle(int position) {
                        return new TabView.TabTitle.Builder().setContent(typename.get(position)).setTextSize(15).setTextColor(Color.parseColor("#333333"), Color.parseColor("#999999")).build();
                    }

                    @Override
                    public int getBackground(int position) {
                        return 0;
                    }
                });

            }
        });
    }

    //自定义适配器
    class MyFragmentViewpager extends FragmentPagerAdapter {

        public MyFragmentViewpager(@NonNull FragmentManager fm) {
            super(fm);
        }
        //返回选项卡的文本 ，，，添加选项卡
        @Override
        public CharSequence getPageTitle(int position) {
            return typename.get(position);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            /**
             * 这里加载右边的fragment
             */
           ClassificationPageRightFragment classificationPageRightFragment = new ClassificationPageRightFragment();
            Bundle bundle = new Bundle();
//            放入值
           // bundle.putString("name", typename.get(position));
//            //传值
            bundle.putInt("position",position);
        //    bundle.putSerializable("banners", (Serializable) bannerBeans.get(position));

            classificationPageRightFragment.setArguments(bundle);
//            放入布局文件中
            return classificationPageRightFragment;
        }

        @Override
        public int getCount() {
            return typename.size();
        }
    }
}
