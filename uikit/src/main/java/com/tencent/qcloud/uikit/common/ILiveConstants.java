package com.tencent.qcloud.uikit.common;

import android.os.Environment;

import com.tencent.qcloud.uikit.ILiveUIKit;

/**
 * Created by valexhuang on 2018/7/16.
 */

public class ILiveConstants {

    public static String SD_CARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static String APP_DIR = SD_CARD_PATH + "/" + ILiveUIKit.getAppContext().getPackageName();
    public static String RECORD_DIR = SD_CARD_PATH + "/" + ILiveUIKit.getAppContext().getPackageName() + "/record";
    public static String MEDIA_DIR = SD_CARD_PATH + "/" + ILiveUIKit.getAppContext().getPackageName() + "/media";

    public static final String CAMERA_IMAGE_PATH = "camera_image_path";
    public static final String CAMERA_VIDEO_PATH = "camera_video_path";
    public static String UI_PARAMS = "ilive_ui_params";
    public static String SOFT_KEY_BOARD_HEIGHT = "soft_key_board_height";
    public static String NAVIGATION_BAR_HEIGHT = "navigation_bar_height";
}
