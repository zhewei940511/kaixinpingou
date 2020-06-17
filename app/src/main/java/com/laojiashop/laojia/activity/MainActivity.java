package com.laojiashop.laojia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.fragment.ClassificationPageFragment;
import com.laojiashop.laojia.fragment.HappybeanPageFragment;
import com.laojiashop.laojia.fragment.HomemallPageFragment;
import com.laojiashop.laojia.fragment.MePageFragment;
import com.laojiashop.laojia.fragment.MyspellingPageFragment;
import com.laojiashop.laojia.utils.StatusBarUtil;
import com.next.easynavigation.view.EasyNavigationBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private EasyNavigationBar navigationBar;
    //定义字体颜色
    private int normalTextColor = Color.parseColor("#333333");
    private int selectTextColor = Color.parseColor("#FF666C");
    private String[] tabText = {"商城", "分类","我的拼团", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.home_icon_uncheck,R.mipmap.classification_icon_uncheck, R.mipmap.merchants_icon_uncheck, R.mipmap.me_icon_uncheck};
    //选中时icon
    private int[] selectIcon = {R.mipmap.home_icon_check, R.mipmap.classification_icon_check,R.mipmap.merchants_icon_check, R.mipmap.me_icon_check};

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
//        //StatusBarUtil.setTranslucentStatus(mActivity);
//        initViews();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        navigationBar = findViewById(R.id.navigationBar);
        fragments.add(new HomemallPageFragment());
        fragments.add(new ClassificationPageFragment());
        fragments.add(new MyspellingPageFragment());
//        fragments.add(new HappybeanPageFragment());
        fragments.add(new MePageFragment());
        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .selectTextColor(selectTextColor)
                .normalTextColor(normalTextColor)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .build();
        navigationBar.selectTab(0);
    }

    @Override
    public void getDataFromServer() {

    }
    /**
     * 按键执行操作，连点两次退出程序
     *
     * @param keyCode
     * @param event
     * @return
     */
    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(getApplicationContext(), "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
