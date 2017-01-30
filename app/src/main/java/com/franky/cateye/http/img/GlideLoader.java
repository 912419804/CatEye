package com.franky.cateye.http.img;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2017/1/13.
 * Glide 框架实现
 */

public class GlideLoader implements ImageLoader {


    @Override
    public void load(Context context, String url, ImageView imageView) {
        Glide.with(context)
             .load(url)
             .into(imageView);
    }



}
