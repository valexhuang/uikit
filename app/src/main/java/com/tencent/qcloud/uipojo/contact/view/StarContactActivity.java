package com.tencent.qcloud.uipojo.contact.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tencent.TIMUserProfile;
import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;
import com.tencent.qcloud.uikit.api.contact.IContactPanel;
import com.tencent.qcloud.uikit.business.contact.view.event.ContactPanelEvent;
import com.tencent.qcloud.uipojo.R;
import com.tencent.qcloud.uipojo.contact.presenter.ContactPresenter;

/**
 * Created by Administrator on 2018/6/25.
 */

public class StarContactActivity extends Activity implements ContactPanelEvent, IContactView {

    private ContactPresenter mPresenter;
    private IContactPanel mContactPanel;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_star);
        mContactPanel = findViewById(R.id.star_contact_panel);
        mContactPanel.setContactPanelEvent(this);
        mPresenter = new ContactPresenter(this);
    }


    @Override
    public IContactDataProvider onLoadFinish(IContactDataProvider provider) {
        return mContactPanel.setProxyDataProvider(provider);
    }

    @Override
    public void onAddContactClick(View view, String identify) {
        mPresenter.addContact(identify);
    }

    @Override
    public void onDelContactClick(View view, final String identify) {
        new AlertDialog.Builder(this).setTitle("提示").setMessage("确认删除该好友？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.delContact(identify);
                    }
                }).create().show();


    }

    @Override
    public void onContactItemClick(View view, int position, TIMUserProfile userProfile) {
        new AlertDialog.Builder(this).setTitle("").setMessage(userProfile.getIdentifier()).setNeutralButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }


    @Override
    public void onAddContactFail(Object res) {

    }

    @Override
    public void onDelContactFail(Object res) {

    }
}
