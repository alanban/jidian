package com.qidianliterature.jidian.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.qidianliterature.jidian.main.R;

/**
 * 可设置一个可拖动的View的RelativeLayout
 * Created by Liang Guan Quan on 2015/8/23.
 */
public class Dragger extends RelativeLayout {

    private ViewDragHelper mDragger;
    private View mDraggerView;
    private Context context;
    private PopupWindow startInsipration;
    /**
     * 判断控件是否被拖动过，一次性
     */
    private boolean isDragged = false;

    private int mTouchSlop;
    private float mLastY, mLastX;
    private int windowHeight,popWindowHeight,popWindowWidth;
    private DraggerClickListener draggerClickListener;
    /*  定义 Dragger View 的方向常量 */

    /**
     * dragger view的默认值 dragger view默认向下展示
     */
    private static final int NORMAL_DIR =  0x2<<1;
    private static final int TOP_RIGHT = 0x3;
    private static final int TOP_LEFT = 0x4;

    private static final int BOTTOM = 0x5;
    private static final int BOTTOM_LEFT = 0x6;
    private static final int BOTTOM_RIGHT = 0x7;

    private static final int ON_LEFT = 0x8;
    private static final int ON_RIGHT = 0x9;

    private enum DIRECTION{
        NORMAL_DIR,TOP_RIGHT,TOP_LEFT,BOTTOM,BOTTOM_LEFT,BOTTOM_RIGHT,ON_LEFT,ON_RIGHT
    }
    /*  定义 Dragger View 的方向常量 */

    public Dragger(Context context) {
        this(context, null);
    }

    public Dragger(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        windowHeight = getScreenHeight();

        initDragger();
        initPopWindow();
    }



    private void initDragger() {
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                final int leftBound = getPaddingLeft();
                final int rightBound = getWidth() - mDraggerView.getWidth();
                final int newLeft = Math.min(Math.max(left, leftBound), rightBound);
                return newLeft;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                final int topBound = getPaddingTop();
                final int bottomBound = getHeight() - mDraggerView.getHeight();
                final int newTop = Math.min(Math.max(top, topBound), bottomBound);
                return newTop;
            }

            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == mDraggerView;
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    public View getmDraggerView() {
        return mDraggerView;
    }

    public void setmDraggerView(View v, LayoutParams rlp) {
        if (v != null) {
            this.mDraggerView = v;
            this.addView(mDraggerView, rlp);
        } else {
            throw new NullPointerException("customerize  dragger view can not be null!!");
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        final int action = MotionEventCompat.getActionMasked(event);
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
            mDragger.cancel();
            return false;
        }
        return mDragger.shouldInterceptTouchEvent(event);
    }

    /**
     * 根据像素坐标，判断指定的View是否被拖拽
     *
     * @param view
     * @param x
     * @param y
     * @return
     */
    private boolean isViewHit(View view, int x, int y) {
        int[] viewLocation = new int[2];
        view.getLocationOnScreen(viewLocation);

        int[] screeLocation = new int[2];
        //得到去除顶部导航栏以外的坐标中心点
        this.getLocationOnScreen(screeLocation);

        int screenX = screeLocation[0] + x;
        int screenY = screeLocation[1] + y;

        return screenX >= viewLocation[0] && screenX < viewLocation[0] + view.getWidth() &&
                screenY >= viewLocation[1] && screenY < viewLocation[1] + view.getHeight();
    }

