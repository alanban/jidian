package com.qidianliterature.jidian.FragmentInActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.baidu.searchbox.ui.pullrefresh.PullToRefreshBase;
import com.baidu.searchbox.ui.pullrefresh.PullToRefreshListView;
import com.qidianliterature.jidian.activity.ArticleActivity;
import com.qidianliterature.jidian.adapter.AdapterForFocusFragment;
import com.qidianliterature.jidian.main.R;

public class FocusFragement extends Fragment {
    private View root;

    private SwipeRefreshLayout swipeRefreshLayout;
    private PullToRefreshListView listView;

    private AdapterForFocusFragment adapterForFocusFragemnt;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_focus, container, false);
        init();
        return root;
    }

    private void init(){
        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.focus_outer_refresher);
        swipeRefreshLayout.setColorScheme(R.color.theme_color);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //get data  code
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        listView = (PullToRefreshListView) root.findViewById(R.id.focus_listview);
        this.adapterForFocusFragemnt=new AdapterForFocusFragment(this.getActivity());
        this.adapterForFocusFragemnt.setArticleItemClick(new AdapterForFocusFragment.ItemOnclick() {
            @Override
            public void OnArticleItemClick(int position, int Article_IDInDB) {
                //ListView 某项被点击操作  Article_number对应数据库中文章号
                Intent intent = new Intent(getActivity(), ArticleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putInt("Article_IDInDB", Article_IDInDB);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        listView.setAdapter(adapterForFocusFragemnt);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if(listView.isFirstItemVisible()){
                    swipeRefreshLayout.setEnabled(true);
                }else{
                    if(swipeRefreshLayout.isEnabled()){
                        swipeRefreshLayout.setEnabled(false);
                    }
                }
            }
        });
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
        listView.setFooterBackgroundResource(R.color.bk_color);


    }

}
