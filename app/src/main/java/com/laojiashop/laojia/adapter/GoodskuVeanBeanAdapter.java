package com.laojiashop.laojia.adapter;

import android.view.View;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.GoodsDetailBean;

import java.util.List;

/**
 * 商品规格内层适配器
 */
public class GoodskuVeanBeanAdapter extends BaseQuickAdapter<GoodsDetailBean.SkuBean.TreeBean.VBean, BaseViewHolder> {
    public GoodskuVeanBeanAdapter() {
        super(R.layout.item_goodskuvbean);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsDetailBean.SkuBean.TreeBean.VBean item) {
        helper.setText(R.id.btn_vbeansettext, item.getName());
        helper.addOnClickListener(R.id.btn_vbeansettext);
    }

//    public SelectedItem getSelectedItem() {
//        return selectedItem;
//    }
//
//    public void setSelectedItem(SelectedItem selectedItem) {
//        this.selectedItem = selectedItem;
//    }
//
//    //创建事件回调
//    public SelectedItem selectedItem;
//    //接口回调
//    public interface SelectedItem {
//        void onItemClick(GoodsDetailBean.SkuBean.TreeBean.VBean item, int position);
//    }
}
