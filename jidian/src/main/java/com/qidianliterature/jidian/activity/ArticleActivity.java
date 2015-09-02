package com.qidianliterature.jidian.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.searchbox.ui.pullrefresh.PullToRefreshBase;
import com.baidu.searchbox.ui.pullrefresh.PullToRefreshListView;
import com.qidianliterature.jidian.DB.DBAdapter.DBAdapter;
import com.qidianliterature.jidian.DB.JavaBean.Article;
import com.qidianliterature.jidian.DB.JavaBean.User;
import com.qidianliterature.jidian.adapter.AdapterForArticleActivity;
import com.qidianliterature.jidian.main.R;
import com.qidianliterature.jidian.util.AppHelper;
import com.qidianliterature.jidian.view.RoundImageView;

import java.util.ArrayList;

public class ArticleActivity extends AppCompatActivity implements View.OnClickListener{
    int Articleposition;//被点击Item的position
    int Article_idInDB;// 在数据库中ile ID
    Article[] article;//获取数据库中文章对象
    ArrayList<User> user; //获取数据库中 用户对象
    RoundImageView WriterPic;
    DBAdapter dbAdapter;
    AdapterForArticleActivity adapterForArticleActivity;
    int position;//用户正在阅读的章节号

    //added by Lgq
    private PullToRefreshListView listView;
    private ActionBar actionBar;
    private View backBtn;
    private TextView textViewTitle;//ActionBar
    private TextView textView_ArticleName;//文章标题
    private TextView textView_WriterName;//作者昵称
    private TextView textView_Article_Writer_words;//作者寄语

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAdapter=new DBAdapter(this.getApplicationContext());
        setContentView(R.layout.activity_article);
        Intent intent = getIntent();
        Articleposition = intent.getIntExtra("position", 0);
        Article_idInDB = intent.getIntExtra("Article_IDInDB", 1);
        getArtileAndUserInDB();
        init();
        initActionBar();
        //**文章信息设置
        this.textView_ArticleName=(TextView)findViewById(R.id.Article_Name);
        this.textView_ArticleName.setText(article[0].getTitle());
        this.textView_WriterName=(TextView)findViewById(R.id.textView_WriterName);
        this.textView_WriterName.setText(user.get(0).getUser_NickNAME());
        this.textView_Article_Writer_words=(TextView)findViewById(R.id.Article_Writer_words);
        this.textView_Article_Writer_words.setText("奇点创作，创作奇点");

    }

    private void initActionBar(){
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        View back = getLayoutInflater().inflate(R.layout.back_layout_width_right_txt,null);
        backBtn = back.findViewById(R.id.back_icon);
        textViewTitle=(TextView)back.findViewById(R.id.back_indicator_txt);
        textViewTitle.setText(this.article[0].Title);
        backBtn.setOnClickListener(this);
        actionBar.setCustomView(back);

    }
    public void init() {
        listView = (PullToRefreshListView) findViewById(R.id.id_stickynavlayout_listview);
        adapterForArticleActivity=new AdapterForArticleActivity(getApplicationContext(),Article_idInDB);
        adapterForArticleActivity.setOnSectionItemClick(new AdapterForArticleActivity.OnItemClick() {
            @Override
            public void OnSectionItemClick(int position, int Article_IDInDB,CharSequence SectionContent) {
                //参数为 pisition 和 该文章在本地数据库中号
//                AppHelper.ShowToast(getApplicationContext(),"--->>Position:"+position+"---->>ArticleID"+Article_idInDB);
                Intent intent=new Intent(ArticleActivity.this,ActivityReadingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putCharSequence("SectionContent",SectionContent);
                bundle.putInt("position", position);
                bundle.putInt("Article_IDInDB", Article_IDInDB);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        listView.setAdapter(adapterForArticleActivity);
        listView.setPullRefreshEnabled(false);
        listView.setPullLoadEnabled(true);

        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                //这里是刷新完成之后调用的接口，在这里进行数据更新操作

                listView.onPullDownRefreshComplete();

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                //这里是刷新完成之后调用的接口，在这里进行数据更新操作

                listView.onPullUpRefreshComplete();
            }
        });
        listView.setFooterBackgroundResource(R.color.white);

        WriterPic = (RoundImageView) findViewById(R.id.Writer_Pic_In_ArticleActivity);
        WriterPic.setType(R.styleable.RoundImageView_borderRadius);
        WriterPic.setImageResource(R.drawable.writerpic);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_in_single_article, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
    public void getArtileAndUserInDB(){
        dbAdapter.OpenDB();
        article= dbAdapter.QueryArticleByID(Article_idInDB);
        user=dbAdapter.QueryUserByID(article[0].getUser_ID());
        dbAdapter.CloseDB();
    }

    @Override
    public void onClick(View view) {
        if(view==backBtn){
            this.finish();
        }
    }


}
