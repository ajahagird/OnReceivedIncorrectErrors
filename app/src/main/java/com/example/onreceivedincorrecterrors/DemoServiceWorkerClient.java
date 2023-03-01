package com.example.onreceivedincorrecterrors;

import android.content.Context;
import android.util.Log;
import android.webkit.ServiceWorkerClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;

import java.io.InputStream;
import java.util.Map;

public class DemoServiceWorkerClient  extends ServiceWorkerClient {
    private static final String TAG = "DemoServiceWorkerClient";
    private final Context context;

    DemoServiceWorkerClient(Context context) {
        this.context = context;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebResourceRequest request) {
        final Map<String, String> headerMap = request.getRequestHeaders();
        // intercept service-worker installation network request
        if (headerMap != null && headerMap.getOrDefault("Service-Worker", "Unknown").equals("script")) {
            Log.i(TAG, "shouldInterceptRequest intercepting " + request.getUrl() + " to provide local response");
            InputStream ins = context.getResources().openRawResource(R.raw.local_service_worker);
            return new WebResourceResponse("application/x-javascript", "utf-8", ins);
        }
        //otherwise let service worker handle this
        return null;
    }
}
