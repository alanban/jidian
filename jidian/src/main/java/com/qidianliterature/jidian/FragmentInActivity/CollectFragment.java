package com.qidianliterature.jidian.FragmentInActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qidianliterature.jidian.main.R;

/**
 * 项目名称：MyApplication
 * 类描述：
 * 创建人：BS
 * 创建时间：2015/8/12 0012 17:52
 * 修改人：
 * 修改时间：2015/8/12 0012 17:52
 * 修改备注：
 */
public class CollectFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collect, container, false);

    }
}
