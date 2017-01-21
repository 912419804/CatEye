package com.franky.cateye.activity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.franky.cateye.R;
import com.franky.cateye.activity.base.CatActivity;
import com.franky.cateye.anim.DefaultAnimationListener;
import com.franky.cateye.view.FontTextView;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/1/19.
 * 启动欢迎页,该界面主要是由连续的动画组成
 */

public class SplashActivity extends CatActivity {

    @BindView(R.id.ll_title)
    LinearLayout ll_title;
    @BindView(R.id.iv_splash)
    ImageView iv_splash;
    @BindView(R.id.iv_eye_inner)
    ImageView iv_eye_inner;
    @BindView(R.id.iv_eye_outer)
    ImageView iv_eye_outer;
    @BindView(R.id.iv_eye_white)
    ImageView iv_eye_white;
    @BindView(R.id.fl_eye)
    FrameLayout fl_eye;
    @BindView(R.id.fl_title)
    FrameLayout fl_title;
    @BindView(R.id.ftv_title_black)
    FontTextView ftv_title_black;
    @BindView(R.id.ftv_title_black_en)
    FontTextView ftv_title_black_en;
    @BindView(R.id.ftv_title_black_cn)
    FontTextView ftv_title_black_cn;


    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_splash);
        final Animation landing_zoom_in = AnimationUtils.loadAnimation(this, R.anim.landing_zoom_in);
        final Animation landing_alpha_out = AnimationUtils.loadAnimation(this, R.anim.landing_alpha_out);
        final Animation landing_alpha_out_text_en = AnimationUtils.loadAnimation(this, R.anim.landing_alpha_out);
        final Animation landing_alpha_out_text_cn = AnimationUtils.loadAnimation(this, R.anim.landing_alpha_out);
        final Animation landing_alpha_in = AnimationUtils.loadAnimation(this, R.anim.landing_alpha_in);
        final Animation landing_translation_up = AnimationUtils.loadAnimation(this, R.anim.landing_translation_up);
        final Animation landing_rotate = AnimationUtils.loadAnimation(this, R.anim.landing_rotate);
        iv_splash.setAnimation(landing_zoom_in);
        landing_zoom_in.setAnimationListener(new DefaultAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                iv_eye_outer.setVisibility(View.VISIBLE);
                iv_eye_inner.setVisibility(View.VISIBLE);
                ftv_title_black.setVisibility(View.VISIBLE);
                iv_eye_outer.setAnimation(landing_alpha_out);
                iv_eye_inner.setAnimation(landing_alpha_out);
                ftv_title_black.setAnimation(landing_alpha_out);
                iv_splash.setAnimation(landing_alpha_in);
                ll_title.setAnimation(landing_translation_up);
            }
        });
        landing_translation_up.setAnimationListener(new DefaultAnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                super.onAnimationEnd(animation);
                ftv_title_black_en.setVisibility(View.VISIBLE);
                ftv_title_black_cn.setVisibility(View.VISIBLE);
                landing_alpha_out_text_en.setStartOffset(500);
                landing_alpha_out_text_cn.setStartOffset(1000);
                ftv_title_black_en.setAnimation(landing_alpha_out_text_en);
                ftv_title_black_cn.setAnimation(landing_alpha_out_text_cn);
                landing_rotate.setStartOffset(2000);
                iv_eye_inner.setAnimation(landing_rotate);
            }
        });
    }
}