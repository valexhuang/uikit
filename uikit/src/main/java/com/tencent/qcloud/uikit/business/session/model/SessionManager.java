package com.tencent.qcloud.uikit.business.session.model;

import com.tencent.ilivesdk.ILiveCallBack;

/**
 * Created by valexhuang on 2018/7/17.
 */

public class SessionManager {

    private SessionProvider provider;

    private static SessionManager instance = new SessionManager();

    public static SessionManager getInstance() {
        return instance;
    }

    private SessionManager() {
        provider = new SessionProvider();
    }


    public void loadSession(ILiveCallBack callBack) {
        for (int i = 0; i < 20; i++) {
            SessionInfo info = new SessionInfo();
            info.setTitle("测试消息" + i);
            info.setMsg("无量寿佛，恭喜发财");
            info.setTime(i + "分钟");
            info.setUnRead(i);
            info.setGroup(i % 2 == 0);
            provider.getDataSource().add(info);
        }
        if (callBack != null) {
            callBack.onSuccess(provider);
        }
    }

}
