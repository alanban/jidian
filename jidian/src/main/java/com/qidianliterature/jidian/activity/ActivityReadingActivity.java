package com.qidianliterature.jidian.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.qidianliterature.jidian.DB.DBAdapter.DBAdapter;
import com.qidianliterature.jidian.DB.JavaBean.Article;
import com.qidianliterature.jidian.DB.JavaBean.Section;
import com.qidianliterature.jidian.DB.JavaBean.User;
import com.qidianliterature.jidian.main.R;

import java.util.ArrayList;

public class ActivityReadingActivity extends AppCompatActivity implements View.OnClickListener{
    int Articleposition;//被点击章节的position
    int Article_idInDB;// 在数据库中Article ID

    CharSequence SectionContent;//被点击的SectionItem的文字内容
    Article[] article;//获取数据库中文章对象
    Section[] sections;//文章所拥有的所有章节
    ArrayList<User> user; //获取数据库中 用户对象

    ImageView writerpic;//用户头像
    TextView WriterName;//用户昵称
    TextView WriterWords;//用户简介
    TextView DianzanNum;//点赞数
    TextView TextView_CurrentSectionNum,TextView_SectionNumIntotal;
    int DianzanNumberInDB=26;//初始点赞数
    DBAdapter dbAdapter;
    ImageButton  Article_mark,article_comment,Button_dianzan;
    ViewPager SectionViewPage;
    private FragmentPagerAdapter fragmentPagerAdapter;
    private SectionFragemnt[] fragments;
    private int which=0;//当前阅读Fragemnt的游标
    private ActionBar actionBar;
    private View backBtn;
    private TextView textViewTitle;

    /**
     * 判断用户是否已经点赞
     */
    private boolean isLikeClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_reading);
        dbAdapter=new DBAdapter(getApplicationContext());
        Intent intent = getIntent();
        Articleposition = intent.getIntExtra("position", 0);
        Article_idInDB = intent.getIntExtra("Article_IDInDB", 1);
        SectionContent =intent.getCharSequenceExtra("SectionContent");
        getArtileAndUserandSectionInDB();
        fragments=new SectionFragemnt[sections.length];
        initActionBar();
        init();

    }
    public void init(){

        //用户头像
        this.writerpic=(ImageView)findViewById(R.id.Article_imageView);
        this.writerpic.setImageResource(R.drawable.writerpic);
        //用户昵称
        this.WriterName=(TextView)findViewById(R.id.Article_textView_AccountName);
        this.WriterName.setText(user.get(0).getUser_NickNAME());
        //点赞按钮
        this.Button_dianzan=(ImageButton)findViewById(R.id.Article_imageButton_dianzan);
        this.Button_dianzan.setOnClickListener(this);
        //点赞数
        this.DianzanNum=(TextView)findViewById(R.id.Article_textView_dianzanNub);
        this.DianzanNum.setText("" + DianzanNumberInDB);
        //章节进度
        this.TextView_SectionNumIntotal=(TextView)findViewById(R.id.TextView_SectionNumIntotal);
        this.TextView_CurrentSectionNum=(TextView)findViewById(R.id.TextView_CurrentSectionNum);
        this.TextView_SectionNumIntotal.setText(""+sections.length);

        this.SectionViewPage=(ViewPager)findViewById(R.id.ViewPage_ReadArticle);
        this.fragmentPagerAdapter=new FragmentPagerAdapter(this.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                which=position;
                return getFragment(position);
            }

            @Override
            public int getCount() {
                return sections.length;
            }
        };
        this.SectionViewPage.setAdapter(fragmentPagerAdapter);
        this.Article_mark=(ImageButton)findViewById(R.id.Article_mark);
        this.article_comment=(ImageButton)findViewById(R.id.article_comment);
        this.Article_mark.setOnClickListener(this);
        this.article_comment.setOnClickListener(this);
        this.SectionViewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                TextView_CurrentSectionNum.setText(""+(position+1));//改变阅读进度提示

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public Fragment getFragment(int which){
        Bundle bundle = new Bundle();
        bundle.putCharSequence("SectionContent",SectionContent);

        fragments[which]=new SectionFragemnt(bundle);
        fragments[which].SendContent(sections[which]);

        return fragments[which];
    }

    private void initActionBar(){
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        View back = getLayoutInflater().inflate(R.layout.back_layout_width_right_txt,null);
        backBtn = back.findViewById(R.id.back_icon);
        textViewTitle=(TextView)back.findViewById(R.id.back_indicator_txt);
        textViewTitle.setText(article[0].Title);
        backBtn.setOnClickListener(this);
        actionBar.setCustomView(back);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_reading, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
    public void getArtileAndUserandSectionInDB(){
        dbAdapter.OpenDB();
        article= dbAdapter.QueryArticleByID(Article_idInDB);
        user=dbAdapter.QueryUserByID(article[0].getUser_ID());
        sections=dbAdapter.QuerySectionByArticle_ID(article[0].getArticle_ID());
        System.out.println("---------------->>>Reading:-->>section number:"+sections.length);
        dbAdapter.CloseDB();
    }

    @Override
    public void onClick(View view) {
        if( view == backBtn){
            this.finish();
        }
        if (view==Article_mark){

        }
        if (view==article_comment){

        }
        if(view==Button_dianzan){
            if(!isLikeClicked){
                Button_dianzan.setBackgroundResource(R.drawable.article_adapter_bt_selector_clicked);
                isLikeClicked = true;
                this.DianzanNum.setText(""+DianzanNumberInDB--);

            }else{
                Button_dianzan.setBackgroundResource(R.drawable.article_adpater_bt_selector);
                isLikeClicked = false;
                this.DianzanNum.setText(""+DianzanNumberInDB++);
            }

        }
    }
}
