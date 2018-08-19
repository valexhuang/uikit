package com.tencent.qcloud.uikit.business.chat.view.widget;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.qcloud.uikit.ILiveUIKit;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.business.chat.model.ILiveAudioMachine;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfo;
import com.tencent.qcloud.uikit.common.ILiveConstants;
import com.tencent.qcloud.uikit.common.component.face.FaceManager;
import com.tencent.qcloud.uikit.common.component.video.VideoViewActivity;
import com.tencent.qcloud.uikit.common.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valxehuang on 2018/7/18.
 */

public class ChatAdapter extends RecyclerView.Adapter {

    private List<MessageInfo> mDataSource = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ILiveUIKit.getAppContext());
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case MessageInfo.MSG_TYPE_TEXT:
                holder = new ChatTextHolder(inflater.inflate(R.layout.chat_adapter_text, parent, false));
                break;
            case MessageInfo.MSG_TYPE_IMAGE:
            case MessageInfo.MSG_TYPE_VIDEO:
                holder = new ChatImageHolder(inflater.inflate(R.layout.chat_adapter_image, parent, false));
                break;
            case MessageInfo.MSG_TYPE_AUDIO:
                holder = new ChatAudioHolder(inflater.inflate(R.layout.chat_adapter_audio, parent, false));
                break;
            case MessageInfo.MSG_TYPE_FILE:
                holder = new ChatAudioHolder(inflater.inflate(R.layout.chat_adapter_file, parent, false));
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        BaseChatHolder chatHolder = (BaseChatHolder) holder;
        final MessageInfo msg = getItem(position);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);//(RelativeLayout.LayoutParams) chatHolder.dataView.getLayoutParams();
        RelativeLayout.LayoutParams iconParams = new RelativeLayout.LayoutParams(UIUtils.getPxByDp(50), UIUtils.getPxByDp(50));//(RelativeLayout.LayoutParams) chatHolder.dataView.getLayoutParams();
        if (msg.isSelf()) {
            chatHolder.rootView.setGravity(Gravity.RIGHT);
            iconParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            chatHolder.userIcon.setLayoutParams(iconParams);
            params.addRule(RelativeLayout.LEFT_OF, R.id.iv_user_icon);
            chatHolder.contentGroup.setLayoutParams(params);
            chatHolder.contentGroup.setGravity(Gravity.RIGHT);
            chatHolder.dataView.setBackgroundResource(R.drawable.ilive_message_right_selector);

        } else {
            chatHolder.rootView.setGravity(Gravity.LEFT);
            iconParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            chatHolder.userIcon.setLayoutParams(iconParams);
            params.addRule(RelativeLayout.RIGHT_OF, R.id.iv_user_icon);
            chatHolder.contentGroup.setLayoutParams(params);
            chatHolder.contentGroup.setGravity(Gravity.LEFT);
            chatHolder.dataView.setBackgroundResource(R.drawable.ilive_message_left_selector);


        }
        switch (getItemViewType(position)) {
            case MessageInfo.MSG_TYPE_TEXT:
                ChatTextHolder msgHolder = (ChatTextHolder) chatHolder;
                msgHolder.msg.setVisibility(View.VISIBLE);
                //msgHolder.msg.setText(msg.getMsg());
                FaceManager.handlerEmojiText(msgHolder.msg, msg.getMsg());
                break;
            case MessageInfo.MSG_TYPE_VIDEO:
            case MessageInfo.MSG_TYPE_IMAGE:
                final ChatImageHolder imgHolder = (ChatImageHolder) chatHolder;
                if (getItemViewType(position) == MessageInfo.MSG_TYPE_VIDEO) {
                    imgHolder.imgData.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ILiveUIKit.getAppContext(), VideoViewActivity.class);
                            intent.putExtra(ILiveConstants.CAMERA_IMAGE_PATH, msg.getBitmapPath());
                            intent.putExtra(ILiveConstants.CAMERA_VIDEO_PATH, msg.getDataUri());
                            ILiveUIKit.getAppContext().startActivity(intent);
                        }
                    });
                } else {
                    imgHolder.imgData.setOnClickListener(null);
                }

                imgHolder.imgData.setVisibility(View.VISIBLE);
                imgHolder.imgData.setImageBitmap(msg.getMsgBitmap());
                break;
            case MessageInfo.MSG_TYPE_AUDIO:
                final ChatAudioHolder audioHolder = (ChatAudioHolder) chatHolder;
                RelativeLayout.LayoutParams dataParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);//(RelativeLayout.LayoutParams) chatHolder.dataView.getLayoutParams();
                RelativeLayout.LayoutParams extraParams = new RelativeLayout.LayoutParams(UIUtils.getPxByDp(50), UIUtils.getPxByDp(50));//(RelativeLayout.LayoutParams) chatHolder.dataView.getLayoutParams();
                if (msg.isSelf()) {
                    dataParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                    audioHolder.dataView.setLayoutParams(dataParams);
                    audioHolder.dataView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
                    extraParams.addRule(RelativeLayout.LEFT_OF, R.id.ll_msg_data_group);
                    audioHolder.extra.setLayoutParams(extraParams);
                } else {
                    dataParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
                    audioHolder.dataView.setLayoutParams(dataParams);
                    audioHolder.dataView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
                    extraParams.addRule(RelativeLayout.RIGHT_OF, R.id.ll_msg_data_group);
                    audioHolder.extra.setLayoutParams(extraParams);
                }
                audioHolder.imgPlay.setImageResource(R.drawable.voice_msg_playing_3);
                audioHolder.time.setText(msg.getAudioTime() + "''");
                audioHolder.dataView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        audioHolder.imgPlay.setImageResource(R.drawable.play_voice_message);
                        final AnimationDrawable animationDrawable = (AnimationDrawable) audioHolder.imgPlay.getDrawable();
                        animationDrawable.start();
                        ILiveAudioMachine.getInstance().playRecord(msg.getDataPath(), new ILiveAudioMachine.AudioHandleCallback() {
                            @Override
                            public void recordComplete() {

                            }

                            @Override
                            public void playComplete() {
                                if (animationDrawable != null)
                                    animationDrawable.stop();
                                audioHolder.imgPlay.setImageResource(R.drawable.voice_msg_playing_3);
                            }
                        });
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mDataSource.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getMsgType();
    }


    public void setDataSource(List<MessageInfo> mDataSource) {
        this.mDataSource = mDataSource;
    }

    public MessageInfo getItem(int position) {
        return mDataSource.get(position);
    }


    class BaseChatHolder extends RecyclerView.ViewHolder {

        protected ImageView userIcon;
        protected TextView userName;
        protected RelativeLayout rootView;
        protected LinearLayout contentGroup;
        protected LinearLayout dataView;


        public BaseChatHolder(View itemView) {
            super(itemView);
            rootView = (RelativeLayout) itemView.findViewById(R.id.chat_info_content);
            userName = itemView.findViewById(R.id.tv_user_name);
            userIcon = itemView.findViewById(R.id.iv_user_icon);
            contentGroup = itemView.findViewById(R.id.ll_content_group);
            dataView = itemView.findViewById(R.id.ll_msg_data_group);
        }
    }

    class ChatTextHolder extends BaseChatHolder {
        private TextView msg;

        public ChatTextHolder(View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.tv_user_msg);

        }
    }

    class ChatImageHolder extends BaseChatHolder {
        private ImageView imgData;

        public ChatImageHolder(View itemView) {
            super(itemView);
            imgData = itemView.findViewById(R.id.iv_user_image);
        }
    }

    class ChatAudioHolder extends BaseChatHolder {
        private ImageView imgPlay;
        private ImageView unread;
        private RelativeLayout extra;
        private TextView time;

        public ChatAudioHolder(View itemView) {
            super(itemView);
            imgPlay = itemView.findViewById(R.id.audio_play);
            unread = itemView.findViewById(R.id.unread_flag);
            extra = itemView.findViewById(R.id.rl_msg_extra);
            time = itemView.findViewById(R.id.audio_time);
        }
    }
}