package com.example.shinelon.demo.demo7;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * Created by Shinelon on 2017/1/18.
 */
//需要实现 RealmMigration 接口。执行版本升级时的处理
public class UpDataHome implements RealmMigration {
    @Override
    public void migrate(final DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();


        if(oldVersion==0 && newVersion==1){
            RealmObjectSchema user = schema.get("User");
//            新增个@Required的id字段
            user.addField("id",Integer.class, FieldAttribute.REQUIRED)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                        obj.set("id",1);
                        }
                    })
                    .removeField("age");//
            oldVersion++;
        }

        if(oldVersion==1 && newVersion==2){
            RealmObjectSchema dog = schema.create("dog");
            dog.addField("name",String.class);
            schema.get("User")
                    .addRealmListField("dog",dog)
                    .transform(new RealmObjectSchema.Function() {
                        @Override
                        public void apply(DynamicRealmObject obj) {
                            DynamicRealmObject realmObject = realm.createObject("dog");
                            realmObject.set("name","小狗");
                            obj.getList("dogs").add(realmObject);
                        }
                    });
            oldVersion++;
        }
    }
}
