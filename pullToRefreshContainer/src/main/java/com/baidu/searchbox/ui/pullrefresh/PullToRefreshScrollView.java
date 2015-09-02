package com.baidu.searchbox.ui.pullrefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

/**
 * Created by Liang Guan Quan on 2015/8/18.
 */
public class PullToRefreshScrollView extends PullToRefreshBase<ScrollView>{

    private ScrollView scrollView;

    public PullToRefreshScrollView(Context context) {
        this(context, null);
    }

    public PullToRefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected ScrollView createRefreshableView(Context context, AttributeSet attrs) {
        ScrollView scrollView = new ScrollView(context);
        setRefreshableView(scrollView);

        return scrollView;
    }

    protected void setRefreshableView(ScrollView refreshableView) {
        scrollView = refreshableView;
    }

    @Override
    protected boolean isReadyForPullDown() {
        return false;
    }

    @Override
    protected boolean isReadyForPullUp() {
        return false;
    }
}
