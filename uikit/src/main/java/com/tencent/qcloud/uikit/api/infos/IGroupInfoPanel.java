package com.tencent.qcloud.uikit.api.infos;

import com.tencent.qcloud.uikit.business.infos.model.GroupInfoBean;
import com.tencent.qcloud.uikit.business.infos.model.InfoItemAction;
import com.tencent.qcloud.uikit.business.infos.view.widget.GroupInfoPanelEvent;
import com.tencent.qcloud.uikit.business.session.view.wedgit.PopMenuAction;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;

import java.util.List;

/**
 * Created by valxehuang on 2018/7/17.
 */

public interface IGroupInfoPanel {
    void initGroupInfo(GroupInfoBean info);

    void setGroupInfoPanelEvent(GroupInfoPanelEvent event);

    void initDefault();

    PageTitleBar getTitleBar();

    public void addInfoItem(List<InfoItemAction> items, int group, int index);

    public void addPopActions(List<PopMenuAction> actions);
}
