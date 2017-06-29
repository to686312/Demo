package com.example.shinelon.demo.demo7;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;

/**
 * 直接继承于 RealmObject 来声明 Realm 数据模型之外，
 * 还可以通过实现 RealmModel接口 并添加 @RealmClass 修饰符来声明。
 *
 * 支持的属性
 boolean, byte, short, int, long, float, double,
 String, Date 和 byte[], RealmObject, RealmList<? extends RealmObject>

 还支持 Boolean, Byte, Short, Integer, Long, Float 和 Double



 @PrimaryKey——表示该字段是主键

 @Required——表示该字段非空

 @Ignore——表示忽略该字段  被添加 @Ignore 标签后，存储数据时会忽略该字段。

 @Index——添加搜索索引 为字段添加搜索索引，这样会使得插入的速度变慢，数据量也变得更大。
 不过在查询速度将变得更快，建议只在优化读取性能的特定情况时添加索引

 如果你创建Model并运行过，然后修改了Model。那么就需要升级数据库，否则会抛异常。
 */
@RealmClass
public class User extends RealmObject {
    @Required
    private String name;
    @PrimaryKey
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
