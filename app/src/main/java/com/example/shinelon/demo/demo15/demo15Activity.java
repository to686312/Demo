package com.example.shinelon.demo.demo15;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.example.shinelon.demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:Shinelon
 * Time:2017/5/9
 * Email:13222231846@163.com
 * Description:
 */
public class demo15Activity extends AppCompatActivity {
    @Bind(R.id.v_layout_recy)
    RecyclerView mVLayoutRecy;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo15_layout);
        ButterKnife.bind(this);
        VirtualLayoutManager virtualLayoutManager = new VirtualLayoutManager(this);
        mVLayoutRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mVLayoutRecy.setLayoutManager(virtualLayoutManager);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        mVLayoutRecy.setRecycledViewPool(recycledViewPool);
        recycledViewPool.setMaxRecycledViews(0,20);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
