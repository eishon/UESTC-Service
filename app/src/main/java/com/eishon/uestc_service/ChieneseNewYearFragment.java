package com.eishon.uestc_service;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class ChieneseNewYearFragment extends Fragment {

    WebView web;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_chienese_new_year,null);

        web = (WebView) rootview.findViewById(R.id.webview_chienese_new_year);
        web.getSettings().setJavaScriptEnabled(true);
        //web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        web.getSettings().setMinimumFontSize(40);
        web.loadUrl("file:///android_asset/holidays/chienese_new_year.html");

        return rootview;
    }
}