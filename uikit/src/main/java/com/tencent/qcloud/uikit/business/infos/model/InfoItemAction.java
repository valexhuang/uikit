package com.tencent.qcloud.uikit.business.infos.model;

import android.view.View;

/**
 * Created by valexhuang on 2018/8/7.
 */

public class InfoItemAction {
    private int leftIcon;
    private String label;
    private String action;
    private String extra;
    private boolean rightIconVisible;
    private InfoItemClick itemClick;

    public int getLeftIcon() {
        return leftIcon;
    }

    public void setLeftIcon(int resIcon) {
        this.leftIcon = resIcon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public InfoItemClick getItemClick() {
        return itemClick;
    }

    public boolean isRightIconVisible() {
        return rightIconVisible;
    }

    public void setRightIconVisible(boolean rightIconVisible) {
        this.rightIconVisible = rightIconVisible;
    }

    public void setItemClick(InfoItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public interface InfoItemClick {
        void onItemClick(View view, String action);
    }
}
