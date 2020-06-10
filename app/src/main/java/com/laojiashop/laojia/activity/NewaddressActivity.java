package com.laojiashop.laojia.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 新增地址
 */
public class NewaddressActivity extends BaseActivity {
    @BindView(R.id.header_title_view)
    RelativeLayout mTitleView;
    @BindView(R.id.tv_header_title)
    TextView mHeaderTitle;
    public final static int REQUEST_CODE_SELECT_BD_LOCATION = 101;
    private EditText detailEdt;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_newaddress);
    }

    @Override
    protected void initViews() {
        getBarDistance(mTitleView);
        mHeaderTitle.setText("新增地址");
        detailEdt=findViewById(R.id.detailEdt);
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
    @OnClick(R.id.ry_locationaddress)
    void locationClick() {
        Intent intent = new Intent(mAt, SelectLocationActivity.class);
//        startActivity(intent);
//        intent.putExtra("province", );
//        intent.putExtra("city", );
//        intent.putExtra("district", );
        startActivityForResult(intent, REQUEST_CODE_SELECT_BD_LOCATION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == REQUEST_CODE_SELECT_BD_LOCATION || resultCode == Activity.RESULT_OK) {
                detailEdt.setText(data.getStringExtra("title")+" "+data.getStringExtra("message"));
            }
        }
    }
}
