package com.qidianliterature.jidian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qidianliterature.jidian.DB.JavaBean.Article;
import com.qidianliterature.jidian.DB.JavaBean.User;
import com.qidianliterature.jidian.main.R;
import com.qidianliterature.jidian.view.RoundImageView;

import java.util.ArrayList;

/**
 * 项目名称：jidian
 * 类描述：
 * 创建人：BS
 * 创建时间：2015/8/22 0022 16:19
 * 修改人：
 * 修改时间：2015/8/22 0022 16:19
 * 修改备注：                            此Adpater未写操作数据库函数
 */
public class AdapterForArticleActivity extends BaseAdapter implements View.OnClickListener{
    Context mContext;
    int Article_IDInDB;
    LayoutInflater mInflater;
    OnItemClick onItemClick;
    Article[] article;//数据库中文章对象
    public AdapterForArticleActivity(Context context,int article_IDInDB) {
        this.mContext=context;
        this.Article_IDInDB=article_IDInDB;
        this.mInflater=LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return 15;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view==null){
            holder=new Holder();
            view=mInflater.inflate(R.layout.article_adapter_layout_choose,null);
            view.setOnClickListener(this);
            holder.mianLayout = view.findViewById(R.id.mainlayout);
            holder.leftLayout = view.findViewById(R.id.leftLayout);
            holder.rightLayout = view.findViewById(R.id.rightLayout);
            holder.roundImageViewLeft=(RoundImageView)view.findViewById(R.id.ArticleListView_WriterPic_left);
            holder.textView_WriterNameLeft=(TextView)view.findViewById(R.id.ArticleListView_WriterName);
            holder.roundImageViewRight=(RoundImageView)view.findViewById(R.id.ArticleListView_WriterPic_right);
            holder.textView_WriterNameRight=(TextView)view.findViewById(R.id.ArticleListView_WriterName1);
            holder.textView_ArticleContent=(TextView)view.findViewById(R.id.ArticleListView_ArticleContent);

            view.setTag(holder);

        }else{
            holder=(Holder)view.getTag();
        }

        if(i%2==0){
            //偶数  ->左侧显示头像及名称
            holder.id=i;
            holder.mianLayout.setBackgroundResource(R.color.Article_ListView_1);
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.rightLayout.setVisibility(View.GONE);

            holder.roundImageViewLeft.setImageResource(R.drawable.writerpic);
            holder.textView_WriterNameLeft.setText("李健");
        }else {
            //奇数  ->右侧显示头像及名称
            holder.id=i;
            holder.mianLayout.setBackgroundResource(R.color.Article_ListView_2);
            holder.leftLayout.setVisibility(View.GONE);
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.roundImageViewRight.setImageResource(R.drawable.writerpic);
            holder.textView_WriterNameRight.setText("李健");

        }


        holder.textView_ArticleContent.setText("第"+(i+1)+"章\n"+"奇点创作");
        return view;
    }

    @Override
    public void onClick(View view) {
        Holder holder=(Holder)view.getTag();
        this.onItemClick.OnSectionItemClick(holder.id,Article_IDInDB,holder.textView_ArticleContent.getText());//参数为 pisition和该文章在本地数据库中号
    }


    public  class Holder {
        int id;
        View mianLayout,leftLayout,rightLayout;
        RoundImageView roundImageViewLeft;
        TextView textView_WriterNameLeft;
        RoundImageView roundImageViewRight;
        TextView textView_WriterNameRight;
        TextView textView_ArticleContent;
    }
    public void setOnSectionItemClick(OnItemClick onItemClick){
        if(onItemClick!=null){
            this.onItemClick=onItemClick;
        }else{
            throw new NullPointerException(" Can not be NULL!");
        }
    }

    public  interface OnItemClick{
        public void OnSectionItemClick(int position,int Article_IDInDB,CharSequence SectionContent);//,Article article,ArrayList<User> User
    }

}
