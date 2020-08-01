package com.laojiashop.laojia.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kyleduo.switchbutton.SwitchButton;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.AddressmanagementBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.utils.LoginInfoUtil;
import com.laojiashop.laojia.utils.ToastUtil;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.style.cityjd.JDCityPicker;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 新增地址
 */
public class NewaddressActivity extends BaseActivity {
    @BindView(R.id.header_title_view)
    RelativeLayout mTitleView;
    @BindView(R.id.tv_header_title)
    TextView mHeaderTitle;
    @BindView(R.id.iv_header_back)
    ImageView ivHeaderBack;
    @BindView(R.id.tv_showadress)
    TextView tvShowadress;
    @BindView(R.id.nameEdt)
    EditText nameEdt;
    @BindView(R.id.mobileEdt)
    EditText mobileEdt;
    @BindView(R.id.detailEdt)
    EditText detailEdt;
    @BindView(R.id.order_switch)
    SwitchButton orderSwitch;
    @BindView(R.id.saveBtn)
    Button saveBtn;
    private RelativeLayout ry_selectaddress;
    //  //  List<ADProvince> adProvincess = new ArrayList<>();
//    AddressIn addresss;
    //声明省市区
    private String provincename, cituname, districtname, pcdaddress;
    //省市区ID
    private String provinceid, cituid, districid, code;
    private JDCityPicker jdCityPicker;
    //是否设置默认地址
    private int isdefault_address = 0;
    private String addressid;
    //    public JDCityConfig.ShowType mWheelType = JDCityConfig.ShowType.PRO_CITY;
//    private JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_newaddress);
    }

    @Override
    protected void initViews() {
        int flag = getIntent().getIntExtra("flag", 0);
        AddressmanagementBean.DataBean bean = (AddressmanagementBean.DataBean) getIntent().getSerializableExtra("data");
        if (flag==1){
            //编辑都写到这个里面等一哈，如果我不需要处理这个flag会出现什么情况是不是近来的title都是新增地址，一会试下这个实体类应该是为1的时候去取，为其他的不取？
            mHeaderTitle.setText("编辑地址");
            nameEdt.setText(bean.getName());
            mobileEdt.setText(bean.getPhone());
//            pcdaddress=bean.getSsq();
//            tvShowadress.setText(pcdaddress);
//            detailEdt.setText(bean.getAddress());
            isdefault_address=bean.getIs_def();
            if (isdefault_address==1)
            {
                orderSwitch.setChecked(true);
            }else {
                orderSwitch.setChecked(false);
            }
            addressid=String.valueOf(bean.getId());
        }else {
            mHeaderTitle.setText("新增地址");
        }
        getBarDistance(mTitleView);

        ry_selectaddress = findViewById(R.id.ry_selectaddress);
        jdCityPicker = new JDCityPicker();
        jdCityPicker.init(this);
        ry_selectaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jdCityPicker.showCityPicker();
            }
        });
        jdCityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                String proData = null;
                if (province != null) {
                    proData = "name:  " + province.getName() + "   id:  " + province.getId();
                    provincename = province.getName();
                    provinceid = province.getId();
                }

                String cituData = null;
                if (city != null) {
                    cituData = "name:  " + city.getName() + "   id:  " + city.getId();
                    cituname = city.getName();
                    cituid = city.getId();
                }

                String districtData = null;
                if (district != null) {
                    districtData = "name:  " + district.getName() + "   id:  " + district.getId();
                    districtname = district.getName();
                    districid = district.getId();
                    code = district.getId();
                }
                tvShowadress.setText(province.getName() + "\t"
                        + city.getName() + "\t"
                        + district.getName());
                pcdaddress = province.getName() + "/" + city.getName() + "/" + district.getName();
            }

            @Override
            public void onCancel() {
            }
        });

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        orderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println("当前的选择是" + b);
                if (b) {
                    isdefault_address = 1;
                } else {
                    isdefault_address = 0;
                }
            }
        });
    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.saveBtn,R.id.iv_header_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_header_back:
                finish();
                break;
            case R.id.saveBtn:
                String name = nameEdt.getText().toString().trim();
                String phone = mobileEdt.getText().toString().trim();
                String address = detailEdt.getText().toString().trim();
                if (TextUtils.isEmpty(name)) {
                    showToast("请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(phone)) {
                    showToast("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(provincename)) {
                    showToast("请选择地址");
                    return;
                }
                if (TextUtils.isEmpty(address)) {
                    showToast("请输入详细地址");
                    return;
                }

                HttpRxObservable.getObservable(ApiUtils.getApiService().addressaddrecord(addressid,name,
                        phone,
                        provincename,
                        cituname,
                        districtname,
                        provinceid,
                        cituid,
                        districid,
                        code,
                        address,
                        isdefault_address,
                        pcdaddress, LoginInfoUtil.getUid())).subscribe(new BaseObserver<Object>(mAt) {
                    @Override
                    public void onHandleSuccess(Object o) throws IOException {
                        showToast("添加成功" );
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        showToast("添加地址失败了");
                    }
                });
                break;
        }
    }

    //我先给你举个例子，你需要啥自己加
    public static void invoke(Activity activity, int flag,  AddressmanagementBean.DataBean item) {
        Intent intent = new Intent(activity, NewaddressActivity.class);
        //cao.//
        intent.putExtra("flag",flag);
        intent.putExtra("data", (Serializable) item);
//        intent.putExtra("flag",flag);
//        intent.putExtra("name", name);
//        intent.putExtra("phone",phone);
//        intent.putExtra("ssq",ssq);
//        intent.putExtra("addressstr",addressstr);
//        intent.putExtra("isdefault_address",isdefault_address);
        activity.startActivity(intent);
    }
}
