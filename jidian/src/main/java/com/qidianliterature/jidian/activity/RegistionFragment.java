package com.qidianliterature.jidian.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qidianliterature.jidian.main.R;

/**
 * Created by Liang Guan Quan on 2015/8/8.
 */
public class RegistionFragment extends Fragment{

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.regist_fragment_layout,null);
        return root;
    }

}
