package com.franky.cateye.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.franky.cateye.Constants;
import com.franky.cateye.R;
import com.franky.cateye.api.AndroidService;
import com.franky.cateye.base.CatFragment;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/1/27.
 * android 页面
 */

public class AndroidFragment extends CatFragment {

    @Override
    protected View initView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_android,null,false);
    }

    @Override
    protected void initData() {
        super.initData();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        AndroidService androidService = retrofit.create(AndroidService.class);
        androidService.getData(10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String s) {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
