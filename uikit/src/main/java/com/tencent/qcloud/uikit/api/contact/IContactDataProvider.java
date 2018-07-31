package com.tencent.qcloud.uikit.api.contact;

import com.tencent.TIMUserProfile;

import java.util.List;

/**
 * Created by valexhuang on 2018/6/22.
 */

public interface IContactDataProvider {

    public List<TIMUserProfile> getDataSource();

    public void addContact(TIMUserProfile contact);

    public void deleteContact(String contactName);
}
