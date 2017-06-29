package com.example.shinelon.demo.demo2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.shinelon.demo.R;
import com.example.shinelon.demo.demo2.bean.Planet;

import java.util.ArrayList;
import java.util.List;

import static android.os.Looper.prepare;

/**
 * -->此类说明：
 * -->用于 仿太阳系星球转动
 * author:Shinelon
 * time:2017/4/18
 * email:13222231846@163.com
 */

public class PlantMovingView extends View{

    private Paint mTrackPaint;
    private Paint mPlanetPaint;
    private float roundx;//圆心x轴
    private float roundy;//圆心Y轴

    private List<Planet> mPlanets;
    private Bitmap mBitmap;
    private Paint mBackgroundPaint;
    private double paintCount=0.0d;
    private double mAngle;//旋转角度

    public PlantMovingView(Context context) {
        this(context, null);
    }
    public PlantMovingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public PlantMovingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //用来保存plant对象
        mPlanets = new ArrayList<>();
        //初始化轨迹画笔
        initTrackPaint();
        //初始化星球画笔
        initPlanetPaint();
    }
    private void initPlanetPaint() {
        //这是星球的画笔
        mPlanetPaint = new Paint();
        mPlanetPaint.setStyle(Paint.Style.FILL);
        mPlanetPaint.setAntiAlias(true);
    }

    private void initTrackPaint() {
        //这是轨迹的画笔
        mTrackPaint = new Paint();
        mTrackPaint.setStyle(Paint.Style.STROKE);
        mTrackPaint.setAntiAlias(true);
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
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        roundx = w / 2;
        roundy = h / 2;
        float ry = h - roundy;
        double rx = Math.pow(roundx * roundx + ry * ry, 1.f / 2.f);
        setRadialGradient(roundx,roundy,(float) rx, Color.BLACK,Color.WHITE);
        Prepare();
    }
    /**
     * 设置背景渐变
     * 设置中心点之后再做此事
     */
    public void setRadialGradient(float x, float y, float r, int sc, int ec) {
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setShader(new RadialGradient(x, y, r, sc, ec, Shader.TileMode.CLAMP));
        Prepare();
    }
    private synchronized void Prepare() {
        if (mPlanets.size() == 0) return;
        if (mBitmap != null) {
            mBitmap.recycle();
            mBitmap = null;
        }
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mBitmap);
        if (getBackground() != null) {
            getBackground().draw(canvas);
        }
        if (mBackgroundPaint != null && mBackgroundPaint.getShader() != null) {
            canvas.drawRect(0, 0, getWidth(), getHeight(), mBackgroundPaint);
        }
        for (Planet bean : mPlanets) {
            mTrackPaint.setStrokeWidth(bean.getTrackWidth());
            mTrackPaint.setColor(bean.getTrackColor());
            canvas.drawCircle(roundx, roundy, bean.getRadius(), mTrackPaint);
        }
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (mPlanets.size() == 0) return;
        int save = canvas.save();
        if (mBitmap != null) canvas.drawBitmap(mBitmap, 0, 0, mPlanetPaint);
        for (Planet bean : mPlanets) {
            if (bean.isClockwise()) {//true顺时针
                mAngle = (bean.getOriginAngle() + paintCount + bean.getAngleRate()) % 360;
            } else {//逆时针
                mAngle =360 - ( bean.getOriginAngle() + paintCount + bean.getAngleRate()) % 360;
            }
            double x = Math.cos(mAngle) * bean.getRadius() + roundx;
            double y = Math.sin(mAngle) * bean.getRadius() + roundy;
            mPlanetPaint.setColor(bean.getColor());
            canvas.drawCircle((float) x, (float) y, bean.getSelfRadius(), mPlanetPaint);
        }
        canvas.restoreToCount(save);
        paintCount=paintCount+0.01d;
        if (paintCount < 0) {
            paintCount = 0.0d;
        }
        handler.sendEmptyMessageDelayed(0,30);
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            postInvalidate();
        }
    };


    public void addPlanets(List<Planet> planets) {
        this.mPlanets.addAll(planets);
    }

    public void addPlanets(Planet planet) {
        mPlanets.add(planet);
    }

    public void clear() {
        mPlanets.clear();
    }
}
