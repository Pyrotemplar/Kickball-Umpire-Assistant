package com.pyrotemplar.refereehelper.Utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pyrotemplar.refereehelper.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Manuel Montes de Oca on 8/10/2017.
 */

public class CustomWebview extends WebViewClient {


    //@BindView(R.id.webViewProgressBar)
   // ProgressBar webViewProgressBar;

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        // TODO Auto-generated method stub
        super.onPageStarted(view, url, favicon);
        ButterKnife.bind(view);

    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return false;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onReceivedError(WebView view, int errorCode,
                                String description, String failingUrl) {
        // TODO Auto-generated method stub

        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceivedError(WebView view,
                                WebResourceRequest request,
                                WebResourceError error) {
        super.onReceivedError(view, request, error);

    }

    @Override
    public void onPageFinished(WebView view, String url) {
        // TODO Auto-generated method stub
        super.onPageFinished(view, url);
        Toast.makeText(view.getContext(), "Testing View Finish", Toast.LENGTH_SHORT).show();
        //ButterKnife.bind(view);
        //ProgressBar webViewProgressBar = (ProgressBar) findViewById(R.id.webViewProgressBar);
       // webViewProgressBar.setVisibility(View.GONE);
    }
}
