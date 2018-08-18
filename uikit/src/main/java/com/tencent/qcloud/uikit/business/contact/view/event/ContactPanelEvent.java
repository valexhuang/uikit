package com.tencent.qcloud.uikit.business.contact.view.event;

import android.view.View;
import android.widget.AdapterView;

import com.tencent.TIMUserProfile;

/**
 * Created by valexhuang on 2018/6/28.
 */

public interface ContactPanelEvent {
    void onContactItemClick(View view, int position, TIMUserProfile userProfile);

    void onContactItemLongClick(View view, int position, TIMUserProfile userProfile);

    void onContactItemSelected(View view, int position, TIMUserProfile userProfile);


}
