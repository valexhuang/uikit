package com.tencent.qcloud.uipojo.main;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tencent.qcloud.uikit.business.infos.view.fragment.SelfInfoFragment;
import com.tencent.qcloud.uipojo.R;
import com.tencent.qcloud.uipojo.contact.view.ContactFragment;
import com.tencent.qcloud.uipojo.session.SessionFragment;

/**
 * Created by valxehuang on 2018/7/17.
 */

public class MainActivity extends Activity {

    private SessionFragment sessionFragment;
    private ContactFragment contactFragment;
    private SelfInfoFragment selfInfoFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        if (sessionFragment == null)
            sessionFragment = new SessionFragment();
        getFragmentManager().beginTransaction().replace(R.id.empty_view, sessionFragment).commitAllowingStateLoss();
    }


    public void tabClick(View view) {
        Fragment current = null;
        switch (view.getId()) {
            case R.id.message:
                if (sessionFragment == null)
                    sessionFragment = new SessionFragment();
                current = sessionFragment;
                break;
            case R.id.contact:
                if (contactFragment == null)
                    contactFragment = new ContactFragment();
                current = contactFragment;
                break;
            case R.id.mine:
                if (selfInfoFragment == null)
                    selfInfoFragment = new SelfInfoFragment();
                current = selfInfoFragment;
                break;
        }
        if (current != null)
            getFragmentManager().beginTransaction().replace(R.id.empty_view, current).commitAllowingStateLoss();
    }
}
