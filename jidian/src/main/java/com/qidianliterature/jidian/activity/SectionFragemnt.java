package com.qidianliterature.jidian.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qidianliterature.jidian.DB.JavaBean.Section;
import com.qidianliterature.jidian.main.R;

/**
 * 项目名称：jidian
 * 类描述：
 * 创建人：BS
 * 创建时间：2015/8/23 0023 16:38
 * 修改人：
 * 修改时间：2015/8/23 0023 16:38
 * 修改备注：
 */
public class SectionFragemnt extends Fragment {
    View root;
    TextView SectionContent,Sevtion_Title;
    Bundle mbundle;
    CharSequence mContent;
    Section section;

    public SectionFragemnt(Bundle bundle) {
        this.mbundle=bundle;
       this.mContent= mbundle.getCharSequence("SectionContent");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root=       inflater.inflate(R.layout.fragment_section, container, false);
        this.SectionContent=(TextView)root.findViewById(R.id.Section_content);
        this.Sevtion_Title=(TextView)root.findViewById(R.id.Section_Title);
        this.Sevtion_Title.setText(this.section.Section_Title);
//        this.SectionContent.setText(mContent);
        this.SectionContent.setText("奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n奇点创作，创作奇点\n");
        return root;
    }
    public void SendContent(Section section){
        this.section=section;
    }
}
