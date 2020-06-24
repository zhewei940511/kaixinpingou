package com.laojiashop.laojia.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.laojiashop.laojia.model.ADArea;
import com.laojiashop.laojia.model.ADCity;
import com.laojiashop.laojia.model.ADProvince;
import com.laojiashop.laojia.model.AddressIn;
import com.laojiashop.laojia.model.District;
import com.laojiashop.laojia.view.AddressBottomDialog;
import com.zhuosongkj.android.library.model.BaseResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import chihane.jdaddressselector.AddressProvider;
import chihane.jdaddressselector.model.City;
import chihane.jdaddressselector.model.County;
import chihane.jdaddressselector.model.Province;
import chihane.jdaddressselector.model.Street;
import io.reactivex.Observer;

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
    private RelativeLayout ry_selectaddress;
  //  List<ADProvince> adProvincess = new ArrayList<>();
    AddressIn addresss;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_newaddress);
    }

    @Override
    protected void initViews() {
        getBarDistance(mTitleView);
        mHeaderTitle.setText("新增地址");
//        HttpRxObservable.getObservable(ApiUtils.getApiService().districts(0)).subscribe(new BaseObserver<List<ADProvince>>(mAt) {
//            @Override
//            public void onHandleSuccess(List<ADProvince> adProvinces) throws IOException {
//                adProvincess.addAll(adProvinces);
//            }
//        });
        ry_selectaddress = findViewById(R.id.ry_selectaddress);
        ry_selectaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddressBottomDialog dialog = AddressBottomDialog.show(mAt);
                dialog.setTitles("收货地址");
                dialog.setAddressProvider(new AddressProvider() {
                    @Override
                    public void provideProvinces(AddressReceiver<Province> addressReceiver) {
                        HttpRxObservable.getObservable(ApiUtils.getApiService().districts(0)).subscribe(new BaseObserver<List<ADProvince>>(mAt) {
                            @Override
                            public void onHandleSuccess(List<ADProvince> adProvinces) throws IOException {
                               // adProvincess.addAll(adProvinces);
                                ArrayList<Province> provinces = new ArrayList<>();
                                for (ADProvince adProvince : adProvinces) {
                                    Province province = new Province();
                                    province.id = adProvince.id;
                                    province.name = adProvince.name;
                                    provinces.add(province);
                                }
                                addressReceiver.send(provinces);
                            }
                        });
                    }

                    @Override
                    public void provideCitiesWith(int provinceId, AddressReceiver<City> addressReceiver) {
                        HttpRxObservable.getObservable(ApiUtils.getApiService().districts(provinceId)).subscribe(new BaseObserver<List<ADProvince>>(mAt) {
                            @Override
                            public void onHandleSuccess(List<ADProvince> adProvinces) throws IOException {
                                ArrayList<City> cities = new ArrayList<>();
                                for (ADProvince adProvince : adProvinces) {
                                    if (adProvince.id == provinceId) {
                                        for (ADCity adCity : adProvince.cities) {
                                            City city = new City();
                                            city.id = adCity.id;
                                            city.name = adCity.name;
                                            city.province_id = adCity.pid;
                                            cities.add(city);
                                        }
                                        break;
                                    }
                                }
                                addressReceiver.send(cities);
                            }
                        });
                    }

                    @Override
                    public void provideCountiesWith(int cityId, AddressReceiver<County> addressReceiver) {
                        HttpRxObservable.getObservable(ApiUtils.getApiService().districts(cityId)).subscribe(new BaseObserver<List<ADProvince>>(mAt) {
                            @Override
                            public void onHandleSuccess(List<ADProvince> adProvinces) throws IOException {
                                ArrayList<County> counties = new ArrayList<>();
                                for (ADProvince adProvince : adProvinces) {
                                    for (ADCity adCity : adProvince.cities) {
                                        if (adCity.id == cityId) {
                                            for (ADArea adArea : adCity.getAreas()) {
                                                County county = new County();
                                                county.id = adArea.id;
                                                county.name = adArea.name;
                                                county.city_id = adArea.pid;
                                                counties.add(county);
                                            }
                                            break;
                                        }
                                    }
                                }
                                addressReceiver.send(counties);
                            }
                        });
                    }

                    @Override
                    public void provideStreetsWith(int countyId, AddressReceiver<Street> addressReceiver) {
                        addressReceiver.send(null);
                    }
                });
                dialog.setOnAddressSelectedListener((province, city, county, street) -> {
                    addresss = new AddressIn(province, city, county);
                    tvShowadress.setText(addresss.toString());
                    dialog.dismiss();
                });
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
