package com.franky.cateye.activity;

import android.widget.FrameLayout;

import com.franky.cateye.R;
import com.franky.cateye.activity.base.CatActivity;
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
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initData() {
        super.initData();
        tabs.add(new TabItem(R.drawable.selector_tab_triangle, R.string.android));
        tabs.add(new TabItem(R.drawable.selector_tab_circle, R.string.ios));
        tabs.add(new TabItem(R.drawable.selector_tab_cross, R.string.welfare));
        tabs.add(new TabItem(R.drawable.selector_tab_square, R.string.video));
        tab_layout.initData(tabs, this);
        tab_layout.setCurrentTab(0);
    }

    @Override
    public void onTabClick(TabItem tabItem) {
        tab_layout.setCurrentTab(tabs.indexOf(tabItem));
    }

    //    @Override
//    protected void initData() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Constants.BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//        GirlService girlService = retrofit.create(GirlService.class);
//        girlService.getData(10, 1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Girls>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                    }
//
//                    @Override
//                    public void onNext(Girls girls) {
//                        List<Girl> results = girls.getResults();
//                        for (Girl girl : results) {
//                            Log.d(girl);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//    }
}
