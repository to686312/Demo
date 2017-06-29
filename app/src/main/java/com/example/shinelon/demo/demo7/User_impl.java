package com.example.shinelon.demo.demo7;

import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.annotations.RealmClass;

/**
 *直接继承于 RealmObject 来声明 Realm 数据模型之外，
 * 还可以通过实现 RealmModel接口 并添加 @RealmClass 修饰符来声明。
 */
@RealmClass
public class User_impl implements RealmModel {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
