package com.qidianliterature.jidian.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qidianliterature.jidian.FragmentInActivity.FirstStartCreation;
import com.qidianliterature.jidian.FragmentInActivity.SecondStartCreation;
import com.qidianliterature.jidian.FragmentInActivity.ThirdStartCreation;
import com.qidianliterature.jidian.main.R;
import com.qidianliterature.jidian.util.AnimationUtil;
import com.qidianliterature.jidian.util.AppHelper;
import com.qidianliterature.jidian.util.ResourceUtil;
import com.qidianliterature.jidian.util.VerifyUtil;
import com.qidianliterature.jidian.view.StaticViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liang Guan Quan on 2015/8/26.
 */
public class StartCreation extends AppCompatActivity implements View.OnClickListener{

    private ActionBar actionBar;
    private ImageView backBtn;
    private TextView  indicatorTxt;

    private StaticViewPager creationStepContainer;
    private int cruPage = 0;
    private FragmentPagerAdapter containerAdapter;

    private List<Fragment> fragments = new ArrayList<Fragment>();
    private FirstStartCreation firstFragment = new FirstStartCreation();
    private SecondStartCreation secondFragment = new SecondStartCreation();
    private ThirdStartCreation thirdFragment = new ThirdStartCreation();

    private View towardsLayout;
    private ImageView towardsIcon;
    private TextView innerText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_creation);
        initData();
        initActionBar();
        init();
    }

    private void initData(){
        fragments.add(firstFragment);
        fragments.add(secondFragment);
        fragments.add(thirdFragment);
    }

    private void init(){
        towardsLayout = findViewById(R.id.towards_layout_in_start_creation);
        towardsLayout.setOnClickListener(this);
        towardsIcon = (ImageView) findViewById(R.id.towards_icon);
        innerText = (TextView) findViewById(R.id.inner_text);
        AnimationUtil.startCompleteScaleAni(AnimationUtil.ZERO_TO_FULL, towardsLayout, AnimationUtil.DEFAULT_TIME);

        creationStepContainer = (StaticViewPager) findViewById(R.id.creation_step_container);
        //屏蔽第一页的滑动手势
        creationStepContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        //改变ViewPager切换页面的速度，使切换更加平滑
        AppHelper.initViewPagerSpeed(creationStepContainer, 1500);

        containerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        creationStepContainer.setAdapter(containerAdapter);
    }

    private void startScaleAnimation(final View view){

        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleX", 1f, 0.5f);
        PropertyValuesHolder pvhZ = PropertyValuesHolder.ofFloat("scaleY", 1f,0.5f);
        ObjectAnimator oa = ObjectAnimator.ofPropertyValuesHolder(view, pvhY, pvhZ).setDuration(1000);
        oa.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                towardsIcon.setVisibility(View.VISIBLE);
                AnimationUtil.startCompleteAlphaAni(AnimationUtil.ZERO_TO_FULL, towardsIcon, AnimationUtil.DEFAULT_TIME, null);
                innerText.setVisibility(View.GONE);
            }
        });
        oa.start();
    }

    private void initActionBar(){
        LayoutInflater inflater = getLayoutInflater();
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        View backLayout = inflater.inflate(R.layout.back_layout_width_right_txt, null);
        backBtn = (ImageView) backLayout.findViewById(R.id.back_icon);
        indicatorTxt = (TextView) backLayout.findViewById(R.id.back_indicator_txt);
        indicatorTxt.setText("创建文章基本信息");
        backBtn.setOnClickListener(this);
        actionBar.setCustomView(backLayout);
    }

    private void actionClickOnPageOne(){
        VerifyUtil vu =  VerifyUtil.getInstance(this);
        vu.setmListener(new VerifyUtil.VerifyMessageListener() {
            @Override
            public void MessageCallBack(String result) {
                Toast.makeText(StartCreation.this, result, Toast.LENGTH_SHORT).show();
            }
        });

        //只有通过字段完整性约束才会跳转到第三个页面
        if(vu.startVerify(secondFragment.getTitleView() ,secondFragment.getSelfCommentView() )){
            //更新title和作者名
            thirdFragment.setAuthorName("李健");
            thirdFragment.setTitle(secondFragment.getTitle());

            //变化按钮
            innerText.setText("完成");
            innerText.setTextSize(ResourceUtil.getDimension(this, R.dimen.hotpush_title_txt_size));
            AnimationUtil.startCompleteAlphaAni(AnimationUtil.FULL_TO_ZERO, towardsIcon, AnimationUtil.DEFAULT_TIME, new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    innerText.setVisibility(View.VISIBLE);
                    AnimationUtil.startCompleteAlphaAni(AnimationUtil.ZERO_TO_FULL, innerText, AnimationUtil.DEFAULT_TIME, null);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    towardsIcon.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            toNextPage();
        }


    }

    private void toNextPage(){
        int item = creationStepContainer.getCurrentItem();
        creationStepContainer.setCurrentItem(item + 1);
    }

    @Override
    public void onClick(View v) {
        if( v == backBtn){
            this.finish();
        }else if( v == towardsLayout){
            int item = creationStepContainer.getCurrentItem();
            if(item == 0){
                startScaleAnimation(v);
                creationStepContainer.setCurrentItem(item+1);
            }else if(item == 1){
                actionClickOnPageOne();
            }else if(item == 2){

            }
        }
    }

}
