package com.tencent.qcloud.uikit.api.session;

import android.view.View;

import com.tencent.qcloud.uikit.business.session.model.SessionInfo;

/**
 * Created by valxehuang on 2018/7/17.
 */

public interface SessionListEvent {

    void onSessionClick(View v, int position, SessionInfo session);

    void onSessionLongClick(View v, int position, SessionInfo session);

    void onSessionLeftSlide(View v, int position, SessionInfo session);
}
