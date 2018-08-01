package com.tencent.qcloud.uikit.business.infos.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.business.infos.view.GroupMemberPanel;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;

/**
 * Created by valexhuang on 2018/8/1.
 */

public class GroupMemberFragment extends BaseFragment {
    private GroupMemberPanel mMemberPanel;
    private View mBaseView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBaseView = inflater.inflate(R.layout.group_fragment_members, container, false);
        mMemberPanel = mBaseView.findViewById(R.id.group_member_grid_panel);
        init();
        return mBaseView;
    }

    private void init() {
        mMemberPanel.getTitleBar().setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backward();
            }
        });
        mMemberPanel.getTitleBar().setTitle("群成员(22)", PageTitleBar.POSITION.CENTER);
    }
}
