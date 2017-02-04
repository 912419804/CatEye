package com.franky.cateye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.franky.cateye.R;
import com.franky.cateye.base.CatActivity;


/**
 * 程序崩溃时展示异常信息的界面，主要用于测试时展示错误
 */
public class CrashShowActivity extends CatActivity {
    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uncaught_exception);
        ((TextView) findViewById(R.id.discription)).setText(getIntent().getStringExtra("information"));
        ((TextView) findViewById(R.id.device_discription)).setText(getIntent().getStringExtra("deviceinformation"));
        ((TextView) findViewById(R.id.feed_back)).setText("出现这个界面说明应用已经崩溃了~\n 之前进程id " + getIntent().getStringExtra("mypid") +
                "; 现在进程id " + android.os.Process.myPid() + " - Thread " + android.os.Process.getThreadPriority(android.os.Process.myTid())
        );
        findViewById(R.id.discription).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFinish();
            }
        });
    }

    private void checkFinish() {
        if ((System.currentTimeMillis() - exitTime) > 500) {
            exitTime = System.currentTimeMillis();
        } else {
            startActivity(new Intent(CrashShowActivity.this, SplashActivity.class));
            this.finish();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(CrashShowActivity.this, SplashActivity.class));
        this.finish();
    }
}
