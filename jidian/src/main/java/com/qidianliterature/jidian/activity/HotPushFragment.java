package com.qidianliterature.jidian.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.qidianliterature.jidian.adapter.BasicViewPagerAdapter;
import com.qidianliterature.jidian.main.R;
import com.qidianliterature.jidian.util.AppHelper;
import com.qidianliterature.jidian.util.ResourceUtil;
import com.qidianliterature.jidian.view.OuterScrollView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liang Guan Quan on 2015/8/15.
 */
public class HotPushFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener , View.OnClickListener{

    private View root;
    private Context context;
//    private PullToRefreshListView pullToRefreshListView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ScrollView contentScroll;
    private ViewPager hotpushPager;
    private BasicViewPagerAdapter viewPagerAdapter;

    private RelativeLayout loadMoreHotPushBtn;
    private RelativeLayout loadMoreRecentlyBtn;

    private ViewGroup hotProContainer;
    private ViewGroup recentProContainer;

    private static final int DEF_HOT_PRO_COUNT = 3;
    private static final int DEF_RECENT_PRO_COUNT = 5;
    //*******
    Button Hot_Product_Read;
    ArrayList<View> view_hot_pro_list;
    ArrayList<View> view_recent_pro_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.hotpush_fragment_layout,null);
        context = root.getContext();
        init();
        initFootView();
        initListener();

        generateHotPro();
        generateRecentPro();
        return root;
    }

    private void initFootView(){

        //初始化 热门作品的footerView
        loadMoreHotPushBtn = new RelativeLayout(context);
        loadMoreHotPushBtn.setBackgroundResource(R.color.light_theme_color);

        TextView tv1 = new TextView(context);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp.setMargins(0,5,0,5);
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
        tv1.setText(ResourceUtil.getString(context, R.string.more_hotpush_txt));
        tv1.setTextColor(ResourceUtil.getColor(context, R.color.theme_color));
        tv1.setTextSize(15);

        loadMoreHotPushBtn.addView(tv1,rlp);
        loadMoreHotPushBtn.setOnClickListener(this);

        //初始化 最近更新的footerView
        loadMoreRecentlyBtn = new RelativeLayout(context);
        loadMoreRecentlyBtn.setBackgroundResource(R.color.light_theme_color);

        TextView tv2 = new TextView(context);
        RelativeLayout.LayoutParams rlp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        rlp2.setMargins(0,5,0,5);
        rlp2.addRule(RelativeLayout.CENTER_IN_PARENT);
        tv2.setText(ResourceUtil.getString(context, R.string.more_recently_txt));
        tv2.setTextColor(ResourceUtil.getColor(context, R.color.theme_color));
        tv2.setTextSize(15);

        loadMoreRecentlyBtn.addView(tv2,rlp2);
        loadMoreRecentlyBtn.setOnClickListener(this);
    }

    private void init(){
        recentProContainer = AppHelper.$(root,R.id.recently_update_container);
        hotProContainer = AppHelper.$(root,R.id.hot_product_container);

        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.hotpush_outer_refresher);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorScheme(R.color.theme_color);

        contentScroll = (ScrollView) root.findViewById(R.id.hotpush_content_scrollview);

        hotpushPager = (ViewPager) root.findViewById(R.id.hotpush_pager);
        initBanner();
    }

    private void initListener() {

    }

    private void initBanner(){
        ImageView[] ivs = new ImageView[4];

        ImageView iv = new ImageView(root.getContext());
        iv.setImageResource(R.drawable.dd);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView iv1 = new ImageView(root.getContext());
        iv1.setImageResource(R.drawable.bk2);
        iv1.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView iv2 = new ImageView(root.getContext());
        iv2.setImageResource(R.drawable.bk1);
        iv2.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ImageView iv3 = new ImageView(root.getContext());
        iv3.setImageResource(R.drawable.bk);
        iv3.setScaleType(ImageView.ScaleType.CENTER_CROP);

        ivs[0] = iv;
        ivs[1] = iv1;
        ivs[2] = iv2;
        ivs[3] = iv3;

        viewPagerAdapter = new BasicViewPagerAdapter(hotpushPager,ivs,root.getContext());
        viewPagerAdapter.startAutoScroll();
        viewPagerAdapter.setIndex((LinearLayout) root.findViewById(R.id.index_container));
        hotpushPager.setOnPageChangeListener(viewPagerAdapter);
        hotpushPager.setAdapter(viewPagerAdapter);
    }



    private void getData(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    private void generateHotPro(){
        LayoutInflater inflater = LayoutInflater.from(context);
        view_hot_pro_list=new ArrayList<>();
        for (int i = 0; i < DEF_HOT_PRO_COUNT; i++) {
            View hotProView = inflater.inflate(R.layout.hotpush_product_layout,null);
            Button button=(Button)hotProView.findViewById(R.id.goto_read_in_hotpush);
            TextView Title=(TextView)hotProView.findViewById(R.id.hot_product_name_in_hotpush);
            TextView Content=(TextView)hotProView.findViewById(R.id.litera_content);
            TextView WriterName=(TextView)hotProView.findViewById(R.id.username_in_hotpush);
            button.setOnClickListener(this);
            view_hot_pro_list.add(i,hotProView);
            //
            if( i < DEF_HOT_PRO_COUNT -1 ){
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                llp.setMargins(0,0,0,13);
                if(i==0){
                    Title.setText("假如爱有天意");
                    Content.setText("讲述了大学生智惠，无意中找到母亲主熙留下的日记，重温她的初恋。");
                    WriterName.setText("李健");
                }else if(i==1){
                    Title.setText("何以笙箫默");
                    Content.setText("一段年少时的追逐，牵出一生的爱恋。大学时代的赵默笙阳光灿烂，对法学系大才子何以琛一见倾心，开朗直率的她拔足倒追，终于使才气出众的他为她停留驻足");
                    WriterName.setText("顾漫");
                }
                hotProContainer.addView(hotProView,llp);
            }else{
                Title.setText("华胥引");
                Content.setText("若用生命换一个过往完美的幻境，你可否答应？或许你会摇头，但她们应了。在这个发生在乱世的故事里，卫国公主叶蓁以身殉国，依靠鲛珠死而复生。当她弹起华胥调，便生死人肉白骨，探入梦境与回忆。以命易梦轻叹悲欢离合一场戏，黄梁之后，尚剩几何？而她与亡她国家的陈国世子苏誉一次一次于幻境中相遇，身份两重，缘也两重。对他们而言，世界的倾塌只需要那么轻轻一句话，无奈痛苦的现实，难以承受的痛，不如只求在梦中得到一个圆满。");
                WriterName.setText("唐七公子");
                hotProContainer.addView(hotProView);
            }

            if(i == DEF_HOT_PRO_COUNT - 1){
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ResourceUtil.getDimension(context,R.dimen.fragment_foot_view_height));
                llp.setMargins(ResourceUtil.getDimension(context, R.dimen.normal_margin), 0, ResourceUtil.getDimension(context, R.dimen.normal_margin), 0);
                hotProContainer.addView(loadMoreHotPushBtn,llp);
            }

        }
    }

    private void generateRecentPro(){
        LayoutInflater inflater = LayoutInflater.from(root.getContext());
        for (int i = 0; i < DEF_RECENT_PRO_COUNT; i++) {
            View recProView = inflater.inflate(R.layout.recently_update_layout,null);
            if( i < DEF_RECENT_PRO_COUNT -1 ){
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ResourceUtil.getDimension(context,R.dimen.fragment_recent_height));
                llp.setMargins(0,0,0,13);
                recentProContainer.addView(recProView,llp);
            }else{
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ResourceUtil.getDimension(context,R.dimen.fragment_recent_height));
                recentProContainer.addView(recProView,llp);
            }

            //����footView
            if(i == DEF_RECENT_PRO_COUNT - 1){
                LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ResourceUtil.getDimension(context,R.dimen.fragment_foot_view_height));
