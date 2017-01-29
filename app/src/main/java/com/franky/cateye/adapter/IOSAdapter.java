package com.franky.cateye.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.franky.cateye.R;
import com.franky.cateye.bean.IOS;
import com.franky.cateye.img.CatImgLoader;

import java.util.List;

/**
 * Created by Administrator on 2017/1/28.
 * AndroidFragment adapter
 */

public class IOSAdapter extends BaseQuickAdapter<IOS, BaseViewHolder> {

    private Context mContext;

    public IOSAdapter(Context context, List<IOS> list) {
        super(R.layout.item_android_list, list);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, IOS item) {
        helper.setText(R.id.tv_title, item.getDesc());
        ImageView view = helper.getView(R.id.iv_title_img);
        if (item.getImages() != null) {
            CatImgLoader.load(mContext, item.getImages().get(0), view);
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.GONE);
        }
    }

}
