package com.laojiashop.laojia.adapter;

import android.accessibilityservice.AccessibilityService;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.OrderBean;
import com.laojiashop.laojia.entity.OrderStatus;

import java.util.List;

public class MeShopFragmentAdapter extends BaseQuickAdapter<OrderBean.DataBean, BaseViewHolder> {

    public MeShopFragmentAdapter(@Nullable List<OrderBean.DataBean> data) {
        super(R.layout.item_meshop, data);
    }
    @Override
    protected void convert(@NonNull BaseViewHolder helper, OrderBean.DataBean item) {
        //添加事件
        helper.addOnClickListener(R.id.btn_orderdetails,R.id.btn_cancelorders,R.id.btn_delectorder,R.id.btn_confirmthegoods);
        helper.setText(R.id.orderNumTxt, "编号:"+item.getOrder_no())
                .setText(R.id.statusTxt, item.getStatus_txt())
                .setText(R.id.specTxt, "￥" + item.getGoods_pay())
                .setText(R.id.freighttxt, "(含运费￥" + item.getFreight() + ")")
                .setText(R.id.total, "￥" + item.getTotal());

      //  int position = helper.getLayoutPosition();
        //System.out.println("position" + item.getGoods_info().get(position));
       // Button cancleButton=helper.getView(R.id.btn_cancelorders);
        //获取用户信息
        List<OrderBean.DataBean.GoodsInfoBean> goodsInfoBeanList=item.getGoods_info();
        for (int i = 0; i < goodsInfoBeanList.size(); i++) {
            Glide.with(mContext).load(goodsInfoBeanList.get(i).getPath()).apply(new RequestOptions().placeholder(R.mipmap.default_userhead_image)).into((ImageView) helper.getView(R.id.meshop_pic));
            helper.setText(R.id.titleTxt, goodsInfoBeanList.get(i).getGoods_title())
                    .setText(R.id.tv_skuname, goodsInfoBeanList.get(i).getSku_name());
        }
        //待付款
        helper.setGone(R.id.btn_cancelorders, OrderStatus.DAI_FU_KUAN.status==(item.getStatus()));
        //待收货
        helper.setGone(R.id.btn_confirmthegoods,OrderStatus.DAI_SHOU_HUO.status==(item.getStatus()));
        //已签收
        helper.setGone(R.id.btn_delectorder,OrderStatus.YI_QIAN_SHOU.status==(item.getStatus()));
        //已退货
        helper.setGone(R.id.btn_delectorder,OrderStatus.YI_TUI_KUAN.status==(item.getStatus()));
        //过期
        helper.setGone(R.id.btn_delectorder,OrderStatus.GUO_QI.status==(item.getStatus()));
        //判断按钮隐藏显示
//        switch (item.getStatus())

//        {
//            //待付款
//            case 1:
//                helper.setVisible(R.id.btn_cancelorders,true);
//                break;
//                //待发货
//            case 2:
//                break;
//                //待收货
//            case 3:
//                helper.setVisible(R.id.btn_confirmthegoods,true);
//                break;
//                //已签收
//            case 4:
//                helper.setVisible(R.id.btn_delectorder,true);
//                break;
//            case 5:
//                break;
//            case 91:
//                helper.setVisible(R.id.btn_delectorder,true);
//                break;
//        }
//        helper.setGone(R.id.btn_canceorder, OrderStatus.DAI_FU_KUAN.status.equals(item.getStatus()));
//       // helper.setGone(R.id.btn_delectorder, OrderStatus.DAI_FA_HUO.status.equals(item.getStatus()));
//        helper.setGone(R.id.btn_confirmthegoods,OrderStatus.DAI_SHOU_HUO.status.equals(item.getStatus()));
//        helper.setGone(R.id.btn_delectorder,OrderStatus.DAI_PING_JIA.status.equals(item.getStatus()));
//        Glide.with(mContext).load(item.getGoods_info().get(position).getPath()).apply(new RequestOptions().placeholder(R.mipmap.default_userhead_image)).into((ImageView) helper.getView(R.id.meshop_pic));
//        helper.setText(R.id.titleTxt,item.getGoods_info().get(position).getGoods_title())
//                .setText(R.id.tv_skuname,item.getGoods_info().get(position).getSku_name());

    }
}
