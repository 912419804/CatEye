package com.franky.cateye.http.img;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/1/13.
 * 图片加载框架封装
 */

public class CatImgLoader {

    private static ImageLoader instance = new GlideLoader();

    private CatImgLoader() {
    }

    /**
     * 可选操作
     */
    public static void init(Class<? extends ImageLoader> imageLoaderClass) {
        try {
            instance = imageLoaderClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            instance = new GlideLoader();
        }

    }

    public static void load(Context context, String url, ImageView imageView) {
        instance.load(context, url, imageView);
    }
    public static void load(Context context, int resId, ImageView imageView) {
        instance.load(context, resId, imageView);
    }

    /**
     * 需要在子线程执行
     *
     * @param context
     * @param url
     * @return
     */
    public static Bitmap load(Context context, String url) {
        return instance.load(context, url);
    }
}
