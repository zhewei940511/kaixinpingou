package com.laojiashop.laojia.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 自定义滑动
 * 标题栏显示和隐藏
 */
public class GoodsCustomScrollView extends ScrollView {
    private ScrollViewListener scrollViewListener = null;
    public interface ScrollViewListener
    {
        void onScrollChanged(GoodsCustomScrollView scrollView, int x, int y,
                             int oldx, int oldy);

    }
    public GoodsCustomScrollView(Context context) {
        super(context);
    }

    public GoodsCustomScrollView(Context context, AttributeSet attrs,
                              int defStyle) {
        super(context, attrs, defStyle);
    }

    public GoodsCustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
