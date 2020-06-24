package com.laojiashop.laojia.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;
import com.laojiashop.laojia.publish.Category;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

public class NavigationAdapter extends BaseQuickAdapter<Category, BaseViewHolder> {
    public NavigationAdapter(int layoutResId, @Nullable List<Category> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, Category item) {
        if (!TextUtils.isEmpty(item.getCate_name())) {
            helper.setText(R.id.item_navigation_tv, item.getCate_name());
        }
        TagFlowLayout mTagFlowLayout = helper.getView(R.id.item_navigation_flow_layout);

        List<Category> itemChildren = item.getChildren();
        mTagFlowLayout.setAdapter(new TagAdapter<Category>(itemChildren) {
            @Override
            public View getView(FlowLayout parent, int position, Category navigationChildBean) {
                View rootItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.flow_layout,
                        mTagFlowLayout, false);
                ImageView circleView = rootItemView.findViewById(R.id.navigation_icon);
                TextView nameTv = rootItemView.findViewById(R.id.navigation_title);
                if (navigationChildBean == null) {
                    return null;
                }


                Glide.with(parent.getContext()).load(navigationChildBean.getCate_icon()).into(circleView);

                nameTv.setText(navigationChildBean.getCate_name());

                return rootItemView;
            }
        });

        mTagFlowLayout.setOnTagClickListener((view, position1, parent1) -> {

//            Intent intent = new Intent(mContext, ClassicResultActivity.class);
//            intent.putExtra(Constants.SEARCH_TITLE, itemChildren.get(position1).getCate_name());
//            intent.putExtra(Constants.SEARCH_TYPE, itemChildren.get(position1).getType());
//            intent.putExtra(Constants.SEARCH_ID, itemChildren.get(position1).getCate_id());
//            mContext.startActivity(intent);
            return false;
        });
    }
}
