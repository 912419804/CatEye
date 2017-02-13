package com.franky.cateye.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.franky.cateye.R;
import com.franky.cateye.adapter.GankDataAdapter;
import com.franky.cateye.api.IOSService;
import com.franky.cateye.base.CatActivity;
import com.franky.cateye.base.CatFragment;
import com.franky.cateye.base.CatWebActivity;
import com.franky.cateye.bean.GankData;
import com.franky.cateye.bean.GankResult;
import com.franky.cateye.http.Http;
import com.franky.cateye.utils.CatLog;

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
 * ios页面
 */

public class IOSFragment extends CatFragment {


    @BindView(R.id.rv_list)
    RecyclerView mRecyclerView;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout mRefreshLayout;
    private int page = 1;
    private GankDataAdapter mAdapter;
    private List<GankResult> mList = new ArrayList<>();
    private IOSService mIOSService;
    private Observable<GankData<List<GankResult>>> observable;
    private Disposable mDisposable;
    private View notDataView;
    private View errorView;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_ios;
    }

    @Override
    protected void onFirstUserVisible() {
        mRefreshLayout.setRefreshing(true);
        getData();
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {
    }


    @Override
    protected void initViewsAndEvents(View view) {
        notDataView = ((CatActivity) mContext).getLayoutInflater().inflate(R.layout.empty_view, (ViewGroup) mRecyclerView.getParent(), false);
        notDataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });
        errorView = ((CatActivity) mContext).getLayoutInflater().inflate(R.layout.error_view, (ViewGroup) mRecyclerView.getParent(), false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });
        mAdapter = new GankDataAdapter(mContext, mList);
        mIOSService = Http.create(IOSService.class);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.setRefreshing(true);
                page = 1;
                getData();
            }
        });
        mAdapter.setEnableLoadMore(true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mRecyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getData();
                    }
                }, 1000);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, CatWebActivity.class);
                intent.putExtra("url", mList.get(position).getUrl());
                startActivity(intent);
            }

        });
    }

    private void refreshData() {
        mRefreshLayout.setRefreshing(true);
        page = 1;
        getData();
    }
    public void getData() {
        observable = mIOSService.getData(10, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<GankData<List<GankResult>>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(GankData<List<GankResult>> data) {
                mRefreshLayout.setRefreshing(false);
                mAdapter.loadMoreComplete();
                if (page == 1) {
                    mList.clear();
                }
                mList.addAll(data.getResults());
                mAdapter.notifyDataSetChanged();
                page++;
            }

            @Override
            public void onError(Throwable e) {
                mRefreshLayout.setRefreshing(false);
                mAdapter.setEmptyView(errorView);
                CatLog.d("error>", e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });

    }


    @Override
    protected void DestroyViewAndThing() {
        observable.unsubscribeOn(AndroidSchedulers.mainThread());
        if (null != mDisposable){
            mDisposable.dispose();
        }
    }
}
