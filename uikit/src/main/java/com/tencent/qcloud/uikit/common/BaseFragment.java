package com.tencent.qcloud.uikit.common;

import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by valexhuang on 2018/6/14.
 */

public class BaseFragment extends Fragment {

    public void forward(Fragment fragment) {
        forward(getId(), fragment, null);
    }


    public void forward(int viewId, Fragment fragment, String name) {
        FragmentTransaction trans = getFragmentManager().beginTransaction();
        trans.replace(viewId, fragment);
        trans.addToBackStack(name);
        trans.commitAllowingStateLoss();
    }

    public void backward() {
        getFragmentManager().popBackStack();
    }
}
