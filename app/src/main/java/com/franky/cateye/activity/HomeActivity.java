package com.franky.cateye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.franky.cateye.R;
import com.franky.cateye.activity.base.CatActivity;
import com.franky.cateye.utils.Log;
import com.orhanobut.logger.Logger;

import static com.franky.cateye.Constante.TAG_EXIT;

public class HomeActivity extends CatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("hello");
        Log.e("hello");
        Log.w("hello");
        Log.v("hello");
        Log.wtf("hello");
        Log.json("{'abc':'123'}");
        Log.xml("<A><b>xml test</b></A>");
        Log.log(Logger.DEBUG, "tag", "message", new Throwable());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            //获取退出标记,来退出app
            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
            if (isExit) {
                this.finish();
            }
        }
    }

    private boolean mIsExit;

    @Override
    /** * 双击返回键退出 */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                this.finish();
            } else {
                show("再按一次退出");
                mIsExit = true;
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
