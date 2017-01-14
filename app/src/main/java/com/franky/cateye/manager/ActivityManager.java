package com.franky.cateye.manager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.franky.cateye.utils.Log;
import com.umeng.analytics.MobclickAgent;

/**
 * Created by Administrator on 2017/1/11.
 * Activity管理栈，单例模式,实现了生命周期回调
 */

public class ActivityManager implements Application.ActivityLifecycleCallbacks {

    private static ActivityManager instance;

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            synchronized (ActivityManager.class) {
                if (instance == null) {
                    instance = new ActivityManager();
                }
            }
        }
        return instance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.d(activity.getClass().getSimpleName(),"[Created]");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.d(activity.getClass().getSimpleName(),"[Started]");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        Log.d(activity.getClass().getSimpleName(),"[Resumed]");
        MobclickAgent.onResume(activity);//友盟
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Log.d(activity.getClass().getSimpleName(),"[Paused]");
        MobclickAgent.onPause(activity);

    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.d(activity.getClass().getSimpleName(),"[Stopped]");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Log.d(activity.getClass().getSimpleName(),"[SaveInstanceState]");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(activity.getClass().getSimpleName(),"[Destroyed]");
    }
}
