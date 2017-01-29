package com.franky.cateye.api;

import com.franky.cateye.bean.GankData;
import com.franky.cateye.bean.IOS;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/1/24.
 * Gank.io中的分类接口中的android数据
 */

public interface IOSService {

//    http://gank.io/api/data/iOS/10/1

    @GET("data/iOS/{num}/{page}")
    Observable<GankData<List<IOS>>> getData(@Path("num") int num, @Path("page") int page);
}
