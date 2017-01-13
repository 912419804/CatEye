package com.franky.cateye.img;

import android.content.Context;
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
}
