package com.tencent.qcloud.uikit.business.infos.view.widget;

import com.tencent.qcloud.uikit.business.infos.model.PersonalInfoBean;

/**
 * Created by valxehuang on 2018/7/30.
 */

public interface PersonalInfoPanelEvent {

    void onBackClick();

    void onButtonClick(boolean isFriend, PersonalInfoBean info);

    void onAddFriendClick(PersonalInfoBean info);

    void onDelFriendClick(PersonalInfoBean info);

    void onAddToBlackListClick(PersonalInfoBean info);

}
