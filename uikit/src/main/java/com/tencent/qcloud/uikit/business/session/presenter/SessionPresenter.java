package com.tencent.qcloud.uikit.business.session.presenter;

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.qcloud.uikit.api.session.ISessionPanel;
import com.tencent.qcloud.uikit.business.session.model.SessionManager;
import com.tencent.qcloud.uikit.business.session.model.SessionProvider;
import com.tencent.qcloud.uikit.common.utils.UIUtils;

/**
 * Created by valxehuang on 2018/7/17.
 */

public class SessionPresenter {
    private ISessionPanel mSessionPanel;
    private SessionManager mManager;

    public SessionPresenter(ISessionPanel sessionPanel) {
        this.mSessionPanel = sessionPanel;
        mManager = SessionManager.getInstance();
    }

    public void loadSessionData() {
        mManager.loadSession(new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                mSessionPanel.setDataProvider((SessionProvider) data);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                UIUtils.toastLongMessage("加载消息失败");
            }
        });
    }
}
