package com.example.shinelon.demo.demo7;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.example.shinelon.demo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;

/**
 * Created by Shinelon on 2017/1/18.
 * <p>
 * 博客  ： http://mp.weixin.qq.com/s/dC7L1yy6WpiNP5i4E3Dg5g
 */
public class Demo7Activity extends AppCompatActivity {
    @Bind(R.id.write)
    AppCompatButton mWrite;
    @Bind(R.id.read)
    AppCompatButton mRead;
    @Bind(R.id.change)
    AppCompatButton mChange;
    private Realm mMRealm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo7_layout);
        ButterKnife.bind(this);

        Realm.init(this);

        /**
         * RealmConfiguration 支持的方法：
         Builder.name : 指定数据库的名称。如不指定默认名为default。
         Builder.schemaVersion : 指定数据库的版本号。
         Builder.encryptionKey : 指定数据库的密钥。
         Builder.migration : 指定迁移操作的迁移类。
         Builder.deleteRealmIfMigrationNeeded : 声明版本冲突时自动删除原数据库。
         Builder.inMemory : 声明数据库只在内存中持久化。
         build : 完成配置构建。
         */
        RealmConfiguration xj = new RealmConfiguration.Builder()
                .name("xj.realm")//问价名
                .schemaVersion(0)//版本号
//                .inMemory()   加入这个就是保存到内存中  应用结束 就删除了
//                      创建非持久化的 Realm，也就是保持在内存中，应用关闭后就清除了。
                .migration(new UpDataHome())//升级数据是 执行的升级操作在updatahome里面
//                .encryptionKey()加密
                .build();

        mMRealm = Realm.getInstance(xj);
//        Realm mRealm = Realm.getDefaultInstance();   这是默认配置


        String path = mMRealm.getPath();
        Toast.makeText(this, path, Toast.LENGTH_LONG).show();
    }


    @OnClick({R.id.write, R.id.read, R.id.change})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.write:
                // copyToRealmOrUpdate  推荐  使用 copyToRealmOrUpdate 或 copyToRealm 方法插入数据
                //当Model中存在主键的时候，推荐使用 copyToRealmOrUpdate 方法插入数据。如果对象存在，就更新该对象；
                // 反之，它会创建一个新的对象。若该Model没有主键，使用 copyToRealm 方法，否则将抛出异常。

                //第一种 添加   不推荐
                mMRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        User user = realm.createObject(User.class);
                        user.setName("张三");
                        user.setAge(23);
                    }
                });

                // //第二种
//                final User user2 = new User();
//                user2.setName("李四");
//                user2.setAge(20);
//                mMRealm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        realm.copyToRealmOrUpdate(user2);
//                    }
//                });


                //第三种
                //如果user里面还有对象  请参照  博客 http://mp.weixin.qq.com/s/dC7L1yy6WpiNP5i4E3Dg5g


                //第四种
//                可 executeTransaction 方法插入数据，
// 还有另一种方法可以用于插入数据——beginTransaction 和 commitTransaction
//                mMRealm.beginTransaction();
//                User user4 = mMRealm.createObject(User.class);
//                user4.setName("张三");
//                user4.setAge(23);
//                mMRealm.commitTransaction();


                ///在UI和后台线程同时开启创建write的事务，可能会导致ANR错误。
                // 为了避免该问题，可以使用executeTransactionAsync来实现。
//                RealmAsyncTask task = mMRealm.executeTransactionAsync(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        User user = realm.createObject(User.class);
//                        user.setName("张三");
//                        user.setAge(23);
//                    }
//                });

//                还可以加入监听：
//                mMRealm.executeTransactionAsync(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        User user = realm.createObject(User.class);
//                        user.setName("张三");
//                        user.setAge(23);
//                    }
//                }, new Realm.Transaction.OnSuccess() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//                }, new Realm.Transaction.OnError() {
//                    @Override
//                    public void onError(Throwable error) {
//
//                    }
//                });
                //如果当 Acitivity 或 Fragment 被销毁时，在 OnSuccess 或 OnError 中执行UI操作，将导致程序奔溃 。
                // 用 RealmAsyncTask .cancel();可以取消事务在 onStop 中调用，避免 crash。


                //Realm还是个很nice的功能就是将Json字符串转化为对象，厉害了我的Realm（直接借用官方的例子）
