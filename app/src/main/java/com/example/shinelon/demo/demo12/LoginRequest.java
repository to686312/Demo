package com.example.shinelon.demo.demo12;

import java.io.File;
import java.util.ArrayList;

/**
 * Author:Shinelon
 * Time:2017/4/28
 * Email:13222231846@163.com
 * Description:
 */
public class LoginRequest {
    public String xclass;
    public String app = "Cas";
    public String _class = "UploadPhotos";
    public ArrayList<File> files = new ArrayList<>();//上传的文件
}
