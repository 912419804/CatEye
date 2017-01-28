package com.franky.cateye.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import com.franky.cateye.R;
import com.franky.cateye.base.CatFragment;

/**
 * Created by Administrator on 2017/1/27.
 * 视频页面
 */

public class VideoFragment extends CatFragment {

    @Override
    protected View getView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_video,null,false);
    }
}
