package com.qidianliterature.jidian.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;

/**
 * Created by Liang Guan Quan on 2015/8/20.
 */
public class AppHelper {

    public static <T> T $(View root,int viewId){
        return (T) root.findViewById(viewId);
    }

    public static void ShowToast(Context context,String text) {
        Toast mToast = null;
        if (!TextUtils.isEmpty(text)) {
            if (mToast == null) {
                mToast = Toast.makeText(context, text,Toast.LENGTH_SHORT);
            } else {
                mToast.setText(text);
            }
            mToast.show();
        }
    }

    public static int getVersionCode(){
        return Build.VERSION.SDK_INT;
    }

    public static void startActivityWithNoArgs(Activity fromActivity,Class<?> toActivity){
        Intent i = new Intent(fromActivity,toActivity);
        fromActivity.startActivity(i);
    }

    public static void initViewPagerSpeed(ViewPager mViewPager,int scrollDuration) {
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
}