    private int getScreenHeight(){
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        return dm.heightPixels;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        mDragger.processTouchEvent(ev);
        final int action = ev.getAction();
        final float x = ev.getX();
        final float y = ev.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                mLastX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                float dy = y - mLastY;
                float dx = x - mLastX;
                /**
                 * 判断用户是否有拖动DragView
                 * 因为用户可以横向拖动，也可以纵向拖动，所以 dy 与 dx 是或的关系
                 */
                if (!isDragged && (Math.abs(dy) > mTouchSlop || Math.abs(dx) > mTouchSlop)) {
                    isDragged = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                //判断用户是否是点击了DragView
                if ( !isDragged && isViewHit(mDraggerView, (int) x, (int) y)) {
//                    whereShouldShow(mDraggerView , (int) x, (int) y);
//                    whereIAm(mDraggerView);
//                    Toast.makeText(context, "Bingo!", Toast.LENGTH_SHORT).show();
                    draggerClickListener.doAction();
                }else{

                }
                isDragged = false;
                break;

        }
        return isViewHit(mDraggerView, (int) x, (int) y);
    }

    /**
     * 判断PopWindow应该往上还是下去show
     *
     * @param cruX
     * @param cruY
     * @return true -- 应该向下
     *          false -- 应该向上
     */
    private boolean whereShouldShow(View view , int cruX, int cruY){
        int[] viewLocation = new int[2];
        view.getLocationOnScreen(viewLocation);

        int[] screeLocation = new int[2];
        //得到去除顶部导航栏以外的坐标中心点
        this.getLocationOnScreen(screeLocation);

        //View所在屏幕的像素X坐标
        int screenX = screeLocation[0]+viewLocation[0];
        //View所在屏幕的像素Y坐标
        int screenY = screeLocation[1]+viewLocation[1];
        if( windowHeight - screenY >= popWindowHeight ){
            showPopWindow(mDraggerView);
        }else{
            showBeyond(mDraggerView);
        }
        return true;
    }

    /**
     * 返回 DIRECTION 类型的 方位信息
     * @param view
     * @return
     */
    private DIRECTION whereIAm(View view){
        int[] viewLocation = new int[2];
        view.getLocationOnScreen(viewLocation);

        int[] screeLocation = new int[2];
        //得到去除顶部导航栏以外的坐标中心点
        this.getLocationOnScreen(screeLocation);

        return DIRECTION.NORMAL_DIR;
    }

    public void setDraggerClickListener(DraggerClickListener draggerClickListener) {
        this.draggerClickListener = draggerClickListener;
    }

    public interface DraggerClickListener{
        public void doAction();
    }

    private void initPopWindow() {
//        View popupView = LayoutInflater.from(context).inflate(R.layout.layout, null);
//        popupView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
//        popWindowHeight = popupView.getMeasuredHeight();
//        popWindowWidth = popupView.getMeasuredWidth();
    }

    private void showBeyond(View parentView){
//        View popupView = LayoutInflater.from(context).inflate(R.layout.layout, null);
//        View popupView = LayoutInflater.from(context).inflate(R.layout.layout, null);
//        PopupWindow pw = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,  LayoutParams.WRAP_CONTENT, true);
//        pw.setBackgroundDrawable(new BitmapDrawable());
//        popupView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
//        int popupWidth = popupView.getMeasuredWidth();
//        int popupHeight =  popupView.getMeasuredHeight();
//        int[] viewLocation = new int[2];
//        parentView.getLocationOnScreen(viewLocation);
//        pw.showAtLocation(parentView, Gravity.NO_GRAVITY, (viewLocation[0] + parentView.getWidth() / 2) - popupWidth / 2, viewLocation[1] - popupHeight + 50);
    }

    private void showPopWindow(View anchor) {
//        View popupView = LayoutInflater.from(context).inflate(R.layout.layout1, null);
//        PopupWindow pw = new PopupWindow(popupView, LayoutParams.WRAP_CONTENT,  LayoutParams.WRAP_CONTENT, true);
//        pw.setBackgroundDrawable(new BitmapDrawable());
//        popupView.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
//        int popupWidth = popupView.getMeasuredWidth();
//        int[] viewLocation = new int[2];
//        anchor.getLocationOnScreen(viewLocation);
//        pw.showAtLocation(anchor, Gravity.NO_GRAVITY, (viewLocation[0] + anchor.getWidth() / 2) - popupWidth / 2, viewLocation[1] + mDraggerView.getHeight()/2);
    }

}
