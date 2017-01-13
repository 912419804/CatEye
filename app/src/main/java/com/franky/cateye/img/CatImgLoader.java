package com.franky.cateye.img;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/1/13.
 * 图片加载框架封装
 */

public class CatImgLoader {

    private static ImageLoader instance = new GlideLoader();

    private CatImgLoader(){ }
    /**
     * 可选操作
     */
    public static void init(Class<? extends ImageLoader> imageLoaderClass) {
        try {
            instance = imageLoaderClass.newInstance();
        }catch (Exception e){
            e.printStackTrace();
            instance = new GlideLoader();
        }

    }

    public static void load(Context context, String url, ImageView imageView) {
        instance.load(context,url,imageView);
    }
}
