package com.tencent.qcloud.uikit.api.infos;

import com.tencent.qcloud.uikit.business.infos.model.GroupInfoBean;

/**
 * Created by valxehuang on 2018/7/30.
 */

public interface GroupInfoPanelEvent {

    void onBackClick();

    void onDissolve(GroupInfoBean info);

    void onModifyGroupName(GroupInfoBean info);

    void onModifyGroupNotice(GroupInfoBean info);

}
