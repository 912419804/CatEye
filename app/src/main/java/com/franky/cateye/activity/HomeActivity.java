package com.franky.cateye.activity;

import android.widget.FrameLayout;

import com.franky.cateye.R;
import com.franky.cateye.base.CatActivity;
import com.franky.cateye.fragment.AndroidFragment;
import com.franky.cateye.fragment.IOSFragment;
import com.franky.cateye.fragment.VideoFragment;
import com.franky.cateye.fragment.WelfareFragment;
import com.franky.cateye.view.tab.TabItem;
import com.franky.cateye.view.tab.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 应用首页
 */

public class HomeActivity extends CatActivity implements TabLayout.OnTabClickListener {


    @BindView(R.id.fl_main)
    FrameLayout fl_main;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    ArrayList<TabItem> tabs = new ArrayList<TabItem>();

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initData() {
        super.initData();
        tabs.add(new TabItem(R.drawable.selector_tab_triangle, R.string.fragment_android, AndroidFragment.class));
        tabs.add(new TabItem(R.drawable.selector_tab_circle, R.string.fragment_ios, IOSFragment.class));
        tabs.add(new TabItem(R.drawable.selector_tab_cross, R.string.fragment_welfare, WelfareFragment.class));
        tabs.add(new TabItem(R.drawable.selector_tab_square, R.string.fragment_video, VideoFragment.class));
        tab_layout.initData(tabs, this);
        onTabClick(tabs.get(0));
    }

    @Override
    public void onTabClick(TabItem tabItem) {
        tab_layout.setCurrentTab(tabs.indexOf(tabItem));
        addFragment(tabItem.initView());
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.fl_main;
    }
}
