package com.laojiashop.laojia.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.MyteamListBean;

import java.util.List;

public class MyTeamAdapter extends BaseQuickAdapter<MyteamListBean.ListBean,BaseViewHolder> {

    public MyTeamAdapter(List<MyteamListBean.ListBean> data) {
        super(R.layout.item_mytem, data);
    }
    @Override
    protected void convert(@NonNull BaseViewHolder helper, MyteamListBean.ListBean item) {
        Glide.with(mContext).load(item.getHeadimgurl()).apply(new RequestOptions().placeholder(R.mipmap.default_userhead_image)).into((ImageView) helper.getView(R.id.img_headimgurl));
        helper.setText(R.id.tv_uernametitle,item.getName());
        helper.setText(R.id.tv_phone,item.getPhone());
        helper.setText(R.id.tv_total_number_teams,String.valueOf(item.getTotal_number_teams()));
        helper.setText(R.id.tv_number_team_added_today,String.valueOf(item.getNumber_team_added_today()));
        helper.setText(R.id.tv_team_performance,item.getTeam_performance());
        helper.setText(R.id.tv_team_new_performance_today,item.getTeam_new_performance_today());
        //判断图片加载
        switch (item.getLevel())
        {
            //普通会员
            case 1:
                Glide.with(mContext).load(R.mipmap.icon_teammembers).into((ImageView) helper.getView(R.id.img_level));
                break;
                // 顾客
            case 2:
                Glide.with(mContext).load(R.mipmap.icon_teamcustomer).into((ImageView) helper.getView(R.id.img_level));
                break;
                //导购
            case 3:
                Glide.with(mContext).load(R.mipmap.icon_teamshoppers).into((ImageView) helper.getView(R.id.img_level));
                break;
                //经销商
            case 4:
                Glide.with(mContext).load(R.mipmap.icon_teamdealers).into((ImageView) helper.getView(R.id.img_level));
                break;
                //代理商
            case 5:
                Glide.with(mContext).load(R.mipmap.icon_teamagent).into((ImageView) helper.getView(R.id.img_level));
                break;
        }
    }
}
