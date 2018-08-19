package com.tencent.qcloud.uipojo.session;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.uikit.business.session.model.SessionInfo;
import com.tencent.qcloud.uikit.business.session.view.SessionPanel;
import com.tencent.qcloud.uikit.api.session.SessionListEvent;
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
        sessionPanel.setSessionListEvent(new SessionListEvent() {
            @Override
            public void onSessionClick(View v, int position,SessionInfo session) {
                ChatActivity.startChat(getActivity(),session.getSessionId(),session.getTitle(),session.isGroup());

            }

            @Override
            public void onSessionLongClick(View v, int position,SessionInfo session) {

            }

            @Override
            public void onSessionLeftSlide(View v, int position,SessionInfo session) {

            }


        });

        baseView.postDelayed(new Runnable() {
            @Override
            public void run() {
                SessionInfo sessionInfo = sessionPanel.getDataProvider().getDataSource().get(0);
                sessionInfo.setMsg("丫丫个呸的");
                sessionPanel.setDataProvider(sessionPanel.getDataProvider());
            }
        }, 10000);

    }
}
