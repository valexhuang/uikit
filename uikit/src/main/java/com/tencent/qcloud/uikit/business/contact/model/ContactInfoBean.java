package com.tencent.qcloud.uikit.business.contact.model;


import com.tencent.qcloud.uikit.business.contact.view.widget.IndexBar.bean.BaseIndexPinyinBean;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class ContactInfoBean extends BaseIndexPinyinBean {
    public static final String INDEX_STRING_TOP = "↑";
    private String city;//城市名字
    private boolean isTop;//是否是最上面的 不需要被转化成拼音的
    private boolean isSelected;

    public ContactInfoBean() {
    }

    public ContactInfoBean(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public ContactInfoBean setCity(String city) {
        this.city = city;
        return this;
    }

    public boolean isTop() {
        return isTop;
    }

    public ContactInfoBean setTop(boolean top) {
        isTop = top;
        return this;
    }

    @Override
    public String getTarget() {
        return city;
    }

    @Override
    public boolean isNeedToPinyin() {
        return !isTop;
    }


    @Override
    public boolean isShowSuspension() {
        return !isTop;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
