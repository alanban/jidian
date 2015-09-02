package com.qidianliterature.jidian.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Liang Guan Quan on 2015/8/8.
 */
public class StaticViewPager extends ViewPager{
    public StaticViewPager(Context context) {
        this(context, null);
    }
    public StaticViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        return false;
    }
}
