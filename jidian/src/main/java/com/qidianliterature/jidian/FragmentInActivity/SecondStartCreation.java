package com.qidianliterature.jidian.FragmentInActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.qidianliterature.jidian.main.R;

/**
 * Created by Liang Guan Quan on 2015/8/26.
 */
public class SecondStartCreation extends Fragment {

    private View root;
    private Context context;
    private EditText titleName;
    private EditText selfComment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.start_creation_two_part,null);
        context = root.getContext();
        init();
        return root;
    }

    private void init(){
        titleName = (EditText) root.findViewById(R.id.title_in_two_part);
        selfComment = (EditText) root.findViewById(R.id.self_comment_in_creation_part);
    }

    public TextView getTitleView(){
        return titleName;
    }

    public TextView getSelfCommentView(){
        return selfComment;
    }

    public String getTitle(){
        return titleName.getText().toString();
    }

    public String getSelfComment(){
        return selfComment.getText().toString();
    }

}
