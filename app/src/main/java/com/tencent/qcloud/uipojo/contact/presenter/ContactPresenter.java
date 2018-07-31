package com.tencent.qcloud.uipojo.contact.presenter;

import com.tencent.qcloud.uikit.api.contact.IContactPanel;
import com.tencent.qcloud.uipojo.contact.model.PojoContactManager;
import com.tencent.qcloud.uipojo.contact.view.IContactView;

/**
 * Created by Administrator on 2018/7/3.
 */

public class ContactPresenter {
    private IContactView view;
    private PojoContactManager manager = PojoContactManager.getInstance();

    public ContactPresenter(IContactView view) {
        this.view = view;
        manager.setDataProvider(view.onLoadFinish(manager.getDataProvider()));
    }

    public void addContact(String identify) {
        manager.addFriend(identify, new PojoContactManager.ContactCallback() {
            @Override
            public void onSuccess(Object res) {
            }

            @Override
            public void onFail(Object res) {
                view.onAddContactFail(res);
            }
        });
    }

    public void delContact(String identify) {
        manager.delFriend(identify, new PojoContactManager.ContactCallback() {
            @Override
            public void onSuccess(Object res) {

            }

            @Override
            public void onFail(Object res) {
                view.onDelContactFail(res);
            }
        });
    }
}
