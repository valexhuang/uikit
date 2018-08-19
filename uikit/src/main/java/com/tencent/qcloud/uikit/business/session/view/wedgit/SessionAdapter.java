
package com.tencent.qcloud.uikit.business.session.view.wedgit;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.qcloud.uikit.ILiveUIKit;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.business.session.model.SessionInfo;
import com.tencent.qcloud.uikit.common.BackgroundTasks;
import com.tencent.qcloud.uikit.common.utils.UIUtils;
import com.tencent.qcloud.uikit.common.component.picture.imageEngine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.List;

public class SessionAdapter extends BaseAdapter {

    private List<SessionInfo> dataSource = new ArrayList<>();

    private int mRightWidth = UIUtils.getPxByDp(60);

    public SessionAdapter() {

    }

    public void setDataSource(final List datas) {
        dataSource = datas;
        BackgroundTasks.getInstance().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public SessionInfo getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(ILiveUIKit.getAppContext()).inflate(R.layout.session_adapter, parent, false);
            holder = new ViewHolder();
            holder.item_left = (RelativeLayout) convertView.findViewById(R.id.item_left);
            holder.item_right = (RelativeLayout) convertView.findViewById(R.id.item_right);

            holder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            holder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.tv_msg = (TextView) convertView.findViewById(R.id.tv_msg);
            holder.tv_time = (TextView) convertView.findViewById(R.id.tv_time);
            holder.tv_unRead = (TextView) convertView.findViewById(R.id.tv_unRead);
            holder.item_right_txt = (TextView) convertView.findViewById(R.id.item_right_txt);
            convertView.setTag(holder);
        } else {// 有直接获得ViewHolder
            holder = (ViewHolder) convertView.getTag();
        }


        /*LayoutParams lp2 = new LayoutParams(mRightWidth, LayoutParams.MATCH_PARENT);
        holder.item_right.setLayoutParams(lp2);*/

        SessionInfo msg = dataSource.get(position);
        holder.tv_title.setText(msg.getTitle());
        holder.tv_msg.setText(msg.getMsg());
        holder.tv_time.setText(msg.getTime());
        if (!TextUtils.isEmpty(msg.getIconUrl()))
            GlideEngine.loadImage(holder.iv_icon, msg.getIconUrl());
        if (msg.getUnRead() > 0)
            holder.tv_unRead.setText("" + msg.getUnRead());
        holder.item_right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRightItemClick(v, position);
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        RelativeLayout item_left;
        RelativeLayout item_right;

        TextView tv_title;
        TextView tv_msg;
        TextView tv_time;
        TextView tv_unRead;
        ImageView iv_icon;

        TextView item_right_txt;
    }

    /**
     * 单击事件监听器
     */
    private onRightItemClickListener mListener = null;

    public void setOnRightItemClickListener(onRightItemClickListener listener) {
        mListener = listener;
    }

    public interface onRightItemClickListener {
        void onRightItemClick(View v, int position);
    }
}
