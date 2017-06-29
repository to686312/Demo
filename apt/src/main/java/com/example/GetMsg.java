package com.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 修饰 class等 .CLASS:在class文件中有效（即class保留）　
 * 对于方法
 * 这个注解可以设置两个值。id和name。name是有默认值的，可以不设置
 */
//@Retention(RetentionPolicy.CLASS)
//@Target(ElementType.METHOD)

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface GetMsg {
    int id();
    String name() default "default";
}
