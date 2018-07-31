package com.tencent.qcloud.uikit.api.session;

import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;
import com.tencent.qcloud.uikit.business.session.view.wedgit.PopMenuAction;
import com.tencent.qcloud.uikit.business.session.view.wedgit.SessionPanelEvent;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;

import java.util.List;

/**
 * Created by valxehuang on 2018/7/17.
 */

public interface ISessionPanel {

    PageTitleBar pageTitleBar();

    void addPopActions(List<PopMenuAction> actions);

    void setContactPanelEvent(SessionPanelEvent event);

    void setDataProvider(ISessionProvider provider);

    IContactDataProvider setProxyDataProvider(ISessionProvider provider);

    void refreshData();

    void initDefault();


}
