package com.tencent.qcloud.uikit.business.chat.model;

import com.tencent.ilivesdk.ILiveCallBack;

/**
 * Created by valxehuang on 2018/7/18.
 */

public class ChatManager {
    private static ChatManager instance = new ChatManager();
    ChatProvider provider;

    public static ChatManager getInstance() {
        return instance;
    }

    private ChatManager() {
        provider = new ChatProvider();
    }

    public void loadChatMessages(String userId, ILiveCallBack callBack) {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setFromUser("大美女");
        messageInfo.setMsg("帅哥，晚上约嘛");
        messageInfo.setMsgId("abc_123");
        provider.getDataSource().add(messageInfo);

        messageInfo = new MessageInfo();
        messageInfo.setFromUser("大帅哥");
        messageInfo.setMsg("不约，晚上要带娃");
        messageInfo.setMsgId("efg_123");
        messageInfo.setSelf(true);
        provider.getDataSource().add(messageInfo);

        callBack.onSuccess(provider);
    }


    public void sendMessage(MessageInfo message, ILiveCallBack callBack) {
        provider.getDataSource().add(message);
        callBack.onSuccess(provider);
    }


}
