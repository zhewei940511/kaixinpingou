package com.laojiashop.laojia.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.ClassificationPageBean;
import com.laojiashop.laojia.utils.SharedPreferencesManager;

import java.util.List;

public class TestFragment extends Fragment {
    //创建集合
    private List<ClassificationPageBean> data;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.fragment_classificationpageright, null);
//        TextView cte = (TextView) inflate.findViewById(R.id.oo);
        Bundle arguments = getArguments();
        String name = arguments.getString("name");
        int position=arguments.getInt("position");
        //轮播图数据源
        //  bannerBeans= (List<ClassificationPageBean.BannerBean>) arguments.getSerializable("banners");
//        tvCeshi.setText("动态fragment：" + name+position);
        data= SharedPreferencesManager.get("classinfo",null);
        System.out.println("正确数据"+data.get(position).getBanner());
//        Log.e("chen", "onCreateView: ------" + name);
//        cte.setText("动态fragment："+name);
        return inflate;
    }


}
