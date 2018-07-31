package com.tencent.qcloud.uikit.business.login.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.api.login.ILoginPanel;

import java.util.ArrayList;

/**
 * Created by valexhuang on 2018/7/2.
 */

public class LoginView extends LinearLayout implements ILoginPanel {
    private EditText mUserAccount, mPassword;
    private Button mLoginButton, mRegisterButton;
    private LinearLayout mLoginBtnGroup;
    private ILoginEvent mLoginEvent;

    public LoginView(Context context) {
        super(context);
        init();
    }

    public LoginView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.login_panel, this);
        mUserAccount = findViewById(R.id.login_user);
        mPassword = findViewById(R.id.login_password);
        mLoginButton = findViewById(R.id.login_btn);
        mRegisterButton = findViewById(R.id.register_btn);
        mLoginBtnGroup = findViewById(R.id.login_btn_groups);
        mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoginEvent != null)
                    mLoginEvent.onLoginClick(v, mUserAccount.getText().toString(), mPassword.getText().toString());
            }
        });
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLoginEvent != null)
                    mLoginEvent.onRegisterClick(v, mUserAccount.getText().toString(), mPassword.getText().toString());
            }
        });
    }

    public void setLoginEvent(ILoginEvent event) {
        this.mLoginEvent = event;
    }


    public void addButtons(ArrayList<View> buttons) {
        for (int i = 0; i < buttons.size(); i++) {
            mLoginBtnGroup.addView(buttons.get(i));
        }

    }

    public EditText getAccountEditor() {
        return mUserAccount;
    }


    public EditText getPasswordEditor() {
        return mPassword;
    }

    public LinearLayout getButtonGroup() {
        return mLoginBtnGroup;
    }
}
