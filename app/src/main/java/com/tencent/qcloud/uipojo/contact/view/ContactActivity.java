package com.tencent.qcloud.uipojo.contact.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.tencent.qcloud.uikit.api.contact.IContactPanel;
import com.tencent.qcloud.uipojo.R;

/**
 * Created by Administrator on 2018/6/25.
 */

public class ContactActivity extends Activity {

    private IContactPanel mContactPanel;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        mContactPanel = findViewById(R.id.contact_panel);
        mContactPanel.initDefault();
    }


}
