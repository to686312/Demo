package com.example.shinelon.demo.demo12;

import android.content.SyncStatusObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.AndroidCharacter;
import android.util.Log;
import android.widget.TextView;

import com.example.shinelon.demo.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author:Shinelon
 * Time:2017/4/28
 * Email:13222231846@163.com
 * Description:
 */

public class demo12Activity extends AppCompatActivity {
    @Bind(R.id.demo1)
    TextView mDemo1;
    @Bind(R.id.show_data)
    TextView mShowData;
    private Subscription mSubscription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo12_activity);
        ButterKnife.bind(this);
    }

    private LoginBean bean;

    @OnClick(R.id.demo1)
    public void onClick() {
        Flowable.create(new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> e) throws Exception {
                FileReader reader = new FileReader("text.txt");
                BufferedReader bufferedReader = new BufferedReader(reader);
                String str;
                while ((str=bufferedReader.readLine())!=null && !e.isCancelled()){
                    while (e.requested()==0){
                        if(e.isCancelled()){
                            break;
                        }
                        e.onNext(str);
                    }
                }
                reader.close();
                bufferedReader.close();
                e.onComplete();
            }
        },BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                    mSubscription =s;
                        s.request(1);
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.print(s);
                        try {
                            Thread.sleep(1000);
                            mSubscription.request(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
//        Flowable.create(new FlowableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull FlowableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onNext(4);
//                e.onComplete();
//            }
//        }, BackpressureStrategy.ERROR)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<Integer>() {
//                    @Override
//                    public void onSubscribe(Subscription s) {
//                    s.request(Long.MAX_VALUE);
//                    }
//
//                    @Override
//                    public void onNext(Integer integer) {
//                        Log.e("测试xj=", "onNext: " + integer);
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        Log.e("测试xj=", "onError: "+t);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onNext(4);
//                e.onNext(5);
//                e.onNext(6);
//                e.onComplete();
//            }
//        });
//
//        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
//                e.onNext("A");
//                e.onNext("B");
//                e.onNext("C");
//                e.onNext("D");
//                e.onNext("E");
////                e.onNext("G");
//                e.onComplete();
//            }
//        });
//
//        Observable.zip(observable, observable1, new BiFunction<Integer, String, String>() {
//            @Override
//            public String apply(@NonNull Integer integer, @NonNull String s) throws Exception {
//                return integer+s;
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull String s) {
//                Log.e("测试xj=", "onNext: "+s);
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//
//            }
//        }).flatMap(new Function<Integer, ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> apply(@NonNull Integer integer) throws Exception {
//                List<String> list = new ArrayList<>();
//                list.add(integer+"___");
//                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull String s) {
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });


//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//
//            }
//        }).map(new Function<Integer, String>() {
//            @Override
//            public String apply(@NonNull Integer integer) throws Exception {
//                return integer+"?????";
//            }
//        }).subscribe(new Observer<String>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(@NonNull String s) {
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        })

//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//            e.onNext(1);
//            }
//        });
//
//        Consumer<Integer> consumer = new Consumer<Integer>() {
//            @Override
//            public void accept(@NonNull Integer integer) throws Exception {
//                Log.e("测试xj=", "accept: "+integer);
//            }
//        };
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(consumer);
//        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//                e.onNext(1);
//                e.onNext(2);
//                e.onNext(3);
//                e.onNext(4);
//                e.onComplete();
//            }
//        });
//        Observer<Integer> observer = new Observer<Integer>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                Log.e("测试xj=", "onSubscribe: ");
//                temp =d;
//            }
//
//            @Override
//            public void onNext(@NonNull Integer integer) {
//                Log.e("测试xj=", "onNext: " + integer);
//                temp.dispose();
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//                Log.e("测试xj=", "onComplete: ");
//            }
//        };
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread());
//        observable.subscribe(observer);
    }
}
