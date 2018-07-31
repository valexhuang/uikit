package com.tencent.qcloud.uikit.common;

import android.widget.BaseAdapter;

import com.tencent.qcloud.uikit.api.contact.IContactDataProvider;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by valexhuang on 2018/7/3.
 */

public class ProxyFactory {
    public static IContactDataProvider createContactProviderProxy(IContactDataProvider provider, BaseAdapter adapter) {
        ProxySubject proxy = new ProxySubject(provider, adapter);
        IContactDataProvider sub = (IContactDataProvider) Proxy.newProxyInstance(provider.getClass().getClassLoader(),
                provider.getClass().getInterfaces(), proxy);
        return sub;
    }


    public static class ProxySubject implements InvocationHandler {
        protected Object subject;
        private BaseAdapter adapter;

        public ProxySubject(Object subject, BaseAdapter adapter) {
            this.subject = subject;
            this.adapter = adapter;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object res = method.invoke(subject, args);
            if (method.getName().equals("addContact") || method.getName().equals("deleteContact")) {
                adapter.notifyDataSetChanged();
            }
            return res;
        }
    }
}
