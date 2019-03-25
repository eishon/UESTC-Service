package com.eishon.uestc_service;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class MenuAboutUESTCFragment extends Fragment {

    WebView web;
    Context context;
    WebViewController webViewController;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.menu_fragment_about_uestc,null);

        context=getActivity().getApplicationContext();
        web = (WebView) rootview.findViewById(R.id.webview_about_uestc);

        webViewController=new WebViewController(context,web);
        web.loadUrl("file:///android_asset/about_uestc.html");

        return rootview;
    }
}