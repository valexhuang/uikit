package com.tencent.qcloud.uikit.api.session;

import android.view.View;

/**
 * Created by valxehuang on 2018/7/17.
 */

public interface SessionListEvent {

    void onSessionClick(View v, int position);

    void onSessionLongClick(View v, int position);

    void onSessionLeftSlide(View v, int position);
}
