package com.example.shinelon.demo.demo13;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.shinelon.demo.R;
import com.example.shinelon.demo.demo12.LoginBean;
import com.example.shinelon.demo.demo12.LoginRequest;
import com.zhy.http.okhttp.utils.L;

import org.reactivestreams.Subscriber;

import java.io.File;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static com.example.shinelon.demo.demo13.HttpMethod.Updata;

/**
 * Author:Shinelon
 * Time:2017/5/2
 * Email:13222231846@163.com
 * Description:
 */
public class demo13Activity extends AppCompatActivity {
    @Bind(R.id.updata)
    Button mUpdata;
    @Bind(R.id.updata_file)
    Button mUpdataFile;
    @Bind(R.id.tv)
    TextView mTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo13_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.updata, R.id.updata_file})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.updata:
               HttpMethod.Updata(new ProgressSubscriber<HttpBase<LoginBean>>(new SubscriberOnNextListener<HttpBase<LoginBean>>() {
                    @Override
                    public void onNext(HttpBase<LoginBean> o) {

                    }
                }, this), new LoginRequest());
                break;
            case R.id.updata_file:

                break;
        }
    }

}
