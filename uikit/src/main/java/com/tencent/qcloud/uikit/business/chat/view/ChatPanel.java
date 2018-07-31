package com.tencent.qcloud.uikit.business.chat.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.api.chat.IChatPanel;
import com.tencent.qcloud.uikit.api.chat.IChatProvider;
import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfo;
import com.tencent.qcloud.uikit.business.chat.presenter.ChatPresenter;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatAdapter;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatBottomAction;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatPanelEvent;
import com.tencent.qcloud.uikit.business.infos.model.BaseInfoBean;
import com.tencent.qcloud.uikit.business.infos.model.PersonalInfoBean;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;

import java.util.List;

/**
 * Created by valxehuang on 2018/7/18.
 */

public class ChatPanel extends LinearLayout implements IChatPanel {

    RecyclerView mChatList;
    ChatAdapter mAdapter;
    ChatPresenter mPresenter;
    ChatBottomGroup mBottomGroup;
    View mRecordingGroup;
    ImageView mVolumeImg;
    AnimationDrawable mVolumeAnim;
    Activity mActivity;
    PageTitleBar mTitleBar;
    private BaseInfoBean mBaseInfo;
    private ChatPanelEvent mEvent;


    public ChatPanel(Context context) {
        super(context);
        init();
    }

    public ChatPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChatPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.chat_panel, this);
        mTitleBar = findViewById(R.id.chat_page_title);
        mTitleBar.getLeftIcon().setImageResource(R.drawable.title_back);
        mChatList = findViewById(R.id.chat_list);
        mChatList.setLayoutFrozen(false);
        mChatList.setItemViewCacheSize(0);
        mBottomGroup = findViewById(R.id.chat_bottom_box);
        mBottomGroup.setBottomAreaHandler(new ChatBottomGroup.ChatBottomHandler() {
            @Override
            public void bottomAreaShow() {
                scrollToEnd();
            }

            @Override
            public void bottomAreaHide() {

            }

            @Override
            public void sendMessage(MessageInfo messageInfo) {
                mEvent.sendMessage(messageInfo);
                scrollToEnd();
            }

            @Override
            public void startRecording() {
                mRecordingGroup.setVisibility(View.VISIBLE);
                mVolumeAnim.start();
            }

            @Override
            public void stopRecording() {
                mVolumeAnim.stop();
                mRecordingGroup.setVisibility(View.GONE);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mChatList.setLayoutManager(linearLayoutManager);
        mAdapter = new ChatAdapter();
        mChatList.setAdapter(mAdapter);

        mRecordingGroup = findViewById(R.id.voice_recording_view);
        mVolumeImg = findViewById(R.id.record_volume);
        mVolumeAnim = (AnimationDrawable) mVolumeImg.getDrawable();
    }

    public void setBottomActions(List<ChatBottomAction> actions) {
        mBottomGroup.setBottomActions(actions);
    }


    private void scrollToEnd() {
        mChatList.scrollToPosition(mAdapter.getItemCount() - 1);
    }


    @Override
    public void setChatPanelEvent(ChatPanelEvent event) {
        this.mEvent = event;
    }

    @Override
    public void setDataProvider(IChatProvider provider) {
        mAdapter.setDataSource(provider.getDataSource());
    }

    @Override
    public void setBaseInfo(BaseInfoBean info) {
        this.mBaseInfo = info;
        if (mBaseInfo instanceof PersonalInfoBean)
            mTitleBar.getRightIcon().setImageResource(R.drawable.personal_info);
        else
            mTitleBar.getRightIcon().setImageResource(R.drawable.group_info);
    }

    @Override
    public IContactDataProvider setProxyDataProvider(IChatProvider provider) {
        return null;
    }

    @Override
    public void refreshData() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initDefault() {
        mActivity = (Activity) getContext();
        mPresenter = new ChatPresenter(this);
        mPresenter.loadChatInfo(null);
        mBottomGroup.setActivity(mActivity);
        setChatPanelEvent(new ChatPanelEvent() {
            @Override
            public void sendMessage(MessageInfo messageInfo) {
                mPresenter.sendMessage(messageInfo);
            }

            @Override
            public void onMessageLongClick() {

            }
        });

    }

    @Override
    public PageTitleBar getTitleBar() {
        return mTitleBar;
    }
}
