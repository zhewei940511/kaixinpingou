package com.laojiashop.laojia.adapter;

import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.PayWayBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayWaysAdapter extends BaseQuickAdapter<PayWayBean.PayWayListBean, BaseViewHolder> {
    private Map<Integer, Boolean> map = new HashMap<>();
    private boolean onBind;
    private int checkedPosition = -1;
    public PayWaysAdapter(int layoutResId, @Nullable List<PayWayBean.PayWayListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, PayWayBean.PayWayListBean item) {
        Glide.with(mContext).load(item.getIcon()).into((ImageView) helper.getView(R.id.iv_balance));
        helper.setText(R.id.tv_pay_ways,item.getPayWayName());
        AppCompatCheckBox checkBox = helper.getView(R.id.cb_balance_selector_pay);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()){
                    map.clear();
                    map.put(helper.getLayoutPosition(),true);
                    checkedPosition=helper.getLayoutPosition();
                    itemPayWays.getPayWays(item.getPayWayName(),helper.getLayoutPosition());
                }else {
                    map.remove(helper.getLayoutPosition());
                    if (map.size()==0){
                        checkedPosition=-1;//表示一个都没选择
                    }
                }
                if (!onBind){
                    notifyDataSetChanged();
                }

            }
        });
        onBind=true;

        if (map!=null&&map.containsKey(helper.getLayoutPosition())){
            checkBox.setChecked(true);
        }else {
            checkBox.setChecked(false);
        }
        onBind=false;
    }
   public interface ItemPayWays{
        void getPayWays(String payWays,int position);
    }
    private ItemPayWays itemPayWays;

    public void setItemPayWays(ItemPayWays itemPayWays) {
        this.itemPayWays = itemPayWays;
    }
}
