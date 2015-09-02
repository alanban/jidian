package com.qidianliterature.jidian.util;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * Created by Liang Guan Quan on 2015/8/26.
 */
public class AnimationUtil {

    public static final int DEFAULT_TIME = 1000;

    public static final int FULL_TO_ZERO = 0x10;
    public static final int ZERO_TO_FULL = 0x11;

    /**
     * 开始一个完整(将某项属性从0.0 - 1.0的过程)的缩放动画效果
     * @param type AnimationUtil.FULL_TO_ZERO or AnimationUtil.ZERO_TO_FULL
     * @param view
     * @param duration
     */
    public static void startCompleteScaleAni(int type ,View view,int duration){
        if(type == ZERO_TO_FULL) {
            ScaleAnimation sa = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            sa.setDuration(1000);
            view.startAnimation(sa);
        }else if( type == FULL_TO_ZERO ){
            ScaleAnimation sa = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            sa.setDuration(1000);
            view.startAnimation(sa);
        }
    }

    /**
     * 开始一个完整(将某项属性从0.0 - 1.0的过程)的透明动画效果
     * @param type AnimationUtil.FULL_TO_ZERO or AnimationUtil.ZERO_TO_FULL
     * @param view
     * @param duration
     */

    public static void startCompleteAlphaAni(int type , View view,int duration,Animation.AnimationListener animationListener){
        if( type == ZERO_TO_FULL){
            AlphaAnimation aa = new AlphaAnimation(0.0f,1.0f);
            aa.setDuration(1000);
            if( animationListener != null){
                aa.setAnimationListener(animationListener);
            }
            view.startAnimation(aa);
        }else if( type == FULL_TO_ZERO ){
            AlphaAnimation aa = new AlphaAnimation(1.0f,0.0f);
            if( animationListener != null){
                aa.setAnimationListener(animationListener);
            }
            aa.setDuration(1000);
            view.startAnimation(aa);
        }
    }



}
