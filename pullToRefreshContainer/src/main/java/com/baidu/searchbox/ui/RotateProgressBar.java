package com.baidu.searchbox.ui;

import java.lang.reflect.Field;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Indeterminate ProgressBar
 * 由于android1.5 没有 AnimattedRotateDrawable 类，不能实现每次递增多少度的旋转，
 * 无法使用 animated-rotate 设置图片属性。
 * 
 * 增加该类，兼容android各个版本.
 */
public class RotateProgressBar extends ProgressBar {
    /**
     * 最大level，和 android framework RotateDrawable 中的 MAX_LEVEL对应. 
     */
    protected static final int MAX_LEVEL = 10000;
    
    /**
     * 动态更新时间间隔.
     */
    protected static final int ANIMATION_RESOLUTION = 200;
    
    /**
     * 每次递增角度.
     */
    protected static final int INCREMENT = 30;
    
    /**
     * 最大角度.
     */
    protected static final int MAX_DEGREE = 360;
    
    /**
     * 当前角度.
     */
    protected int mDegree = 0;
    
    /**
     * 进度drawable.
     */
    protected Drawable mCurrentDrawable;
    
    /**
     * the last draw time.
     */
    protected long mLastDrawTime;
    
    /**
     * Frame Duration.
     */
    protected int mFrameDuration;
    
    /**
     * constructor.
     * @param context context
     * @param attrs attrs
     * @param defStyle defStyle
     */
    public RotateProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * constructor.
     * @param context context
     * @param attrs attrs
     */
    public RotateProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    /**
     * constructor.
     * @param context context
     */
    public RotateProgressBar(Context context) {
        super(context);
        init();
    }
    
    /**
     * init
     */
    private void init() {
        mFrameDuration = ANIMATION_RESOLUTION;
        
        try {
            Field field = ProgressBar.class.getDeclaredField("mDuration");
            
            if (field != null) {
                field.setAccessible(true);
                int duration = field.getInt(this);
                mFrameDuration = (int) (((float) duration) / (MAX_DEGREE / INCREMENT) + 0.5f);  // SUPPRESS CHECKSTYLE
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        Drawable drawable = mCurrentDrawable;
        
        if (drawable != null) {
            drawable.draw(canvas);
            
            // 延时重绘
            long delay = SystemClock.uptimeMillis() - mLastDrawTime;
            if (delay < mFrameDuration) {
                
                postInvalidateDelayed(mFrameDuration - delay);
                
            } else {
                mLastDrawTime = SystemClock.uptimeMillis();
                
                mDegree += INCREMENT;
                if (mDegree >= MAX_DEGREE) {
                    mDegree = 0;
                }
                int level = (int) (MAX_LEVEL * mDegree / (float) MAX_DEGREE);
                
                drawable.setLevel(level);
                
                postInvalidateDelayed(mFrameDuration);
            }
        }
    }


    @Override
    public synchronized void setIndeterminateDrawable(Drawable d) {
        super.setIndeterminateDrawable(d);
        
        if (isIndeterminate()) {
            mCurrentDrawable = d;
        }
    }

    @Override
    public synchronized void setIndeterminate(boolean indeterminate) {
        super.setIndeterminate(indeterminate);
        
        if (indeterminate) {
            mCurrentDrawable = getIndeterminateDrawable();
        }
    }

    @Override
    public void invalidateDrawable(Drawable dr) {
        //super.invalidateDrawable(dr);
        // do nothing
    }
    
    
}
