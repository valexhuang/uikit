package com.tencent.qcloud.uikit.business.infos.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.api.infos.IGroupInfoPanel;
import com.tencent.qcloud.uikit.business.infos.model.GroupInfoBean;
import com.tencent.qcloud.uikit.business.infos.presenter.GroupInfoPresenter;
import com.tencent.qcloud.uikit.business.infos.view.widget.GroupInfoPanelEvent;
import com.tencent.qcloud.uikit.common.component.picture.imageEngine.impl.GlideEngine;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;
import com.tencent.qcloud.uikit.common.utils.PopWindowUtil;
import com.tencent.qcloud.uikit.common.widget.ILiveSwitch;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class GroupInfoPanel extends LinearLayout implements IGroupInfoPanel {
    private ImageView mGroupIcon;
    private TextView mGroupName, mGroupAccount, mGroupSignature, mGroupType, mJoinType, mMyNickName;
    private ILiveSwitch mTopSwitch;
    private Button mDissolveBtn, mModifyGroupName, mModifyGroupNotice, mCancelBtn;
    private PageTitleBar mTitleBar;
    private GroupInfoPresenter mPresenter;
    private GroupInfoPanelEvent mEvent;
    private AlertDialog mDialog;
    private GroupInfoBean mGroupInfo;

    public GroupInfoPanel(Context context) {
        super(context);
        init();
    }

    public GroupInfoPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GroupInfoPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.group_info_panel, this);
        mGroupIcon = findViewById(R.id.group_icon);
        mGroupName = findViewById(R.id.group_name);
        mGroupAccount = findViewById(R.id.group_account);
        mGroupSignature = findViewById(R.id.group_signature);
        mGroupType = findViewById(R.id.group_type);
        mJoinType = findViewById(R.id.join_group_type);
        mMyNickName = findViewById(R.id.in_group_nick_name);
        mTopSwitch = (ILiveSwitch) findViewById(R.id.chat_to_top_switch);
        mDissolveBtn = findViewById(R.id.group_dissolve_button);
        mTitleBar = findViewById(R.id.group_info_title_bar);
        mTitleBar.setRightClick(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDialog != null && mDialog.isShowing()) {
                    mDialog.dismiss();
                } else {
                    buildPopMenu();
                }
            }
        });

        mPresenter = new GroupInfoPresenter(this);
    }

    private void buildPopMenu() {
        if (mDialog == null) {
            mDialog = PopWindowUtil.buildFullScreenDialog((Activity) getContext());
            View moreActionView = inflate(getContext(), R.layout.group_more_action_panel, null);
            mModifyGroupName = moreActionView.findViewById(R.id.modify_group_name);
            mModifyGroupName.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopWindowUtil.buildEditorDialog((Activity) getContext(), "修改群名称", "", "取消", "确定", new PopWindowUtil.EnsureListener() {
                        @Override
                        public void sure(Object obj) {

                        }

                        @Override
                        public void cancel() {

                        }
                    });
                }
            });
            mModifyGroupNotice = moreActionView.findViewById(R.id.modify_group_notice);
            mModifyGroupNotice.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mEvent.onModifyGroupNotice(mGroupInfo);
                }
            });
            mCancelBtn = moreActionView.findViewById(R.id.cancel);
            mCancelBtn.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                }
            });
            mDialog.setContentView(moreActionView);
        } else {
            mDialog.show();
        }

    }

    @Override
    public PageTitleBar getTitleBar() {
        return mTitleBar;
    }

    @Override
    public void initGroupInfo(GroupInfoBean info) {
        this.mGroupInfo = info;
        GlideEngine.loadImage(mGroupIcon, info.getIconUrl());
    }

    @Override
    public void setGroupInfoPanelEvent(GroupInfoPanelEvent event) {
        mEvent = event;
    }

    @Override
    public void initDefault() {
        setGroupInfoPanelEvent(new GroupInfoPanelEvent() {
            @Override
            public void onBackClick() {
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                }
            }

            @Override
            public void onDissolve(GroupInfoBean info) {

            }

            @Override
            public void onModifyGroupName(GroupInfoBean info) {

            }

            @Override
            public void onModifyGroupNotice(GroupInfoBean info) {

            }


        });
    }
}
