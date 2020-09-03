package com.laojiashop.laojia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;

import com.laojiashop.laojia.fragment.ClassificationPageFragment;
import com.laojiashop.laojia.fragment.HappybeanPageFragment;
import com.laojiashop.laojia.fragment.HomePageTestFragment;
import com.laojiashop.laojia.fragment.HomemallPageFragment;
import com.laojiashop.laojia.fragment.LiveFragment;
import com.laojiashop.laojia.fragment.MePageFragment;
import com.laojiashop.laojia.fragment.MyspellingPageFragment;
import com.laojiashop.laojia.utils.ActivityManage;
import com.laojiashop.laojia.utils.LogUtil;
import com.laojiashop.laojia.utils.LoginInfoUtil;
import com.laojiashop.laojia.utils.StatusBarUtil;
import com.next.easynavigation.view.EasyNavigationBar;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {
    private EasyNavigationBar navigationBar;
    //定义字体颜色
    private int normalTextColor = Color.parseColor("#333333");
    private int selectTextColor = Color.parseColor("#FF666C");
    private String[] tabText = {"商城", "分类","直播", "我的拼团", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.home_icon_uncheck, R.mipmap.classification_icon_uncheck, R.mipmap.live_icon_uncheck,R.mipmap.merchants_icon_uncheck, R.mipmap.me_icon_uncheck};
    //选中时icon
    private int[] selectIcon = {R.mipmap.home_icon_check, R.mipmap.classification_icon_check, R.mipmap.live_icon_check,R.mipmap.merchants_icon_check, R.mipmap.me_icon_check};

    private List<Fragment> fragments = new ArrayList<>();
    private int showTag;

    public static void invoke(Activity mAt, int i) {
        Intent intent = new Intent(mAt, MainActivity.class);
        intent.putExtra("showTag", i);
        mAt.startActivity(intent);
    }

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
//        //StatusBarUtil.setTranslucentStatus(mActivity);
//        initViews();
        showTag = getIntent().getIntExtra("showTag", 0);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        navigationBar = findViewById(R.id.navigationBar);
        fragments.add(new HomemallPageFragment());
        //fragments.add(new HomePageTestFragment());
        fragments.add(new ClassificationPageFragment());
        fragments.add(new LiveFragment());
        fragments.add(new MyspellingPageFragment(showTag));
//        fragments.add(new HappybeanPageFragment());
        fragments.add(new MePageFragment());

        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .selectTextColor(selectTextColor)
                .normalTextColor(normalTextColor)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                //刷新页面
                .onTabClickListener
                        (new EasyNavigationBar.OnTabClickListener() {
                    @Override

                    public boolean onTabClickEvent(View view, int position) {
                        if (position==4)
                        {
                            MePageFragment mePageFragment = (MePageFragment) fragments.get(4);
                            mePageFragment.refresh();
                        }
                        return false;
                    }
                })
                .build();
        navigationBar.selectTab(0);
        checkRxPermission();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (showTag == 1) {
            navigationBar.selectTab(3);
        }
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
                //移除所有activity
                ActivityManage.finishAll();
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

    //动态申请权限
    @SuppressLint("CheckResult")
    private void checkRxPermission() {
        RxPermissions rxPermissions = new RxPermissions((Activity) mAt);
        String[] permissionsArr = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CALL_PHONE
        };
        rxPermissions
                .requestEach(
                        permissionsArr)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            LogUtil.i("testRxPermission CallBack onPermissionsGranted() : " + permission.name +
                                    " request granted , to do something...");
                            //todo somthing

                        } else if (permission.shouldShowRequestPermissionRationale) {
                            LogUtil.e("testRxPermission CallBack onPermissionsDenied() : " + permission.name + "request denied");
                            //ToastUtil.showShort(instance, "拒绝权限，等待下次询问哦");
                            alertDialog(mAt);
                            //todo request permission again
                        } else {
                            LogUtil.e("testRxPermission CallBack onPermissionsDenied() : this " + permission.name + " is denied " +
                                    "and never ask again");
                            // ToastUtil.showShort(instance, "拒绝权限，不再弹出询问框，请前往APP应用设置中打开此权限");
                            //todo nothing
                        }
                    }
                });
    }

    /**
     * 用户拒绝，并且选择不再提示,可以引导用户进入权限设置界面开启权限
     * 弹窗是否显示根据需求选择调用
     *
     * @param context
     */
    private static void alertDialog(final Activity context) {
        if (context != null) {
            new AlertDialog.Builder(context)
                    .setCancelable(false)
                    .setTitle("权限要求")
                    .setMessage("如果没有请求的权限，此应用程序可能无法正常工作。打开app设置界面修改app权限")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                            intent.setData(uri);
                            context.startActivity(intent);
                            context.finish();
                        }
                    })
                    .setNegativeButton("取消", null)
                    .create()
                    .show();
        }
    }
}
