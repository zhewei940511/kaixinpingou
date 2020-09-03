package com.laojiashop.laojia.adapter;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.GoodsDetailBean;
import com.laojiashop.laojia.entity.HomePageBean;
import com.laojiashop.laojia.entity.HomePageTestBean;

import java.util.List;

public class SpecialTestAdapter extends BaseMultiItemQuickAdapter<HomePageTestBean.SpecialBean.GoodsListBean, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SpecialTestAdapter(List<HomePageTestBean.SpecialBean.GoodsListBean> data) {
        super(data);
        addItemType(HomePageTestBean.SpecialBean.STYLEONE, R.layout.item_styleofone);
        addItemType(HomePageTestBean.SpecialBean.STYLETWO, R.layout.item_styleoftwo);
        addItemType(HomePageTestBean.SpecialBean.STYLETHREE, R.layout.item_styleofthree);
    }

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     */
//    public SpecialTestAdapter(List<HomePageTestBean.SpecialBean> data) {
//        super(data);
//        //判断后台返回的数量添加不同的样式
//        addItemType(HomePageTestBean.SpecialBean.STYLEONE, R.layout.item_styleofone);
//        addItemType(HomePageTestBean.SpecialBean.STYLETWO, R.layout.item_styleoftwo);
//        addItemType(HomePageTestBean.SpecialBean.STYLETHREE, R.layout.item_styleofthree);
////        switch (HomePageTestBean.SpecialBean.STYLEONE)
////        {
////            case 1:
////                addItemType(HomePageTestBean.SpecialBean.display, R.layout.item_styleofone);
////                break;
////            case 2:
////                addItemType(HomePageTestBean.SpecialBean.display, R.layout.item_styleoftwo);
////                break;
////            case 3:
////                addItemType(HomePageTestBean.SpecialBean.display, R.layout.item_styleofthree);
////                break;
////        }
//    }

//    @Override
//    protected void convert(@NonNull BaseViewHolder helper, HomePageTestBean.SpecialBean item) {
//        switch (helper.getItemViewType())
//        {
//            case HomePageTestBean.SpecialBean.STYLEONE:
////                helper.setText(R.id.tv_specialtitle,item.getGoods_list().get(0).getStitle());
//                break;
//            case HomePageTestBean.SpecialBean.STYLETWO:
//                break;
//            case HomePageTestBean.SpecialBean.STYLETHREE:
//                break;
//        }
//
//    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HomePageTestBean.SpecialBean.GoodsListBean item) {

    }
}
