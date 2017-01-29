package com.franky.cateye.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/1/29.
 */

public class MyStaggerLayoutManager extends StaggeredGridLayoutManager {

    public MyStaggerLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    public MyStaggerLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void collectAdjacentPrefetchPositions(int dx, int dy, RecyclerView.State state, LayoutPrefetchRegistry layoutPrefetchRegistry) {
        try {
            super.collectAdjacentPrefetchPositions(dx, dy, state, layoutPrefetchRegistry);
        } catch (IllegalArgumentException e) {
        }
    }
}