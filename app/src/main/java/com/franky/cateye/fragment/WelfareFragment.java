package com.franky.cateye.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.franky.cateye.R;
import com.franky.cateye.adapter.WelfareAdapter;
import com.franky.cateye.api.GirlService;
import com.franky.cateye.base.CatFragment;
import com.franky.cateye.base.CatWebActivity;
import com.franky.cateye.bean.GankData;
import com.franky.cateye.bean.Girl;
import com.franky.cateye.http.Http;
import com.franky.cateye.service.DataService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/27.
 * 图片页面
 */

public class WelfareFragment extends CatFragment {

    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefreshLayout;
    private int page = 1;
    private WelfareAdapter mAdapter;
    private ArrayList<Girl> mList = new ArrayList<>();
    private GirlService mGirlService;

    @Override
    protected View getView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welfare, null, false);
    }

    @Override
    protected void initView() {
        super.initView();
        EventBus.getDefault().register(this);
        mAdapter = new WelfareAdapter(mCatActivity, mList);
        mGirlService = Http.create(GirlService.class);
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
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mCatActivity, CatWebActivity.class);
                intent.putExtra("url", mList.get(position).getUrl());
                startActivity(intent);
            }

        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //防止第一行到顶部有空白区域
                layoutManager.invalidateSpanAssignments();
            }
        });
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void initData() {
        super.initData();
        mGirlService.getData(10, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankData<ArrayList<Girl>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GankData<ArrayList<Girl>> girls) {
                        DataService.startService(mCatActivity,girls.getResults());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dataEvent(List<Girl> data) {
            if (page == 1){
                mList.clear();
            }
            page++;
            mList.addAll(data);
            if (page == 1){
                mAdapter.notifyDataSetChanged();
            }else {
                mAdapter.notifyItemInserted(data.size());
            }
            mAdapter.loadMoreComplete();
            mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }
}
