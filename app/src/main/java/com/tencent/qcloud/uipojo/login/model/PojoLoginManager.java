package com.tencent.qcloud.uipojo.login.model;


import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.tencent.qcloud.uipojo.PojoApplication;

import org.json.JSONObject;

/**
 * Created by valexhuang on 2018/6/21.
 */

public class PojoLoginManager {
    private static PojoLoginManager instance = new PojoLoginManager();

    public static PojoLoginManager getInstance() {
        return instance;
    }

    private PojoLoginManager() {
    }

    public void login(final String name, final String password, final LoginCallback callback) {
/*
        TIMCallBack mycallBack = new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                if (callback != null)
                    callback.onFail("", code, desc);

            }

            @Override
            public void onSuccess() {
                afterLogin(name, password);
                if (callback != null)
                    callback.onSuccess("");
            }
        };
        mycallBack.onSuccess();
*/


        TIMManager.getInstance().login(name, password, new TIMCallBack() {
            @Override
            public void onError(int code, String desc) {
                if (callback != null)
                    callback.onFail("", code, desc);

            }

            @Override
            public void onSuccess() {
                afterLogin(name, password);
                if (callback != null)
                    callback.onSuccess("");
            }
        });


    }

    // 登录成功
    private void afterLogin(String userName, String password) {
        UserInfo.getInstance().setAccount(userName);
        UserInfo.getInstance().setPassword(password);
        UserInfo.getInstance().writeToCache(PojoApplication.instance());
    }

    public interface LoginCallback {
        void onSuccess(Object data);

        void onFail(String module, int errCode, String errMsg);
    }
}
