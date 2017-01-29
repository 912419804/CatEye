package com.franky.cateye.img;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;

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

    /**
     * 需要在子线程执行
     *
     * @param context
     * @param url
     * @return
     */
    public static Bitmap load(Context context, String url) {
        try {
            return Glide.with(context)
                    .load(url)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
