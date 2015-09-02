package com.qidianliterature.jidian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.qidianliterature.jidian.DB.DBAdapter.DBAdapter;
import com.qidianliterature.jidian.DB.JavaBean.Article;
import com.qidianliterature.jidian.DB.JavaBean.User;
import com.qidianliterature.jidian.main.R;
import com.qidianliterature.jidian.view.RoundImageView;

import java.util.ArrayList;
import java.util.Random;

/**
 * 项目名称：jidian
 * 类描述：
 * 创建人：BS
 * 创建时间：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class AdapterForFocusFragment extends BaseAdapter implements View.OnClickListener{

    LayoutInflater mInflater;
    Context mContext;
    Random random;
    ItemOnclick ItemOnclick;
    DBAdapter dbAdapter;
    Article[] articles;
    int[] FoucusWriterNumber={001};//用户关注人ID集合
    String WriterName;//作者名


    public AdapterForFocusFragment(Context context) {
        this.dbAdapter=new DBAdapter(context);
        this.mContext=context;
        this.random=new Random();
        this.mInflater =LayoutInflater.from(context);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
                    GetDataInDB();
                    FindWriterNameInDB(FoucusWriterNumber[0]);
//                }catch (Exception e){
//                    System.out.println("---------------->>>>>>>>Thread  wrong!!!"+WriterName);
//                }
//            }
//        }).start();

    }
    @Override
    public int getCount() {
        return articles.length;//
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
        if(view==null){
            view=mInflater.inflate(R.layout.foucus_adapter_layout,null);
            view.setOnClickListener(this);
            holder=new Holder();
            holder.Article_Name=(TextView)view.findViewById(R.id.Article_name);
            holder.Article_Writer=(TextView)view.findViewById(R.id.Article_writer);
            holder.Writer_Pic=(RoundImageView)view.findViewById(R.id.Writer_pic);
            holder.Writer_Pic.setType(R.styleable.RoundImageView_borderRadius);
            holder.Article_Surface=(ImageView)view.findViewById(R.id.Article_surface);
            holder.Button_Follow_Writer_Number=(Button)view.findViewById(R.id.button_follow_writer_number);
            holder.Article_Content=(TextView)view.findViewById(R.id.Article_content);
            view.setTag(holder);
        }else{
            holder=(Holder)view.getTag();
        }
        if(articles.length>0) {//如果为空，
            holder.id = i;
            holder.Article_IDInDB = (articles[i].Article_ID);
            holder.Article_Name.setText(articles[i].Title);
            holder.Writer_Pic.setImageResource(R.drawable.writerpic);//涉及到 图片的绝对地址转换等，先用Drawble里的图片用着
            holder.Article_Surface.setBackgroundResource(R.drawable.surface);//同上
//            holder.Article_Writer.setText("更新来自 "+WriterName);
            holder.Article_Writer.setText("更新来自"+FindWriterNameInDB(articles[i].getUser_ID()));
            holder.Button_Follow_Writer_Number.setText("" + random.nextInt(100));
            holder.Article_Content.setText(""+articles[i].getArticle_Content());
        }
        return view;
    }

    @Override
    public void onClick(View view) {
        Holder nHolder=(Holder)view.getTag();
        this.ItemOnclick.OnArticleItemClick(nHolder.id,nHolder.Article_IDInDB);

    }
    public void setArticleItemClick(ItemOnclick itemOnclick){
        if(itemOnclick!=null){
            this.ItemOnclick=itemOnclick;
        }else{
            throw new NullPointerException(" Can not be NULL!");
        }
    }

    public  class Holder{
        int id ;
        int Article_IDInDB;
        TextView Article_Name;
        TextView Article_Writer;
        RoundImageView Writer_Pic;
        ImageView Article_Surface;
        Button Button_Follow_Writer_Number;
        TextView Article_Content;
    }
   public interface ItemOnclick{
        public void OnArticleItemClick(int position, int Article_IDInDB);
    }
    /***
     * 随机创建新的文章
     */
    public void NewAnArticle(){

    }
    public void LoadMoreArticle(){

    }
    public void GetDataInDB(){
        //查询数据库中所有的Article

                //查询数据库中 所关注者的文章
        dbAdapter.OpenDB();
//        articles=new Article[dbAdapter.QueryAllArticle().length];                   //这个地方有逻辑问题 查询多个关注者 如何赋值给articles
//        articles=dbAdapter.QueryArticleByUserID(FoucusWriterNumber[0]);
        articles=dbAdapter.QueryAllArticle();
        if(articles.length>0){
            System.out.println("---------------->>>>>>>>GETDta  success!!!"+articles.length);
        }else{
            System.out.println("---------------->>>>>>>>GETDta  wrong!!!"+articles.length);
        }
        dbAdapter.CloseDB();
    }

    public String FindWriterNameInDB(final int User_id){//查询用户昵称

        dbAdapter.OpenDB();
//        User[] users
        ArrayList<User> userslist= dbAdapter.QueryUserByID(User_id);
        User user=userslist.get(0);
        WriterName=user.User_NickNAME;
        System.out.println("---------------->>>>>>>>GET UserName  success!!!"+WriterName);
        dbAdapter.CloseDB();
        return WriterName;
    }











}
