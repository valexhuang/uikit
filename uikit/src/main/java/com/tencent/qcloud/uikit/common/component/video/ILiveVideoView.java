package com.tencent.qcloud.uikit.common.component.video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by valxehuang on 2018/8/5.
 */

public class ILiveVideoView extends VideoView {
    public ILiveVideoView(Context context) {
        super(context);
    }

    public ILiveVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ILiveVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 其实就是在这里做了一些处理。
        int width = getDefaultSize(0, widthMeasureSpec);
        int height = getDefaultSize(0, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }
}
