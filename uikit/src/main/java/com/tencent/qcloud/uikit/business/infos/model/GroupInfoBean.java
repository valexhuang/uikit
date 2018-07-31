package com.tencent.qcloud.uikit.business.infos.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class GroupInfoBean extends BaseInfoBean implements Serializable {
    private String groupName;
    private String iconUrl;
    private String account;
    private String notice;
    private List<PersonalInfoBean> members;
    private int groupType;
    private int joinType;
    private String inGroupNickName;
    private boolean isTopChat;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String signature) {
        this.notice = signature;
    }

    public List<PersonalInfoBean> getMembers() {
        return members;
    }

    public void setMembers(List<PersonalInfoBean> members) {
        this.members = members;
    }

    public int getGroupType() {
        return groupType;
    }

    public void setGroupType(int groupType) {
        this.groupType = groupType;
    }

    public int getJoinType() {
        return joinType;
    }

    public void setJoinType(int joinType) {
        this.joinType = joinType;
    }

    public String getInGroupNickName() {
        return inGroupNickName;
    }

    public void setInGroupNickName(String inGroupNickName) {
        this.inGroupNickName = inGroupNickName;
    }

    public boolean isTopChat() {
        return isTopChat;
    }

    public void setTopChat(boolean topChat) {
        isTopChat = topChat;
    }
}
