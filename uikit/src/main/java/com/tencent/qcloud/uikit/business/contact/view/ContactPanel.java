package com.tencent.qcloud.uikit.business.contact.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.tencent.TIMUserProfile;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;
import com.tencent.qcloud.uikit.api.contact.IContactPanel;
import com.tencent.qcloud.uikit.business.contact.presenter.ContactPresenter;
import com.tencent.qcloud.uikit.business.contact.view.adapter.ContactAdapter;
import com.tencent.qcloud.uikit.business.contact.view.event.ContactPanelEvent;
import com.tencent.qcloud.uikit.common.ProxyFactory;
import com.tencent.qcloud.uikit.common.utils.UIUtils;


/**
 * Created by valexhuang on 2018/6/22.
 */

public class ContactPanel extends LinearLayout implements IContactPanel, View.OnClickListener {

    ContactPresenter mPresenter;

    private ListView mContactList;
    private Button mAddBtn;
    private EditText mSearchView;
    private ContactAdapter mAdapter;
    private ContactPanelEvent mEvent = new ContactPanelEvent() {
        @Override
        public void onAddContactClick(View view, String identify) {
            mPresenter.addContact(identify);
        }

        @Override
        public void onDelContactClick(View view, String identify) {
            mPresenter.delContact(identify);
        }

        @Override
        public void onItemClick(View view, int position, TIMUserProfile userProfile) {
            UIUtils.toastLongMessage(mAdapter.getItem(position).getIdentifier());
        }
    };

    public ContactPanel(Context context) {
        super(context);
        init();
    }

    public ContactPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.contact_panel, this);
        mContactList = findViewById(R.id.contact_list);
        mContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mEvent != null)
                    mEvent.onItemClick(view, position, mAdapter.getItem(position));
            }
        });
        mContactList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                if (mEvent != null)
                    mEvent.onDelContactClick(view, mAdapter.getItem(position).getIdentifier());

                return true;
            }
        });
        mAdapter = new ContactAdapter();
        mContactList.setAdapter(mAdapter);
        mSearchView = findViewById(R.id.contact_search_view);
        mAddBtn = findViewById(R.id.contact_add_btn);
        mAddBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.contact_add_btn) {
            if (mEvent != null)
                mEvent.onAddContactClick(v, mSearchView.getText().toString());
        }
    }

    public void setBackground(int Color) {
        setBackgroundColor(Color);
    }


    @Override
    public void setContactPanelEvent(ContactPanelEvent event) {
        this.mEvent = event;
    }

    @Override
    public void setDataProvider(IContactDataProvider provider) {
        mAdapter.setDataSource(provider.getDataSource());
    }

    @Override
    public IContactDataProvider setProxyDataProvider(IContactDataProvider provider) {
        IContactDataProvider proxyProvider = ProxyFactory.createContactProviderProxy(provider, mAdapter);
        mAdapter.setDataSource(proxyProvider.getDataSource());
        return proxyProvider;
    }

    public void refreshData() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initDefault() {
        mPresenter = new ContactPresenter(this);
    }

}
