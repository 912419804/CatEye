package com.franky.cateye.http.img;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2017/1/13.
 * Picasso框架实现
 */

public class PicassoLoader implements ImageLoader {


    @Override
    public void load(Context context, String url, ImageView imageView) {
        Picasso.with(context)
                .load(url)
                .into(imageView);
    }

    @Override
    public void load(Context context, int resId, ImageView imageView) {
        Picasso.with(context)
                .load(resId)
                .into(imageView);
    }

    @Override
    public Bitmap load(Context context, String url) {
        try {
            return Picasso.with(context)
                    .load(url)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
