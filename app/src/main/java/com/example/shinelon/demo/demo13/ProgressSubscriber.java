package com.example.shinelon.demo.demo13;


import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Author:Shinelon
 * Time:2017/5/4
 * Email:13222231846@163.com
 * Description:
 */
public class ProgressSubscriber<T> implements Observer<T> {
    private final SubscriberOnNextListener mListener;
    private final Context mContext;

    public ProgressSubscriber(SubscriberOnNextListener mSubscriberOnNextListener, Context context) {
        mListener = mSubscriberOnNextListener;
        mContext = context;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        mListener.onNext(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }


//

//
//    @Override
//    public void onSubscribe(Subscription s) {
//
//    }
//
//    @Override
//    public void onNext(T t) {
//        mListener.onNext(t);
//    }
//
//    @Override
//    public void onError(Throwable t) {
//
//    }
//
//    @Override
//    public void onComplete() {
//
//    }
}
