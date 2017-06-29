package com.example.shinelon.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.shinelon.demo.demo1.demo1Activity;
import com.example.shinelon.demo.demo2.Demo2Activity;
import com.example.shinelon.demo.demo3.Demo3Activity;
import com.example.shinelon.demo.demo4.Demo4Activity;
import com.example.shinelon.demo.demo5.Demo5Activity;
import com.example.shinelon.demo.demo6.Demo6Activity;
import com.example.shinelon.demo.demo7.Demo7Activity;
import com.example.shinelon.demo.demo8.demo8Activity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.demo1)
    TextView demo1;
    @Bind(R.id.demo2)
    TextView demo2;
    @Bind(R.id.demo3)
    TextView demo3;
    @Bind(R.id.demo4)
    TextView demo4;
    @Bind(R.id.demo5)
    TextView demo5;
    @Bind(R.id.demo6)
    TextView demo6;
    @Bind(R.id.demo7)
    TextView mDemo7;
    @Bind(R.id.demo8)
    TextView mDemo8;


    //    android:descendantFocusability="blocksDescendants"    区块 响应点击事件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.demo1)
    public void onClick1() {
        startActivity(new Intent(this, demo1Activity.class));
    }

    @OnClick(R.id.demo2)
    public void onClick2() {
        startActivity(new Intent(this, Demo2Activity.class));
    }


    @OnClick(R.id.demo3)
    public void onClick3() {
        startActivity(new Intent(this, Demo3Activity.class));
    }

    @OnClick(R.id.demo4)
    public void onClick4() {
        startActivity(new Intent(this, Demo4Activity.class));
    }

    @OnClick(R.id.demo5)
    public void onClick5() {
        startActivity(new Intent(this, Demo5Activity.class));
    }

    @OnClick(R.id.demo6)
    public void onClick6() {
        startActivity(new Intent(this, Demo6Activity.class));
    }

    @OnClick(R.id.demo7)
    public void onClick7() {
        startActivity(new Intent(this, Demo7Activity.class));
    }

    @OnClick(R.id.demo8)
    public void onClick8() {
        startActivity(new Intent(this, demo8Activity.class));
    }
}
