package com.tencent.qcloud.uipojo.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.qcloud.uikit.business.infos.model.GroupInfoBean;
import com.tencent.qcloud.uikit.business.infos.model.PersonalInfoBean;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uipojo.R;
import com.tencent.qcloud.uipojo.common.Constants;

/**
 * Created by valxehuang on 2018/7/18.
 */

public class ChatActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        BaseFragment fragment = null;
        Bundle bundle = new Bundle();
        PersonalInfoBean personalInfo = (PersonalInfoBean) getIntent().getSerializableExtra(Constants.PERSONAL_INFO);
        if (personalInfo != null) {
            fragment = new PersonalChatFragment();
            bundle.putSerializable(Constants.PERSONAL_INFO, personalInfo);
        } else {
            GroupInfoBean groupInfo = (GroupInfoBean) getIntent().getSerializableExtra(Constants.GROUP_INFO);
            fragment = new GroupChatFragment();
            bundle.putSerializable(Constants.GROUP_INFO, groupInfo);
        }

        if (fragment != null) {
            fragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.empty_view, fragment).commitAllowingStateLoss();
        }
    }


    public static void startPersonalChat(Context context, PersonalInfoBean personalInfo) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(Constants.PERSONAL_INFO, personalInfo);
        context.startActivity(intent);
    }

    public static void startGroupChat(Context context, GroupInfoBean groupInfo) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(Constants.GROUP_INFO, groupInfo);
        context.startActivity(intent);
    }
}
