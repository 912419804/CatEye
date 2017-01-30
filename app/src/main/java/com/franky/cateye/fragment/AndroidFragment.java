package com.franky.cateye.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.franky.cateye.R;
import com.franky.cateye.adapter.AndroidAdapter;
import com.franky.cateye.api.AndroidService;
import com.franky.cateye.base.CatFragment;
import com.franky.cateye.base.CatWebActivity;
import com.franky.cateye.bean.Android;
import com.franky.cateye.bean.GankData;
import com.franky.cateye.http.Http;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/27.
 * android 页面
 */

public class AndroidFragment extends CatFragment {

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefreshLayout;
    private int page = 1;
    private AndroidAdapter mAdapter;
    private List<Android> mList = new ArrayList<>();
    private AndroidService mAndroidService;
    private Observable<GankData<List<Android>>> observable;

    @Override
    protected View getView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_android, null, false);
    }

    @Override
    protected void initView() {
        super.initView();
        mAdapter = new AndroidAdapter(mCatActivity, mList);
        mAndroidService = Http.create(AndroidService.class);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(true);
                page = 1;
                initData();
            }
        });
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initData();
                    }
                }, 1000);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mCatActivity));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mCatActivity, CatWebActivity.class);
                intent.putExtra("url", mList.get(position).getUrl());
                startActivity(intent);
            }

        });
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void initData() {
        super.initData();
        observable = mAndroidService.getData(10, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<GankData<List<Android>>>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(GankData<List<Android>> androids) {
                mRefreshLayout.setRefreshing(false);
                mAdapter.loadMoreComplete();
                if (page == 1) {
                    mList.clear();
                }
                mList.addAll(androids.getResults());
                mAdapter.notifyDataSetChanged();
                page++;
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    @Override
    public void onStop() {
        observable.unsubscribeOn(AndroidSchedulers.mainThread());
        super.onStop();
    }
}