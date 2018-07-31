package com.tencent.qcloud.uipojo.contact.model;

import com.tencent.TIMUserProfile;
import com.tencent.imcore.FriendProfile;
import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;

/**
 * Created by Administrator on 2018/7/3.
 */

public class PojoContactManager {
    private static PojoContactManager instance = new PojoContactManager();
    private IContactDataProvider dataProvider = (IContactDataProvider) new PojoContactProvider();

    public static PojoContactManager getInstance() {
        return instance;
    }

    private PojoContactManager() {
    }

    public void addFriend(final String userName, final ContactCallback callback) {

        if (checkValid(userName)) {
            callback.onFail("好友已存在");
        } else {
            FriendProfile profile = new FriendProfile();
            profile.setSIdentifier(userName);
            TIMUserProfile userProfile = new TIMUserProfile(profile);
            dataProvider.addContact(userProfile);
            callback.onSuccess(userName);
        }

    }


    public IContactDataProvider getDataProvider() {
        return dataProvider;
    }

    public void setDataProvider(IContactDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }


    public void delFriend(final String userName, final ContactCallback callback) {
        if (checkValid(userName)) {
            dataProvider.deleteContact(userName);
            callback.onSuccess(userName);
        } else {
            callback.onFail("好友不存在");
        }
    }

    private boolean checkValid(String identifier) {
        for (int i = 0; i < dataProvider.getDataSource().size(); i++) {
            if (dataProvider.getDataSource().get(i).getIdentifier().equals(identifier))
                return true;
        }
        return false;
    }

    public interface ContactCallback {
        public void onSuccess(Object res);

        public void onFail(Object res);
    }
}
