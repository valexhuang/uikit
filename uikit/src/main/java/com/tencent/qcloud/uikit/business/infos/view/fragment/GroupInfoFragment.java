package com.tencent.qcloud.uikit.business.infos.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.business.infos.view.GroupInfoPanel;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class GroupInfoFragment extends BaseFragment {
    private View mBaseView;
    private GroupInfoPanel infoPanel;
    private PageTitleBar chatTitleBar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.info_fragment_group, container, false);
        initView();
        return mBaseView;
    }

    private void initView() {
        infoPanel = mBaseView.findViewById(R.id.group_info_panel);
        chatTitleBar = infoPanel.getTitleBar();
        chatTitleBar.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backward();
            }
        });
        infoPanel.setMemberBarClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forward(new GroupMemberFragment());
            }
        });
    }
}
