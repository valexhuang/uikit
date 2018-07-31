package com.tencent.qcloud.uikit.business.infos.presenter;

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.qcloud.uikit.api.infos.IPersonalInfoPanel;
import com.tencent.qcloud.uikit.business.infos.model.PersonalInfoBean;
import com.tencent.qcloud.uikit.business.infos.model.PersonalInfoManager;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class PersonalPresenter {
    private IPersonalInfoPanel mInfoPanel;
    private PersonalInfoManager mManager = PersonalInfoManager.getInstance();

    public PersonalPresenter(IPersonalInfoPanel infoPanel) {
        this.mInfoPanel = infoPanel;
    }

    public void initPersonalInfo(PersonalInfoBean info) {
        mInfoPanel.initPersonalInfo(info);
    }

    public void addFriend(PersonalInfoBean info) {
        mManager.addFriend(info, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {

            }

            @Override
            public void onError(String module, int errCode, String errMsg) {

            }
        });
    }


    public void delFriend(PersonalInfoBean info) {
        mManager.delFriend(info, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {

            }

            @Override
            public void onError(String module, int errCode, String errMsg) {

            }
        });
    }

    public void addToBlackList(PersonalInfoBean info) {
        mManager.addToBlackList(info, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {

            }

            @Override
            public void onError(String module, int errCode, String errMsg) {

            }
        });
    }
}
