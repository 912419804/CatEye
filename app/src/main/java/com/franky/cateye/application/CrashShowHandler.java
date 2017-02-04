package com.franky.cateye.application;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.franky.cateye.activity.CrashShowActivity;
import com.franky.cateye.utils.CatLog;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p/>
 * <p>DEBUG情况下的异常捕捉,便于调试. <p>
 * RELEASE 发布版的异常捕捉
 */
public class CrashShowHandler implements Thread.UncaughtExceptionHandler {

    private Context mAppContext;

    public CrashShowHandler(Context context) {
        mAppContext = context.getApplicationContext();
    }

    public static String getProcessName(Context appContext) {
        String currentProcessName = "";
        int pid = android.os.Process.myPid();
        android.app.ActivityManager manager = (android.app.ActivityManager) appContext.getSystemService(Context.ACTIVITY_SERVICE);
        for (android.app.ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                currentProcessName = processInfo.processName;
                break;
            }
        }
        return currentProcessName;
    }

    private String LINE_SEPARATOR = "\n";

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        String processName = getProcessName(mAppContext);
        if (mAppContext.getPackageName().equals(processName)) {
            StringWriter stackTrace = new StringWriter();
            ex.printStackTrace(new PrintWriter(stackTrace));
            StringBuilder errorReport = new StringBuilder();
            StringBuilder diviceErrorReport = new StringBuilder();
//            errorReport.append("************ 异常信息 ************\n\n");
            errorReport.append(stackTrace.toString());

            diviceErrorReport.append("\n************ 设备信息 ***********\n");
            diviceErrorReport.append("生产厂家(Brand): ");
            diviceErrorReport.append(Build.BRAND);
            diviceErrorReport.append(LINE_SEPARATOR);
            diviceErrorReport.append("设备(Device): ");
            diviceErrorReport.append(Build.DEVICE);
            diviceErrorReport.append(LINE_SEPARATOR);
            diviceErrorReport.append("型号(Model): ");
            diviceErrorReport.append(Build.MODEL);
            diviceErrorReport.append(LINE_SEPARATOR);
            diviceErrorReport.append("Id: ");
            diviceErrorReport.append(Build.ID);
            diviceErrorReport.append(LINE_SEPARATOR);
            diviceErrorReport.append("产品名(Product): ");
            diviceErrorReport.append(Build.PRODUCT);
            diviceErrorReport.append(LINE_SEPARATOR);
            diviceErrorReport.append("\n************ 硬件信息 ************\n");
            diviceErrorReport.append("SDK: ");
            diviceErrorReport.append(Build.VERSION.SDK_INT);
            diviceErrorReport.append(LINE_SEPARATOR);
            diviceErrorReport.append("安卓版本(Release): ");
            diviceErrorReport.append(Build.VERSION.RELEASE);
            diviceErrorReport.append(LINE_SEPARATOR);
            diviceErrorReport.append("版本号(Incremental): ");
            diviceErrorReport.append(Build.VERSION.INCREMENTAL);
            diviceErrorReport.append(LINE_SEPARATOR);
            Intent intent = new Intent(mAppContext, CrashShowActivity.class);
            /**
             *
             * http://chintanrathod.com/auto-restart-application-after-crash-forceclose-in-android/
             *
             * Here,
             *
             * Intent.FLAG_ACTIVITY_CLEAR_TOP is used because
             *
             * If set, and the activity being launched is already running in the current task, then instead of launching a new instance of that activity, all of the other activities on top of it will be closed and this Intent will be delivered to the (now on top) old activity as a new Intent.
             *
             * Intent.FLAG_ACTIVITY_CLEAR_TASK is used because
             *
             * Flag will cause any existing task that would be associated with the activity to be cleared before the activity is started.
             *
             * Intent.FLAG_ACTIVITY_NEW_TASK is used because
             *
             * If set, this activity will become the start of a new task on this history stack. A task (from the activity that started it to the next task activity) defines an atomic group of activities that the user can move to.
             */
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("information", errorReport.toString());
            intent.putExtra("deviceinformation", diviceErrorReport.toString());
            intent.putExtra("mypid", android.os.Process.myPid() + " - Thread " + android.os.Process.getThreadPriority(android.os.Process.myTid()));
            mAppContext.startActivity(intent);
            CatLog.d("crash","show!!!!!!!!!!!!!!!!");
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(10);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