//                mMRealm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        //使用json插入数据
//                       realm.createAllFromJson(User.class,"{name:\"张三\" ,age:15}");
//
//                    }
//                });
                //使用inputstream  插入数据
//                mMRealm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        try {
//                            FileInputStream in = new FileInputStream(new File("xj"));
//                            realm.createObjectFromJson(User.class,in);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });


                break;
            case R.id.read:
//                findAll ——查询
//                例：查询所有的User
//                RealmResults<User> userList = mMRealm.where(User.class).findAll();

                // RealmResults虽然实现了List接口，不过有很多方法是不能用的。比如add、addAll、remove、clear等，
                // 调用后会直接抛异常。不过也不用当心误用这些方法，因为它们都被标记为 @Deprecated 了。


//                findAllAsync——异步查询
                //这里并不会马上查到数据，是有一定延时的。也就是说，
                // 你马上使用 userList 的时候，里面是没有数据的。
                // 可以注册 RealmChangeListener 监听器，或者使用 isLoaded() 方法，判断是否查询完成

//                RealmResults<User> async = mMRealm.where(User.class)
//                        .equalTo("name", "xj")
//                        .findAllAsync();
//                if (async.isLoaded()) {
//                    // 完成查询
//                }


                //findFirst ——查询第一条数据
//                User user2 = mMRealm.where(User.class).findFirst();


//                equalTo ——根据条件查询
//                得到 name 为 张三 的用户列表：

                RealmResults<User> async = mMRealm.where(User.class)
                        .equalTo("name", "张三")
                        .findAllAsync();


//                当数据量较大，可能会引起ANR的时候，就可以使用 findAllAsync  数据少就使用 findAll()

                //equalTo ——多条件查询
                RealmResults<User> async1 = mMRealm.where(User.class)
                        .equalTo("name", "张三")
                        .equalTo("age", 23)
                        .findAllAsync();

//                更多查询条件
//
//                上面就展示了 equalTo 的用法。当然，查询还有更多的用法，我就不一一示例了。已知的方法如下：
//
//                sum()：对指定字段求和。
//
//                average()：对指定字段求平均值。
//
//                min(): 对指定字段求最小值。
//
//                max() : 对指定字段求最大值。count : 求结果集的记录数量。
//
//                findAll(): 返回结果集所有字段，返回值为RealmResults队列
//
//                findAllSorted() : 排序返回结果集所有字段，返回值为RealmResults队列
//
//                between(), greaterThan(),lessThan(), greaterThanOrEqualTo() & lessThanOrEqualTo()
//
//                equalTo() & notEqualTo()
//
//                contains(), beginsWith() & endsWith()
//
//                isNull() & isNotNull()
//
//                isEmpty()& isNotEmpty()

//                RealmQuery<User> where = mMRealm.where(User.class);
//                where.equalTo("name", "张三");
//                where.or().equalTo("age", 23);
//                RealmResults<User> whereAll = where.findAll();


//                mMRealm.where(User.class)
//                        .equalTo("name","张三")
//                        .or().equalTo("age", 23)
//                        .findAll()

                //排序
//                查询结束后，还可以进行排序，

//                RealmResults<User> all = mMRealm.where(User.class).findAll();
//                RealmResults<User> age = all.sort("age");//正序
//                RealmResults<User> age1 = all.sort("age", Sort.DESCENDING);//逆序


                //删除
//使用 deleteFromRealm()
//                final RealmResults<User> all = mMRealm.where(User.class).findAll();
//                mMRealm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        all.get(0).deleteFromRealm();
//                    }
//                });


                break;
            case R.id.change:

                break;
        }
    }

    //如果当 Acitivity 或 Fragment 被销毁时，在 OnSuccess 或 OnError 中执行UI操作，将导致程序奔溃 。
    // 用 RealmAsyncTask .cancel();可以取消事务在 onStop 中调用，避免 crash
//    RealmAsyncTask task;
    @Override
    protected void onStop() {
        super.onStop();
//        if(task!=null && !task.isCancelled()){
//            task.cancel();
//        }
    }

    //关闭Realm
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the Realm instance.
        mMRealm.close();
    }
}
