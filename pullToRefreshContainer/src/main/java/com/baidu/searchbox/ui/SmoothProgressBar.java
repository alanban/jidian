package com.baidu.searchbox.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;


public class SmoothProgressBar extends RotateProgressBar {
    
    private static final int FRAME_RATE;
    
    static {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            FRAME_RATE = 36;  // SUPPRESS CHECKSTYLE
        } else {
            FRAME_RATE = 25;  // SUPPRESS CHECKSTYLE
        }
    }
    
    /**
     * constructor.
     * @param context context
     * @param attrs attrs
     * @param defStyle defStyle
     */
    public SmoothProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * constructor.
     * @param context context
     * @param attrs attrs
     */
    public SmoothProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    /**
     * constructor.
     * @param context context
     */
    public SmoothProgressBar(Context context) {
        super(context);
        init();
    }
    
    /**
     * init
     */
    private void init() {
        mFrameDuration = (int) (((((float) mFrameDuration) * (MAX_DEGREE / INCREMENT) / FRAME_RATE) / 2.0f) + 0.5f);  // SUPPRESS CHECKSTYLE
    }
    
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        Drawable drawable = mCurrentDrawable;
        
        if (drawable != null) {
            drawable.draw(canvas);
            
            long delay = SystemClock.uptimeMillis() - mLastDrawTime;
//            if (delay < mFrameDuration) {
//                
////                postInvalidateDelayed(mFrameDuration - delay);
//                
//            } else {
            if (delay >= mFrameDuration) {
                mLastDrawTime = SystemClock.uptimeMillis();
                
                mDegree += MAX_LEVEL / FRAME_RATE;
                if (mDegree >= MAX_LEVEL) {
                    mDegree -= MAX_LEVEL;
                }
                
                drawable.setLevel(mDegree);
                
                postInvalidateDelayed(mFrameDuration);
            }
        }
    }
}
