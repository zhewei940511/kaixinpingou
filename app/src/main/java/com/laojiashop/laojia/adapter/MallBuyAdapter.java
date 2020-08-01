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
import com.orhanobut.logger.Logger;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import static com.laojiashop.laojia.utils.ToastUtil.showToast;

/**
 * 商品详情规格适配器
 */
public class MallBuyAdapter extends BaseQuickAdapter<GoodsDetailBean.SkuBean.TreeBean, BaseViewHolder> {
//    private List<GoodsDetailBean.SkuBean.ListBean> listBeans;
    private List<GoodsDetailBean.SkuBean.TreeBean> treeBeanList=new ArrayList<>();
    private HashMap<Object, Object> map;
    private ArrayList<String> strings;
    public MallBuyAdapter(int layoutResId, @Nullable List<GoodsDetailBean.SkuBean.TreeBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, GoodsDetailBean.SkuBean.TreeBean item) {
        helper.setText(R.id.tv_specification, item.getK());
        //流式布局
        TagFlowLayout flowLayout = helper.getView(R.id.id_flowlayout);
        final LayoutInflater mInflater = LayoutInflater.from(mContext);
        treeBeanList.add(item);
        //添加的数据
        //final List<GoodsDetailBean.SkuBean.TreeBean.VBean> items = item.getV();
        map = new HashMap<>();
        strings = new ArrayList<>();
        for (int i = 0; i < treeBeanList.size(); i++) {
            strings.add(i, "");
        }
        // vBeans = item.getV();
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
                for (Integer in : selectPosSet) {

//                    int pId = item.getV().get(in).getId();
//                    int id = items.get(in).getId();
//                    map.put(pId, pId + "-" + id);
                    attributeView(in, item);

//                    if (itemClickKind != null) {
//                        itemClickKind.getItemKind(item.getV().get(in).getName());
//                    }
                    // Logger.i("------类型选择学段----" + pId + "-" + id);
                }
//                if (selectPosSet.size() > 0) {
//                    Integer next = selectPosSet.iterator().next();
//                    if (itemClickKind != null) {
//                        itemClickKind.getItemKind(item.getV().get(next).getName());
//                    }
//                }
            }
        });
    }
//
//        private void attributeView(int in, GoodsDetailBean.S kuBean.TreeBean item) {
//        Collection<Object> values = map.values();
//        List<Object> valueList = new ArrayList<Object>(values);
//        for (int i = 0; i < treeBeanList.size(); i++) {
//            if (item.getV().get(i).getId() == treeBeanList.get(i).getV().get(i).getId()) {
//                strings.set(i, item.getV().get(in).getName());
//            }
//        }
//        Logger.i("------111----" + valueList.toString());
//        Logger.i("------选择的名称----" + strings.toString());
//        StringBuilder mystr = new StringBuilder();
//        for (String s : strings) {
//            if (!s.equals("")) {
//                mystr.append(s).append(" ");
//            }
//        }
//        //tv_shop_attr.setText(mystr);
//        Logger.i("------要显示的东西----" + mystr);
//        if (treeBeanList.size() == valueList.size()) {
//            for (GoodsDetailBean.SkuBean.ListBean specPrice : listBeans) {
//                String[] specIdArray = specPrice.getCost_price().split(",");
//                List<String> specIdList = Arrays.asList(specIdArray);
//                Logger.i("------222----" + specIdList.toString());
//                //500-502, 491-493, 503-505, 497-499, 494-495, 506-507]
//                if (specIdList.size() == valueList.size() && specIdList.containsAll(valueList)) {
//                    String price = specPrice.getPrice();
//                   // tv_shop_price.setText(price);
//                    // goInput.setBackgroundResource(R.drawable.default_button_background_selector2);
//                    Logger.i("------最终选择----" + valueList.toString());
//                    Logger.i("------最终id----" + specPrice.getSku());
//                }
//            }
//        }
//    }
    private void attributeView(int in, GoodsDetailBean.SkuBean.TreeBean item) {
        Collection<Object> values = map.values();
        List<Object> valueList = new ArrayList<Object>(values);
        for (int i = 0; i < treeBeanList.size(); i++) {
            if (item.getV().get(i).getId() == treeBeanList.get(i).getV().get(i).getId()) {
                strings.set(i, String.valueOf(item.getV().get(in).getId()));
            }
        }
        Logger.i("------111----" + valueList.toString());
        Logger.i("------选择的名称----" + strings.toString());
        StringBuilder mystr = new StringBuilder();
        for (String s : strings) {
            if (!s.equals("")) {
                mystr.append(s).append("-");
            }
        }
       String selectKind = mystr.substring(0, mystr.length() - 1);
        if (itemClickKind != null) {
            itemClickKind.getItemKind(selectKind);
        }
//        tv_shop_attr.setText(mystr);
        Logger.i("------要显示的东西----" + selectKind);
//    if (treeBeanList.size() == valueList.size()) {
//        for (GoodsDetailBean.SkuBean.ListBean specPrice : listBeans) {
//            String[] specIdArray = specPrice.getCost_price().split(",");
//            List<String> specIdList = Arrays.asList(specIdArray);
//            Logger.i("------222----" + specIdList.toString());
//            //500-502, 491-493, 503-505, 497-499, 494-495, 506-507]
//            if (specIdList.size() == valueList.size() && specIdList.containsAll(valueList)) {
//                String price = specPrice.getPrice();
//                tv_shop_price.setText(price);
//                // goInput.setBackgroundResource(R.drawable.default_button_background_selector2);
//                Logger.i("------最终选择----" + valueList.toString());
//                Logger.i("------最终id----" + specPrice.getSku());
//            }
//        }
//    }
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
