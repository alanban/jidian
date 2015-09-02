package com.qidianliterature.jidian.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.TextView;

import com.qidianliterature.jidian.main.R;


/**
 * Created by Liang Guan Quan on 2015/8/8.
 */
public class LoginFragment extends Fragment{

    private View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.login_fragment_layout,null);

        return root;
    }

}

