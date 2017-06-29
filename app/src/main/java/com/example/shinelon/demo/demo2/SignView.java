package com.example.shinelon.demo.demo2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.shinelon.demo.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Shinelon on 2016/11/23.
 * 书翻页效果
 */
public class SignView extends View {

    private Context context;
    private TextPaint paint;
    private int mHeight;
    private int mWidth;
    private float mTextSizeNormal; //一般字大小
    private float mTextSizeLarger;//大号字
    private float mClipX;// 裁剪右端点坐标
    private float mAutoAreaLeft, mAutoAreaRight;// 控件左侧和右侧自动吸附的区域
    private float mCurPointX;// 指尖触碰屏幕时点X的坐标值
    private float mMoveValid;// 移动事件的有效距离
    private boolean isNextPage, isLastPage;// 是否该显示下一页、是否最后一页的标识值

    private int pageIndex;// 当前显示mBitmaps数据的下标

    public SignView(Context context) {
        this(context, null);
    }

    public SignView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SignView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }
    private List<Bitmap> src=new ArrayList<>();
    private void init() {
        //创建textpaint  画文字的画笔
        paint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.LINEAR_TEXT_FLAG);
        paint.setTextAlign(Paint.Align.CENTER);
        src.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.image1));
        src.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.image2));
        src.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.image3));
        src.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.image4));
        src.add(BitmapFactory.decodeResource(context.getResources(), R.drawable.image5));

        setBitmaps(src);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 每次触发TouchEvent重置isNextPage为true
        isNextPage = true;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                // 获取当前事件点x坐标
                mCurPointX = event.getX();
                /*
                * 如果事件点位于回滚区域
                */
                if (mCurPointX < mAutoAreaLeft) {
                    // 那就不翻下一页了而是上一页
                    isNextPage = false;
                    pageIndex--;
                    mClipX = mCurPointX;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = mCurPointX - event.getX();
                if(Math.abs(dx)>mMoveValid){//滑动的最小距离
                    // 获取触摸点的x坐标
                    mClipX = event.getX();
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                // 判断是否需要自动滑动
                judgeSlideAuto();
                break;
        }
        return true;
    }
    /**
     * 判断是否需要自动滑动
     * 根据参数的当前值判断自动滑动
     */
    private void judgeSlideAuto() {
       /*
        * 如果裁剪的右端点坐标在控件左端十分之一的区域内，那么我们直接让其自动滑到控件左端
        */
        if(mClipX<mAutoAreaLeft){
            while(mClipX<0){
                mClipX--;
                invalidate();
            }
        }

        /*
         * 如果裁剪的右端点坐标在控件右端十分之一的区域内，那么我们直接让其自动滑到控件右端
         */
        if(mClipX>mAutoAreaRight){
            while (mClipX<mWidth){
                mClipX++;
                invalidate();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

          /*
         * 如果数据为空则显示默认提示文本
         */
        if (mBitmaps == null || mBitmaps.size() == 0) {
            //绘制  文字
            defaultDisplay(canvas);
            return;//结束掉
        }
        //绘制图片
        // 绘制位图
        drawBtimaps(canvas);

    }

    private void drawBtimaps(Canvas canvas) {
        // 绘制位图前重置isLastPage为false
        isLastPage = false;//是否是最后一页

        // 限制pageIndex的值范围
        pageIndex = pageIndex < 0 ? 0 : pageIndex;
        pageIndex = pageIndex > mBitmaps.size() ? mBitmaps.size() : pageIndex;
        // 计算数据起始位置
        int start = mBitmaps.size() - 2 - pageIndex;
        int end = mBitmaps.size() - pageIndex;

        /*
         * 如果数据起点位置小于0则表示当前已经到了最后一张图片
         */
        if (start < 0) {
            // 此时设置isLastPage为true
            isLastPage = true;
            // 并显示提示信息
            showToast("This is fucking lastest page");
            // 强制重置起始位置
            start = 0;
            end = 1;
        }

        for (int i = start; i < end; i++) {
            canvas.save();
            /*
             * 仅裁剪位于最顶层的画布区域
             * 如果到了末页则不在执行裁剪
             */
            if (!isLastPage && i == end - 1) {
                canvas.clipRect(0, 0, mClipX, mHeight);
            }
            canvas.drawBitmap(mBitmaps.get(i), 0, 0, null);

            canvas.restore();
        }
    }

    /**
     * 设置位图数据
     *
     * @param bitmaps 位图数据列表
     */
    public synchronized void setBitmaps(List<Bitmap> bitmaps) {
        /*
         * 如果数据为空则抛出异常
         */
        if (null == bitmaps || bitmaps.size() == 0)
            throw new IllegalArgumentException("no bitmap to display");

        /*
         * 如果数据长度小于2则GG思密达
         */
        if (bitmaps.size() < 2)
            throw new IllegalArgumentException("fuck you and fuck to use imageview");

        mBitmaps = bitmaps;
        invalidate();
    }

    //没有图片 绘制文字
    private void defaultDisplay(Canvas canvas) {
        // 绘制底色
        canvas.drawColor(Color.WHITE);
        //设置大字的大小
        paint.setTextSize(mTextSizeLarger);
        paint.setColor(Color.RED);
        // 绘制标题文本
        canvas.drawText("FBI WARNING", mWidth / 2, mHeight / 4, paint);
        // 绘制提示文本
        paint.setTextSize(mTextSizeNormal);
        paint.setColor(Color.BLACK);
        canvas.drawText("Please set data use setBitmaps method", mWidth / 2, mHeight / 3, paint);
    }

    private static final float TEXT_SIZE_NORMAL = 1 / 40F, TEXT_SIZE_LARGER = 1 / 20F;// 标准文字尺寸和大号文字尺寸的占比

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取控件的宽高
        mWidth = w;
        mHeight = h;
        // 初始化位图数据
        initBitmaps();//画几张重叠的图片
        //计算文字大小
        mTextSizeNormal = TEXT_SIZE_NORMAL * mHeight;
        mTextSizeLarger = TEXT_SIZE_LARGER * mHeight;

        // 初始化裁剪右端点坐标
        mClipX = mWidth;
        // 计算控件左侧和右侧自动吸附的区域
        mAutoAreaLeft = mWidth * 1 / 5F;
        mAutoAreaRight = mWidth * 4 / 5F;
        // 计算一度的有效距离
        mMoveValid = mWidth * 1 / 100F;
    }

    private List<Bitmap> mBitmaps;// 位图数据列表

    /**
     * 初始化位图数据
     * 缩放位图尺寸与屏幕匹配
     */
    private void initBitmaps() {
        List<Bitmap> temp = new ArrayList<Bitmap>();
        for (int i = mBitmaps.size() - 1; i >= 0; i--) {
            Bitmap scaledBitmap = Bitmap.createScaledBitmap(mBitmaps.get(i), mWidth, mHeight, true);
            temp.add(scaledBitmap);
        }
        mBitmaps = temp;
    }

    /**
     * Toast显示
     *
     * @param msg Toast显示文本
     */
    private void showToast(Object msg) {
        Toast.makeText(context, msg.toString(), Toast.LENGTH_SHORT).show();
    }
}
