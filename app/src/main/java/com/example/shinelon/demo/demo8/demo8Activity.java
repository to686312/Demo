package com.example.shinelon.demo.demo8;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.widget.ImageView;

import com.example.shinelon.demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shinelon on 2017/1/19.
 */

public class demo8Activity extends AppCompatActivity {
    @Bind(R.id.show)
    AppCompatButton mShow;
    @Bind(R.id.image)
    ImageView mImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo8_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.show)
    public void onClick() {

    }
}
