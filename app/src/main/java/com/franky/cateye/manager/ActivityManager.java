package com.franky.cateye.manager;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.franky.cateye.utils.CatLog;
import com.umeng.analytics.MobclickAgent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2017/1/11.
 * Activity管理栈，单例模式,实现了生命周期回调
 */

public class ActivityManager implements Application.ActivityLifecycleCallbacks {

    private static ActivityManager instance;
    /**
     * 计算activity数量
     **/
    private static volatile AtomicInteger ACTIVITY_COUNT = new AtomicInteger(0);

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
        ACTIVITY_COUNT.getAndIncrement();
        CatLog.d(activity.getClass().getSimpleName(), "[Created]");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        CatLog.d(activity.getClass().getSimpleName(), "[Started]");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        CatLog.d(activity.getClass().getSimpleName(), "[Resumed]");
        MobclickAgent.onResume(activity);//友盟
    }

    @Override
    public void onActivityPaused(Activity activity) {
        CatLog.d(activity.getClass().getSimpleName(), "[Paused]");
        MobclickAgent.onPause(activity);

    }

    @Override
    public void onActivityStopped(Activity activity) {
        ACTIVITY_COUNT.getAndDecrement();
        CatLog.d(activity.getClass().getSimpleName(), "[Stopped]");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        CatLog.d(activity.getClass().getSimpleName(), "[SaveInstanceState]");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        CatLog.d(activity.getClass().getSimpleName(), "[Destroyed]");
    }
}
