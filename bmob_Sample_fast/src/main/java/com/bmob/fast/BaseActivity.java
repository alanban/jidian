package com.bmob.fast;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import cn.bmob.v3.Bmob;

/**
 * ����
 * @ClassName: BaseActivity
 * @Description: TODO
 * @author smile
 * @date 2014-5-20 ����9:55:34
 */
public class BaseActivity extends Activity {

	private String Bmob_AppId = "bf3fe974a31df1f3dbd8a20fcb34bb70";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bmob.initialize(this, Bmob_AppId);
	}

	Toast mToast;

	public void ShowToast(String text) {
		if (!TextUtils.isEmpty(text)) {
			if (mToast == null) {
				mToast = Toast.makeText(getApplicationContext(), text,
						Toast.LENGTH_SHORT);
			} else {
				mToast.setText(text);
			}
			mToast.show();
		}
	}
}
