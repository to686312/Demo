package com.example.shinelon.demo.demo13;

import com.example.shinelon.demo.demo12.LoginBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Author:Shinelon
 * Time:2017/5/2
 * Email:13222231846@163.com
 * Description:
 */
public interface ApiService {
    @FormUrlEncoded
    @POST
    Observable<HttpBase<LoginBean>> login(@QueryMap Map<String, RequestBody> map);


    @Multipart
    @POST
    Observable<HttpBase<LoginBean>> Fileupload(@FieldMap Map<String,String> map, @HeaderMap Map<String,String> head);
}
