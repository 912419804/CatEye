package com.franky.cateye.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.franky.cateye.R;
import com.franky.cateye.base.CatActivity;
import com.franky.cateye.base.CatFragment;
import com.franky.cateye.fragment.AndroidFragment;
import com.franky.cateye.fragment.IOSFragment;
import com.franky.cateye.fragment.VideoFragment;
import com.franky.cateye.fragment.WelfareFragment;
import com.franky.cateye.receiver.NetworkReceiver;
import com.franky.cateye.utils.CatLog;
import com.franky.cateye.utils.NetworkUtils;
import com.franky.cateye.view.tab.TabItem;
import com.franky.cateye.view.tab.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 应用首页
 */

public class HomeActivity extends CatActivity implements TabLayout.OnTabClickListener, NetworkReceiver.NetworkStateListener {

    @BindView(R.id.fl_main)
    FrameLayout fl_main;
    @BindView(R.id.tab_layout)
    TabLayout tab_layout;
    int index = 0;
    int lastIndex = 0;
    ArrayList<TabItem> tabs = new ArrayList<TabItem>(4);
    int[] titles = new int[]{R.string.fragment_android, R.string.fragment_ios, R.string.fragment_welfare, R.string.fragment_video};
    CatFragment[] fragments = new CatFragment[4];
    FragmentManager fm;
    private boolean isFirst = true;
    private NetworkReceiver nwr;

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_home);
        if (!NetworkUtils.isAvailableByPing()){
            nwr = new NetworkReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            nwr.setNetworkStateListener(this);
            registerReceiver(nwr, intentFilter);
        }
    }


    @Override
    protected void initData() {
        fm = getSupportFragmentManager();
        fragments[0] = new AndroidFragment();
        fragments[1] = new IOSFragment();
        fragments[2] = new WelfareFragment();
        fragments[3] = new VideoFragment();
        tabs.add(new TabItem(R.drawable.selector_tab_triangle, titles[0]));
        tabs.add(new TabItem(R.drawable.selector_tab_circle, titles[1]));
        tabs.add(new TabItem(R.drawable.selector_tab_cross, titles[2]));
        tabs.add(new TabItem(R.drawable.selector_tab_square, titles[3]));
        tab_layout.initData(tabs, this);
        onTabClick(tabs.get(index));
    }

    @Override
    public void onTabClick(TabItem tabItem) {
        lastIndex = index;
        index = tabs.indexOf(tabItem);
        tab_layout.setCurrentTab(index);
        setTitle(titles[index]);
        addAndChangeFragment(fragments[index]);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {

    }

    public void addAndChangeFragment(Fragment fragment) {
        FragmentTransaction ft = fm.beginTransaction();
        if (isFirst) {
            ft.add(R.id.fl_main, fragment, fragment.getClass().getSimpleName());
            fragments[index].setUserVisibleHint(true);
            isFirst = false;
        } else {
            if (fragment.equals(fragments[lastIndex])) {
                return;
            }
            ft.hide(fragments[lastIndex]);
            fragments[lastIndex].setUserVisibleHint(false);
            if (fragment.isAdded()) {
                ft.show(fragment);
            } else {
                ft.add(R.id.fl_main, fragment, fragment.getClass().getSimpleName());
            }
            fragments[index].setUserVisibleHint(true);
        }
        ft.commit();
    }

    @Override
    protected void onDestroy() {
        if (null != nwr) {
            unregisterReceiver(nwr);
        }
        super.onDestroy();
    }

    @Override
    public void onNetworkConnected() {
        CatLog.d("network","okok");
        for (CatFragment fragment : fragments) {
            if (fragment.isAdded()){
                fragment.refreshData();
            }
        }
    }
}
