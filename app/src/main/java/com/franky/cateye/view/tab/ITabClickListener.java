package com.franky.cateye.view.tab;

import com.franky.cateye.base.CatFragment;

/**
 * Created by yx on 16/4/6.
 */
public interface ITabClickListener {


    void onMenuItemClick();

    CatFragment getFragment();
}
