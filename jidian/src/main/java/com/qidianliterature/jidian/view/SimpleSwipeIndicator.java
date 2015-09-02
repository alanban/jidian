package com.qidianliterature.jidian.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qidianliterature.jidian.main.R;
import com.qidianliterature.jidian.util.ResourceUtil;
import com.qidianliterature.jidian.util.StringUtil;


public class SimpleSwipeIndicator extends LinearLayout implements View.OnClickListener , ViewPager.OnPageChangeListener{
	
	private String[] titleStrs = StringUtil.INDEX_SWIPE_TITLE;

	private TextView[] mTextViews;
	private Paint mPaint;
	/**
	 * 指示条的颜色 默认为白色
	 */
	private int indicatorColor;
	/**
	 * 文字的颜色 默认为白色
	 */
	private int clickedTtextColor;
	/**
	 * 文字的颜色 默认为白色
	 */
	private int unClickedTtextColor;

	private Context mContext;
	private ViewPager mViewPager;
	
	private static int tabCount;
	private static final float tabWidthRadio = 1/2f;
	private static float tabLineWidth;
	private static float tabItemWidth;
	private static float tabLineHeight;
	private static float tabLineOffset = 0;
	
	public SimpleSwipeIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		mContext = context;

		initAttr(attrs);
		init();
	}

	public SimpleSwipeIndicator(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * you must call this method to draw TextViews and The Lowerline
	 * 
	 * @param titleStrs
	 */
	public void setTitleStrs(String[] titleStrs) {
		this.titleStrs = titleStrs;
//		generateText(titleStrs.length);
	}
	
	/**
	 * 默认第一项选中
	 * @param mViewPager
	 */
	public void attachViewPager(ViewPager mViewPager){
		if(mViewPager != null) {
			mViewPager.setOnPageChangeListener(this);
			this.mViewPager = mViewPager;
			mViewPager.setCurrentItem(0);
		}else{
			throw new NullPointerException("The Specific ViewPager can not be NULL!!");
		}
	}

	private void initAttr(AttributeSet attrs){
		TypedArray a = mContext.obtainStyledAttributes(attrs,R.styleable.SimpleSwipeIndicator);
		int count = a.length();
		for (int i = 0; i < count; i++) {
			int attr = a.getIndex(i);
			switch (attr){
				case R.styleable.SimpleSwipeIndicator_text_color:
					clickedTtextColor = a.getColor(i , ResourceUtil.getColor(mContext,R.color.white));
					break;
				case R.styleable.SimpleSwipeIndicator_indicator_color:
					indicatorColor = a.getColor(i , ResourceUtil.getColor(mContext,R.color.white));
					break;
				case R.styleable.SimpleSwipeIndicator_text_unclicked_color:
					unClickedTtextColor = a.getColor(i , ResourceUtil.getColor(mContext,R.color.white));
					break;
			}
		}
	}

	private void init(){
		/* initialize painter */
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setColor(indicatorColor);

		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		this.setOrientation(LinearLayout.HORIZONTAL);

		tabCount = titleStrs.length;
		mTextViews = new TextView[tabCount];
		
		generateText(tabCount);
		  
	}
	
	private void generateText(int itemCount){
		LayoutParams llp = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT,1);
		int yMargin = 4;
		llp.setMargins(0, yMargin, 0, yMargin);
		for (int i = 0; i < itemCount; i++) {
			TextView tv = new TextView(mContext);
			tv.setText(titleStrs[i]);
			tv.setGravity(Gravity.CENTER);
			tv.setOnClickListener(this);
			tv.setTextColor(unClickedTtextColor);
			mTextViews[i] = tv;
			this.addView(tv,llp);
		}

		resetStyle(0);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		
		tabItemWidth = getWidth()/tabCount;
		tabLineWidth = tabItemWidth * tabWidthRadio;
		tabLineHeight = (float) (getHeight() / Math.pow(tabWidthRadio, 2));
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		
		canvas.save();
		canvas.translate(tabLineWidth / 2 + tabLineOffset, getHeight() - 5);
		canvas.drawRect(new RectF(0, 0, tabLineWidth, 5), mPaint);
		canvas.restore();
		
		super.dispatchDraw(canvas);
	}
	
	private void scrollLowerLine(int pos , float offset){
		tabLineOffset = tabItemWidth * (pos+offset);
		Log.e("from scrollLowerLine", "posistion is " + pos + "offset is " + offset + "and offset is " + tabLineOffset);
		invalidate();
	}


	public void resetStyle(int which){
		for (int i = 0; i < mTextViews.length; i++) {
			TextView tv = mTextViews[i];
			if(which == i){
				tv.setTextColor(clickedTtextColor);
			}else{
				tv.setTextColor(unClickedTtextColor);
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		for (int i = 0; i < mTextViews.length; i++) {
			if(v == mTextViews[i]){
				mViewPager.setCurrentItem(i);
				resetStyle(i);
			}
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
		scrollLowerLine(position,positionOffset);
	}

	@Override
	public void onPageSelected(int position) {
		resetStyle(position);
	}

	@Override
	public void onPageScrollStateChanged(int state) {

	}
}
