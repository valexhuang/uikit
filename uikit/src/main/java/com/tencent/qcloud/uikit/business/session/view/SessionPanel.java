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
import com.tencent.qcloud.uikit.api.session.SessionListEvent;
import com.tencent.qcloud.uikit.business.session.view.wedgit.SessionPopMenuAdapter;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;
import com.tencent.qcloud.uikit.common.utils.PopWindowUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valxehuang on 2018/7/17.
 */

public class SessionPanel extends RelativeLayout implements ISessionPanel {
    private ListView mPopMenuList, mItemPopMenuList;
    private SessionListView mSessionList;
    private SessionListEvent mSessionEvent;
    private SessionAdapter mAdapter;
    private SessionPopMenuAdapter mMenuAdapter, mItemMenuAdapter;
    private SessionPresenter mPresenter;
    private PageTitleBar mTitleBar;
    private AlertDialog mPopDialog;
    private View mPopView;
    private ISessionProvider mProvider;

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
        mSessionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mSessionEvent.onSessionClick(view, i, mAdapter.getItem(i));
            }
        });
        mSessionList.setItemLongClickListener(new SessionListView.ItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, int position, float x, float y) {
                showItemPopMenu(x, y);
            }
        });
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


    public void showItemPopMenu(float locationX, float locationY) {
        View itemPop = inflate(getContext(), R.layout.session_item_pop_menu, null);
        List<PopMenuAction> menuActions = new ArrayList<PopMenuAction>();
        PopMenuAction action = new PopMenuAction();
        action.setActionName("添加好友");
        menuActions.add(action);
        action = new PopMenuAction();
        action.setActionName("发起群聊");
        menuActions.add(action);
        action = new PopMenuAction();
        action.setActionName("发起群聊");
        menuActions.add(action);
        action = new PopMenuAction();
        action.setActionName("发起群聊");
        menuActions.add(action);
        action = new PopMenuAction();
        action.setActionName("发起群聊");
        menuActions.add(action);
        mItemPopMenuList = itemPop.findViewById(R.id.session_item_pop_list);
        mItemMenuAdapter = new SessionPopMenuAdapter();
        mItemPopMenuList.setAdapter(mItemMenuAdapter);
        mItemMenuAdapter.setDataSource(menuActions);
        PopWindowUtil.popupWindow(itemPop, this, locationX, locationY);
    }

    @Override
    public PageTitleBar pageTitleBar() {
        return mTitleBar;
    }


    @Override
    public void addTopPopActions(List<PopMenuAction> actions) {

    }

    @Override
    public void addItemPopActions(List<PopMenuAction> actions) {

    }

    @Override
    public void setItemLeftSlideAble(boolean enbale) {

    }


    @Override
    public void setSessionListEvent(SessionListEvent event) {
        this.mSessionEvent = event;
    }

    @Override
    public void setDataProvider(ISessionProvider provider) {
        mProvider = provider;
        mAdapter.setDataSource(provider.getDataSource());
    }

    @Override
    public ISessionProvider getDataProvider() {
        return mProvider;
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
    }
}
