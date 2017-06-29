package com.example.shinelon.demo.demo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Shinelon on 2016/11/24.
 */
public class NextPagerView extends View {

    private int Heght;//宽
    private int Width;//高
    private Paint paint;
    private float curX;
    private float curY;
    private Path path;
    private float mValueAdded = 1.0f;

    public NextPagerView(Context context) {
        this(context, null);
    }

    public NextPagerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NextPagerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        canvas.drawColor(Color.WHITE);
        if (curX == 0 || curY == 0) {
            return;
        }

        /*
 * 判断触摸点是否在短边的有效区域内
 */
        if (!mRegionShortSize.contains((int) curX, (int) curY)) {
            // 如果不在则通过x坐标强行重算y坐标
            curY = (float) (Math.sqrt((Math.pow(Width, 2) - Math.pow(curX, 2))) - Heght);

            // 精度附加值避免精度损失
            curY = Math.abs(curY) + mValueAdded;
        }
        float mK = Width - curX;
        float mL = Heght - curY;
        float temp = (float) (Math.pow(curX, 2) + Math.pow(curY, 2));
       /*
       * 计算短边长边长度
       */
        float sizeShort = temp / (2F * mK);
        float sizeLong = temp / (2F * mL);


        path.moveTo(curX, curY);
        path.lineTo(Width, Heght - sizeLong);
        path.lineTo(Width - sizeShort, Heght);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);


        path = new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_MOVE:
                curX = event.getX();
                curY = event.getY();
                invalidate();
                break;
        }
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Width = w;
        Heght = h;
    }

    private Region mRegionShortSize = new Region();

    private void MakeSureCircleSize() {
        // 短边圆形路径对象
        Path shorPath = new Path();
        // 用来装载Path边界值的RectF对象
        RectF shortRectF = new RectF();

        // 添加圆形到Path
        shorPath.addCircle(0, Heght, Width, Path.Direction.CCW);

        // 计算边界
        shorPath.computeBounds(shortRectF, true);

        // 将Path转化为Region
        mRegionShortSize.setPath(shorPath, new Region((int) shortRectF.left, (int) shortRectF.top, (int) shortRectF.right, (int) shortRectF.bottom));
    }
}
