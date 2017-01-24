package com.franky.cateye.activity;

import com.franky.cateye.R;
import com.franky.cateye.activity.base.CatActivity;
import com.franky.cateye.api.AndroidService;
import com.franky.cateye.utils.Log;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 应用首页
 */

public class HomeActivity extends CatActivity {

    String baseUrl = "http://gank.io/api/";

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_main);
        getData();
    }

    private void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        AndroidService androidService = retrofit.create(AndroidService.class);
        androidService.getData(10,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.json(s);
                    }
                });

    }
}
