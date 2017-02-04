package com.franky.cateye.utils;

import com.franky.cateye.BuildConfig;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.Printer;
import com.orhanobut.logger.Settings;

/**
 * Created by Administrator on 2017/1/12.
 * Log打印,使用自己定义BuildConfig.LOG_DEBUG来动态控制日志输出
 */

public class CatLog {
    public static boolean isDebug = BuildConfig.LOG_DEBUG;

    /**
     * It is used to get the settings object in order to change settings
     *
     * @return the settings object
     */
    public static Settings init() {
        return Logger.init();
    }

    /**
     * It is used to change the tag
     *
     * @param tag is the given string which will be used in Logger as TAG
     */
    public static Settings init(String tag) {
        return Logger.init(tag);
    }

    public static void resetSettings() {
        Logger.resetSettings();
    }

    public static Printer t(String tag) {
        return Logger.t(tag);
    }

    public static Printer t(int methodCount) {
        return Logger.t(methodCount);
    }

    public static Printer t(String tag, int methodCount) {
        return Logger.t(tag, methodCount);
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        if (isDebug) Logger.log(priority, tag, message, throwable);
    }

    public static void v(String message, Object... args) {
        if (isDebug) Logger.v(message, args);
    }

    public static void i(String message, Object... args) {
        if (isDebug) Logger.i(message, args);
    }

    public static void d(String message, Object... args) {
        if (isDebug) Logger.d(message, args);
    }

    public static void d(Object object) {
        if (isDebug) Logger.d(object);
    }

    public static void w(String message, Object... args) {
        if (isDebug) Logger.w(message, args);
    }

    public static void e(String message, Object... args) {
        if (isDebug) Logger.e(message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if (isDebug) Logger.e(throwable, message, args);
    }

    public static void wtf(String message, Object... args) {
        if (isDebug) Logger.wtf(message, args);
    }


    public static void json(String json) {
        if (isDebug) Logger.json(json);
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        if (isDebug) Logger.xml(xml);
    }

    //提供原生Log来打印Activity的生命周期回调
    public static void d(String tag,String message){
        if (isDebug) android.util.Log.d(tag,message);
    }

}
