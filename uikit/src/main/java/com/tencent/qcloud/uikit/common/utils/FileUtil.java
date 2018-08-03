package com.tencent.qcloud.uikit.common.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import com.tencent.qcloud.uikit.common.ILiveConstants;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * =====================================
 * 作    者: 陈嘉桐
 * 版    本：1.1.4
 * 创建日期：2017/4/25
 * 描    述：
 * =====================================
 */
public class FileUtil {

    public static void initPath() {
        File f = new File(ILiveConstants.MEDIA_DIR);
        if (!f.exists()) {
            f.mkdirs();
        }
    }

    public static String saveBitmap(String dir, Bitmap b) {
        initPath();
        String jpegName = ILiveConstants.MEDIA_DIR + File.separator + "picture_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".jpg";
        try {
            FileOutputStream fout = new FileOutputStream(jpegName);
            BufferedOutputStream bos = new BufferedOutputStream(fout);
            b.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            return jpegName;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static boolean deleteFile(String url) {
        boolean result = false;
        File file = new File(url);
        if (file.exists()) {
            result = file.delete();
        }
        return result;
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static String getPathFromUri(Uri uri) {
        try {
            return new URI(uri.toString()).getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    public static Uri getUriFromPath(String path) {
        try {
            return Uri.fromFile(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
