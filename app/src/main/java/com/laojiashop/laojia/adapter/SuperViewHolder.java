package com.laojiashop.laojia.adapter;


import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ：方燚
 * time       ：2018/2/5.
 * description:ViewHolder基类
 */
public class SuperViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> views;

    public SuperViewHolder(View itemView) {
        super(itemView);
        this.views = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }
}
