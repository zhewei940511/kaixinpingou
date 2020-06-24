package com.laojiashop.laojia.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.lujun.androidtagview.ColorFactory;
import co.lujun.androidtagview.TagContainerLayout;
import co.lujun.androidtagview.TagView;

/**
 * 搜索页面
 */
public class GotoSearchActivity extends BaseActivity {
    @BindView(R.id.img_suresearch)
    ImageView imgSuresearch;
    @BindView(R.id.search_edittext)
    EditText searchEdittext;
    @BindView(R.id.tv_searchcancle)
    TextView tvSearchcancle;
    @BindView(R.id.tga_recentsearch)
    TagContainerLayout tgaRecentsearch;
    @BindView(R.id.tga_topsearch)
    TagContainerLayout tgaTopsearch;
    //模拟测试数据
    private List<String> searchtag;

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_gotosearch);
    }

    @Override
    protected void initViews() {
        //设置历史搜索标签主题
        tgaRecentsearch.setTheme(ColorFactory.NONE);
        tgaRecentsearch.setTagBackgroundColor(Color.parseColor("#F2F2F2"));
        //设置热门搜索标签主题
        tgaTopsearch.setTheme(ColorFactory.NONE);
        tgaTopsearch.setTagBackgroundColor(Color.parseColor("#F2F2F2"));
        //历史搜索点击事件
        tgaRecentsearch.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                searchEdittext.setText(text);
                searchEdittext.setSelection(text.length());
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onSelectedTagDrag(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });
        //热门搜索点击事件
        tgaTopsearch.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                searchEdittext.setText(text);
                searchEdittext.setSelection(text.length());
            }

            @Override
            public void onTagLongClick(int position, String text) {

            }

            @Override
            public void onSelectedTagDrag(int position, String text) {

            }

            @Override
            public void onTagCrossClick(int position) {

            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        searchtag=new ArrayList<>();
        searchtag.add("抽纸");
        searchtag.add("洗衣液");
        searchtag.add("植物保罗洗发水");
        searchtag.add("洗衣液");
        searchtag.add("抽纸");
        tgaRecentsearch.setTags(searchtag);
        tgaTopsearch.setTags(searchtag);
    }

    @Override
    public void getDataFromServer() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.img_suresearch, R.id.tv_searchcancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_suresearch:
                jumpActivity(SearchdetailspageActivity.class);
                break;
            case R.id.tv_searchcancle:
                finish();
                break;
        }
    }
}
