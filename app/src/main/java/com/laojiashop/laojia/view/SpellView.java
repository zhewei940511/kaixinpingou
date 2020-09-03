package com.laojiashop.laojia.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.adapter.SpellgrouprecordsAdapter;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.entity.SpellgrouprecordsBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpellView extends BottomPopupView {
    private RecyclerView recyclerView;
    private List<SpellgrouprecordsBean.JoinListBean> joinListBeans=new ArrayList<>();
    private SpellgrouprecordsAdapter spellgrouprecordsAdapter;
    private  int tid;
    private Context context;
    //适配器

    public SpellView(@NonNull Context context,int tid) {
        super(context);
        this.tid=tid;
        this.context=context;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_bottom_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        //HttpRxObservable.getObservable(ApiUtils.getApiService().)

        //网络请求
        HttpRxObservable.getObservable(ApiUtils.getApiService().getTuanAllData(tid)).subscribe(new BaseObserver<SpellgrouprecordsBean>((BaseActivity) context) {
          @Override
          public void onHandleSuccess(SpellgrouprecordsBean spellgrouprecordsBean) throws IOException {
               joinListBeans=spellgrouprecordsBean.getJoinList();
               recyclerView=findViewById(R.id.recyclerView);
               recyclerView.setLayoutManager(new LinearLayoutManager(context));
               spellgrouprecordsAdapter=new SpellgrouprecordsAdapter(joinListBeans);
               spellgrouprecordsAdapter.setEmptyView(LayoutInflater.from(context).inflate(R.layout.layout_spellempty_view, recyclerView, false));
               recyclerView.setHasFixedSize(true);
               recyclerView.setAdapter(spellgrouprecordsAdapter);
          }
      });
    }
    //完全可见执行
    @Override
    protected void onShow() {
        super.onShow();
    }

    //完全消失执行
    @Override
    protected void onDismiss() {

    }
    @Override
    protected int getMaxHeight() {
//        return XPopupUtils.getWindowHeight(getContext());
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .9f);
    }
}
