package com.example.shinelon.demo.demo6;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;

import com.example.shinelon.demo.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

import static com.zhy.http.okhttp.log.LoggerInterceptor.TAG;

/**
 * Created by Shinelon on 2016/12/19.
 */

public class Demo6Activity extends Activity {
    @Bind(R.id.request)
    AppCompatButton request;
    @Bind(R.id.content)
    AppCompatTextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo6_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.request)
    public void onClick() {

        OkHttpUtils.post()
                .url("https://10.58.148.74:8080/jzProvideForTheAged/f/app/user/sendSMSCaptcha")
                .addParams("account","13881100969")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "onError: "+e.getMessage() );
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "onResponse: "+response );
                        if(!TextUtils.isEmpty(response)){
                            content.setText(response);
                        }
                    }
                });

    }
}
