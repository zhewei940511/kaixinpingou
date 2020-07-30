package com.laojiashop.laojia.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.ClassificationPageBean;
import com.laojiashop.laojia.entity.SelectEvent;
import com.laojiashop.laojia.fragment.ClassificationDetailsFragment;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 分类详情页
 */
public class ClassificationDetailsActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_switch)
    ImageView imgSwitch;
    @BindView(R.id.tb_classifica)
    SlidingTabLayout tbClassifica;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.ly_classificationnew)
    LinearLayout lyClassificationnew;
    @BindView(R.id.ly_classificationsales)
    LinearLayout lyClassificationsales;
    @BindView(R.id.ly_classificationprice)
    LinearLayout lyClassificationprice;
    @BindView(R.id.ly_classificationevaluation)
    LinearLayout lyClassificationevaluation;
    @BindView(R.id.tv_classificationnew)
    TextView tvClassificationnew;
    @BindView(R.id.tv_classificationsales)
    TextView tvClassificationsales;
    @BindView(R.id.tv_classificationprice)
    TextView tvClassificationprice;
    @BindView(R.id.img_up)
    ImageView imgUp;
    @BindView(R.id.img_down)
    ImageView imgDown;
    @BindView(R.id.tv_classificationevaluation)
    TextView tvClassificationevaluation;
//    private SharedPreferences sharedPreferences;
//    //数据源
//    private List<ClassificationPageBean.SonBean> sonBeans;
    //创建碎片集合
    private ArrayList<Fragment> classificationDetailsFragments = new ArrayList<>();
    //声明标题集合
    private List<String> mTitles = new ArrayList<>();
    //vp适配器
    private ClassificaDetailsPager adapter;
//    private String datalist;
    private List<ClassificationPageBean.SonBean> classData;
    private int position;
    private boolean isClick = true;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_classificationdetails);
    }

    @Override
    protected void initViews() {
        //接收数据
        classData = (List<ClassificationPageBean.SonBean>) getIntent().getSerializableExtra("classData");
        //接收点击的位置
        position = getIntent().getIntExtra("position", 0);
        System.out.println("当前点击的商品ID是" + classData.get(position).getId());
        lyClassificationnew.setSelected(true);
        tvClassificationnew.setTextColor(Color.parseColor("#FF666C"));
        //获取点击的数据
//        sharedPreferences = getSharedPreferences("CLASSIFICA_INFO", MODE_PRIVATE);
//        //获取到数据
//        datalist = sharedPreferences.getString("SON_DATA", "");//你在哪存的
        //判断集合是否为空
//        if (!datalist.equals("")) {
        //手动解析数据源
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<ClassificationPageBean.SonBean>>() {
//            }.getType();
//            sonBeans = gson.fromJson(datalist, type);

        for (int i = 0; i < classData.size(); i++) {
            //添加title
            mTitles.add(classData.get(i).getTypename());
            classificationDetailsFragments.add(ClassificationDetailsFragment.getInstance(classData.get(i).getId()));
        }

//        }

        adapter = new ClassificaDetailsPager(getSupportFragmentManager(), mTitles, classificationDetailsFragments);
        viewPager.setAdapter(adapter);
        tbClassifica.setViewPager(viewPager);
        tbClassifica.setCurrentTab(position);  //这个方法是干什么用的 你昨天写的啊前天
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectstatu();
                lyClassificationnew.setSelected(true);
                tvClassificationnew.setTextColor(Color.parseColor("#FF666C"));
                tbClassifica.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    @OnClick({R.id.img_back, R.id.img_switch, R.id.ly_classificationnew, R.id.ly_classificationsales, R.id.ly_classificationprice, R.id.ly_classificationevaluation})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //返回按钮
            case R.id.img_back:
                finish();
                break;
            //切换瀑布流
            case R.id.img_switch:
                break;
            //判断条件按钮
            case R.id.ly_classificationnew:
                EventBus.getDefault().post(SelectEvent.getInstance("newest","desc"));//选择类型
                selectstatu();
                lyClassificationnew.setSelected(true);
                tvClassificationnew.setTextColor(Color.parseColor("#FF666C"));
                imgUp.setVisibility(View.VISIBLE);
                imgDown.setVisibility(View.VISIBLE);
                isClick = true;
                break;
            case R.id.ly_classificationsales:
                EventBus.getDefault().post(SelectEvent.getInstance("goodsSales","desc"));//选择类型
                selectstatu();
                lyClassificationsales.setSelected(true);
                tvClassificationsales.setTextColor(Color.parseColor("#FF666C"));
                imgUp.setVisibility(View.VISIBLE);
                imgDown.setVisibility(View.VISIBLE);
                isClick = true;
                break;
            case R.id.ly_classificationprice://这个价格先放这，我没搞明白
                //价格
                selectstatu();
                lyClassificationprice.setSelected(true);
                tvClassificationprice.setTextColor(Color.parseColor("#FF666C"));
                if (isClick) {
                    imgUp.setVisibility(View.VISIBLE);
                    imgDown.setVisibility(View.GONE);
                    //请求升序的数据
                    EventBus.getDefault().post(SelectEvent.getInstance("goodsPrice","asc"));//选择类型
                    isClick = false;
                } else {
                    //请求降序的数据
                    EventBus.getDefault().post(SelectEvent.getInstance("goodsPrice","desc"));//选择类型
                    imgUp.setVisibility(View.GONE);
                    imgDown.setVisibility(View.VISIBLE);
                    isClick = true;
                }
                break;
            case R.id.ly_classificationevaluation://goodsComment
                EventBus.getDefault().post(SelectEvent.getInstance("goodsComment","desc"));//选择类型
                selectstatu();
                lyClassificationevaluation.setSelected(true);
                tvClassificationevaluation.setTextColor(Color.parseColor("#FF666C"));
                imgUp.setVisibility(View.VISIBLE);
                imgDown.setVisibility(View.VISIBLE);
                isClick = true;
                break;
        }
    }

    //重置所有状态
    private void selectstatu() {
        lyClassificationevaluation.setSelected(false);
        lyClassificationnew.setSelected(false);
        lyClassificationprice.setSelected(false);
        lyClassificationsales.setSelected(false);
        //文字样式
        tvClassificationevaluation.setTextColor(Color.parseColor("#666666"));
        tvClassificationnew.setTextColor(Color.parseColor("#666666"));
        tvClassificationprice.setTextColor(Color.parseColor("#666666"));
        tvClassificationsales.setTextColor(Color.parseColor("#666666"));
    }


    //创建适配器
    private class ClassificaDetailsPager extends FragmentPagerAdapter {
        private List<String> title;
        List<Fragment> fragments;

        public ClassificaDetailsPager(@NonNull FragmentManager fm, List<String> title, List<Fragment> fragments) {
            super(fm);
            this.title = title;
            this.fragments = fragments;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    public static void invoke(Activity activity, List<ClassificationPageBean.SonBean> sonBeans, int position) {
        Intent intent = new Intent(activity, ClassificationDetailsActivity.class);
        intent.putExtra("classData", (Serializable) sonBeans);
        intent.putExtra("position", position);
        activity.startActivity(intent);
    }

}
