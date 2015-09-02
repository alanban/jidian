package com.qidianliterature.jidian.FragmentInActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qidianliterature.jidian.main.R;
import com.qidianliterature.jidian.util.ResourceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liang Guan Quan on 2015/8/26.
 */
public class ThirdStartCreation extends Fragment implements View.OnClickListener{

    private View root;
    private Context context;
    private ViewGroup converSelector;

    private List<View> convers = new ArrayList<View>();
    private View preClickedView;

    private TextView title;
    private TextView authorName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.start_creation_three_part,null);
        context = root.getContext();
        init();
        getView();
        return root;
    }

    private void init() {
        converSelector = (ViewGroup) root.findViewById(R.id.conver_container);
        title = (TextView) root.findViewById(R.id.title_in_three_part);
        authorName = (TextView) root.findViewById(R.id.autthoer_name_in_three_part);
        getCover();
    }

    private void getCover(){
        View conver1 = root.findViewById(R.id.cover_1);
        preClickedView = conver1;
        View conver2 = root.findViewById(R.id.cover_2);
        View conver3 = root.findViewById(R.id.cover_3);

        convers.add(conver1);
        convers.add(conver2);
        convers.add(conver3);


        conver1.setOnClickListener(this);
        conver2.setOnClickListener(this);
        conver3.setOnClickListener(this);


    }

    public void setTitle(String title){
        this.title.setText(title);
    }

    public void setAuthorName(String authorName){
        this.authorName.setText(authorName);
    }

    @Override
    public void onClick(View v) {
        int count = convers.size();
        int margin = ResourceUtil.getDimension(context, R.dimen.normal_margin);
        for (int i = 0; i <count; i++) {
            View cover = convers.get(i);
            if( v == cover){
                if( cover != preClickedView){
                    RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) cover.getLayoutParams();
                    rlp.setMargins(margin,margin,margin,margin);
                    cover.setLayoutParams(rlp);

                    RelativeLayout.LayoutParams rlp1 = (RelativeLayout.LayoutParams) preClickedView.getLayoutParams();
                    rlp1.setMargins(0,0,0,0);
                    preClickedView.setLayoutParams(rlp1);

                    preClickedView = cover;

                }
            }
        }
    }

}
