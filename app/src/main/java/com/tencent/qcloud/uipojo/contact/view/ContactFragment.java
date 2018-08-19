package com.tencent.qcloud.uipojo.contact.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.tencent.qcloud.uikit.api.contact.IContactPanel;
import com.tencent.qcloud.uikit.business.contact.view.fragment.StartGroupChatFragment;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uipojo.R;

/**
 * Created by Administrator on 2018/6/25.
 */

public class ContactFragment extends BaseFragment {
    private View baseView;
    private IContactPanel mContactPanel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        baseView = inflater.inflate(R.layout.contact_fragment, container, false);
        mContactPanel = baseView.findViewById(R.id.contact_panel);
        mContactPanel.initDefault();
        return baseView;
    }
}
