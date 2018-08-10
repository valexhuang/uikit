package com.tencent.qcloud.uikit.api.session;

import com.tencent.qcloud.uikit.business.session.model.SessionInfo;

import java.util.List;

/**
 * Created by valexhuang on 2018/7/17.
 */

public interface ISessionProvider {

    public List<SessionInfo> getDataSource();

    public boolean addSession(SessionInfo session);

    public boolean deleteSession(SessionInfo session);

    public boolean updateSession(SessionInfo session);

}
