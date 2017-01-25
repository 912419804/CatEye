package com.franky.cateye.bean;

/**
 * Created by Administrator on 2017/1/25.
 * 通用数据封装类
 */

public class Data <T>{
    private boolean error;
    private T results;

    public boolean isError() {
        return error;
    }

    public T getResults() {
        return results;
    }
}
