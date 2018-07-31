package com.tencent.qcloud.uipojo.contact.model;

import com.tencent.TIMUserProfile;
import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */

public class PojoContactProvider implements IContactDataProvider {
    List<TIMUserProfile> dataSouce = new ArrayList<>();

    @Override
    public List<TIMUserProfile> getDataSource() {
        return dataSouce;
    }

    @Override
    public void addContact(TIMUserProfile contact) {
        dataSouce.add(contact);
    }

    @Override
    public void deleteContact(String identifier) {
        for (int i = 0; i < dataSouce.size(); i++) {
            if (dataSouce.get(i).getIdentifier().equals(identifier)) {
                dataSouce.remove(i);
                return;
            }
        }
    }
}
