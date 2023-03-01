package com.example.onreceivedincorrecterrors;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.ServiceWorkerClient;
import android.webkit.ServiceWorkerController;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView webView = findViewById(R.id.webview);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        WebView.setWebContentsDebuggingEnabled(true);
        //webView.loadUrl("https://western-slow-arthropod.glitch.me/");
        webView.loadUrl("https://www.youtube.com?app=desktop");

        findViewById(R.id.reload_webview_button).setOnClickListener(view -> {
            webView.reload();
        });

        ServiceWorkerController.getInstance().setServiceWorkerClient(new DemoServiceWorkerClient(getApplicationContext()));

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.i("WEB_VIEW_TEST", "onPageStarted url:" + url);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.i("WEB_VIEW_TEST", "onPageFinished url:" + url);
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view,
                                        WebResourceRequest request,
                                        WebResourceError error) {
                Log.i("WEB_VIEW_TEST", "****onReceivedError*** error code:" + error.getErrorCode() + " description : " + error.getDescription().toString());
                if (request.isForMainFrame()) {
                    onReceivedError(view,
                            error.getErrorCode(),
                            error.getDescription().toString(),
                            request.getUrl().toString());
                }
            }
        });
    }
}