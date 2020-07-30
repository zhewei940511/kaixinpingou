package com.laojiashop.laojia.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.laojiashop.laojia.R;

import butterknife.ButterKnife;

public abstract  class BaseListOtherActivity extends AppCompatActivity {
    protected ViewGroup rootView;

    protected ViewGroup contentView;
    protected BaseListOtherActivity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        setContentView(R.layout.activity_base);
        contentView = findViewById(R.id.baseContentView);
        LayoutInflater.from(mActivity).inflate(getContentView(), contentView);
        rootView = findViewById(R.id.rootView);
        ButterKnife.bind(mActivity);
    }
    @LayoutRes
    protected abstract int getContentView();
}
