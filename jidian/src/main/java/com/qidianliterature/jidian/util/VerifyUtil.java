package com.qidianliterature.jidian.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class VerifyUtil {
	
	/**
	 * 验证需要涉及到的View
	 */
	private List<View> views = new ArrayList<View>();
	
	/**
	 * 一般需要验证的View类型
	 */
	private List<View> defViewType;

	private static VerifyUtil mVerifyUtil = null;
	
	private VerifyMessageListener mListener;
	
	private String result;
	
	private VerifyUtil(Context mContext){
		init(mContext);
	}
	
	public static VerifyUtil getInstance(Context mContext){
		if(mVerifyUtil == null){
			mVerifyUtil = new VerifyUtil(mContext);
		}
		return mVerifyUtil;
	}
	
	private void init(Context mContext){
		
		defViewType = new ArrayList<View>();
		defViewType.add(new ImageView(mContext));
		defViewType.add(new RadioButton(mContext));
		defViewType.add(new CheckBox(mContext));
		defViewType.add(new TextView(mContext));
		
	}
	
	/**
	 * 
	 * @param params The views you wanna verify if it's content is null(<b> just null </b>)
	 * @return
	 */
	public boolean startVerify(View...params){
		for (int i = 0; i < params.length; i++) {
			
			if(params[i] instanceof EditText){
				EditText tv = (EditText) params[i];
				String txt = tv.getText().toString();
				System.out.println("This is an EditText");
				if(TextUtils.isEmpty(txt)){
					mListener.MessageCallBack(tv.getHint().toString());
					return false;
				}
			}else if(params[i] instanceof TextView){
				TextView tv = (TextView) params[i];
				String hint = tv.getHint().toString();
				String content = tv.getText().toString();
				System.out.println("This is an TextView");
				if(hint == content || TextUtils.isEmpty(content)){
					mListener.MessageCallBack("别忘了"+hint);
					return false;
				}
			}else if(params[i] instanceof CheckBox){
				CheckBox cb = (CheckBox) params[i];
				String hint = cb.getHint().toString();
				System.out.println("This is an CheckBox");
				if(!cb.isChecked()){
					mListener.MessageCallBack(hint);
					return false;
				}
			}
			
		}
		return true;
	}
	
	public boolean addVerify(){
		
		return true;
	}
	
	public interface VerifyMessageListener{
		public void MessageCallBack(String result);
	}

	public void setmListener(VerifyMessageListener mListener) {
		this.mListener = mListener;
	}
	
}
