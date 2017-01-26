package com.franky.cateye.view.tab;

import com.franky.cateye.fragment.base.CatFragment;

/**
 * Created by yx on 16/4/3.
 */
public class TabItem {

    /**
     * icon
     */
    public int imageResId;
    /**
     * 文本
     */
    public int lableResId;


    public Class<? extends CatFragment> tagFragmentClz;

    public TabItem(int imageResId, int lableResId) {
        this.imageResId = imageResId;
        this.lableResId = lableResId;
    }


    public TabItem(int imageResId, int lableResId, Class<? extends CatFragment> tagFragmentClz) {
        this.imageResId = imageResId;
        this.lableResId = lableResId;
        this.tagFragmentClz = tagFragmentClz;
    }

    public void initView() {
        try {
            this.tagFragmentClz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



