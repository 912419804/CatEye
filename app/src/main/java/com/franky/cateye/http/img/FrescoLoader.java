package com.franky.cateye.http.img;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.widget.ImageView;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.CloseableBitmap;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;

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

    @Override
    public void load(Context context, int resId, ImageView imageView) {
        imageView.setImageResource(resId);
    }

    @Override
    public Bitmap load(Context context, String url) {
        Uri uri = Uri.parse(url);
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        ImageRequest imageRequest = ImageRequest.fromUri(uri);
        DataSource<CloseableReference<CloseableImage>> dataSource =
                imagePipeline.fetchImageFromBitmapCache(imageRequest, CallerThreadExecutor.getInstance());
        try {
            CloseableReference<CloseableImage> imageReference = dataSource.getResult();
            if (imageReference != null) {
                try {
                    CloseableBitmap image = (CloseableBitmap) imageReference.get();
                    // do something with the image
                    Bitmap loadedImage = image.getUnderlyingBitmap();
                    if (loadedImage != null) {
                        return loadedImage;
                    } else {
                        return null;
                    }
                } finally {
                    CloseableReference.closeSafely(imageReference);
                }
            }
        } finally {
            dataSource.close();
        }
        return null;
    }
}
