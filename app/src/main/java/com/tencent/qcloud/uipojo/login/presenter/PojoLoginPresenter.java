package com.tencent.qcloud.uipojo.login.presenter;

import com.tencent.qcloud.uipojo.login.model.PojoLoginManager;
import com.tencent.qcloud.uipojo.login.view.ILoginView;

import org.json.JSONObject;

/**
 * Created by Administrator on 2018/7/2.
 */

public class PojoLoginPresenter {
    ILoginView mView;
    PojoLoginManager mManager = PojoLoginManager.getInstance();


    public PojoLoginPresenter(ILoginView view) {
        mView = view;
    }

    public void login(String userName, String userPassword) {
        mManager.login(userName, userPassword, new PojoLoginManager.LoginCallback() {
            @Override
            public void onSuccess(Object res) {
                mView.onLoginSuccess(res);
            }

            @Override
            public void onFail(String module, int errCode, String errMsg) {
                mView.onLoginFail(module, errCode, errMsg);
            }
        });
    }

    public void register(String userName, String userPassword) {
        mManager.register(userName, userPassword, new PojoLoginManager.LoginCallback() {
            @Override
            public void onSuccess(Object res) {
                mView.onRegisterSuccess(res);
            }

            @Override
            public void onFail(String module, int errCode, String errMsg) {
                mView.onRegisterFail(module, errCode, errMsg);
            }
        });
    }
}
