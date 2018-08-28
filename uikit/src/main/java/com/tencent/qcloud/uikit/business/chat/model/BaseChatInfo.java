package com.tencent.qcloud.uikit.business.chat.model;

/**
 * Created by valexhuang on 2018/8/21.
 */

public class BaseChatInfo {
    private MessageInfo lastMessage;
    private String oppositeName;

    public MessageInfo getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageInfo lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getOppositeName() {
        return oppositeName;
    }

    public void setOppositeName(String oppositeName) {
        this.oppositeName = oppositeName;
    }
}
