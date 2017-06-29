package com.example.shinelon.demo.demo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by Shinelon on 2016/11/23. 水波纹实现
 * 正余弦函数方程为：
 * y = Asin(wx+b)+h ，这个公式里：w影响周期，A影响振幅，h影响y位置，b为初相；
 */
public class waterWaveView extends View {

    private int totalwidth;
    private float mCycleFactorW;
    private float[] mYPositions;
    private int totalheight;
    private Paint paint;
    private PaintFlagsDrawFilter mDrawFilter;

    public waterWaveView(Context context) {
        this(context, null);
    }

    public waterWaveView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public waterWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private static final float STRETCH_FACTOR_A = 20;
    private static final int OFFSET_Y = 0;
    private void init() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        if(totalwidth==0 || totalheight==0){
            totalwidth=300;
            totalheight=300;
        }
        // 将周期定为view总宽度
        mCycleFactorW = (float) (2 * Math.PI / totalwidth);
        mYPositions=new float[totalwidth];
        // 根据view总宽度得出所有对应的y值
        for (int i=0;i<totalwidth;i++){
            mYPositions[i]=(float)(STRETCH_FACTOR_A*Math.sin(mCycleFactorW*i)+OFFSET_Y);
        }

        mDrawFilter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.setDrawFilter(mDrawFilter);
        for (int i=0;i<mYPositions.length;i++){
            // 减400只是为了控制波纹绘制的y的在屏幕的位置，大家可以改成一个变量，然后动态改变这个变量，从而形成波纹上升下降效果
            // 绘制第一条水波纹
            canvas.drawLine(i,totalheight-mYPositions[i]-400,i,totalheight,paint);


            // 绘制第二条水波纹
            canvas.drawLine(i, totalheight - mYPositions[i]-400, i,
                    totalheight,
                    paint);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        totalwidth = w;
        totalheight=h;
    }
}
