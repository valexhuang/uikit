package com.tencent.qcloud.uikit.business.contact.model;

import com.tencent.TIMUserProfile;
import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valexhuang on 2018/7/3.
 */

public class ContactProvider implements IContactDataProvider {
    public List<TIMUserProfile> dataSource = new ArrayList<>();

    @Override
    public List<TIMUserProfile> getDataSource() {
        return dataSource;
    }

    @Override
    public void addContact(TIMUserProfile contact) {
        dataSource.add(contact);
    }

    @Override
    public void deleteContact(String identifier) {
        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i).getIdentifier().equals(identifier)) {
                dataSource.remove(i);
            }
            break;
        }
    }
}
