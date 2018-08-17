package com.tencent.qcloud.uipojo;

import android.app.Application;

import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.qcloud.uikit.ILiveUIKit;
import com.tencent.qcloud.uikit.common.ILiveConstants;
import com.tencent.qcloud.uipojo.test.ANRWatchDog;

/**
 * Created by valexhuang on 2018/6/21.
 */

public class PojoApplication extends Application {

    private static PojoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        ILiveUIKit.init(this);
        TIMManager.getInstance().init(this, new TIMSdkConfig(999));
        instance = this;
        //new ANRWatchDog().start();
    }

    public static PojoApplication instance() {
        return instance;
    }

    public void test() {

    }
}
