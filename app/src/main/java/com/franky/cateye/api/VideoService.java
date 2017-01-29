package com.franky.cateye.api;

import com.franky.cateye.bean.Girl;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/1/25.
 * Gank.io中的分类接口中的图片数据
 */

public interface VideoService {
    //    http://gank.io/api/data/福利/10/1

    @GET("data/休息视频/{num}/{page}")
    Observable<List<Girl>> getData(@Path("num") int num, @Path("page") int page);
}
