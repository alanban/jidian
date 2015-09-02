package com.qidianliterature.jidian.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.qidianliterature.jidian.main.R;

/**
 * Created by Liang Guan Quan on 2015/8/18.
 */
public class OuterScrollView extends ScrollView{

    /**
     *
     */
    private int scrolledDistance = 0;
    /**
     * �Ƿ�ַ���ֱ���������¼�
     */
    private boolean isDeliverGeasure = true;
    /**
     * ��¼�û����µ�Y����
     */
    private float downY = 0;
    /**
     * ScrollView�е�ͷ���֣�ViewPager֮���
     */
    private View headView;
    /**
     * ScrollView�ĵ�һ������
     */
    private View child;

    private int headHeight;

    private Context mContext;


    public OuterScrollView(Context context) {
        this(context, null);
    }

    public OuterScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    private void init(){

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        headHeight = headView.getLayoutParams().height;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if(this.getChildCount() > 0){
            //�õ�ScrollView�ĵ�һ������
            child = getChildAt(0);
            headView = child.findViewById(R.id.hotpush_pager);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:

                if(!isHeadViewVisible( getScrollY() )){
                    Toast.makeText(mContext, "Visible", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }


    private boolean isHeadViewVisible(float scrolledDistance){
        return scrolledDistance < headHeight ? true:false;
    }

}
