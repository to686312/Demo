package com.example.shinelon.demo.demo9;

import android.app.Notification;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.shinelon.demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Shinelon on 2017/3/7.
 */

public class demo9Activity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener {

    @Bind(R.id.content)
    FrameLayout mContent;
    @Bind(R.id.bottom)
    BottomNavigationBar mBottom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo9_layout);
        ButterKnife.bind(this);

        mBottom.addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "首页"))
                .setActiveColor(R.color.colorAccent)
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "首页"))
                .setActiveColor(R.color.colorAccent)
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "首页"))
                .setActiveColor(R.color.colorAccent)
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "首页"))
                .setActiveColor(R.color.colorAccent)
                .setFirstSelectedPosition(0)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setTabSelectedListener(this)
                .setBarBackgroundColor(R.color.colorPrimaryDark)
                .initialise();


    }

    @Override
    public void onTabSelected(int position) {
        Log.e("11111", "onTabSelected: " + position);
    }

    @Override
    public void onTabUnselected(int position) {
        Log.e("22222", "onTabUnselected: " + position);
    }

    @Override
    public void onTabReselected(int position) {
        Log.e("33333", "onTabReselected: " + position);
    }
}
