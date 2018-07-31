package com.tencent.qcloud.uikit.api.chat;

import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatBottomAction;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatPanelEvent;
import com.tencent.qcloud.uikit.business.infos.model.BaseInfoBean;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;

import java.util.List;

/**
 * Created by valxehuang on 2018/7/17.
 */

public interface IChatPanel {

    void setChatPanelEvent(ChatPanelEvent event);

    void setDataProvider(IChatProvider provider);

    void setBaseInfo(BaseInfoBean info);

    IContactDataProvider setProxyDataProvider(IChatProvider provider);

    void refreshData();

    void initDefault();

    void setBottomActions(List<ChatBottomAction> actions);

    PageTitleBar getTitleBar();
}
