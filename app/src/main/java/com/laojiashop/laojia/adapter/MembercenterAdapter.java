package com.laojiashop.laojia.adapter;

import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.laojiashop.laojia.R;
import com.laojiashop.laojia.entity.ServiceFeeBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 身份选择适配器
 */
public class MembercenterAdapter extends BaseQuickAdapter<ServiceFeeBean.LevelBean , BaseViewHolder> {
    private Map<Integer, Boolean> map = new HashMap<>();
    private boolean onBind;
    private int checkedPosition = -1;
    public MembercenterAdapter(@Nullable List<ServiceFeeBean.LevelBean> data) {
        super(R.layout.item_membercenter, data);
    }

    //你写的在哪
    @Override
    protected void convert(@NonNull BaseViewHolder helper, ServiceFeeBean.LevelBean item) {
        AppCompatCheckBox checkBox = helper.getView(R.id.cb_balance_fuwufei);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()){
                    map.clear();
                    map.put(helper.getLayoutPosition(),true);
                    checkedPosition=helper.getLayoutPosition();
                    levels.getWays(String.valueOf(item.getLevel()),helper.getLayoutPosition());
                }else {
                    map.remove(helper.getLayoutPosition());
                    if (map.size()==0){
                        checkedPosition=-1;//表示一个都没选择
                        levels.getWays("",helper.getLayoutPosition());
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
        helper.setText(R.id.btn_levelStr,item.getLevelStr());
        helper.setText(R.id.btn_margintxt,String.valueOf("￥"+item.getMargin()));
        helper.setText(R.id.btn_performancetxt,String.valueOf("￥"+item.getPerformance()));
        helper.setText(R.id.btn_cycletxt,String.valueOf(item.getCycle())+"天");
    }
    public interface ItemWays{
        void getWays(String level,int position);
    }
    private ItemWays levels;

    public void setItemPayWays(ItemWays level) {
        this.levels = level;
    }
}
