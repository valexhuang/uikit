package com.tencent.qcloud.uipojo.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.uikit.business.chat.view.ChatPanel;
import com.tencent.qcloud.uikit.business.infos.model.PersonalInfoBean;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;
import com.tencent.qcloud.uipojo.R;
import com.tencent.qcloud.uipojo.common.Constants;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class PersonalChatFragment extends BaseFragment {
    private View mBaseView;
    private ChatPanel chatPanel;
    private PageTitleBar chatTitleBar;
    private PersonalInfoBean mInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.chat_fragment_personal, container, false);
        mInfo = (PersonalInfoBean) getArguments().getSerializable(Constants.PERSONAL_INFO);
        initView();
        return mBaseView;
    }

    private void initView() {
        chatPanel = mBaseView.findViewById(R.id.chat_panel);
        chatPanel.initDefault();
        chatPanel.setBaseInfo(mInfo);
        chatTitleBar = chatPanel.getTitleBar();
        chatTitleBar.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        chatTitleBar.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forward(new PersonalInfoFragment());
            }
        });
    }
}
