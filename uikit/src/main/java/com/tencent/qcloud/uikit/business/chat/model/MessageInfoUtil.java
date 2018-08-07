package com.tencent.qcloud.uikit.business.chat.model;

import android.net.Uri;
import android.view.View;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatActionsFragment;
import com.tencent.qcloud.uikit.common.utils.FileUtil;
import com.tencent.qcloud.uikit.common.utils.ImageUtil;

/**
 * Created by valexhuang on 2018/8/6.
 */

public class MessageInfoUtil {


    public static MessageInfo buildMessage(String message) {
        MessageInfo info = new MessageInfo();
        info.setMsg(message);
        info.setSelf(true);
        return info;
    }


    public static MessageInfo buildImageMessage(Uri uri) {
        MessageInfo info = new MessageInfo();
        info.setSelf(true);
        info.setMsgType(MessageInfo.MSG_TYPE_IMAGE);
        info.setMsgBitmap(ImageUtil.getBitmapFormUri(uri));
        return info;
    }

    public static MessageInfo buildVideoMessage(Uri imgUri, Uri videoUri) {
        MessageInfo info = new MessageInfo();
        info.setSelf(true);
        info.setMsgType(MessageInfo.MSG_TYPE_VIDEO);
        info.setDataUri(videoUri);
        info.setDataPath(FileUtil.getPathFromUri(videoUri));
        info.setMsgBitmap(ImageUtil.getBitmapFormUri(imgUri));
        info.setBitmapPath(FileUtil.getPathFromUri(imgUri));
        return info;
    }

    public static MessageInfo buildAudioMessage() {
        MessageInfo info = new MessageInfo();
        info.setDataPath(ILiveAudioMachine.getInstance().getRecordAudioPath());
        info.setSelf(true);
        info.setMsgType(MessageInfo.MSG_TYPE_AUDIO);
        return info;
    }

    public static MessageInfo buildFileMessage(Uri fileUri) {
        MessageInfo info = new MessageInfo();
        info.setSelf(true);
        info.setDataPath(FileUtil.getPathFromUri(fileUri));
        info.setDataUri(fileUri);
        info.setMsgType(MessageInfo.MSG_TYPE_FILE);
        return info;
    }

}
