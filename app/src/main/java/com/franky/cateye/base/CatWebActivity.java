package com.franky.cateye.base;

import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.franky.cateye.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/1/28.
 * 封装的 WebView Activity
 */

public class CatWebActivity extends CatActivity {

    @BindView(R.id.wv_web)
    WebView mWebView;
    private String url;

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_webview);
    }

    @Override
    protected boolean handleIntent(Intent intent) {
        if (intent.hasExtra("url")) {
            url = intent.getStringExtra("url");
            return true;
        }
        return false;
    }

    @Override
    protected void initData() {
        super.initData();
        WebSettings settings = mWebView.getSettings();
        settings.setSupportZoom(true);
        //设置浏览器支持javaScript
        settings.setJavaScriptEnabled(true);
        //设置打开自带缩放按钮
        settings.setBuiltInZoomControls(true);
        // 进行跳转用户输入的url地址
        mWebView.loadUrl(url);

    }
}
