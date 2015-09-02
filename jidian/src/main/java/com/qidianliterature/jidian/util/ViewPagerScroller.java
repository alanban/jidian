package com.qidianliterature.jidian.util;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class ViewPagerScroller extends Scroller {

	private int mScrollDuration;
	
	/**
	 * @param context the context view which the viewpager is running on
	 * @param mScrollDuration the speed of viewpager sliding speed
	 */
	public ViewPagerScroller(Context context, int mScrollDuration) {
		super(context);
		this.mScrollDuration = mScrollDuration;
	}

	public ViewPagerScroller(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public ViewPagerScroller(Context context, Interpolator interpolator) {
		super(context, interpolator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy) {
		// TODO Auto-generated method stub
		super.startScroll(startX, startY, dx, dy, mScrollDuration);
	}

	@Override
	public void startScroll(int startX, int startY, int dx, int dy, int duration) {
		// TODO Auto-generated method stub
		super.startScroll(startX, startY, dx, dy, mScrollDuration);
	}

}
