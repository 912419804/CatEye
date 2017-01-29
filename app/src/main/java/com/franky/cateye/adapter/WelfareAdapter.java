package com.franky.cateye.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.franky.cateye.R;
import com.franky.cateye.bean.Girl;
import com.franky.cateye.img.CatImgLoader;
import com.franky.cateye.view.ScaleImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/1/28.
 * AndroidFragment adapter
 */

public class WelfareAdapter extends BaseQuickAdapter<Girl, BaseViewHolder> {

    private Context mContext;

    public WelfareAdapter(Context context, List<Girl> list) {
        super(R.layout.item_welfare_list, list);
        this.mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, Girl item) {
        ScaleImageView imageView = helper.getView(R.id.girl_item_iv);
        imageView.setInitSize(item.getWidth(), item.getHeight());
        CatImgLoader.load(mContext, item.getUrl(), imageView);
    }

}
