package com.tencent.qcloud.uikit.business.contact.model;


import com.tencent.qcloud.uikit.common.ILiveUICallback;
/**
 * Created by valexhuang on 2018/6/21.
 */

public class ContactManager {
    private ContactProvider mProvider = new ContactProvider();
    private static final ContactManager instance = new ContactManager();

    private ContactManager() {
    }

    public static ContactManager getInstance() {
        return instance;
    }


    public void loadFriends(final ILiveUICallback callback) {

    }

    public ContactProvider getContactProvider() {
        return mProvider;
    }

    public void addFriend(final String identifier, final ILiveUICallback callback) {

    }

    public void delFriend(final String identifier, final ILiveUICallback callback) {

    }

}
