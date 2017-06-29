package com.example.shinelon.demo.demo11;

import android.widget.ImageView;

import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/**
 * Created by Shinelon on 2017/3/28.
 */

public class ImageAdapter implements IWXImgLoaderAdapter {
    @Override
    public void setImage(String s, ImageView view, WXImageQuality quality, WXImageStrategy strategy) {
        //实现图片下载接口，初始化时设置。
    }
}
