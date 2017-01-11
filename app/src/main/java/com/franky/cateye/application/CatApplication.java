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
        //注册Activity生命周期，用于ActivityManager进行Activity的管理
        registerActivityLifecycleCallbacks(ActivityManager.getInstance());
    }
}
