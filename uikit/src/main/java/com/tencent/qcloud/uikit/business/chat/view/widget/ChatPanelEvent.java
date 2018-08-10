package com.tencent.qcloud.uikit.business.chat.view.widget;

import android.view.View;

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfo;

/**
 * Created by valxehuang on 2018/7/18.
 */

public interface ChatPanelEvent {

    void sendMessage(MessageInfo messageInfo, ILiveCallBack callBack);

    void onMessageClick(View view, int position);

    void onMessageDoubleClick(View view, int position);

    void onMessageLongClick(View view, int position);
}
