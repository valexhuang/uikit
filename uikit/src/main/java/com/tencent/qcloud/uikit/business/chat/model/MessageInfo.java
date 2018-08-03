package com.tencent.qcloud.uikit.business.chat.model;

import android.graphics.Bitmap;
import android.net.Uri;

/**
 * Created by valxehuang on 2018/7/18.
 */

public class MessageInfo {
    public static final int MSG_TYPE_TEXT = 0;
    public static final int MSG_TYPE_IMAGE = 1;
    public static final int MSG_TYPE_AUDIO = 2;
    public static final int MSG_TYPE_VIDEO = 3;
    public static final int MSG_TYPE_FILE = 4;
    public static final int MSG_TYPE_LOCATION = 5;

    private String msgId;
    private String fromUser;
    private String toUser;
    private String msg;
    private int msgType;
    private String dataPath;
    private Uri dataUri;
    private String fromUserIcon;
    private byte[] msgByteData;
    private String bitmapPath;
    private Bitmap msgBitmap;
    private boolean self;
    private boolean read;
    private int audioTime;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getDataPath() {
        return dataPath;
    }

    public void setDataPath(String dataPath) {
        this.dataPath = dataPath;
    }

    public Uri getDataUri() {
        return dataUri;
    }

    public void setDataUri(Uri dataUri) {
        this.dataUri = dataUri;
    }

    public String getFromUserIcon() {
        return fromUserIcon;
    }

    public void setFromUserIcon(String fromUserIcon) {
        this.fromUserIcon = fromUserIcon;
    }

    public byte[] getMsgByteData() {
        return msgByteData;
    }

    public void setMsgByteData(byte[] msgByteData) {
        this.msgByteData = msgByteData;
    }

    public boolean isSelf() {
        return self;
    }

    public void setSelf(boolean self) {
        this.self = self;
    }

    public String getBitmapPath() {
        return bitmapPath;
    }

    public void setBitmapPath(String bitmapPath) {
        this.bitmapPath = bitmapPath;
    }

    public Bitmap getMsgBitmap() {
        return msgBitmap;
    }

    public void setMsgBitmap(Bitmap msgBitmap) {
        this.msgBitmap = msgBitmap;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getAudioTime() {
        return audioTime;
    }

    public void setAudioTime(int audioTime) {
        this.audioTime = audioTime;
    }
}