//                llp.setMargins(ResourceUtil.getDimension(context, R.dimen.normal_margin), 0, ResourceUtil.getDimension(context, R.dimen.normal_margin), 0);
                recentProContainer.addView(loadMoreRecentlyBtn,llp);
            }
        }
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View v) {
        if( v == loadMoreHotPushBtn){
            AppHelper.ShowToast(root.getContext(), "加载更多");
        }else if( v == loadMoreRecentlyBtn){
            AppHelper.ShowToast(root.getContext(),"加载更多");
        }
//        else if( v.getId()==R.id. goto_read_in_hotpush){
//            //没写判断是那个hotpro的button  只要是这个ID就弹出同一篇  ，已把hotpro的view存入ArrayList；
//            Intent intent=new Intent(getActivity().getApplicationContext(),ArticleActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putInt("position", 0);
//            bundle.putInt("Article_IDInDB", 1);
//            intent.putExtras(bundle);
//            startActivity(intent);
//        }
        else if(v==view_hot_pro_list.get(0).findViewById(R.id.goto_read_in_hotpush)){
            Intent intent=new Intent(getActivity().getApplicationContext(),ArticleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("position", 0);
            bundle.putInt("Article_IDInDB", 1);
            intent.putExtras(bundle);
            startActivity(intent);
        }else if(v==view_hot_pro_list.get(1).findViewById(R.id.goto_read_in_hotpush)){
            Intent intent=new Intent(getActivity().getApplicationContext(),ArticleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("position", 0);
            bundle.putInt("Article_IDInDB", 2);
            intent.putExtras(bundle);
            startActivity(intent);
        }else if(v==view_hot_pro_list.get(2).findViewById(R.id.goto_read_in_hotpush)){
            Intent intent=new Intent(getActivity().getApplicationContext(),ArticleActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("position", 0);
            bundle.putInt("Article_IDInDB", 3);
            intent.putExtras(bundle);
            startActivity(intent);
        }

    }
}
