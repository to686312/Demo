package com.example.shinelon.demo;

import android.app.Application;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;

/**
 * Created by Shinelon on 2016/12/19.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InputStream open = null;
        try {
            open = getAssets().open("yl-server.cer");
            HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(new InputStream[]{open}, null, null);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                    .readTimeout(10000L, TimeUnit.MILLISECONDS)
                    .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                    .addInterceptor(new LoggerInterceptor("TAG"))
                    .build();
            OkHttpUtils.initClient(okHttpClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
