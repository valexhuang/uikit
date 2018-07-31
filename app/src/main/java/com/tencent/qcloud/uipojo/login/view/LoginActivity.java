package com.tencent.qcloud.uipojo.login.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.tencent.qcloud.uikit.api.login.ILoginPanel;
import com.tencent.qcloud.uikit.business.login.view.ILoginEvent;
import com.tencent.qcloud.uikit.business.login.view.LoginView;
import com.tencent.qcloud.uikit.common.utils.UIUtils;
import com.tencent.qcloud.uipojo.R;
import com.tencent.qcloud.uipojo.common.Constants;
import com.tencent.qcloud.uipojo.contact.view.MainContactActivity;
import com.tencent.qcloud.uipojo.login.presenter.PojoLoginPresenter;
import com.tencent.qcloud.uipojo.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valexhuang on 2018/6/21.
 */

public class LoginActivity extends Activity implements ILoginEvent, ILoginView {
    private static final int REQ_PERMISSION_CODE = 0x100;
    ILoginPanel mLoginPanel;
    PojoLoginPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        //showSystemUI();
        super.onCreate(savedInstanceState);
        checkPermission();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_login);
        mPresenter = new PojoLoginPresenter(this);
        mLoginPanel = findViewById(R.id.login_view);
        mLoginPanel.setLoginEvent(this);


        initCache();

    }

    private void initCache() {
        SharedPreferences shareInfo = getApplication().getSharedPreferences(Constants.USERINFO, 0);
        String account = shareInfo.getString(Constants.ACCOUNT, "");
        String password = shareInfo.getString(Constants.PWD, "");
        ((LoginView) mLoginPanel).getAccountEditor().setText(account);
        ((LoginView) mLoginPanel).getPasswordEditor().setText(password);
    }

    @Override
    public void onLoginClick(View view, String userName, String password) {
        mPresenter.login(userName, password);


    }

    @Override
    public void onRegisterClick(View view, String userName, String password) {
        mPresenter.register(userName, password);
    }


    @Override
    public void onLoginSuccess(Object res) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(((LoginView) mLoginPanel).getPasswordEditor().getWindowToken(), 0);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onLoginFail(String module, int errCode, String errMsg) {
        UIUtils.toastLongMessage("登录失败:" + errCode + "=" + errMsg);
    }

    @Override
    public void onRegisterSuccess(Object res) {
        UIUtils.toastLongMessage("注册成功");
        Intent intent = new Intent(this, MainContactActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRegisterFail(String module, int errCode, String errMsg) {
        UIUtils.toastLongMessage("注册失败:" + errCode + "=" + errMsg);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        showSystemUI();
    }

    private void showSystemUI() {
        final View decorView = getWindow().getDecorView();
        decorView.postDelayed(new Runnable() {
            @Override
            public void run() {
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }, 200);

    }

    private boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(this,
                        (String[]) permissions.toArray(new String[0]),
                        REQ_PERMISSION_CODE);
                return false;
            }
        }

        return true;
    }
}
