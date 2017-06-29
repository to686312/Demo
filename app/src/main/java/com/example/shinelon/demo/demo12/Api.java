package com.example.shinelon.demo.demo12;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

/**
 * Author:Shinelon
 * Time:2017/4/28
 * Email:13222231846@163.com
 * Description:
 */
public interface Api {
    @FormUrlEncoded
    @GET
    Observable<LoginBean> login(@Body LoginRequest bean);

    @Multipart
    @POST
    Observable<LoginBean> test(@FieldMap Map map);


}
