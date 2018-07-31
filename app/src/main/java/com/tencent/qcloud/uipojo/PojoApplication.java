package com.tencent.qcloud.uipojo;

import android.app.Application;

import com.tencent.ilivesdk.ILiveConstants;
import com.tencent.ilivesdk.ILiveSDK;
import com.tencent.ilivesdk.core.ILiveLog;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qcloud.uikit.ILiveUIKit;

/**
 * Created by valexhuang on 2018/6/21.
 */

public class PojoApplication extends Application {

    private static PojoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        if (MsfSdkUtils.isMainProcess(this)) {    // 仅在主线程初始化
            ILiveUIKit.init(this);
            // 初始化LiveSDK
            ILiveSDK.getInstance().setCaptureMode(ILiveConstants.CAPTURE_MODE_SURFACEVIEW);
            ILiveLog.setLogLevel(ILiveLog.TILVBLogLevel.ERROR);
            ILiveSDK.getInstance().initSdk(this, 1400028096, 11851);

        }
        instance = this;
    }

    public static PojoApplication instance() {
        return instance;
    }
}
