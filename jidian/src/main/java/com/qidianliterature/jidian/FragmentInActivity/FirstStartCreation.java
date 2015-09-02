package com.qidianliterature.jidian.FragmentInActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qidianliterature.jidian.main.R;

/**
 * Created by Liang Guan Quan on 2015/8/26.
 */
public class FirstStartCreation extends Fragment {
    private View root;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.start_creation_one_part,null);
        return root;
    }

}
