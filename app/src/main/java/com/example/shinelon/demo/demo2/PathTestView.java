package com.example.shinelon.demo.demo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static android.R.attr.path;

/**
 * -->此类说明：
 * -->用于 了解path的用法
 * author:Shinelon
 * time:2017/4/17
 * email:13222231846@163.com
 */
public class PathTestView extends View {

    private Path mPath;
    private Paint mPaint;

    public PathTestView(Context context) {
        this(context, null);
    }

    public PathTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPath.reset();
        canvas.translate(width / 2, height / 2);
        mPath.moveTo(150, 0);
        mPath.lineTo(200, 250);
        mPath.lineTo(0, 50);
        mPath.lineTo(250, 50);
        mPath.lineTo(100, 250);
        mPath.close();
        canvas.drawPath(mPath, mPaint);
        super.onDraw(canvas);
    }

    private int width;
    private int height;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = getWidth();
        height = getHeight();
    }
}
