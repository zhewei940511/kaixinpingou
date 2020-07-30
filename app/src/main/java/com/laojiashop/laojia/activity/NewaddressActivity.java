package com.laojiashop.laojia.activity;

import android.os.Bundle;
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
    private String provincename,cituname,districtname,pcdaddress;
    //省市区ID
    private String provinceid,cituid,districid,code;
    private JDCityPicker jdCityPicker;
    //是否设置默认地址
    private int isdefault_address=0;

    //    public JDCityConfig.ShowType mWheelType = JDCityConfig.ShowType.PRO_CITY;
//    private JDCityConfig jdCityConfig = new JDCityConfig.Builder().build();
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_newaddress);
    }

    @Override
    protected void initViews() {
        getBarDistance(mTitleView);
        mHeaderTitle.setText("新增地址");
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
                    provincename=province.getName();
                    provinceid=province.getId();
                }

                String cituData = null;
                if (city != null) {
                    cituData = "name:  " + city.getName() + "   id:  " + city.getId();
                    cituname=city.getName();
                    cituid=city.getId();
                }

                String districtData = null;
                if (district != null) {
                    districtData = "name:  " + district.getName() + "   id:  " + district.getId();
                    districtname=district.getName();
                    districid=district.getId();
                    code=district.getId();
                }
//
//                tvShowadress.setText("城市选择结果：\n" + proData + "\n"
//                        + cituData + "\n"
//                        + districtData);
                tvShowadress.setText(province.getName() + "\t"
                        + city.getName() + "\t"
                        + district.getName());
                pcdaddress=province.getName()+"/"+city.getName()+"/"+district.getName();
//                if (mWheelType == JDCityConfig.ShowType.PRO_CITY_DIS) {
//                    tv_showid.setText("城市选择结果：\n" + proData + "\n"
//                            + cituData + "\n"
//                            + districtData);
//                } else {
//                    tv_showid.setText("城市选择结果：\n" + proData + "\n"
//                            + cituData + "\n"
//                    );
//                }
            }

            @Override
            public void onCancel() {
            }
        });
//        ry_selectaddress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                AddressBottomDialog dialog = AddressBottomDialog.show(mAt);
//                dialog.setTitles("收货地址");
//                dialog.setAddressProvider(new AddressProvider() {
//                    @Override
//                    public void provideProvinces(AddressReceiver<Province> addressReceiver) {
//                        HttpRxObservable.getObservable(ApiUtils.getApiService().districts(0)).subscribe(new BaseObserver<List<ADProvince>>(mAt) {
//                            @Override
//                            public void onHandleSuccess(List<ADProvince> adProvinces) throws IOException {
//                                ArrayList<Province> provinces = new ArrayList<>();
//                                for (ADProvince adProvince : adProvinces) {
//                                    Province province = new Province();
//                                    province.id = adProvince.id;
//                                    province.name = adProvince.name;
//                                    provinces.add(province);
//                                }
//                                addressReceiver.send(provinces);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void provideCitiesWith(int provinceId, AddressReceiver<City> addressReceiver) {
//                        HttpRxObservable.getObservable(ApiUtils.getApiService().districts(provinceId)).subscribe(new BaseObserver<List<ADProvince>>(mAt) {
//                            @Override
//                            public void onHandleSuccess(List<ADProvince> adProvinces) throws IOException {
//                                ArrayList<City> cities = new ArrayList<>();
//                                for (ADProvince adProvince : adProvinces) {
//                                    if (adProvince.id == provinceId) {
//                                        for (ADCity adCity : adProvince.cities) {
//                                            City city = new City();
//                                            city.id = adCity.id;
//                                            city.name = adCity.name;
//                                            city.province_id = adCity.pid;
//                                            cities.add(city);
//                                        }
//                                        break;
//                                    }
//                                }
//                                addressReceiver.send(cities);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void provideCountiesWith(int cityId, AddressReceiver<County> addressReceiver) {
//                        HttpRxObservable.getObservable(ApiUtils.getApiService().districts(cityId)).subscribe(new BaseObserver<List<ADProvince>>(mAt) {
//                            @Override
//                            public void onHandleSuccess(List<ADProvince> adProvinces) throws IOException {
//                                ArrayList<County> counties = new ArrayList<>();
//                                for (ADProvince adProvince : adProvinces) {
//                                    for (ADCity adCity : adProvince.cities) {
//                                        if (adCity.id == cityId) {
//                                            for (ADArea adArea : adCity.getAreas()) {
//                                                County county = new County();
//                                                county.id = adArea.id;
//                                                county.name = adArea.name;
//                                                county.city_id = adArea.pid;
//                                                counties.add(county);
//                                            }
//                                            break;
//                                        }
//                                    }
//                                }
//                                addressReceiver.send(counties);
//                            }
//                        });
//                    }
//
//                    @Override
//                    public void provideStreetsWith(int countyId, AddressReceiver<Street> addressReceiver) {
//                        addressReceiver.send(null);
//                    }
//                });
//                dialog.setOnAddressSelectedListener((province, city, county, street) -> {
//                    addresss = new AddressIn(province, city, county);
//                    tvShowadress.setText(addresss.toString());
//                    dialog.dismiss();
//                });
//            }
//        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        orderSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                System.out.println("当前的选择是"+b);
                if (b)
                {
                    isdefault_address=1;
                }else
                {
                    isdefault_address=0;
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

    @OnClick({ R.id.saveBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.saveBtn:
                String name=nameEdt.getText().toString().trim();
                String phone=mobileEdt.getText().toString().trim();
                String address=detailEdt.getText().toString().trim();
                if (TextUtils.isEmpty(name))
                {
                    showToast("请输入姓名");
                    return;
                }
                if (TextUtils.isEmpty(phone))
                {
                    showToast("请输入手机号");
                    return;
                }
                if (TextUtils.isEmpty(provincename))
                {
                    showToast("请选择地址");
                    return;
                }
                if (TextUtils.isEmpty(address))
                {
                    showToast("请输入详细地址");
                    return;
                }

                HttpRxObservable.getObservable(ApiUtils.getApiService().addressaddrecord(name,
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
                        showToast("添加成功"+o.toString());
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        showToast("添加地址失败了"+e);
                    }
                });
                break;
        }
    }
}
