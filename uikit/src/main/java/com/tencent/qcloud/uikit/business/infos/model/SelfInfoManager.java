package com.tencent.qcloud.uikit.business.infos.model;

import com.tencent.ilivesdk.ILiveCallBack;

/**
 * Created by valexhuang on 2018/8/6.
 */

public class SelfInfoManager {
    private static final SelfInfoManager instance = new SelfInfoManager();

    private SelfInfoManager() {
    }

    public static SelfInfoManager getInstance() {
        return instance;
    }

    public void modifyNickName(String nickName, ILiveCallBack callBack) {

    }

    public void modifySignature(String signature, ILiveCallBack callBack) {

    }

    public void modifyBirthday() {

    }
}
