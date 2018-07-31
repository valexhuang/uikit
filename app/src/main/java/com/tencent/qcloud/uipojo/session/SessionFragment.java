package com.tencent.qcloud.uipojo.session;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.uikit.business.infos.model.GroupInfoBean;
import com.tencent.qcloud.uikit.business.infos.model.PersonalInfoBean;
import com.tencent.qcloud.uikit.business.session.view.SessionPanel;
import com.tencent.qcloud.uikit.business.session.view.wedgit.SessionPanelEvent;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uipojo.R;
import com.tencent.qcloud.uipojo.chat.ChatActivity;

/**
 * Created by valxehuang on 2018/7/17.
 */

public class SessionFragment extends BaseFragment {

    private View baseView;
    private SessionPanel sessionPanel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.fragment_session, container, false);
        initView();
        return baseView;
    }

    private void initView() {
        sessionPanel = baseView.findViewById(R.id.session_panel);
        sessionPanel.initDefault();
        sessionPanel.setContactPanelEvent(new SessionPanelEvent() {
            @Override
            public void onSessionClick(View v, int position) {
                if (position % 2 == 0) {
                    ChatActivity.startPersonalChat(getActivity(), new PersonalInfoBean());
                } else {
                    ChatActivity.startGroupChat(getActivity(), new GroupInfoBean());
                }
            }

            @Override
            public void onSessionLongClick() {

            }

            @Override
            public void onSessionRightSlide() {

            }

            @Override
            public void onSessionLeftSlide() {

            }
        });
    }
}
