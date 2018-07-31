package com.tencent.qcloud.uipojo.login.model;

import android.content.Intent;
import android.text.TextUtils;

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.ilivesdk.core.ILiveLoginManager;
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
        ILiveLoginManager.getInstance().tlsLoginAll(name, password, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                afterLogin(name, password);
                if (callback != null)
                    callback.onSuccess(data);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                if (callback != null)
                    callback.onFail(module, errCode, errMsg);
            }
        });
    }

    // 注册
    public void register(String userName, String password, final LoginCallback callback) {

        ILiveLoginManager.getInstance().tlsRegister(userName, password, new ILiveCallBack() {
            @Override
            public void onSuccess(Object data) {
                if (callback != null)
                    callback.onSuccess(data);
            }

            @Override
            public void onError(String module, int errCode, String errMsg) {
                if (callback != null)
                    callback.onFail(module, errCode, errMsg);
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
