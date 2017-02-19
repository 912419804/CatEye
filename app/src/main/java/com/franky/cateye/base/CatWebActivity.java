package com.franky.cateye.base;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.franky.cateye.R;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/1/28.
 * 封装的 WebView Activity
 */

public class CatWebActivity extends CatActivity {

    @BindView(R.id.wv_web)
    WebView mWebView;

    @BindView(R.id.gank_detail_progress)
    ProgressBar mProgressBar;

    @BindView(R.id.tl_title)
    Toolbar tl_title;

    private String url;

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_webview);
        setSupportActionBar(tl_title);
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
        final WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLoadsImagesAutomatically(true);
        settings.setBlockNetworkImage(true);

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);
                }
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loading(false);
                settings.setBlockNetworkImage(false);
            }
        });
        mWebView.loadUrl(url);

    }

    @Override
    protected void onDestroy() {
        mWebView.destroy();
        super.onDestroy();
    }
}
