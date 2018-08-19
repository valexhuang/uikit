package com.tencent.qcloud.uipojo.chat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.qcloud.uikit.business.infos.model.GroupInfoBean;
import com.tencent.qcloud.uikit.business.infos.model.PersonalInfoBean;
import com.tencent.qcloud.uikit.business.session.model.SessionInfo;
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
        Bundle bundle = getIntent().getExtras();
        boolean session = bundle.getBoolean(Constants.IS_GROUP, false);
        if (session) {
            fragment = new PersonalChatFragment();
        } else {
            fragment = new GroupChatFragment();
        }
        if (fragment != null) {
            fragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.empty_view, fragment).commitAllowingStateLoss();
        }
    }


    public static void startChat(Context context, String sessionId, String sessionTitle, boolean isGroup) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(Constants.SESSION_ID, sessionId);
        intent.putExtra(Constants.SESSION_TITLE, sessionTitle);
        intent.putExtra(Constants.IS_GROUP, isGroup);
        context.startActivity(intent);
    }
}
