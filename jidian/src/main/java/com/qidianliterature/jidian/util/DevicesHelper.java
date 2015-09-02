package com.qidianliterature.jidian.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class DevicesHelper {

    public static int[] getScreenSize(Context context){
        int[] a = new int[2];
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        a[0] = dm.heightPixels;
        a[1] = dm.widthPixels;

        return a ;
    }
}
