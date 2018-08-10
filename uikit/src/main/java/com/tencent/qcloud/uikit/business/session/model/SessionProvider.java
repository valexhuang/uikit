package com.tencent.qcloud.uikit.business.session.model;

import com.tencent.qcloud.uikit.api.session.ISessionProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valexhuang on 2018/7/17.
 */

public class SessionProvider implements ISessionProvider {

    ArrayList<SessionInfo> dataSource = new ArrayList();

    @Override
    public List<SessionInfo> getDataSource() {
        return dataSource;
    }

    @Override
    public boolean addSession(SessionInfo session) {
        dataSource.add(session);
        return true;
    }

    @Override
    public boolean deleteSession(SessionInfo session) {
        for (int i = 0; i < dataSource.size(); i++) {
            if (dataSource.get(i).getSessionId().equals(session.getSessionId())) {
                dataSource.remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateSession(SessionInfo session) {
        return false;
    }
}
