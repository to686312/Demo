package com.example.shinelon.demo.demo3;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatImageView;
import android.widget.Toast;

import com.example.shinelon.demo.R;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shinelon on 2016/12/13.
 */

public class Demo3Activity extends Activity {

    @Bind(R.id.demo3_click)
    AppCompatButton demo3Click;
    @Bind(R.id.demo3_read)
    AppCompatButton demo3Read;
    @Bind(R.id.image)
    AppCompatImageView image;
    @Bind(R.id.demo3_remove)
    AppCompatButton demo3Remove;
    @Bind(R.id.demo3_delete)
    AppCompatButton demo3Delete;
    @Bind(R.id.demo3_size)
    AppCompatButton demo3Size;
    private DiskLruCache mDiskLruCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo3);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.demo3_click)
    public void onClick() {//缓存的开始
//        DiskLruCache.open();


        try {
            File cacheDir = getDiskCacheDir(this, "bitmap");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(this), 1, 10 * 1024 * 1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        downloadUrlToStream("http://img.my.csdn.NET/uploads/201309/01/1378037235_7476.jpg",stream);
//        String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
//        String key = hashKeyForDisk(imageUrl);
//        try {
//            DiskLruCache.Editor editor = mDiskLruCache.edit(key);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
                    String key = hashKeyForDisk(imageUrl);
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor != null) {
                        OutputStream outputStream = editor.newOutputStream(0);
                        if (downloadUrlToStream(imageUrl, outputStream)) {
                            editor.commit();
                        } else {
                            editor.abort();
                        }
                    }
                    mDiskLruCache.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            return true;
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

//    检测缓存路径 在是open换成之前调用的
//    是open第一个参数的 结果

    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();// /sdcard/Android/data/<application package>/cache
        } else {
            cachePath = context.getCacheDir().getPath();///data/data/<application package>/cache
        }
        return new File(cachePath + File.separator + uniqueName);//File.separator==\
    }


    public int getAppVersion(Context context) {
        try {
            PackageInfo info = this.getPackageManager().getPackageInfo(this.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    @OnClick(R.id.demo3_read)
    public void onClick2() {
        try {
            String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
            String key = hashKeyForDisk(imageUrl);
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                image.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.demo3_remove)
    public void onClick3() {
/**
 * 因为你完全不需要担心缓存的数据过多从而占用SD卡太多空间的问题，
 * DiskLruCache会根据我们在调用open()方法时设定的缓存最大值来自动删除多余的缓存。
 * 只有你确定某个key对应的缓存内容已经过期，需要从网络获取最新数据的时候才应该调用remove()方法来移除缓存。
 */
        try {
            String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
            String key = hashKeyForDisk(imageUrl);
            mDiskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mDiskLruCache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.demo3_delete)
    public void onClick4() {
        try {
            mDiskLruCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.demo3_size)
    public void onClick5() {
        long size = mDiskLruCache.size();
        Toast.makeText(this, "大小:" + size, Toast.LENGTH_SHORT).show();
    }
}
