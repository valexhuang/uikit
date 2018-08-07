package com.tencent.qcloud.uikit.business.infos.presenter;

import com.tencent.qcloud.uikit.business.infos.model.SelfInfoManager;
import com.tencent.qcloud.uikit.business.infos.view.SelfInfoPanel;

/**
 * Created by valexhuang on 2018/8/6.
 */

public class SelfInfoPresenter {
    private SelfInfoManager manager = SelfInfoManager.getInstance();
    private SelfInfoPanel mSelfPanel;

    public SelfInfoPresenter(SelfInfoPanel panel) {
        mSelfPanel = panel;

    }

}
