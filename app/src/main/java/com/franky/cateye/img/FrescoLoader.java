package com.franky.cateye.img;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/1/17.
 * Fresco框架实现
 */

public class FrescoLoader implements ImageLoader {
    @Override
    public void load(Context context, String url, ImageView imageView) {
        Uri uri = Uri.parse(url);
        imageView.setImageURI(uri);
    }
}
