package com.tencent.qcloud.uikit.business.infos.view.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.qcloud.uikit.ILiveUIKit;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.business.infos.model.PersonalInfoBean;
import com.tencent.qcloud.uikit.common.component.picture.imageEngine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class GroupMembersAdapter extends BaseAdapter {
    private List<PersonalInfoBean> mGroupMembers = new ArrayList<>();

    @Override
    public int getCount() {
        return mGroupMembers.size();
    }

    @Override
    public PersonalInfoBean getItem(int i) {
        return mGroupMembers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        MyViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(ILiveUIKit.getAppContext()).inflate(R.layout.group_member_adpater, viewGroup, false);
            holder = new MyViewHolder();
            holder.memberIcon = view.findViewById(R.id.group_member_icon);
            holder.memberName = view.findViewById(R.id.group_member_name);
            view.setTag(holder);
        } else {
            holder = (MyViewHolder) view.getTag();
        }
        PersonalInfoBean info = getItem(i);
        GlideEngine.loadImage(holder.memberIcon, info.getIconUrl());
        holder.memberName.setText(info.getNickName());
        return view;
    }

    private class MyViewHolder {
        private ImageView memberIcon;
        private TextView memberName;
    }
}
