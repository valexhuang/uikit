package com.tencent.qcloud.uikit.business.session.view.wedgit;

import android.view.View;

/**
 * Created by valxehuang on 2018/7/17.
 */

public interface SessionPanelEvent {

    void onSessionClick(View v, int position);

    void onSessionLongClick();

    void onSessionRightSlide();

    void onSessionLeftSlide();
}
