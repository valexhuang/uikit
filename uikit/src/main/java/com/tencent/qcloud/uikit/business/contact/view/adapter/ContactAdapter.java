package com.tencent.qcloud.uikit.business.contact.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tencent.TIMUserProfile;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.ILiveUIKit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valexhuang on 2018/6/22.
 */

public class ContactAdapter extends BaseAdapter {
    private List<TIMUserProfile> contacts = new ArrayList<TIMUserProfile>();
    LayoutInflater inflater = LayoutInflater.from(ILiveUIKit.getAppContext());

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public TIMUserProfile getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contact_adapter, null);
            holder = new ViewHolder();
            holder.contactName = convertView.findViewById(R.id.contact_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.contactName.setText(getItem(position).getIdentifier());
        return convertView;
    }

    public void setDataSource(List<TIMUserProfile> datas) {
        this.contacts = datas;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView contactName;
    }

}
