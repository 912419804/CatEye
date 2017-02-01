package com.franky.cateye.view.loading;

/**
 * Created by Administrator on 2017/2/1.
 * loading统一页面接口
 */

public interface LoadingView {

    /**
     *
     * @return 是否正在加载
     */
    boolean isLoading();

    /**
     * 加载开始或结束
     * @param isLoading true 开始 false 结束
     */
    void loading(boolean isLoading);

}
