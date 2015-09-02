package com.qidianliterature.jidian.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.qidianliterature.jidian.DB.DBAdapter.DBAdapter;
import com.qidianliterature.jidian.DB.JavaBean.Article;
import com.qidianliterature.jidian.DB.JavaBean.Section;
import com.qidianliterature.jidian.DB.JavaBean.User;
import com.qidianliterature.jidian.FragmentInActivity.CollectFragment;
import com.qidianliterature.jidian.FragmentInActivity.DraftFragment;
import com.qidianliterature.jidian.FragmentInActivity.FocusFragement;
import com.qidianliterature.jidian.FragmentInActivity.FristFragment;
import com.qidianliterature.jidian.activity.LoginAndRegActivity;
import com.qidianliterature.jidian.activity.StartCreation;
import com.qidianliterature.jidian.adapter.MenuListAdapter;
import com.qidianliterature.jidian.util.AppHelper;
import com.qidianliterature.jidian.util.StringUtil;
import com.qidianliterature.jidian.view.Dragger;
import com.qidianliterature.jidian.view.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ActionBar mActionBar;
    private DrawerLayout mDrawerLayout;
    private ListView mMenuList;
    private MenuListAdapter menuListAdapter;
    /**
     * Menu中粉丝
     */
    private View fansView;
    /**
     * Menu中的关注
     */
    private View careView;

    /**
     * Menu中的头像
     */
    private RoundImageView profile;
    private List<Fragment> fragments = new ArrayList<Fragment>();

    final FristFragment fragment1 = new FristFragment();
    final FocusFragement fragment2 = new FocusFragement();
    final CollectFragment fragment3 = new CollectFragment();
    final DraftFragment fragment4 = new DraftFragment();
    DBAdapter dbAdapter;
    Section section;
    private long exitTime=0;//计算时间用

    private int clickedItem = 0;
    private Dragger draggerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivity.this.setTitle(StringUtil.MENU_ITEM_TITLE[clickedItem]);
