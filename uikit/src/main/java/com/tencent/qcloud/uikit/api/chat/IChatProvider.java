package com.tencent.qcloud.uikit.api.chat;

import com.tencent.qcloud.uikit.business.chat.model.MessageInfo;

import java.util.List;

/**
 * Created by valexhuang on 2018/7/17.
 */

public interface IChatProvider {

    public List<MessageInfo> getDataSource();

    public void addMessageInfo(MessageInfo session);

    public void deleteMessageInfo(MessageInfo session);

}
