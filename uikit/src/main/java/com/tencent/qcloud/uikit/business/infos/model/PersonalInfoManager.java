package com.tencent.qcloud.uikit.business.infos.model;

import com.tencent.ilivesdk.ILiveCallBack;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class PersonalInfoManager {

    private static PersonalInfoManager instance = new PersonalInfoManager();

    public static PersonalInfoManager getInstance() {
        return instance;
    }

    private PersonalInfoManager() {
    }

    public void addFriend(PersonalInfoBean info, ILiveCallBack callBack) {

    }

    public void delFriend(PersonalInfoBean info, ILiveCallBack callBack) {

    }

    public void addToBlackList(PersonalInfoBean info, ILiveCallBack callBack) {

    }
}
