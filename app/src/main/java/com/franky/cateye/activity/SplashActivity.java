package com.franky.cateye.activity;

import android.widget.LinearLayout;

import com.franky.cateye.R;
import com.franky.cateye.base.CatActivity;
import com.franky.cateye.utils.Log;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/1/19.
 * 启动欢迎页
 */

public class SplashActivity extends CatActivity {

    @BindView(R.id.ll_splash) LinearLayout ll_splash;

    @Override
    protected void initView() {
        super.initView();
        Log.d("XXX");
//        setContentView(R.layout.activity_splash);
//        Animation landing_zoom_in = AnimationUtils.loadAnimation(this,R.anim.landing_zoom_in);
//        ll_splash.setAnimation(landing_zoom_in);
    }
}
