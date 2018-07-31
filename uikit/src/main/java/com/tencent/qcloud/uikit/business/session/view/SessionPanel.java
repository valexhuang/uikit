package com.tencent.qcloud.uikit.business.session.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;
import com.tencent.qcloud.uikit.api.session.ISessionPanel;
import com.tencent.qcloud.uikit.api.session.ISessionProvider;
import com.tencent.qcloud.uikit.business.session.presenter.SessionPresenter;
import com.tencent.qcloud.uikit.business.session.view.wedgit.PopMenuAction;
import com.tencent.qcloud.uikit.business.session.view.wedgit.SessionAdapter;
import com.tencent.qcloud.uikit.business.session.view.wedgit.SessionPanelEvent;
import com.tencent.qcloud.uikit.business.session.view.wedgit.SessionPopMenuAdapter;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;
import com.tencent.qcloud.uikit.common.utils.PopWindowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valxehuang on 2018/7/17.
 */

public class SessionPanel extends RelativeLayout implements ISessionPanel {
    private ListView mSessionList, mPopMenuList;
    private SessionPanelEvent mSessionEvent;
    private SessionAdapter mAdapter;
    private SessionPopMenuAdapter mMenuAdapter;
    private SessionPresenter mPresenter;
    private PageTitleBar mTitleBar;
    private AlertDialog mPopDialog;
    private View mPopView;

    public SessionPanel(Context context) {
        super(context);
        init();
    }

    public SessionPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SessionPanel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        inflate(getContext(), R.layout.session_panel, this);
        mTitleBar = findViewById(R.id.session_title_panel);
        mSessionList = findViewById(R.id.session_list);
        mAdapter = new SessionAdapter();
        mSessionList.setAdapter(mAdapter);


    }

    private void showPopMenu() {
        if (mPopDialog == null) {
            mMenuAdapter = new SessionPopMenuAdapter();
            List<PopMenuAction> menuActions = new ArrayList<PopMenuAction>();
            PopMenuAction action = new PopMenuAction();
            action.setActionName("添加好友");
            menuActions.add(action);
            action = new PopMenuAction();
            action.setActionName("发起群聊");
            menuActions.add(action);
            mMenuAdapter.setDataSource(menuActions);
            mPopView = inflate(getContext(), R.layout.session_pop_menu, null);
            mPopMenuList = mPopView.findViewById(R.id.session_pop_list);
            mPopMenuList.setAdapter(mMenuAdapter);
            mPopDialog = PopWindowUtil.buildCustomDialog((Activity) getContext());
            //mPopDialog.setView(mPopView, 0, 0, 0, 0);
            mPopDialog.show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.width = 300;
            lp.height = LayoutParams.WRAP_CONTENT;
            lp.y = getResources().getDimensionPixelSize(R.dimen.page_title_height);
            mPopDialog.getWindow().setAttributes(lp);
            mPopDialog.getWindow().setGravity(Gravity.TOP | Gravity.RIGHT);
            mPopDialog.getWindow().setBackgroundDrawable(null);
            mPopDialog.setContentView(mPopView);
        } else {
            mPopDialog.show();
        }

    }

    @Override
    public PageTitleBar pageTitleBar() {
        return mTitleBar;
    }


    @Override
    public void addPopActions(List<PopMenuAction> actions) {

    }


    @Override
    public void setContactPanelEvent(SessionPanelEvent event) {
        this.mSessionEvent = event;
    }

    @Override
    public void setDataProvider(ISessionProvider provider) {
        mAdapter.setDataSource(provider.getDataSource());
    }

    @Override
    public IContactDataProvider setProxyDataProvider(ISessionProvider provider) {
        return null;
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void initDefault() {
        mTitleBar.setTitle("会话", PageTitleBar.POSITION.CENTER);
        mTitleBar.getLeftGroup().setVisibility(View.GONE);
        mTitleBar.setRightClick(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPopDialog != null && mPopDialog.isShowing()) {
                    mPopDialog.dismiss();
                } else {
                    showPopMenu();
                }
            }
        });
        mPresenter = new SessionPresenter(this);
        mPresenter.loadSessionData();
        mSessionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSessionEvent.onSessionClick(view, i);
            }
        });


    }
}
