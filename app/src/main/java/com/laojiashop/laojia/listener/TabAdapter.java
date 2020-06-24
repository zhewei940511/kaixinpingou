package com.laojiashop.laojia.listener;


import com.laojiashop.laojia.view.TabView;

public interface TabAdapter {
    int getCount();

    TabView.TabIcon getIcon(int position);

    TabView.TabTitle getTitle(int position);

    int getBackground(int position);
}
