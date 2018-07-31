package com.tencent.qcloud.uikit.api.contact;

import com.tencent.qcloud.uikit.business.contact.view.event.ContactPanelEvent;

/**
 * Created by valexhuang on 2018/6/28.
 */

public interface IContactPanel {

    void setContactPanelEvent(ContactPanelEvent event);

    void setDataProvider(IContactDataProvider provider);

    IContactDataProvider setProxyDataProvider(IContactDataProvider provider);

    void refreshData();

    void initDefault();
}

