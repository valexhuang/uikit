package com.tencent.qcloud.uikit.business.chat.view.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.common.BaseFragment;

import java.util.List;

/**
 * Created by valxehuang on 2018/7/23.
 */

public class ChatActionsFragment extends BaseFragment {

    private View baseView;
    private BottomBoxWave panelWave = new BottomBoxWave();
    private List<ChatBottomAction> actions;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.chat_bottom_actions_layout, container, false);
        panelWave.init(baseView, actions);
        return baseView;
    }

    public void setActions(List<ChatBottomAction> actions) {
        this.actions = actions;
    }
}
