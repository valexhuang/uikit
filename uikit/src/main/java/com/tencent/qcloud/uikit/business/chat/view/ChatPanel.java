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

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.api.chat.IChatPanel;
import com.tencent.qcloud.uikit.api.chat.IChatProvider;
import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;
import com.tencent.qcloud.uikit.business.chat.model.BaseChatInfo;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfo;
import com.tencent.qcloud.uikit.business.chat.presenter.ChatPresenter;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatAdapter;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatBottomAction;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatListEvent;
import com.tencent.qcloud.uikit.business.chat.view.widget.MessageInterceptor;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;
import com.tencent.qcloud.uikit.common.utils.UIUtils;

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
    View mCoverView;
    ImageView mVolumeImg;
    AnimationDrawable mVolumeAnim;
    Activity mActivity;
    PageTitleBar mTitleBar;
    private BaseChatInfo mBaseInfo;
    private ChatListEvent mEvent;


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

        mChatList = findViewById(R.id.chat_list);
        mChatList.setLayoutFrozen(false);
        mChatList.setItemViewCacheSize(0);

        mCoverView = findViewById(R.id.cover_view);
        mCoverView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomGroup.hideSoftInput();
            }
        });

        mBottomGroup = findViewById(R.id.chat_bottom_box);
        mBottomGroup.setBottomAreaHandler(new ChatBottomGroup.ChatBottomHandler() {
            @Override
            public void bottomAreaShow() {
                scrollToEnd();
                mCoverView.setVisibility(View.VISIBLE);
            }

            @Override
            public void bottomAreaHide() {
                mCoverView.setVisibility(View.GONE);
            }

            @Override
            public void sendMessage(MessageInfo messageInfo) {
                mPresenter.sendMessage(messageInfo);
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
        mPresenter = new ChatPresenter(this);
        mPresenter.loadChatInfo("");
    }

    public void setBottomActions(List<ChatBottomAction> actions) {
        mBottomGroup.setBottomActions(actions);
    }


    private void scrollToEnd() {
        mChatList.scrollToPosition(mAdapter.getItemCount() - 1);
    }


    @Override
    public void setChatListEvent(ChatListEvent event) {
        this.mEvent = event;
        mAdapter.setChatListEvent(event);
    }

    @Override
    public void setDataProvider(IChatProvider provider) {
        mAdapter.setDataSource(provider.getDataSource());
    }

    @Override
    public void setMessageInterceptor(MessageInterceptor interceptor) {
        mAdapter.setEditor(interceptor);
    }

    @Override
    public void setBaseChatInfo(BaseChatInfo info) {
        this.mBaseInfo = info;
        mTitleBar.setTitle(info.getOppositeName(), PageTitleBar.POSITION.LEFT);
        mTitleBar.getLeftGroup().setVisibility(View.VISIBLE);
        // mTitleBar.getRightIcon().setImageResource(R.drawable.group_icon);
    }

    @Override
    public void setBottomActions(List<ChatBottomAction> actions, boolean isAdd) {

    }

    @Override
    public IChatProvider setProxyDataProvider(IChatProvider provider) {
        return null;
    }

    @Override
    public void refreshData() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initDefault() {
        mActivity = (Activity) getContext();
        mPresenter.loadChatInfo(null);
        setChatListEvent(new ChatListEvent() {

            @Override
            public void onMessageClick(View view, int position, MessageInfo messageInfo) {
                UIUtils.toastLongMessage("消息点击");
            }

            @Override
            public void onMessageLongClick(View view, int position, MessageInfo messageInfo) {
                UIUtils.toastLongMessage("消息长按");
            }

            @Override
            public void onUserIconClick(View view, int position, MessageInfo messageInfo) {
                UIUtils.toastLongMessage("头像点击");
            }
        });

        mTitleBar.getLeftIcon().setImageResource(R.drawable.title_bar_back);
        mTitleBar.setLeftClick(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mActivity != null)
                    mActivity.finish();
            }
        });
        mTitleBar.setRightClick(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.toastLongMessage("标题栏右边点击");
            }
        });
    }

    @Override
    public PageTitleBar getTitleBar() {
        return mTitleBar;
    }
}
