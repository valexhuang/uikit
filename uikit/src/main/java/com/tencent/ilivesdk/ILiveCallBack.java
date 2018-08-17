package com.tencent.ilivesdk;

import com.tencent.qcloud.uikit.business.chat.model.ChatProvider;

/**
 * Created by valexhuang on 2018/8/17.
 */

public interface ILiveCallBack {

    public void onSuccess(Object data);

    public void onError(String module, int errCode, String errMsg);
}
