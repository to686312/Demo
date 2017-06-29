package com.example.shinelon.demo.demo17;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.shinelon.demo.R;
import com.meituan.robust.Patch;
import com.meituan.robust.PatchExecutor;
import com.meituan.robust.RobustCallBack;
import com.meituan.robust.patch.RobustModify;
import com.meituan.robust.patch.annotaion.Add;
import com.meituan.robust.patch.annotaion.Modify;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:Shinelon
 * Time:2017/5/17
 * Email:13222231846@163.com
 * Description:
 */
public class demo17Activity extends AppCompatActivity {
    @Bind(R.id.click)
    Button mClick;
    @Bind(R.id.show)
    TextView mShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo17_layout);
        ButterKnife.bind(this);
        RobustModify.modify();
        settext();
    }
    @Modify
    public void settext() {
        mShow.setText(GetInfo());
    }
    @Add
    public String GetInfo(){
        return "xj";
    }

    @OnClick(R.id.click)
    public void onViewClicked() {
        new PatchExecutor(getApplicationContext(),new PatchExecutorImp() , new RobustCallBack() {
            @Override
            public void onPatchListFetched(boolean result, boolean isNet, List<Patch> patches) {

            }

            @Override
            public void onPatchFetched(boolean result, boolean isNet, Patch patch) {

            }

            @Override
            public void onPatchApplied(boolean result, Patch patch) {

            }

            @Override
            public void logNotify(String log, String where) {

            }

            @Override
            public void exceptionNotify(Throwable throwable, String where) {

            }
        }).start();
    }
}
