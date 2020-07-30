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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class IsAbulkAdapter extends BaseQuickAdapter<GoodsDetailBean.TuangouBean, BaseViewHolder> {
//    private ArrayList<String> tuangouBeans=new ArrayList<>();

    public IsAbulkAdapter(int layoutResId, @Nullable List<GoodsDetailBean.TuangouBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsDetailBean.TuangouBean item) {
        //流式布局
        TagFlowLayout flowLayout = helper.getView(R.id.id_flowlayouts);
        final LayoutInflater mInflater = LayoutInflater.from(mContext);
        //定义集合对象
       ArrayList<String> value = new ArrayList<>();
       value.add(item.getNumberStr());
        flowLayout.setAdapter(new TagAdapter<String>(value) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv, flowLayout, false);
                tv.setText(s);
                return tv;
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
                    String s = value.get(next);
//                    System.out.println("信息打印"+s);
                    if (itemClickKind != null) {
                        itemClickKind.getItemKind(value.get(next), s);
                    }
                }
            }
        });
       // helper.setText(R.id.tv_tuangoucontent,item.getNumber()+"人团");
    }
    public interface ItemClickKind {
        void getItemKind(String spec, String kind);
    }

    private ItemClickKind itemClickKind;

    public void setItemClickKind(ItemClickKind itemClickKind) {
        this.itemClickKind = itemClickKind;
    }
}
