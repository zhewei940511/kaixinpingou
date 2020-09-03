package com.laojiashop.laojia.adapter;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.MySpellingBean;
import com.laojiashop.laojia.entity.OrderStatus;
import com.laojiashop.laojia.entity.SpellStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * 我发起团购的适配器
 */
public class IstartedSpellingAdapter extends BaseQuickAdapter<MySpellingBean.DataBean, BaseViewHolder> {
    private List<String> tag=new ArrayList<>();
    public IstartedSpellingAdapter( List<MySpellingBean.DataBean> data) {
        super(R.layout.item_fragmentspell, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, MySpellingBean.DataBean item) {
        Glide.with(mContext).load(item.getPath()).apply(new RequestOptions().placeholder(R.mipmap.default_userhead_image)).into((ImageView) helper.getView(R.id.img_spellpic));
        helper.setText(R.id.tv_spellno,"团号:"+item.getId());
        helper.setText(R.id.tv_spellstatus,item.getStatus_txt());
        helper.setText(R.id.tv_spelltitle,item.getTitle());
        helper.setText(R.id.tv_spellgroupprice,item.getPrice());
        helper.setText(R.id.tv_retailprice,item.getMarket_price());
        helper.setText(R.id.tv_spellstitle,item.getStitle());
        //获取订单状态显示隐藏按钮
        //待付款
        helper.setGone(R.id.btn_shutdown, SpellStatus.JIJIANGKAISHI.status==(item.getStatus()));
        helper.setGone(R.id.btn_shutdown, SpellStatus.JINGXINGZHONG.status==(item.getStatus()));
        //添加点击事件
        helper.addOnClickListener(R.id.btn_toafriend,R.id.btn_statistical,R.id.btn_shutdown,R.id.ly_spellgroupdetails);
        //tag=item.get();
    }
}
