package com.example.shinelon.demo.demo2.bean;

import android.graphics.Color;

/**
 * -->此类说明：
 * -->用于
 * author:Shinelon
 * time:2017/4/18
 * email:13222231846@163.com
 */

public class Planet {

    //半径
    private int mRadius = 100;
    //球自身半径
    private int mSelfRadius = 30;
    //轨迹的宽度
    private int mTrackWidth = 4;
    //球的颜色
    private int mColor = Color.RED;
    //轨迹颜色
    private int mTrackColor = 0XFF6FDB94;
    private float mAngleRate = 0.01F;
    //初始角度
    private int mOriginAngle = 0;
    //顺时针和逆时针
    private boolean isClockwise = true;

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    public int getSelfRadius() {
        return mSelfRadius;
    }

    public void setSelfRadius(int mSelfRadius) {
        this.mSelfRadius = mSelfRadius;
    }

    public int getTrackWidth() {
        return mTrackWidth;
    }

    public void setTrackWidth(int mTrackWidth) {
        this.mTrackWidth = mTrackWidth;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int mColor) {
        this.mColor = mColor;
    }

    public int getTrackColor() {
        return mTrackColor;
    }

    public void setTrackColor(int mTrackColor) {
        this.mTrackColor = mTrackColor;
    }

    public float getAngleRate() {
        return mAngleRate;
    }

    public void setAngleRate(float mAngleRate) {
        this.mAngleRate = mAngleRate;
    }

    public boolean isClockwise() {
        return isClockwise;
    }

    public void setClockwise(boolean clockwise) {
        isClockwise = clockwise;
    }

    public int getOriginAngle() {
        return mOriginAngle;
    }

    public void setOriginAngle(int mOriginAngle) {
        this.mOriginAngle = mOriginAngle;
    }
}
