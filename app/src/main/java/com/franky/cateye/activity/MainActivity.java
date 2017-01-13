package com.franky.cateye.activity;

import android.os.Bundle;

import com.franky.cateye.R;
import com.franky.cateye.base.CatActivity;
import com.franky.cateye.utils.Log;
import com.orhanobut.logger.Logger;

public class MainActivity extends CatActivity {

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
}
