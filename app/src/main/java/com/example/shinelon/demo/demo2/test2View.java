package com.example.shinelon.demo.demo2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Shinelon on 2016/11/21.
 */

public class test2View extends View {

    private Paint paint;
    private Path path;
    private int mViewWidth;
    private int mViewHeight;

    public test2View(Context context) {
        this(context, null);
    }

    public test2View(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public test2View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        path.moveTo(100,100);
//        path.quadTo(200,200,300,100);
//        path.close();
//        canvas.drawPath(path,paint);


           /*
     * 保存并裁剪画布填充绿色
     */
        int saveID1 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 300, mViewHeight / 2F - 300, mViewWidth / 2F + 300, mViewHeight / 2F + 300);
        canvas.drawColor(Color.YELLOW);

    /*
     * 保存并裁剪画布填充绿色
     */
        int saveID2 = canvas.save(Canvas.CLIP_SAVE_FLAG);
        canvas.clipRect(mViewWidth / 2F - 200, mViewHeight / 2F - 200, mViewWidth / 2F + 200, mViewHeight / 2F + 200);
        canvas.drawColor(Color.GREEN);

    /*
     * 保存画布并旋转后绘制一个蓝色的矩形
     */
        int saveID3 = canvas.save(Canvas.MATRIX_SAVE_FLAG);

        // 旋转画布
        canvas.rotate(5);
        paint.setColor(Color.BLUE);
        canvas.drawRect(mViewWidth / 2F - 100, mViewHeight / 2F - 100, mViewWidth / 2F + 100, mViewHeight / 2F + 100, paint);

        paint.setColor(Color.CYAN);
        canvas.drawRect(mViewWidth / 2F, mViewHeight / 2F, mViewWidth / 2F + 200, mViewHeight / 2F + 200, paint);
//        canvas.restoreToCount(saveID2);
    }
}
