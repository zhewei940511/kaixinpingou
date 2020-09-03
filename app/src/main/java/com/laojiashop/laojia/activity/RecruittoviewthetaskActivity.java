package com.laojiashop.laojia.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.king.view.arcseekbar.ArcSeekBar;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.ServiceFeeBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 查看任务页面
 */
public class RecruittoviewthetaskActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.ly_bg)
    LinearLayout lyBg;
    @BindView(R.id.tv_taskgoalnum)
    TextView tvTaskgoalnum;
    @BindView(R.id.tv_hasbeen)
    TextView tvHasbeen;
    @BindView(R.id.tv_inspectionperiodnum)
    TextView tvInspectionperiodnum;
    @BindView(R.id.tv_createtime)
    TextView tvCreatetime;
    @BindView(R.id.arc_jindutiao)
    ArcSeekBar arcJindutiao;
    @BindView(R.id.tv_remainingtime)
    TextView tvRemainingtime;
    private int level;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_recruittoviewthetask);

    }

    @Override
    protected void initViews() {
        level = getIntent().getIntExtra("level", 0);
        //判斷背景背景显示3:导购4:经销商5:代理商
        switch (level) {
            case 3:
                lyBg.setBackgroundResource(R.mipmap.icon_daogoubg);
                break;
            case 4:
                lyBg.setBackgroundResource(R.mipmap.icon_fuwufeidealersbg);
                break;
            case 5:
                lyBg.setBackgroundResource(R.mipmap.icon_fuwufeidailishang);
                break;
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().getServiceFeeindex()).subscribe(new BaseObserver<ServiceFeeBean>(mAt) {
            @Override
            public void onHandleSuccess(ServiceFeeBean serviceFeeBean) throws IOException {
                tvTaskgoalnum.setText("任务目标" + "(￥" + serviceFeeBean.getRecordInfo().getNeed_achieve_performance() + ")");
                tvHasbeen.setText("已完成" + serviceFeeBean.getRecordInfo().getPerformance());
                tvInspectionperiodnum.setText("考核周期" + "(" + String.valueOf(serviceFeeBean.getRecordInfo().getAssessment_cycle()) + "天)");
                System.out.println("申请时间" + stampToDate(serviceFeeBean.getRecordInfo().getReview_time()));
                //时间周期转换成时间戳
                int timeperiod = serviceFeeBean.getRecordInfo().getAssessment_cycle() * 24 * 3600;
                //结束时间
                String endoftime = String.valueOf(Integer.parseInt(serviceFeeBean.getRecordInfo().getReview_time()) + timeperiod);
                System.out.println("结束时间时间戳" + endoftime);
                System.out.println("周期时间戳" + timeperiod);
                System.out.println("结束时间" + stampToDate(endoftime));
                //时间显示
                tvCreatetime.setText(stampToDate(serviceFeeBean.getRecordInfo().getReview_time()) + "~" + stampToDate(endoftime));
                //百分比计算
                float pressent = (float) serviceFeeBean.getRecordInfo().getPerformance() / serviceFeeBean.getRecordInfo().getNeed_achieve_performance() * 100;
                //百分比转换int
                int num = Math.round(pressent);
                //获取系统当前时间
                String times = formatDuring(serviceFeeBean.getRecordInfo().getRemaining_time_for_assessment() * 1000);
                System.out.println("剩余时间"+times);
                tvRemainingtime.setText("还剩余:"+times);
                //进度条
                arcJindutiao.showAnimation(arcJindutiao.getProgress() == 100 ? 0 : arcJindutiao.getProgress(), num, 3000);
            }
        });

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    public static void invoke(Activity activity, int level) {
        Intent intent = new Intent(activity, RecruittoviewthetaskActivity.class);
        intent.putExtra("level", level);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.iv_header_back)
    public void onViewClicked() {
        finish();
    }

    //时间戳时间互转
    /*
     * 将时间戳转换为时间
     *
     * s就是时间戳
     */
    public static String stampToDate(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return times;

    }

    /**
     * 秒转换成天数
     *这个地方是毫秒转换
     * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
     * @author fy.zhang
     */
    public static String formatDuring(long mss) {
        long days = mss / (1000 * 60 * 60 * 24);
        long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        // long seconds = (mss % (1000 * 60)) / 1000;+ seconds + " seconds "
        //return days + "days" + hours + " hours" + minutes + "minutes";
        return days+"天"+hours +"时"+minutes+"分";
    }
}