//        Bmob.initialize();
        CreateDATA();


        init();
    }

    private void init(){

        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);

        profile = (RoundImageView) findViewById(R.id.menu_user_profile);
        profile.setOnClickListener(this);

        fansView = findViewById(R.id.fans_layout);
        fansView.setOnClickListener(this);

        careView = findViewById(R.id.care_layout);
        careView.setOnClickListener(this);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);

        mActionBar = getSupportActionBar();
        if( null != mActionBar ){
            mActionBar.setHomeAsUpIndicator(R.drawable.action_menu_ic);
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        mMenuList = (ListView) findViewById(R.id.menu_list);
        menuListAdapter = new MenuListAdapter(this);
        menuListAdapter.setMenuItemClick(new MenuListAdapter.MenuItemClick() {
            @Override
            public void onMenuItemClick(int which) {
                //这里可以获得用户点击了第几个Menu Item
//                fragments.equals()
                Fragment f = fragments.get(which);
//                AppHelper.ShowToast(getApplicationContext(),"onMenuItemClick------------------>>>"+f);
                changeFragment(f);
                MainActivity.this.setTitle(StringUtil.MENU_ITEM_TITLE[which]);
                clickedItem = which;
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        mMenuList.setAdapter(menuListAdapter);
        initDragger();
    }

    private void initDragger(){
        draggerView = (Dragger) findViewById(R.id.dragger_view);
        draggerView.setDraggerClickListener(new Dragger.DraggerClickListener() {

            @Override
            public void doAction() {
                AppHelper.startActivityWithNoArgs(MainActivity.this, StartCreation.class);
            }
        });
        ImageView iv = new ImageView(this);
        iv.setImageResource(R.drawable.paint_indicator);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(98,98);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        rlp.setMargins(0, 0, 10, 10);
        draggerView.setmDraggerView(iv, rlp);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 切换fragment
     *
     * @param targetFragment
     */
    private void changeFragment(Fragment targetFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        }else if( id == R.id.action_search){
            Toast.makeText(this,"Search action",Toast.LENGTH_SHORT).show();
        }else if( id == R.id.submenu1){
            Toast.makeText(this,"submenu1",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v == profile){
            Intent i = new Intent(this, LoginAndRegActivity.class);
            startActivity(i);
        }
    }
    public void CreateDATA(){
        this.dbAdapter=new DBAdapter(getApplicationContext());
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                dbAdapter.OpenDB();
//                        for(int i=0;i<9;i++){
//                            article.Article_ID=random.nextInt(1000);
//                            dbAdapter.InsertArticle(article);
//                        }
                Article article1=new Article();
                article1.setTitle("假如爱有天意");
                article1.setArticle_Content("讲述了大学生智惠，无意中找到母亲主熙留下的日记，重温她的初恋。");
                article1.setArticle_ID(1);
                article1.setUser_ID(1);
                dbAdapter.InsertArticle(article1);
                User user1=new User();
                user1.setUser_ID(1);
                user1.setUser_NickNAME("李健");
                dbAdapter.InsertUser(user1);
                //2
                Article article2=new Article();
                article2.setTitle("何以笙箫默");
                article2.setArticle_Content("一段年少时的追逐，牵出一生的爱恋。大学时代的赵默笙阳光灿烂，对法学系大才子何以琛一见倾心，开朗直率的她拔足倒追，终于使才气出众的他为她停留驻足");
                article2.setArticle_ID(2);
                article2.setUser_ID(2);
                dbAdapter.InsertArticle(article2);
                User user2=new User();
                user2.setUser_ID(2);
                user2.setUser_NickNAME("顾漫");
                dbAdapter.InsertUser(user2);
                //3
                Article article3=new Article();
                article3.setTitle("华胥引");
                article3.setArticle_Content("若用生命换一个过往完美的幻境，你可否答应？或许你会摇头，但她们应了。在这个发生在乱世的故事里，卫国公主叶蓁以身殉国，依靠鲛珠死而复生。当她弹起华胥调，便生死人肉白骨，探入梦境与回忆。以命易梦轻叹悲欢离合一场戏，黄梁之后，尚剩几何？而她与亡她国家的陈国世子苏誉一次一次于幻境中相遇，身份两重，缘也两重。对他们而言，世界的倾塌只需要那么轻轻一句话，无奈痛苦的现实，难以承受的痛，不如只求在梦中得到一个圆满。[");
                article3.setArticle_ID(3);
                article3.setUser_ID(3);
                dbAdapter.InsertArticle(article3);
                User user3=new User();
                user3.setUser_ID(3);
                user3.setUser_NickNAME("唐七公子");
                dbAdapter.InsertUser(user3);
                //4
                Article article4=new Article();
                article4.setTitle("步步惊心");
                article4.setArticle_Content("故事讲述了现代白领张晓因车祸穿越到清朝康熙年间，成为满族少女马尔泰·若曦，身不由己地卷入“九子夺嫡”的纷争。她看透所有人的命运，却无法掌握自己的结局，个人情感夹杂在争斗的惨烈中备受煎熬。经历几番爱恨嗔痴，若曦身心俱疲再也支撑不住，临终前，她彷佛听到一阵歌声，引她入梦。[");
                article4.setArticle_ID(4);
                article4.setUser_ID(4);
                dbAdapter.InsertArticle(article4);
                User user4=new User();
                user4.setUser_ID(4);
                user4.setUser_NickNAME("桐华");
                dbAdapter.InsertUser(user4);
                //5
                Article article5=new Article();
                article5.setTitle("校花的贴身高手");
                article5.setArticle_Content("本书讲述林逸从黄阶到天道，从世俗界到天阶岛的牛X闪闪的传奇人生。");
                article5.setArticle_ID(5);
                article5.setUser_ID(5);
                dbAdapter.InsertArticle(article5);
                User user5=new User();
                user5.setUser_ID(5);
                user5.setUser_NickNAME("鱼人二代");
                dbAdapter.InsertUser(user5);
                //6
                Article article6=new Article();
                article6.setTitle("被校花逆推之后");
                article6.setArticle_Content("从此一切梦想触手可及，财运滚滚，各色美女蜂拥而至、前仆后继，校花纷纷来逆推——");
                article6.setArticle_ID(6);
                article6.setUser_ID(6);
                dbAdapter.InsertArticle(article6);
                User user6=new User();
                user6.setUser_ID(6);
                user6.setUser_NickNAME("白俗");
                dbAdapter.InsertUser(user6);
                //为文章添加十篇Section
                Article[] articles=dbAdapter.QueryAllArticle();

                for (int a=0;a<10;a++) {
                    for (int i = 0; i < articles.length; i++) {
                        section = new Section();
                        section.setArticle_ID(articles[i].getArticle_ID());
                        section.setSection_Content("奇点文学");
                        section.setSection_ID(i);
                        section.setSection_Title("第" + (a+1) + "章");
                        section.setWriter_ID(1);
                        dbAdapter.InsertSection(section);
                    }
                }


                dbAdapter.CloseDB();

            }
        });
        thread.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK &&event.getAction()==KeyEvent.ACTION_DOWN){

            if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }else if((System.currentTimeMillis()-exitTime)>2000){
                AppHelper.ShowToast(getApplicationContext(),"再按一次退出");
                exitTime=System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
