package com.tencent.qcloud.uikit.business.contact.model;

import com.tencent.TIMAddFriendRequest;
import com.tencent.TIMDelFriendType;
import com.tencent.TIMFriendResult;
import com.tencent.TIMFriendshipManager;
import com.tencent.TIMUserProfile;
import com.tencent.TIMValueCallBack;
import com.tencent.imcore.FriendProfile;
import com.tencent.qcloud.uikit.common.ILiveUICallback;

import java.util.ArrayList;
import java.util.List;

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
        TIMFriendshipManager.getInstance().getFriendList(new TIMValueCallBack<List<TIMUserProfile>>() {
            @Override
            public void onError(int i, String s) {
                if (callback != null)
                    callback.onFail(s);
            }

            @Override
            public void onSuccess(List<TIMUserProfile> timUserProfiles) {
                List datas = new ArrayList<ContactInfoBean>();
                for (int i = 0; i < timUserProfiles.size(); i++) {
                    ContactInfoBean infoBean = new ContactInfoBean();
                    infoBean.setUserProfile(timUserProfiles.get(i));
                }
                mProvider.dataSource = datas;
                if (callback != null) {
                    callback.onSuccess(mProvider);
                }
            }
        });
    }

    public ContactProvider getContactProvider() {
        return mProvider;
    }

    public void addFriend(final String identifier, final ILiveUICallback callback) {
        List<TIMAddFriendRequest> adds = new ArrayList<TIMAddFriendRequest>();
        TIMAddFriendRequest request = new TIMAddFriendRequest();
        request.setIdentifier(identifier);
        adds.add(request);
        TIMFriendshipManager.getInstance().addFriend(adds, new TIMValueCallBack<List<TIMFriendResult>>() {
            @Override
            public void onError(int i, String s) {
                if (callback != null)
                    callback.onFail(s);
            }

            @Override
            public void onSuccess(List<TIMFriendResult> timFriendResults) {
                FriendProfile profile = new FriendProfile();
                profile.setSIdentifier(identifier);
                TIMUserProfile userProfile = new TIMUserProfile(profile);
                ContactInfoBean infoBean = new ContactInfoBean();
                infoBean.setUserProfile(userProfile);
                mProvider.addContact(infoBean);
                if (callback != null)
                    callback.onSuccess(timFriendResults);
            }
        });
    }

    public void delFriend(final String identifier, final ILiveUICallback callback) {
        List<TIMAddFriendRequest> adds = new ArrayList<TIMAddFriendRequest>();
        TIMAddFriendRequest request = new TIMAddFriendRequest();
        request.setIdentifier(identifier);
        adds.add(request);

        TIMFriendshipManager.getInstance().delFriend(TIMDelFriendType.TIM_FRIEND_DEL_BOTH, adds, new TIMValueCallBack<List<TIMFriendResult>>() {
            @Override
            public void onError(int i, String s) {
                if (callback != null)
                    callback.onFail(s);
            }

            @Override
            public void onSuccess(List<TIMFriendResult> timFriendResults) {
                mProvider.deleteContact(identifier);
                if (callback != null)
                    callback.onSuccess(timFriendResults);

            }
        });
    }

}
