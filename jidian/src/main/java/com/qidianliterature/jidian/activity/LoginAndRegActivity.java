package com.qidianliterature.jidian.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qidianliterature.jidian.main.R;
import com.qidianliterature.jidian.util.ResourceUtil;


/**
 * Created by Liang Guan Quan on 2015/8/7.
 */
public class LoginAndRegActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager mLoginFragment;
    private ActionBar mActionBar;
    private FragmentPagerAdapter mAdapter;
    private TextView goToRegist;
    private TextView agreementTwo;
    private Button loginBtn;
    private Fragment[] mFragments;

    private View forgetLayout;
    private View agreementLayout;
    private View backBtn;
    private TextView textViewTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_and_reg_layout);

        init();
    }

    private void initView() {
        forgetLayout = findViewById(R.id.forget_layout);

        mLoginFragment = (ViewPager) findViewById(R.id.login_fragment_pager);
        mLoginFragment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        agreementLayout = findViewById(R.id.agreement_layout);
        agreementLayout.setVisibility(View.GONE);
        agreementTwo = (TextView) findViewById(R.id.agreement_two_txt);
        agreementTwo.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
        agreementTwo.getPaint().setAntiAlias(true);
        agreementTwo.setOnClickListener(this);

        mFragments = new Fragment[2];
        mFragments[0] = new LoginFragment();
        mFragments[1] = new RegistionFragment();
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mFragments.length;
            }
        };
        mLoginFragment.setAdapter(mAdapter);

        goToRegist = (TextView) findViewById(R.id.goto_reg);
        loginBtn = (Button) findViewById(R.id.login_btn);

        goToRegist.setOnClickListener(this);
        loginBtn.setOnClickListener(this);


    }

    private void init() {

        mActionBar = getSupportActionBar();
        mActionBar.show();
        mActionBar.setDisplayHomeAsUpEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setDisplayShowCustomEnabled(true);
        View back = getLayoutInflater().inflate(R.layout.back_layout_width_right_txt,null);
        backBtn = back.findViewById(R.id.back_icon);
        textViewTitle=(TextView)back.findViewById(R.id.back_indicator_txt);
        textViewTitle.setText(ResourceUtil.getString(this,R.string.login_cn));
        backBtn.setOnClickListener(this);
        mActionBar.setCustomView(back);

        initView();
    }

    @Override
    public void onClick(View v) {
        if( v== goToRegist){

            int item = mLoginFragment.getCurrentItem();
            //当点击的时候，index==0，就代表用户将要跳转到注册页面，在这里加载注册信息
            if(item == 0){

                mLoginFragment.setCurrentItem(item + 1);
                loginBtn.setText(ResourceUtil.getString(this, R.string.registion_cn));
                goToRegist.setText(ResourceUtil.getString(this, R.string.login_txt));

                agreementLayout.setVisibility(View.VISIBLE);
                forgetLayout.setVisibility(View.GONE);
            }else if(item == 1){

                //1是注册
                goToRegist.setText(ResourceUtil.getString(this, R.string.registion_txt));
                mLoginFragment.setCurrentItem(item - 1);
                loginBtn.setText(ResourceUtil.getString(this, R.string.login_cn));
                agreementLayout.setVisibility(View.GONE);
                forgetLayout.setVisibility(View.VISIBLE);
            }
        }else if( v==loginBtn ){
            int item = mLoginFragment.getCurrentItem();
            //item == 0的时候，用户点击按钮，就代表用户要登陆，反之要注册
            if(item == 0){

            }else if(item == 1){

            }
        }else if(v==backBtn){
            this.finish();
        }
    }
}
