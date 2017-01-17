package com.franky.cateye.application;

import android.app.Application;
import android.content.Context;

import com.franky.cateye.BuildConfig;
import com.franky.cateye.img.CatImgLoader;
import com.franky.cateye.img.GlideLoader;
import com.franky.cateye.manager.ActivityManager;
import com.franky.cateye.utils.ApplicationUtil;
import com.franky.cateye.utils.Log;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.CrashReport.UserStrategy;

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
        initImageLoader();
        initMonitor();
    }

    /**
     * 注册Activity生命周期回调方法，用于ActivityManager进行Activity的管理
     * 并加入了友盟统计
     */
    private void registerActivityLifecycleCallbacks() {
        registerActivityLifecycleCallbacks(ActivityManager.getInstance());
    }

    /**
     * 图片框架初始化,传入相应的ImageLoader实现类即可
     * 可选Glide,Fresco,Picasso
     */
    private void initImageLoader() {
        CatImgLoader.init(GlideLoader.class);
    }

    /**
     * 设置Logger框架的配置(可选)
     */
    private void initLogSetting() {
        //将全局Log的Tag设置为应用名
        Log.init(ApplicationUtil.getApplicationName(this));
    }

    /**
     * 腾讯bugly统计
     */
    private void initMonitor() {
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = ApplicationUtil.getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        UserStrategy strategy = new UserStrategy(context);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(getApplicationContext(), "9baab4dc67", BuildConfig.DEBUG);
    }
}
