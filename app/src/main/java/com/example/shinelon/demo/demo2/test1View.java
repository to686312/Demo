package com.example.shinelon.demo.demo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Shinelon on 2016/11/16.
 */
public class test1View extends View {

    private Paint paint;
    private Context context;
    private int width;
    private int height;
    private Path path;
    private float x;
    private float y;
    private float mx;
    private float my;
    private float dx;
    private float dy;

    public test1View(Context context) {
        this(context, null);
    }

    public test1View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    public test1View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        //初始化
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10);


        path = new Path();
        setWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();
//                path.rewind();
                mx = x;
                my = y;
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                dx = Math.abs(x - mx);
                dy = Math.abs(y - my);

                if (dx > 5 || dy > 5) {
                    path.quadTo(mx, my, (x + mx) / 2, (y + my) / 2);
//                    path.quadTo((x + mx) / 2, (y + my) / 2,x, y);
                    mx = x;
                    my = y;

//                    path.close();
                    postInvalidate();
                }
                break;
        }
        return true;
    }

    private void setWindow() {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }
}
