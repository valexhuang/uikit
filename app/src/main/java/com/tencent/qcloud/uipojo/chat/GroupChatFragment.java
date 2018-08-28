package com.tencent.qcloud.uipojo.chat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.uikit.business.chat.model.BaseChatInfo;
import com.tencent.qcloud.uikit.business.chat.view.ChatPanel;
import com.tencent.qcloud.uikit.business.infos.model.GroupInfoBean;
import com.tencent.qcloud.uikit.business.infos.view.fragment.GroupInfoFragment;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;
import com.tencent.qcloud.uipojo.R;
import com.tencent.qcloud.uipojo.common.Constants;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class GroupChatFragment extends BaseFragment {
    private View mBaseView;
    private ChatPanel chatPanel;
    private PageTitleBar chatTitleBar;
    private BaseChatInfo mInfo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mBaseView = inflater.inflate(R.layout.chat_fragment_personal, container, false);
        Bundle datas = getArguments();
        mInfo = new BaseChatInfo();
        //mInfo.setGroupName(datas.getString(Constants.SESSION_TITLE, ""));
        initView();
        return mBaseView;
    }

    private void initView() {
        chatPanel = mBaseView.findViewById(R.id.chat_panel);
        chatPanel.initDefault();
        chatPanel.setBaseChatInfo(mInfo);
        chatTitleBar = chatPanel.getTitleBar();
        //chatTitleBar.setTitle(mInfo.getGroupName(), PageTitleBar.POSITION.CENTER);
        chatTitleBar.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        chatTitleBar.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forward(new GroupInfoFragment());
            }
        });
    }
}
