package com.example.shinelon.demo.demo2;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.WindowManager;


import static android.R.attr.value;

/**
 * 像素转换工具
 *
 * @author fmz@zniot.com
 */
public class PixelUtil {


    /**
     * dp转 px.
     *
     * @param value the value
     * @return the int
     */
    public static int dp2px(Context context,float value) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                value, context.getResources().getDisplayMetrics());
    }


    /**
     * px转dp.
     *
     * @param value the value
     * @return the int
     */
    public static int px2dp(Context context,float value) {
        final float scale = context.getResources().getDisplayMetrics().densityDpi;
        return (int) ((value * 160) / scale + 0.5f);
    }
}