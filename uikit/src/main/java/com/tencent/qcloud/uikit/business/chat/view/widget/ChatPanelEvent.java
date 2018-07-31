package com.tencent.qcloud.uikit.business.chat.view.widget;

import com.tencent.qcloud.uikit.business.chat.model.MessageInfo;

/**
 * Created by valxehuang on 2018/7/18.
 */

public interface ChatPanelEvent {

    void sendMessage(MessageInfo messageInfo);

    void onMessageLongClick();


}
