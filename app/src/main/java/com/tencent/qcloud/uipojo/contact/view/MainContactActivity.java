package com.tencent.qcloud.uipojo.contact.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.tencent.qcloud.uikit.api.contact.IContactPanel;
import com.tencent.qcloud.uipojo.R;

/**
 * Created by Administrator on 2018/6/25.
 */

public class MainContactActivity extends Activity {

    private IContactPanel mContactPanel;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_main);
    }


    public void selectActivity(View view) {
        switch (view.getId()) {
            case R.id.normal_contact:
                Intent intent = new Intent(this, ContactFragment.class);
                startActivity(intent);
                break;
            case R.id.star_contact:
                intent = new Intent(this, StarContactActivity.class);
                startActivity(intent);
                break;
        }

    }

}
