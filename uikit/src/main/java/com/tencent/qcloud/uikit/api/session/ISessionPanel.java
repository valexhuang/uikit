package com.tencent.qcloud.uikit.api.session;

import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;
import com.tencent.qcloud.uikit.business.session.view.wedgit.PopMenuAction;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;

import java.util.List;

/**
 * Created by valxehuang on 2018/7/17.
 */

public interface ISessionPanel {

    PageTitleBar pageTitleBar();

    void addTopPopActions(List<PopMenuAction> actions);

    void addItemPopActions(List<PopMenuAction> actions);

    void setItemLeftSlideAble(boolean enbale);

    void setSessionListEvent(SessionListEvent event);

    void setDataProvider(ISessionProvider provider);

    ISessionProvider getDataProvider();

    IContactDataProvider setProxyDataProvider(ISessionProvider provider);

    void refreshData();

    void initDefault();


}
