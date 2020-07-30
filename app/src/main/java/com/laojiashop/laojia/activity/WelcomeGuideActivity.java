package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.text.TextUtils;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.utils.ComParamContact;
import com.laojiashop.laojia.utils.LoginInfoUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 启动页
 */
public class WelcomeGuideActivity extends BaseActivity {
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_welcomeguide);

    }

    @Override
    protected void initViews() {
        //开启线程控制跳转
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                jumpActivity(MainActivity.class);
            }
        };

        timer.schedule(timerTask,1000*2);//2秒过后跳转首页
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
