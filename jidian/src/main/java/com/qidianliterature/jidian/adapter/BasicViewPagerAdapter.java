package com.qidianliterature.jidian.adapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.qidianliterature.jidian.main.R;
import com.qidianliterature.jidian.util.ResourceUtil;
import com.qidianliterature.jidian.util.ViewPagerScroller;

public class BasicViewPagerAdapter extends PagerAdapter implements OnPageChangeListener, Runnable {

    // private ArrayList<String> mdata;
    private ArrayList<Bitmap> mdata;
    private Context mContext;
    private ImageView[] mImageViews;
    private ImageView[] index;
    private LinearLayout mViewPagerContainer;
    private ViewPager mViewPager;
    private int count, imgCount;

    // private Handler mHandler;
    private static int SLEEP_TIME = 5000;
    private int scrollDuration = 1000;
    private ScheduledExecutorService scheduledExecutorService;

    /**
     * @param mImageViews
     *            ImageViews of Banner
     * @param mContext
     */
    public BasicViewPagerAdapter(ViewPager mViewPager, ImageView[] mImageViews,Context mContext) {
        super();
        this.mImageViews = mImageViews;
        this.mContext = mContext;
        this.mViewPager = mViewPager;

        imgCount = mImageViews.length;
        count = imgCount * 100;
        Thread autoScroll = new Thread(this);
        autoScroll.start();

        initViewPagerSpeed();
    }

    public void startAutoScroll() {
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(this, 5, 5,TimeUnit.SECONDS);
    }

    public void stopAutoScroll() {
        if (!scheduledExecutorService.isShutdown()) {
            scheduledExecutorService.shutdown();
        }
    }

    public void setScrollDuration(int scrollDuration) {
        this.scrollDuration = scrollDuration;
    }

    /**
     * This Method will generate a index layout according the count of Pictures
     * for the Banner
     *
     * @param indexContainer
     */
    public void setIndex(LinearLayout indexContainer) {
        mViewPagerContainer = indexContainer;
        index = generateIndex(count / 100);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return count;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        ViewPager vp = (ViewPager) container;
        View iv = mImageViews[position % imgCount];
        if (iv.getParent() == null) {
            vp.addView(iv);
        } else {
            vp.removeView(iv);
            vp.addView(iv);
        }
        return iv;
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPageScrolled(int pos, float offset, int arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageSelected(int pos) {
        // TODO Auto-generated method stub
        changeState(pos % imgCount);
    }

    public ArrayList<Bitmap> getMdata() {
        return mdata;
    }

    private ImageView[] generateIndex(int count) {
        ImageView[] ivs = new ImageView[count];
        int height = ResourceUtil.getDimension(mContext, R.dimen.banner_tips_size);
        int width = height;
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width,height);
        lp.setMargins(0, 0, 10, 5);
        for (int i = 0; i < ivs.length; i++) {
            ImageView iv = new ImageView(mContext);
            iv.setLayoutParams(lp);
            if (i == 0) {
                iv.setImageResource(R.drawable.page_indicator_focused);
            } else {
                iv.setImageResource(R.drawable.page_indicator_unfocused);
            }
            iv.setScaleType(ScaleType.FIT_XY);
            ivs[i] = iv;

            mViewPagerContainer.addView(iv);
        }
        return ivs;
    }

    /**
     *
     * @param cruPos
     *            the selected position
     */
    private void changeState(int cruPos) {
        ImageView iv;
        int myCount = count / 100;
        for (int i = 0; i < myCount; i++) {
            iv = index[i];
            if (i == cruPos) {
                iv.setImageResource(R.drawable.page_indicator_focused);
            } else {
                iv.setImageResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    private void initViewPagerSpeed() {
        try {
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            ViewPagerScroller scroller = new ViewPagerScroller(mViewPager.getContext(), scrollDuration);
            mScroller.set(mViewPager, scroller);
        } catch (NoSuchFieldException e) {

        } catch (IllegalArgumentException e) {

        } catch (IllegalAccessException e) {

        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            System.out.println("Scrolling!!!");
            handler.obtainMessage().sendToTarget();
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // �л���ǰ��ʾ��ͼƬ
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int flag = mViewPager.getCurrentItem();
            mViewPager.setCurrentItem(flag + 1);// �л���ǰ��ʾ��ͼƬ
        };
    };

}
