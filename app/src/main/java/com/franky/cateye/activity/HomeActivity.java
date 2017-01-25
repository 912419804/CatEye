package com.franky.cateye.activity;

import com.franky.cateye.R;
import com.franky.cateye.activity.base.CatActivity;
import com.franky.cateye.api.GirlService;
import com.franky.cateye.bean.Girl;
import com.franky.cateye.bean.Girls;
import com.franky.cateye.utils.Log;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        GirlService girlService = retrofit.create(GirlService.class);
        girlService.getData(10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Girls>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Girls girls) {
                        List<Girl> results = girls.getResults();
                        for (Girl girl : results) {
                            Log.d(girl);
                        }
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
