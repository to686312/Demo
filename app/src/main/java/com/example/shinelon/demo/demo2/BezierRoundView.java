package com.example.shinelon.demo.demo2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.shinelon.demo.R;

/**
 * -->此类说明：
 * -->用于 贝塞尔的练习
 * author:Shinelon
 * time:2017/4/17
 * email:13222231846@163.com
 */

public class BezierRoundView extends View {
    private int color_beze = Color.RED;
    private int color_stroke = Color.RED;
    private int color_touch = Color.RED;

    private int animation_time = 3000;
    private int round_count = 4;

    private float radius = 300f;

    public BezierRoundView(Context context) {
        this(context, null);
    }

    public BezierRoundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BezierRoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context, attrs);
    }

    private void initPaint(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, null);
        color_beze = array.getColor(R.styleable.BezierRoundView_color_beze, color_beze);
        color_stroke = array.getColor(R.styleable.BezierRoundView_color_stroke, color_stroke);
        color_touch = array.getColor(R.styleable.BezierRoundView_color_touch, color_touch);
        animation_time = array.getInteger(R.styleable.BezierRoundView_animation_time, animation_time);
        round_count = array.getInteger(R.styleable.BezierRoundView_round_count, round_count);
        radius = array.getDimension(R.styleable.BezierRoundView_radius, radius);
        array.recycle();

        initPointF();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //设置一个默认值，就是这个View的默认宽度为500，这个看我们自定义View的要求
        int result = 500;
        if (specMode == MeasureSpec.AT_MOST) {//相当于我们设置为wrap_content
            result = specSize;
        } else if (specMode == MeasureSpec.EXACTLY) {//相当于我们设置为match_parent或者为一个具体的值
            result = specSize;
        }
        return result;
    }

    private PointF p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11;
    private int bez = 1 / 4;

    private void initPointF() {
        p0 = new PointF(0, -radius);
        p6 = new PointF(0, radius);

        p1 = new PointF(-radius * bez, -radius);
        p5 = new PointF(radius * bez, radius);

        p2 = new PointF(radius, -radius * bez);
        p4 = new PointF(radius, radius * bez);

        p3 = new PointF(radius, 0);
        p9 = new PointF(-radius, 0);

        p7 = new PointF(-radius * bez, radius);
        p11 = new PointF(-radius * bez, -radius);

        p8 = new PointF(-radius, radius * bez);
        p10 = new PointF(-radius, -radius * bez);
    }

    private Path mPath = new Path();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(p0.x, p0.y);

        mPath.cubicTo(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
        mPath.cubicTo(p4.x, p4.y, p5.x, p5.y, p6.x, p6.y);
        mPath.cubicTo(p7.x, p7.y, p8.x, p8.y, p9.x, p9.y);
        mPath.cubicTo(p10.x, p10.y, p11.x, p11.y, p0.x, p0.y);
        mPath.close();

    }

    private int mwidth;
    private int mheight;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mwidth = w;
        mheight = h;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                p2.set(event.getX()-mwidth/2,-radius*bez);
                p3.set(event.getX()-mwidth/2,0);
                p4.set(event.getX()-mwidth/2,radius*bez);
                postInvalidate();
                break;
        }


        return super.onTouchEvent(event);
    }
}
