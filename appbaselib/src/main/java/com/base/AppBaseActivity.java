package com.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Author:Shinelon
 * Time:2017/5/2
 * Email:13222231846@163.com
 * Description:
 */
public abstract class AppBaseActivity extends AppCompatActivity{

    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FrameLayout framelayout = (FrameLayout) findViewById(R.id.framelayout);
        mTitle = (TextView) findViewById(R.id.title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater.from(this).inflate(getContentView(),framelayout);
        init(savedInstanceState);
    }

    /**
     * 设置标题
     * @param str
     */
    protected  void setTitle(String str){
        if(!TextUtils.isEmpty(str)){
            mTitle.setText(str);
        }
    }
    protected abstract void init(Bundle state);

    protected abstract int getContentView();

    public interface  OnClickListener{
        void onClick();
    }

    private OnClickListener listener;
    protected  void setOnClickListener(int icon,OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            listener.onClick();
        }
        return true;
    }
}
