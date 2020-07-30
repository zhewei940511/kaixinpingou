package com.laojiashop.laojia.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.entity.MyteamListBean;

import java.util.List;

public class MyTeamAdapter extends BaseQuickAdapter<MyteamListBean,BaseViewHolder> {

    public MyTeamAdapter() {
        super(R.layout.item_mytem);
    }


    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyteamListBean item) {
            //获取dapter的position
        int position = helper.getAdapterPosition();
        //获取单个item的数据结构,这个就是每个item的数据
        MyteamListBean item1 = getItem(position);
//        List<MyteamListBean.ListBean> list = item1.getList();//这个就是单个的list，明白没嗯，嗯嗯。等等，我想下还有没有经常要用到的
//        List<MyteamListBean> data = getData();//这个是全部的。可以更换数据源，增删改等操作，我一下也想不出来了，这个是不是就是你要我先试试？嗯嗯
        helper.setText(R.id.tv_uernametitle,item1.getList().get(position).getName());
        //在adapter中获取控件，初始化控件
//        TextView txt=getItemView(R.id.xxx);

    }
}
