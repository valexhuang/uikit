package com.tencent.qcloud.uikit.business.infos.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.business.infos.view.PersonalInfoPanel;
import com.tencent.qcloud.uikit.business.infos.view.SelfInfoPanel;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class SelfInfoFragment extends BaseFragment {
    private View mBaseView;
    private SelfInfoPanel infoPanel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mBaseView = inflater.inflate(R.layout.info_fragment_self, container, false);
        initView();
        return mBaseView;
    }

    private void initView() {
        infoPanel = mBaseView.findViewById(R.id.personal_info_panel);
    }
}
