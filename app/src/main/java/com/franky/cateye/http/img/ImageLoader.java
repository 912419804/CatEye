package com.franky.cateye.http.img;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/1/13.
 * 图片加载接口
 */

public interface ImageLoader {

    /**
     * 加载图片
     * @param context 上下文
     * @param url 图片URL
     * @param imageView ImageView控件
     */
    void load(Context context, String url, ImageView imageView);



}
