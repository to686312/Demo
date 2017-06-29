package com.example.shinelon.demo.demo11;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.shinelon.demo.R;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shinelon on 2017/3/24.
 */

public class demo11Activity extends AppCompatActivity  implements IWXRenderListener {
    @Bind(R.id.btn)
    Button mBtn;
    private WXSDKInstance mMWXSDKInstance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo11_layout);
        ButterKnife.bind(this);


        mMWXSDKInstance = new WXSDKInstance(this);
        mMWXSDKInstance.registerRenderListener(this);



        /**
         * WXSample 可以替换成自定义的字符串，针对埋点有效。
         * template 是.we transform 后的 js文件。
         * option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
         * jsonInitData 可以为空。
         * width 为-1 默认全屏，可以自己定制。
         * height =-1 默认全屏，可以自己定制。
         */
        mMWXSDKInstance.render("WXSample", WXFileUtils.loadFileContent("hello.js", this), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
    }

    @OnClick(R.id.btn)
    public void onClick() {
    }

    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        setContentView(view);
    }

    @Override
    public void onRenderSuccess(WXSDKInstance instance, int i, int i1) {

    }

    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int i, int i1) {

    }

    @Override
    public void onException(WXSDKInstance instance, String s, String s1) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mMWXSDKInstance!=null){
            mMWXSDKInstance.onActivityResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mMWXSDKInstance!=null){
            mMWXSDKInstance.onActivityPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mMWXSDKInstance!=null){
            mMWXSDKInstance.onActivityStop();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mMWXSDKInstance!=null){
            mMWXSDKInstance.onActivityDestroy();
        }
    }
}
