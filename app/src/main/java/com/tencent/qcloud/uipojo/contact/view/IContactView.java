package com.tencent.qcloud.uipojo.contact.view;

import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;

/**
 * Created by Administrator on 2018/7/3.
 */

public interface IContactView {

    void onAddContactFail(Object res);

    void onDelContactFail(Object res);

    IContactDataProvider onLoadFinish(IContactDataProvider provider);
}
