package com.laojiashop.laojia.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.GoodsDetailBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.laojiashop.laojia.utils.ToastUtil.showToast;

/**
 * 商品详情规格适配器
 */
public class MallBuyAdapter extends BaseQuickAdapter<GoodsDetailBean.SkuBean.TreeBean, BaseViewHolder> {
    private List<GoodsDetailBean.SkuBean.TreeBean.VBean> vBeans;

    public MallBuyAdapter(int layoutResId, @Nullable List<GoodsDetailBean.SkuBean.TreeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsDetailBean.SkuBean.TreeBean item) {
        helper.setText(R.id.tv_specification, item.getK());
        //流式布局
        TagFlowLayout flowLayout = helper.getView(R.id.id_flowlayout);
        final LayoutInflater mInflater = LayoutInflater.from(mContext);
        vBeans = item.getV();
        //流式布局适配器加载
        flowLayout.setAdapter(new TagAdapter<GoodsDetailBean.SkuBean.TreeBean.VBean>(item.getV()) {
            @Override
            public View getView(FlowLayout parent, int position, GoodsDetailBean.SkuBean.TreeBean.VBean vBean) {
                TextView textView = (TextView) mInflater.inflate(R.layout.tv, flowLayout, false);
                textView.setText(vBean.getName());
                return textView;
            }
        });
        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                return true;
            }
        });
        flowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {

                if (selectPosSet.size() > 0) {
                    Integer next = selectPosSet.iterator().next();
                   // String s = item.getV().get(next).getName();
//                    StringBuffer buffer = new StringBuffer();
//                    if (item.getV().size() <= 0) {
//                        showToast("请选择" + item.getV().get(next).getName());
//                        return;
//                    } else {
//                        buffer.append(item.getV().get(next).getName()).append("-").append(item.getV().get(next).getId());
//                    }
//                    String selectKind = buffer.toString();
//                    System.out.println("测试轮训"+selectKind);
                    //拼接选中的值
                    if (itemClickKind != null) {
                        itemClickKind.getItemKind(item.getV().get(next).getName());
                    }
                }
            }
        });
    }
    //定义一个适配器的点击事件的接口
    public interface ItemClickKind {
        void getItemKind(String spec);
    }

//    public interface ItemClickKind {
//        void getItemKind(String spec, String kind);
//    }

    private ItemClickKind itemClickKind;

    public void setItemClickKind(ItemClickKind itemClickKind) {
        this.itemClickKind = itemClickKind;
    }
}
