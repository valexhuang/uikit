package com.tencent.qcloud.uikit;

import android.content.Context;

import com.tencent.qcloud.uikit.common.BackgroundTasks;
import com.tencent.qcloud.uikit.common.component.face.FaceManager;

/**
 * Created by valexhuang on 2018/6/22.
 */

public class ILiveUIKit {
    private static Context appContext;

    public static void init(Context context) {
        appContext = context;
        BackgroundTasks.initInstance();
        FaceManager.loadEmojiFiles();
    }


    public static Context getAppContext() {
        return appContext;
    }


}
