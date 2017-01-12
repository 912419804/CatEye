package com.franky.cateye.application;

import android.app.Application;

import com.franky.cateye.manager.ActivityManager;

/**
 * Created by Administrator on 2017/1/11.
 * 自定义的application
 */

public class CatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks();
        initLogSetting();
    }

    /**
     * 注册Activity生命周期回调方法，用于ActivityManager进行Activity的管理
     */
    private void registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(ActivityManager.getInstance());
    }

    /**
     * 设置Logger框架的配置(可选)
     */
    private void initLogSetting() {
        // TODO: 2017/1/12
    }
}
