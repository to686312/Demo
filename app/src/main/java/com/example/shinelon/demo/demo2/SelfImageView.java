package com.example.shinelon.demo.demo2;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;
import com.example.shinelon.demo.R;
/**
 * Created by Shinelon on 2016/11/17.
 */

public class SelfImageView extends ImageView {

    private Matrix current_matrix;
    private Matrix save_matrix;
    private PointF start;
    private PointF min;

    private static final int MODE_NONE = 0x00123;// 默认的触摸模式
    private static final int MODE_DRAG = 0x00321;// 拖拽模式
    private static final int MODE_ZOOM = 0x00132;// 缩放or旋转模式

    private int mode;// 当前的触摸模式

    private Context context;
    private int window_width;
    private int window_height;
    private float save_rot = 0f;
    private float calse=1f;//第一次的距离    不变的

    public SelfImageView(Context context) {
        this(context, null);
    }

    public SelfImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelfImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    private void init() {
        //实例对象
        current_matrix = new Matrix();// 当前和保存了的Matrix对象
        save_matrix = new Matrix();// 当前和保存了的Matrix对象

        start = new PointF();
        min = new PointF();

        mode = MODE_NONE;

        setWindow();

        //设置图片资源
        Bitmap resource = BitmapFactory.decodeResource(getResources(), R.drawable.photo1);

        resource.createScaledBitmap(resource, window_width, window_height, true);
        //设置图片在空间上
        setImageBitmap(resource);
    }

    private float[] preEventCoor;// 上一次各触摸点的坐标集合

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:// 单点接触屏幕时
                //一只手指的拖拽
                save_matrix.set(current_matrix);
                start.set(event.getX(), event.getY());
                mode = MODE_DRAG;
                preEventCoor = null;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:// 第二个点接触屏幕时
                //这是两个手指的距离
                calse = calse(event);//第一次的距离    不变的
                if (calse > 10f) {//距离大于10  就是两指  缩放
                    save_matrix.set(current_matrix);
                    calMidPoint(min, event);//给中点赋值
                    mode = MODE_ZOOM;
                }
                //把两个手指的坐标放到数组中
                preEventCoor = new float[4];
                preEventCoor[0] = event.getX(0);
                preEventCoor[1] = event.getY(0);

                preEventCoor[2] = event.getX(1);
                preEventCoor[3] = event.getY(1);

                //计算旋转角度
                save_rot = calRomot(event);
                break;
            case MotionEvent.ACTION_UP:// 单点离开屏幕时
                mode = MODE_NONE;
                preEventCoor = null;
                break;
            case MotionEvent.ACTION_POINTER_UP:// 第二个点离开屏幕时
                mode = MODE_NONE;
                preEventCoor = null;
                break;
            case MotionEvent.ACTION_MOVE:// 触摸点移动时
                if (mode == MODE_DRAG) {// 单点触控拖拽平移
                    current_matrix.set(save_matrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    current_matrix.postTranslate(dx,dy);
                } else if (mode == MODE_ZOOM && event.getPointerCount() == 2) {//两点触控拖放旋转
                    float distince = calse(event);//两个手指的距离   最后的两指距离 与第一次对比
                    current_matrix.set(save_matrix);
                    if(distince>10f){//指尖移动距离大于10F缩放
                        float last = distince / calse;
                        current_matrix.postScale(last,last,min.x,min.y);
                    }

                    if(preEventCoor!=null){//保持两点时旋转
                        float last_roto = calRomot(event);
                        float r= last_roto - save_rot;
                        current_matrix.postRotate(r,getMeasuredWidth()/2,getMeasuredHeight()/2);
                    }
                }
                break;
        }
        setImageMatrix(current_matrix);
        return true;
    }

    //    计算旋转角度
    private float calRomot(MotionEvent event) {
        //三角公式 计算角度
        double radiuo = Math.atan2(event.getY(0) - event.getY(1), event.getX(0) - event.getX(1));
        return (float) Math.toDegrees(radiuo);
    }

    //计算两个触摸点的中点坐标
    private void calMidPoint(PointF min, MotionEvent event) {
        min.set(event.getX(0) + event.getX(1) / 2, (event.getY(0) + event.getY(1)) / 2);
    }

    //计算两点间的距离
    private float calse(MotionEvent event) {//这是两个手指间的距离
        float dx = event.getX(0) - event.getX(1);
        float dy = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(dx * dx + dy * dy);//勾股定理计算距离
    }

    private void setWindow() {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        window_width = wm.getDefaultDisplay().getWidth();
        window_height = wm.getDefaultDisplay().getHeight();
    }
}
