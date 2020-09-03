package com.laojiashop.laojia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.GoodsDetailBean;
import com.laojiashop.laojia.utils.ToastUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 商品规格外层适配器
 */
public class SpecificationsAdapter extends BaseQuickAdapter<GoodsDetailBean.SkuBean.TreeBean, BaseViewHolder> {
    private List<GoodsDetailBean.SkuBean.TreeBean.VBean> vBeanList=new ArrayList<>();
    private GoodskuVeanBeanAdapter goodskuVeanBeanAdapter;
    public SpecificationsAdapter(@Nullable List<GoodsDetailBean.SkuBean.TreeBean> data) {
        super(R.layout.mall_buy_items, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsDetailBean.SkuBean.TreeBean item) {
        //名字
        helper.setText(R.id.tv_specification, item.getK());
        //规格详情
        vBeanList=item.getV();
        //规格适配器
        goodskuVeanBeanAdapter=new GoodskuVeanBeanAdapter();
        //内层rv
        RecyclerView recyclerView=helper.getView(R.id.img_rvs);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        //内层rv绑定数据
        goodskuVeanBeanAdapter.addData(vBeanList);
        recyclerView.setAdapter(goodskuVeanBeanAdapter);
        //内层适配器设置点击事件
        goodskuVeanBeanAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                GoodsDetailBean.SkuBean.TreeBean.VBean vBean= (GoodsDetailBean.SkuBean.TreeBean.VBean) adapter.getItem(position);
                switch (view.getId())
                {
                    case R.id.btn_vbeansettext:
                        ToastUtil.showToast("你点击了"+vBean.getName());
                        break;
                }
            }
        });
//        goodskuVeanBeanAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
//            @Override
//            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                System.out.println("信息打印你点击了"+adapter.getItem(position));
//                ToastUtil.showToast(""+item.getV().get(position).getName());
//            }
//        });
//        //添加点击事件
//        helper.addOnClickListener()
        //流式布局
//        TagFlowLayout flowLayout = helper.getView(R.id.id_flowlayout);
//        final LayoutInflater mInflater = LayoutInflater.from(mContext);
//        //流式布局适配器加载
//        flowLayout.setAdapter(new TagAdapter<GoodsDetailBean.SkuBean.TreeBean.VBean>(item.getV()) {
//            @Override
//            public View getView(FlowLayout parent, int position, GoodsDetailBean.SkuBean.TreeBean.VBean vBean) {
//                TextView textView = (TextView) mInflater.inflate(R.layout.tv, flowLayout, false);
//                textView.setText(vBean.getName());
//                return textView;
//            }
//        });
//        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
//            @Override
//            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                return true;
//            }
//        });
//        flowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
//            @Override
//            public void onSelected(Set<Integer> selectPosSet) {
//                if (selectPosSet.size()>0)
//                {
//                     Integer next = selectPosSet.iterator().next();
//                     String s=item.getV().get(next).getName();
//                     if (itemClickKind!=null)
//                     {
//                         itemClickKind.getItemKind(item.getK(), s);
//                     }
//                }else {
//                    if (itemClickKind != null) {
//                        itemClickKind.getItemKind(item.getK(), null);
//                    }
//                }
//            }
//        });

    }
//    public interface ItemClickKind {
//        void getItemKind(String spec, String kind);
//    }
//
//    private ItemClickKind itemClickKind;
//
//    public void setItemClickKind(ItemClickKind itemClickKind) {
//        this.itemClickKind = itemClickKind;
//    }
}
