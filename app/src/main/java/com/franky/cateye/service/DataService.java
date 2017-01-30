package com.franky.cateye.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.franky.cateye.bean.Girl;
import com.franky.cateye.http.img.CatImgLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/29.
 */

public class DataService extends IntentService {
    public DataService() {
        super("");
    }

    public static void startService(Context context, ArrayList<Girl> list) {
        Intent intent = new Intent(context, DataService.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("data",list);
        intent.putExtras(bundle);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        List<Girl> datas = (List<Girl>) intent.getSerializableExtra("data");
        handleGirlItemData(datas);
    }

    private void handleGirlItemData(List<Girl> datas) {
        if (datas.size() == 0) {
            EventBus.getDefault().post("finish");
            return;
        }
        for (Girl data : datas) {
            Bitmap bitmap = CatImgLoader.load(this, data.getUrl());

            if (bitmap != null) {
                data.setWidth(bitmap.getWidth());
                data.setHeight(bitmap.getHeight());
            }

        }
        EventBus.getDefault().post(datas);
    }
}
