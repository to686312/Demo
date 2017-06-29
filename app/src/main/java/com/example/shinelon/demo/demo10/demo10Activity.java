package com.example.shinelon.demo.demo10;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.GetMsg;
import com.example.shinelon.demo.R;

/**
 * Created by Shinelon on 2017/3/23.
 */
//@GetMsg(id=2,name="MAIN")
public class demo10Activity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo10_layout);
        getClick();
    }
    @GetMsg(id=2,name="MAIN")
    public void getClick(){

    }
}
