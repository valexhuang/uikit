package com.tencent.qcloud.uikit.common.utils;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.tencent.qcloud.uikit.BuildConfig;
import com.tencent.qcloud.uikit.ILiveUIKit;
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
            int sdkVersion = Build.VERSION.SDK_INT;
            if (sdkVersion >= 19) {
                return getRealPathFromUri_AboveApi19(uri);
            } else {
                return getRealFilePath(uri);
            }
            //return new File(new URI(uri.toString())).getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getRealFilePath(Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = ILiveUIKit.getAppContext().getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    private static String getRealPathFromUri_AboveApi19(Uri uri) {
        String filePath = null;
        String wholeID = DocumentsContract.getDocumentId(uri);

        // 使用':'分割
        String id = wholeID.split(":")[1];

        String[] projection = {MediaStore.Images.Media.DATA};
        String selection = MediaStore.Images.Media._ID + "=?";
        String[] selectionArgs = {id};

        Cursor cursor = ILiveUIKit.getAppContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,//
                projection, selection, selectionArgs, null);
        int columnIndex = cursor.getColumnIndex(projection[0]);
        if (cursor.moveToFirst()) filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    public static Uri getUriFromPath(String path) {
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return FileProvider.getUriForFile(ILiveUIKit.getAppContext(), BuildConfig.APPLICATION_ID + ".fileprovider", new File(path));
            } else {
                return Uri.fromFile(new File(path));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
