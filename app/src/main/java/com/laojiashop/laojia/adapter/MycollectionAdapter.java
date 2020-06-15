package com.laojiashop.laojia.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.HotstyletorecommendBean;

import java.util.ArrayList;
import java.util.List;

import co.lujun.androidtagview.TagContainerLayout;

public class MycollectionAdapter extends BaseQuickAdapter<HotstyletorecommendBean, BaseViewHolder> {
    private List<String> tag;
    public MycollectionAdapter() {
        super(R.layout.item_mycollection);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, HotstyletorecommendBean item) {
        tag=new ArrayList<>();
        for (int i=0;i<3;i++)
        {
            tag.add("测试"+i);
        }
        TagContainerLayout  tagContainerLayout=helper.getView(R.id.tga_mycollection);
        tagContainerLayout.setTags(tag);
        helper.addOnClickListener(R.id.btn_delectcollection);
    }
}
