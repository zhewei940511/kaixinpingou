package com.laojiashop.laojia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.activity.MorecommentsActivity;
import com.laojiashop.laojia.entity.DetailsBean;


/**
 * Created by ：方燚
 * time       ：2018/2/5.
 * description:地址列表
 */

public class DetailsAdapter extends ListBaseAdapter<DetailsBean> {
    private int layoutID;
    private int height=0;
    private Context context;
    public DetailsAdapter(Context context) {
        super(context);
        this.context=context;
    }

    @Override
    public int getItemViewType(int position) {
        int type = mDataList.get(position).getType();
        if (type == 1) {
//            商品
            layoutID = R.layout.item_detailsgoods;
            return 1001;
        } else if (type == 2) {
//            评价
            layoutID = R.layout.item_detailcheckmore;
            return 1002;
        }

        return super.getItemViewType(position);
    }

    @Override
    public int getLayoutId() {
        return layoutID;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        int type = getItemViewType(position);
        if (type == 1001) {
            final LinearLayout item = holder.getView(R.id.item);
            getMeasureHeight(item,type);
        }
        if (type == 1002) {
            final LinearLayout item = holder.getView(R.id.checkitem);
            getMeasureHeight(item,type);
            RelativeLayout checkmore=holder.getView(R.id.rl_checkmore);
            checkmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, MorecommentsActivity.class);
                    context.startActivity(intent);
                }
            });

        }

    }

    /**
     * 获取每个item的高度
     * @param view  item的跟布局
     * @param type  用于判断是那个item的高度
     */
    public void getMeasureHeight(final View view, final int type) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (listener!=null){
                    if(type==1002){
                        if(height!=0){
                            height+=view.getHeight();
                            listener.setOnItemHeightListener(height,type);
                        }else{
                            height=view.getHeight();
                        }
                    }else{
                        listener.setOnItemHeightListener(view.getHeight(),type);
                    }

                }
            }
        });
    }
    public interface OnItemHeightListener{
        void setOnItemHeightListener(int height, int type);
    }
    private OnItemHeightListener listener;
    public void setListener(OnItemHeightListener listener){
        this.listener=listener;
    }
}
