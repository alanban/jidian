package com.qidianliterature.jidian.FragmentInActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qidianliterature.jidian.activity.HotPushFragment;
import com.qidianliterature.jidian.activity.TodayHotFragment;
import com.qidianliterature.jidian.activity.TodayUpdateFragment;
import com.qidianliterature.jidian.main.R;
import com.qidianliterature.jidian.view.SimpleSwipeIndicator;

import java.util.ArrayList;
import java.util.List;


public class FristFragment extends Fragment implements View.OnClickListener {

    private ViewPager fragmentContainer;
    private FragmentPagerAdapter containerAdapter;
    private SimpleSwipeIndicator ssIndicator;

    private HotPushFragment hotPushFragment;
    private TodayUpdateFragment todayUpdateFragment;
    private TodayHotFragment todayHotFragment;

    private List<Fragment> fragments;

    private View root;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_fristpage, container, false);
        init();

        return root;
    }

    private void init() {
        initFragments();

        fragmentContainer = (ViewPager) root.findViewById(R.id.fragment_container);
        containerAdapter = new FragmentPagerAdapter(this.getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        fragmentContainer.setAdapter(containerAdapter);

        ssIndicator = (SimpleSwipeIndicator) root.findViewById(R.id.simple_indicator);
        ssIndicator.attachViewPager(fragmentContainer);
    }

    private void initFragments() {

        fragments = new ArrayList<Fragment>();
        hotPushFragment = new HotPushFragment();
        fragments.add(0,hotPushFragment);

        todayHotFragment = new TodayHotFragment();
        fragments.add(1,todayHotFragment);

        todayUpdateFragment = new TodayUpdateFragment();
        fragments.add(2,todayUpdateFragment);
    }

    @Override
    public void onClick(View view) {

    }
}
