package com.tencent.qcloud.uikit.business.infos.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.business.infos.model.PersonalInfoBean;
import com.tencent.qcloud.uikit.business.infos.view.widget.GroupMembersAdapter;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;
import com.tencent.qcloud.uikit.common.utils.PopWindowUtil;

import java.util.List;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class GroupMemberPanel extends LinearLayout {
    private PageTitleBar mTitleBar;
    private GridView mMemberGrid;
    private EditText mMemberSearch;
    private GroupMembersAdapter mAdapter;
    private AlertDialog dialog;

    public GroupMemberPanel(Context context) {
        super(context);
        init();
    }

    public GroupMemberPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GroupMemberPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.group_member_panel, this);
        mTitleBar = findViewById(R.id.group_member_title_bar);
        mTitleBar.setRightClick(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                } else {
                    if (dialog == null) {
                        buildGroupMemberPopMenu();
                    } else
                        dialog.show();
                }
            }
        });
        mMemberGrid = findViewById(R.id.group_all_members);
        mMemberSearch = findViewById(R.id.group_member_search);
        mAdapter = new GroupMembersAdapter();
        mMemberGrid.setAdapter(mAdapter);
    }

    public PageTitleBar getTitleBar() {
        return mTitleBar;
    }

    public void setMembers(List<PersonalInfoBean> members) {
        mAdapter.setDataSource(members);
    }


    private void buildGroupMemberPopMenu() {
        dialog = PopWindowUtil.buildFullScreenDialog((Activity) getContext());
        View contentView = inflate(getContext(), R.layout.group_member_pop_menu, null);
        dialog.setContentView(contentView);

    }
}
