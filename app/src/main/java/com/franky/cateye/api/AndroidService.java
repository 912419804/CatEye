package com.franky.cateye.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/1/24.
 * Gank.io中的分类接口中的android数据
 */

public interface AndroidService {

//    http://gank.io/api/data/Android/10/1

    @GET("data/Android/{num}/{page}")
    Observable<String> getData(@Path("num") int num, @Path("page") int page);
}
