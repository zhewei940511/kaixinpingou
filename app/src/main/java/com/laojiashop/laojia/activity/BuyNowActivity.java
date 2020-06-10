package com.laojiashop.laojia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.kyleduo.switchbutton.SwitchButton;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 立即购买
 */
public class BuyNowActivity extends BaseActivity {
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_header_title)
    TextView tvHeaderTitle;
    @BindView(R.id.header_title_view)
    RelativeLayout headerTitleView;
    @BindView(R.id.btn_sub)
    ImageView btnSub;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.btn_add)
    ImageView btnAdd;
    @BindView(R.id.order_switch)
    SwitchButton orderSwitch;
    @BindView(R.id.tv_showuse)
    TextView tvShowuse;
    private String number;
    public static final int REQUEST_CODE_BUYNOW = 1110;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_buynow);
    }

    @Override
    protected void initViews() {
        getBarDistance(headerTitleView);
        tvHeaderTitle.setText("确认订单");
        ivHeaderBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        orderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tvShowuse.setVisibility(View.VISIBLE);
                } else {
                    tvShowuse.setVisibility(View.GONE);
                }
            }
        });
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


    @OnClick({R.id.btn_sub, R.id.btn_add, R.id.order_switch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_sub:
                number = tvNum.getText().toString();
                int i = Integer.parseInt(number);
                if (i > 1) {
                    i -= 1;
                    String addNumber = Integer.toString(i);
                    tvNum.setText(addNumber);
                }
                break;
            case R.id.btn_add:
                number = tvNum.getText().toString();
                int addi = Integer.parseInt(number);
                addi += 1;
                String addnum = Integer.toString(addi);
                tvNum.setText(addnum);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_BUYNOW) {
            String address = data.getStringExtra("address");
            if (!TextUtils.isEmpty(address)) {
               // txtSelect.setText(address);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
