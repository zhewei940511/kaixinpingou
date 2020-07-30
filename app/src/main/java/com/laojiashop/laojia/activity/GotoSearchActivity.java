package com.laojiashop.laojia.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.laojiashop.laojia.R;
import com.laojiashop.laojia.base.BaseActivity;
import com.laojiashop.laojia.base.BasePresenter;
import com.laojiashop.laojia.entity.SearchKeywordsBean;
import com.laojiashop.laojia.http.ApiUtils;
import com.laojiashop.laojia.http.BaseObserver;
import com.laojiashop.laojia.http.HttpRxObservable;

import java.io.IOException;
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
    @BindView(R.id.tga_usersearch)
    TagContainerLayout tgaUsersearch;
    @BindView(R.id.tga_hotsearch)
    TagContainerLayout tgahotosearch;
    //最近搜索数据
    private List<String> searchtaghot=new ArrayList<>();
    //热门搜索数据
    private List<String> searchtaguse=new ArrayList<>();
    //定义关键词
    private String keyword;
    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_gotosearch);
    }

    @Override
    protected void initViews() {
        //设置历史搜索标签主题
        tgaUsersearch.setTheme(ColorFactory.NONE);
        tgaUsersearch.setTagBackgroundColor(Color.parseColor("#F2F2F2"));
        //设置热门搜索标签主题
        tgahotosearch.setTheme(ColorFactory.NONE);
        tgahotosearch.setTagBackgroundColor(Color.parseColor("#F2F2F2"));
        //历史搜索点击事件
        tgaUsersearch.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                searchEdittext.setText(text);
                searchEdittext.setSelection(text.length());
                keyword=text;
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
        tgahotosearch.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(int position, String text) {
                searchEdittext.setText(text);
                searchEdittext.setSelection(text.length());
                keyword=text;
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
    }

    @Override
    public void getDataFromServer() {
        HttpRxObservable.getObservable(ApiUtils.getApiService().goodskeyword()).subscribe(new BaseObserver<SearchKeywordsBean>(mAt) {
            @Override
            public void onHandleSuccess(SearchKeywordsBean searchKeywordsBean) throws IOException {
                List<SearchKeywordsBean.HotWordBean> hotWordBeans=searchKeywordsBean.getHot_word();
                List<SearchKeywordsBean.UserWordBean> userWordBeans=searchKeywordsBean.getUser_word();
                for (int i=0;i<hotWordBeans.size();i++)
                {
                    searchtaghot.add(hotWordBeans.get(i).getWord());

                }
                for (int i=0;i<userWordBeans.size();i++)
                {
                    searchtaguse.add(userWordBeans.get(i).getWord());
                }
                tgahotosearch.setTags(searchtaghot);
                tgaUsersearch.setTags(searchtaguse);
            }

        });
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.img_suresearch, R.id.tv_searchcancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_suresearch:
               // jumpActivity(SearchdetailspageActivity.class);
                Intent intent=new Intent(mAt,SearchdetailspageActivity.class);
                intent.putExtra("keyword",keyword);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_searchcancle:
                finish();
                break;
        }
    }
}
