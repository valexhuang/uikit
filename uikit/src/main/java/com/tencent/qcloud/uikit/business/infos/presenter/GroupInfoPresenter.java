package com.tencent.qcloud.uikit.business.infos.presenter;

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.qcloud.uikit.api.infos.IGroupInfoPanel;
import com.tencent.qcloud.uikit.business.infos.model.GroupInfoBean;
import com.tencent.qcloud.uikit.business.infos.model.GroupInfoManager;
import com.tencent.qcloud.uikit.business.infos.model.PersonalInfoBean;
import com.tencent.qcloud.uikit.business.infos.view.GroupInfoPanel;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class GroupInfoPresenter {
    private IGroupInfoPanel mInfoPanel;
    private GroupInfoManager mManager = GroupInfoManager.getInstance();

    public GroupInfoPresenter(GroupInfoPanel infoPanel) {
        this.mInfoPanel = infoPanel;
    }

    public void initPersonalInfo(GroupInfoBean info) {
        mInfoPanel.initGroupInfo(info);
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
