package com.qidianliterature.jidian.FragmentInActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.qidianliterature.jidian.DB.DBAdapter.DBAdapter;
import com.qidianliterature.jidian.DB.JavaBean.Article;
import com.qidianliterature.jidian.DB.JavaBean.Section;
import com.qidianliterature.jidian.DB.JavaBean.User;
import com.qidianliterature.jidian.main.R;

import java.util.Random;

/**
 * 项目名称：MyApplication
 * 类描述：
 * 创建人：BS
 * 创建时间：2015/8/12 0012 17:53
 * 修改人：
 * 修改时间：2015/8/12 0012 17:53
 * 修改备注：
 */
public class DraftFragment extends Fragment {

            View view;
    Button button,button1;
    DBAdapter dbAdapter;
    Article article;
    User user;
    Section section;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_draft, container, false);
        return view;
    }
}
