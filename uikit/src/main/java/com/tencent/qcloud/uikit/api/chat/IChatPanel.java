package com.tencent.qcloud.uikit.api.chat;

import com.tencent.qcloud.uikit.business.chat.model.BaseChatInfo;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatBottomAction;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatListEvent;
import com.tencent.qcloud.uikit.business.chat.view.widget.MessageInterceptor;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;

import java.util.List;


/**
 * Created by valxehuang on 2018/7/17.
 */

public interface IChatPanel {

    /**
     * 获取聊天界面的标题栏组件，可对标题栏做相关定制化修改
     *
     * @return
     */
    PageTitleBar getTitleBar();

    /**
     * 设置聊天界面消息的事件回调
     *
     * @param event
     */
    void setChatListEvent(ChatListEvent event);

    /**
     * 设置聊天界面的消息数据源（若独立使用此组件需自行绑定数据源，管理数据）
     *
     * @param provider
     */
    void setDataProvider(IChatProvider provider);


    /**
     * 设置聊天界面的消息数据源，并返回该数据源的代理对象，后续通过代理对象对数据进行操作，数据变更后界面可自动刷新
     *
     * @param provider
     */
    IChatProvider setProxyDataProvider(IChatProvider provider);

    /**
     * 手动刷新聊天界面
     */
    void refreshData();

    /**
     * 设置消息拦截器，可对数据源中的消息对象进行二次处理（用于添加用户的自定义业务数据字段）
     *
     * @param editor
     */
    void setMessageInterceptor(MessageInterceptor editor);

    /**
     * 设置聊天面板基本信息
     *
     * @param info
     */
    void setBaseChatInfo(BaseChatInfo info);


    /**
     * 设置底部更多按钮的操作项
     *
     * @param actions 操作项的具体配置信息
     * @param isAdd   是否为添加，若为添加则在聊天面包的默认按钮里往后添加，否则移除默认的，使用新设置的
     */
    void setBottomActions(List<ChatBottomAction> actions, boolean isAdd);

    /**
     * 使用初始化配置（即使用UIKIT sdk中聊天面板的默认配置）
     */
    void initDefault();
}
