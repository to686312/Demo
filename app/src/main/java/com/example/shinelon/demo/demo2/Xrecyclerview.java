package com.example.shinelon.demo.demo2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Shinelon on 2016/12/26.
 */

public class Xrecyclerview extends RecyclerView {
    private LoadMoreAndRefreshListener listener;
    private boolean isLoadMore=false;
    private boolean isRefresh=false;

    public Xrecyclerview(Context context) {
        this(context, null);
    }

    public Xrecyclerview(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Xrecyclerview(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
        initListener();
    }

    private void initListener() {
        //区分上啦还是下拉
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy < 0) {//刷新

                } else {//加载

                }
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (isRefresh) {
                    return;
                }
                LayoutManager manager = getLayoutManager();
                //可见的item个数
                int childCount = manager.getChildCount();
                if (childCount > 0 && newState == RecyclerView.SCROLL_STATE_IDLE && !isLoadMore) {
                    View lastVisibleView  = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
                    int lastVisiblePosition  = recyclerView.getChildLayoutPosition(lastVisibleView);
                    if(lastVisiblePosition >=manager.getItemCount()-1){
//                        footerView.setVisibility(VISIBLE);
                        isLoadMore=true;
                        if(listener!=null){
                            listener.LoadMore();
                        }
                    }
                }else{
//                    footerView.setVisibility(GONE);
                }
            }
        });
    }


    private void initView(Context context) {
        setLayoutManager(new LinearLayoutManager(context));

    }


    public void setLoadMoreAndRefreshListener(LoadMoreAndRefreshListener listener) {
        this.listener = listener;
    }


    public interface LoadMoreAndRefreshListener {
        void Refresh();

        void LoadMore();
    }
}
