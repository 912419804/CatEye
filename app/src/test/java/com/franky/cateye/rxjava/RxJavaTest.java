package com.franky.cateye.rxjava;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/1/23.
 * 测试 RxJava 1.1.1 版本
 * 和2.x版本包名不一样
 */

public class RxJavaTest {

    @Test
    public void testRxJava1() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello");
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @Test
    public void testRxJava2() {
        List<String> list = new ArrayList<>();
        list.add("hello");
        list.add("world");
        Observable.from(list)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        System.out.println(s);
                    }
                });
    }


}
