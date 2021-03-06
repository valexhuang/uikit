package com.tencent.qcloud.uikit.business.chat.model;

import com.tencent.qcloud.uikit.api.chat.IChatProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valexhuang on 2018/7/17.
 */

public class ChatProvider implements IChatProvider {

    ArrayList<MessageInfo> dataSource = new ArrayList();

    @Override
    public List<MessageInfo> getDataSource() {
        return dataSource;
    }

    @Override
    public boolean addMessageInfo(MessageInfo msg) {
        return dataSource.add(msg);

    }

    @Override
    public boolean deleteMessageInfo(MessageInfo msg) {
        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i).getMsgId().equals(msg.getMsgId())) {
                dataSource.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateMessageInfo(MessageInfo message) {
        return false;
    }
}
