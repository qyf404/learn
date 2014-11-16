package com.qyf404.learn.qiniu;
import java.io.File;

import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

public class UploadFile {
    
    public static void main(String[] args) throws Exception {
        Config.ACCESS_KEY = "V3O3ITZcuZp8_jJJ7J-6IAT1f54cSWqMVkHlkqbR";
        Config.SECRET_KEY = "aC7Q0YH4zVMx2g6K7bBO6g0gBcl29wcbxlLFlQdS";
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        // 请确保该bucket已经存在
        String bucketName = "ztc777";
        PutPolicy putPolicy = new PutPolicy(bucketName);
        String uptoken = putPolicy.token(mac);
        PutExtra extra = new PutExtra();
        String key = "abc.ppp";
        String localFile = "/Users/qyfmac/temp/777ztc/original/2014-11-08/brand/76355cbc-f59b-469a-af14-317b24426ed1.jpeg";
        PutRet ret = IoApi.putFile(uptoken, key, localFile, extra);
        int code = ret.getStatusCode();
        System.out.println(code);
        System.out.println(ret.getException());
    }
}