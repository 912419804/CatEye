package com.franky.cateye.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.franky.cateye.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/1/13.
 * Activity 基类
 * 某些方法和变量设置为public,可能用于包含的fragment中来调用
 */

public class CatActivity extends AppCompatActivity implements View.OnClickListener {


    public Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CatActivity.this.handleMessage(msg);
        }
    };

    /**
     * 获取第一个fragment
     */
    protected CatFragment getFirstFragment() {
        return null;
    }

    /**
     * 获取Intent,处理传递参数
     */
    protected boolean handleIntent(Intent intent) {

        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //将handleIntent放在前面是因为有时候我们需要判断传递的参数
        //是否正常,如果不合法那么终止界面

        super.onCreate(savedInstanceState);
            if (getIntent() != null && handleIntent(getIntent())) {
                initView();
                initFirstFragment();
                initData();
            } else {
                show(getString(R.string.toast_intent_data_error));
            }
        }

    @Override
    protected void onResume() {
        onResumeFragments();
        super.onResume();
    }

    private void initFirstFragment() {
        //避免重复添加Fragment
        if (null == getSupportFragmentManager().getFragments()) {
            CatFragment firstFragment = getFirstFragment();
            if (null != firstFragment) {
                addFragment(firstFragment);
            }
        }
    }

    protected void initData() {
    }

    protected void initView() {

    }

    /**
     * 添加fragment
     * 需要实现getFragmentContentId()方法
     *
     * @param fragment 待添加的fragment
     */
    protected CatFragment addFragment(CatFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(getFragmentContentId(), fragment, fragment.getClass().getSimpleName())
                    //暂时不添加到fragment栈中,参考微信等应用
//                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
        return fragment;
    }


    protected int getFragmentContentId() {
        return 0;
    }

    protected void handleMessage(Message msg) {

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    /**
     * 简化的startActivity方法
     *
     * @param cls Activity class
     */
    public void startActivity(Class<? extends Activity> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    /**
     * @param cls      Activity class
     * @param isFinish 是否结束当前页
     */
    public void startActivity(Class<? extends Activity> cls, boolean isFinish) {
        startActivity(cls);
        if (isFinish) finish();
    }

    /**
     * 简化的show Toast方法,默认short时间显示
     *
     * @param msg 消息
     */
    public void show(String msg) {
        show(msg, false);
    }

    /**
     * 简化的show Toast方法
     *
     * @param msg        消息
     * @param isShowLong 是否显示long时间
     */
    public void show(String msg, boolean isShowLong) {
        int time = isShowLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
        Toast.makeText(this, msg, time).show();
    }

    /**
     * 弹出Toast后，延迟关闭界面
     *
     * @param msg         消息
     * @param delayMillis 延迟时间,毫秒
     */
    public void show(String msg, long delayMillis) {
        show(msg);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, delayMillis);
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

    /**
     * loading动画
     *
     * @param isLoading true loading false loading over
     */
    public void loading(boolean isLoading) {

    }

    /**
     * 通用dialog弹窗
     */
    public void dialog() {
    }

    @Override
    protected void onDestroy() {
        //在界面destroy的时,移除所有的任务和回调,防止泄露
        mHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {

    }
}
