//package com.example.shinelon.demo.demo2;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.support.v4.view.MotionEventCompat;
//import android.support.v4.widget.ViewDragHelper;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.FrameLayout;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//import com.example.shinelon.demo.R;
//
//import static android.R.attr.left;
//
///**
// * Created by Shinelon on 2016/12/2.
// */
//
//public class AlarmView extends RelativeLayout {
//
//    private String TAG = "XJ=";
//    private Context context;
//    private String title = "安立路";
//    private String content = "到站提醒";
//    private ViewDragHelper helper;
//    private RelativeLayout item_click;
//
//    public AlarmView(Context context) {
//        this(context, null);
//    }
//
//    public AlarmView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public AlarmView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        this.context = context;
//        GetArgums(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void GetArgums(Context context, AttributeSet attrs, int defStyleAttr) {
//        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.AlarmView, 0, 0);
//        title = array.getString(R.styleable.AlarmView_title);
//        content = array.getString(R.styleable.AlarmView_content);
//        array.recycle();
//    }
//
//    @Override
//    protected void onFinishInflate() {
//        item_click = (RelativeLayout) this.findViewById(R.id.item_click);
//        super.onFinishInflate();
//    }
//
//    private void init() {
//        helper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
//            @Override
//            public boolean tryCaptureView(View child, int pointerId) {
//                return child == item_click;
//            }
//
//            @Override
//            public int clampViewPositionHorizontal(View child, int left, int dx) {
//                if (left <= 0) {
//                    return 0;
//                } else if (left > PixelUtil.dp2px(context, 220)) {
//                    return PixelUtil.dp2px(context, 220);
//                } else {
//                    return left;
//                }
//            }
//
//            @Override
//            public int clampViewPositionVertical(View child, int top, int dy) {
//                return 0;
//            }
//
//            //手指释放的时候回调
//            @Override
//            public void onViewReleased(View releasedChild, float xvel, float yvel) {
////                super.onViewReleased(releasedChild, xvel, yvel);
//                Log.e(TAG, "onViewReleased:" + xvel + "=====" + releasedChild.getLeft()+"____"+releasedChild.getWidth());
//                int width = releasedChild.getWidth();
//                int left = releasedChild.getLeft();
//                if (releasedChild == item_click) {
//                    if(left<=width/2){
//                        helper.settleCapturedViewAt(0,0);
//                    }else if(left>width/2){
//                        helper.settleCapturedViewAt(PixelUtil.dp2px(context, 220),0);
//                    }
//                    invalidate();
//                }
//            }
//
//            //在边界拖动时回调
//            @Override
//            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
//                super.onEdgeDragStarted(edgeFlags, pointerId);
//            }
//        });
//
//    }
//
//    @Override
//    public void computeScroll() {
//        if (helper.continueSettling(true)) {
//            invalidate();
//        }
//    }
//    //    @Override
////    public boolean onInterceptTouchEvent(MotionEvent event)
////    {
////        return helper.shouldInterceptTouchEvent(event);
////    }
////
////    @Override
////    public boolean onTouchEvent(MotionEvent event)
////    {
////        helper.processTouchEvent(event);
////        return true;
////    }
//
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        final int action = MotionEventCompat.getActionMasked(ev);
//        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
//            helper.cancel();
//            return false;
//        }
//        return helper.shouldInterceptTouchEvent(ev);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent ev) {
//        helper.processTouchEvent(ev);
//        return true;
//    }
//
//}
