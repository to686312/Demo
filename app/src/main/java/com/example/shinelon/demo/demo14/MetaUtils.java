package com.example.shinelon.demo.demo14;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

/**
 * Created by Richard on 15/8/2.
 */
public class MetaUtils {


    private MetaUtils() {
        throw new AssertionError();
    }


    /**
     * 获取MetaValue
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key, String defaultValue) {
        Bundle metaData = null;
        String value = null;
        if (context == null || key == null) {
            return defaultValue;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                value = metaData.getString(key);
                value = (value == null) ? defaultValue : value;
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return value;
    }

    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }


    /**
     * 获取MetaValue
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key, int defaultValue) {
        Bundle metaData = null;
        int value = 0;
        if (context == null || key == null) {
            return defaultValue;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                value = metaData.getInt(key);
                value = (value == 0) ? defaultValue : value;
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return value;
    }

    public static int getInt(Context context, String key) {
        return getInt(context, key, 0);
    }


    /**
     * 获取MetaValue
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        Bundle metaData = null;
        boolean value = defaultValue;
        if (context == null || key == null) {
            return defaultValue;
        }
        try {
            ApplicationInfo ai = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                value = metaData.getBoolean(key, defaultValue);
            }
        } catch (PackageManager.NameNotFoundException e) {

        }
        return value;
    }
    /**
     * 获取MetaValue
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }
}
