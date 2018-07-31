package com.tencent.qcloud.uikit.business.chat.presenter;

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.qcloud.uikit.api.chat.IChatPanel;
import com.tencent.qcloud.uikit.business.chat.model.ChatManager;
import com.tencent.qcloud.uikit.business.chat.model.ChatProvider;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfo;

/**
 * Created by valxehuang on 2018/7/18.
 */

public class ChatPresenter {
    IChatPanel mChatPanel;
    ChatManager mChatManager;

    public ChatPresenter(IChatPanel chatPanel) {
        this.mChatPanel = chatPanel;
        mChatManager = ChatManager.getInstance();
    }


    public void loadChatInfo(String userId) {
        mChatManager.loadChatMessages(userId, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                mChatPanel.setDataProvider((ChatProvider) data);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {

            }
        });
    }

    public void sendMessage(MessageInfo message) {
        mChatManager.sendMessage(message, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                mChatPanel.refreshData();
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {

            }
        });
    }

}
