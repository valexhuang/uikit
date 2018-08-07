package com.tencent.qcloud.uikit.business.infos.view.widget;

import com.tencent.qcloud.uikit.business.infos.model.GroupInfoBean;

import java.util.Date;

/**
 * Created by valxehuang on 2018/7/30.
 */

public interface SelfInfoPanelEvent {

    void onModifyNickNameClick(String nickName);

    void onModifySignatureClick(String signature);

    void onModifyBirthdayClick(Date date);
}
