package com.example.shinelon.demo.demo13;

import com.example.shinelon.demo.demo12.LoginBean;
import com.example.shinelon.demo.demo12.LoginRequest;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.zhy.http.okhttp.utils.L;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:Shinelon
 * Time:2017/5/2
 * Email:13222231846@163.com
 * Description:
 */
public class HttpMethod {

    private static HttpMethod mMethod;
    private static ApiService mApiService;

    public synchronized static HttpMethod getInstance() {
        if (mMethod == null) {
            mMethod = new HttpMethod();
        }
        return mMethod;
    }

    public static ApiService getApiService() {
        if (mApiService == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("")
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            mApiService = retrofit.create(ApiService.class);
        }
        return mApiService;
    }

    public static Map<String, String> getParams(LoginRequest request) {
        Map<String, String> map = new HashMap<>();
        RequestBody xclass = RequestBody.create(MediaType.parse("text"), request.xclass);
//        map.put("app",xclass);
        return map;
    }


    public static void Updata(Observer<HttpBase<LoginBean>> subscriber, LoginRequest reques) {
        Observable.just(reques)
                .map(new Function<LoginRequest, Map<String, RequestBody>>() {
                    @Override
                    public Map<String, RequestBody> apply(@NonNull LoginRequest request) throws Exception {
                        return genUploadRequestBody(request);
                    }
                }).flatMap(new Function<Map<String, RequestBody>, ObservableSource<HttpBase<LoginBean>>>() {
            @Override
            public ObservableSource<HttpBase<LoginBean>> apply(@NonNull Map<String, RequestBody> map) throws Exception {
                return HttpMethod.getInstance().getApiService().login(map);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }


    //单独图片上传接口
    public static Map<String, RequestBody> genUploadRequestBody(LoginRequest req) {
        L.e("UploadUtil:" + Thread.currentThread().getName());
        Map<String, RequestBody> map = new IdentityHashMap<>();
        makeRequestBody(req, map);
        return map;
    }

    //单独图片上传接口
    private static void makeRequestBody(LoginRequest req, Map<String, RequestBody> map) {
        RequestBody app = RequestBody.create(MediaType.parse("text/plain"), req.app);
        RequestBody clazz = RequestBody.create(MediaType.parse("text/plain"), req._class);

        map.put("app", app);
        map.put("class", clazz);

        ArrayList<File> files = req.files;
        if (files == null || files.isEmpty()) {
            return;
        }
        int i = 0;
        if (files.size() == 1) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), files.get(0));
            map.put("image" + "\"; filename=\"" + files.get(0).getName(), fileBody);
        } else {
            for (File f : files) {
                if (!f.exists()) {
                    continue;
                }
                i++;
                RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), f);
                map.put("image" + (i) + "\"; filename=\"" + f.getName(), fileBody);
            }
        }
    }
}
