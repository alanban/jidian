package com.qidianliterature.jidian.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.baidu.searchbox.ui.pullrefresh.PullToRefreshBase;
import com.baidu.searchbox.ui.pullrefresh.PullToRefreshListView;
import com.qidianliterature.jidian.main.R;

/**
 * Created by Liang Guan Quan on 2015/8/15.
 */
public class TodayHotFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private View root;
    private PullToRefreshListView pullToRefreshListView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.todaypush_fragment_ayout,null);
        init();
        return root;
    }

    private void init(){
        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.todaypush_outer_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorScheme(R.color.theme_color);

        pullToRefreshListView = (PullToRefreshListView) root.findViewById(R.id.todaypush_content_listview);
        pullToRefreshListView.setPullRefreshEnabled(false);
        pullToRefreshListView.setPullLoadEnabled(true);

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                getData();
                pullToRefreshListView.onPullUpRefreshComplete();
            }
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                getData();
                pullToRefreshListView.onPullUpRefreshComplete();
            }
        });
        pullToRefreshListView.setHeaderBackgroundResource(R.color.bk_color);
    }

    private void getData() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    @Override
    public void onRefresh() {

    }
}
