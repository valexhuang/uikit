package com.tencent.qcloud.uikit.api.infos;

import com.tencent.qcloud.uikit.business.infos.model.PersonalInfoBean;
import com.tencent.qcloud.uikit.business.infos.view.widget.PersonalInfoPanelEvent;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;

/**
 * Created by valxehuang on 2018/7/17.
 */

public interface IPersonalInfoPanel {
    void initPersonalInfo(PersonalInfoBean info);

    void setPersonalInfoPanelEvent(PersonalInfoPanelEvent event);

    void initDefault();

    PageTitleBar getTitleBar();

}
