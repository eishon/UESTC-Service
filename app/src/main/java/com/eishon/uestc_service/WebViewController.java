package com.eishon.uestc_service;

import android.content.Context;
import android.webkit.WebView;

/**
 * Created by EISHON on 29-08-16.
 */
public class WebViewController {
    Context controllerContext;
    WebView controllerWebView;


    public WebViewController(Context context,WebView webView) {
        controllerContext=context;
        controllerWebView=webView;
        updateWebView();
    }

    private void updateWebView() {
        controllerWebView.getSettings().setJavaScriptEnabled(true);
        //web.getSettings().setBuiltInZoomControls(true);
        controllerWebView.getSettings().setLoadWithOverviewMode(true);
        controllerWebView.getSettings().setUseWideViewPort(true);
        controllerWebView.getSettings().setMinimumFontSize(40);
    }
}
