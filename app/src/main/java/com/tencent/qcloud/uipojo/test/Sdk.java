package com.tencent.qcloud.uipojo.test;

/**
 * Created by valexhuang on 2018/8/10.
 */

public class Sdk {

    static {
        System.loadLibrary("test");
    }

    public Sdk() {
    }

    //单例
    private static class SdkHodler {
        static Sdk instance = new Sdk();
    }

    public static Sdk getInstance() {
        return SdkHodler.instance;
    }

    //回调到各个线程
    public interface OnSubProgressListener {

        public int onProgressChange(long total, long already);
    }

    ;

    //调到C层的方法
    public native int nativeDownload(String downloadPath, OnSubProgressListener l);

}
