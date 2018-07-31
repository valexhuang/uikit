package com.tencent.qcloud.uikit.business.infos.model;

import com.tencent.ilivesdk.ILiveCallBack;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class GroupInfoManager {

    private static GroupInfoManager instance = new GroupInfoManager();

    public static GroupInfoManager getInstance() {
        return instance;
    }

    private GroupInfoManager() {
    }

    public void addFriend(PersonalInfoBean info, ILiveCallBack callBack) {

    }

    public void delFriend(PersonalInfoBean info, ILiveCallBack callBack) {

    }

    public void addToBlackList(PersonalInfoBean info, ILiveCallBack callBack) {

    }
}
