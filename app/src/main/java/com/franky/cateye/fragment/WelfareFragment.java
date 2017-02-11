package com.franky.cateye.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import io.reactivex.Observable;
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
    private Observable<GankData<ArrayList<Girl>>> observable;
    private Disposable mDisposable;
    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_welfare;
    }

    @Override
    protected void onFirstUserVisible() {
        mRefreshLayout.setRefreshing(true);
        initData();
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {
    }

    @Override
    protected void initViewsAndEvents(View view) {
        EventBus.getDefault().register(this);
        mAdapter = new WelfareAdapter(mContext, mList);
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
                Intent intent = new Intent(mContext, CatWebActivity.class);
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
    }

    public void initData() {
        observable = mGirlService.getData(10, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<GankData<ArrayList<Girl>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(GankData<ArrayList<Girl>> girls) {
                DataService.startService(mContext, girls.getResults());
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
        if (page == 1) {
            mList.clear();
        }
        page++;
        mList.addAll(data);
        if (page == 1) {
            mAdapter.notifyDataSetChanged();
        } else {
            mAdapter.notifyItemInserted(data.size());
        }
        mAdapter.loadMoreComplete();
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void DestroyViewAndThing() {
        observable.unsubscribeOn(AndroidSchedulers.mainThread());
            observable.unsubscribeOn(AndroidSchedulers.mainThread());
            if (null != mDisposable){
                mDisposable.dispose();
            }
        EventBus.getDefault().unregister(this);
    }
}
