package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.UserInfoBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.ToastUtil;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 兑换
 */
public class ExchangeActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    //数量加减
    @BindView(R.id.btn_sub)
    ImageView btnSub;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.btn_add)
    ImageView btnAdd;
    @BindView(R.id.tv_availablescore)
    TextView tvAvailablescore;
    @BindView(R.id.tv_proportionnum)
    TextView tvProportionnum;
    @BindView(R.id.tv_costscore)
    TextView tvCostscore;
    @BindView(R.id.btn_exchangesure)
    Button btnExchangesure;
    //定义兑换数量
    private String number;
    //定义变量接收比例
    private String proportionnum;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_exchange);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("兑换");
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = tvNum.getText().toString();
                int i = Integer.parseInt(number);
                i += 1;
                String addNumber = Integer.toString(i);
                tvNum.setText(addNumber);
                tvCostscore.setText(String.valueOf(Integer.parseInt(proportionnum) * Integer.parseInt(addNumber)));
            }
        });
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                number = tvNum.getText().toString();
                int i = Integer.parseInt(number);
                if (i > 1) {
                    i -= 1;
                    String addNumber = Integer.toString(i);
                    tvNum.setText(addNumber);
                    tvCostscore.setText(String.valueOf(Integer.parseInt(proportionnum) * Integer.parseInt(addNumber)));
                }
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void getDataFromServer() {
        //获取基本信息
        HttpRxObservable.getObservable(ApiUtils.getApiService().getuserinfo()).subscribe(new BaseObserver<UserInfoBean>(mAt) {
            @Override
            public void onHandleSuccess(UserInfoBean userInfoBean) throws IOException {
                proportionnum = userInfoBean.getBonus().getPrice();
                tvAvailablescore.setText(userInfoBean.getScore());
                tvProportionnum.setText("1:" + userInfoBean.getBonus().getPrice());
                tvCostscore.setText(Integer.parseInt(proportionnum) * Integer.parseInt(tvNum.getText().toString()));
            }
        });

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @OnClick({R.id.iv_header_back, R.id.btn_exchangesure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.btn_exchangesure:
                //兑换
                HttpRxObservable.getObservable(ApiUtils.getApiService().corebuyBonus(Integer.parseInt(tvNum.getText().toString()))).subscribe(new BaseObserver<Object>(mAt) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        ToastUtil.showToast("兑换成功");
                        finish();
                    }
                });
                break;
        }
    }

}
