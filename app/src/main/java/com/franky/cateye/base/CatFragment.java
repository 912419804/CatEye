package com.franky.cateye.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/1/26.
 * fragment 基类
 */

public class CatFragment extends Fragment implements View.OnClickListener {

    protected CatActivity mCatActivity;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        if (context instanceof CatActivity) {
            mCatActivity = (CatActivity) context;
            super.onAttach(context);
        } else {
            throw new IllegalArgumentException("context must be CatActivity!");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        handleBundle(savedInstanceState);
        super.onCreate(savedInstanceState);
    }

    /**
     * 处理fragment携带参数的情况
     * @param savedInstanceState bundle
     */
    protected void handleBundle(Bundle savedInstanceState) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = getView(inflater, savedInstanceState);
        unbinder = ButterKnife.bind(this,rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    protected void initView() {
    }

    protected View getView(LayoutInflater inflater, @Nullable Bundle savedInstanceState) {
        return null;
    }

    public void initData() {
    }


    /**
     * 同时设置多个view的onClickListener方法
     *
     * @param views 需要设置的 view
     */
    protected void setMoreOnclickListener(View... views) {
        for (View view : views) {
            if (view != null) {
                view.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {

    }
}