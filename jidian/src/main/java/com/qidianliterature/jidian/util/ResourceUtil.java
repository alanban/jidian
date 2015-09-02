package com.qidianliterature.jidian.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

public class ResourceUtil {
	
	public static int getColor(Context mContext,int id){
		return mContext.getResources().getColor(id);
	}
	
	public static int getDimension(Context mContext,int id){
		return (int) mContext.getResources().getDimension(id);
	}
	
	public static Drawable getDrawable(Context mContext,int id){
		
		return mContext.getResources().getDrawable(id);
	}
	
	public static String getString(Context mContext,int id){
		return mContext.getResources().getString(id);
	}
}
