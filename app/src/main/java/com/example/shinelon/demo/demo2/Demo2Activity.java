package com.example.shinelon.demo.demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.Window;

import com.example.shinelon.demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Demo2Activity extends AppCompatActivity implements Xrecyclerview.LoadMoreAndRefreshListener {

    @Bind(R.id.xrecyclerview)
    Xrecyclerview xrecyclerview;

    private List<String> list=new ArrayList<>();
    private Xadapter xadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_demo2);
        ButterKnife.bind(this);
        for(int i=0;i<100;i++){
            list.add("1");
        }

        xrecyclerview.setItemAnimator(new DefaultItemAnimator());
        xadapter = new Xadapter(this, list);
        xrecyclerview.setAdapter(xadapter);
        xrecyclerview.setLoadMoreAndRefreshListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void Refresh() {

    }

    @Override
    public void LoadMore() {

    }
}
