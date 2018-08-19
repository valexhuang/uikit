package com.tencent.qcloud.uipojo.contact.model;

import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;
import com.tencent.qcloud.uikit.business.contact.model.ContactInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */

public class PojoContactProvider implements IContactDataProvider {
    List<ContactInfoBean> dataSouce = new ArrayList<>();

    @Override
    public List<ContactInfoBean> getDataSource() {
        return dataSouce;
    }

    @Override
    public void addContact(ContactInfoBean contact) {
        dataSouce.add(contact);
    }

    @Override
    public void deleteContact(String identifier) {

    }

    @Override
    public void updateContact(ContactInfoBean contact) {

    }
}
