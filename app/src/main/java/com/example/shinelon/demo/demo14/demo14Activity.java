package com.example.shinelon.demo.demo14;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.shinelon.demo.R;

/**
 * Author:Shinelon
 * Time:2017/5/4
 * Email:13222231846@163.com
 * Description:
 */
public class demo14Activity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo14_layout);
    }
    public void GetData(View view){
        String xj = MetaUtils.getString(this, "XJ","");
        Log.e("测试xj=", "GetData: "+xj);
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.e("测试xj=", "onCreateView: "+name);
        return super.onCreateView(name, context, attrs);
    }


}
