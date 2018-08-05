package com.tencent.qcloud.uikit.common.component.video;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.VideoView;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.common.ILiveConstants;
import com.tencent.qcloud.uikit.common.component.titlebar.PageTitleBar;
import com.tencent.qcloud.uikit.common.utils.ImageUtil;

public class VideoViewActivity extends Activity {
    private VideoView mVideoView;
    private PageTitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        String imagePath = getIntent().getStringExtra(ILiveConstants.CAMERA_IMAGE_PATH);
        Uri videoUri = getIntent().getParcelableExtra(ILiveConstants.CAMERA_VIDEO_PATH);
        Bitmap firstFrame = ImageUtil.getBitmapFormUri(imagePath);
        mVideoView = findViewById(R.id.video_play_view);
        mVideoView.setVideoURI(videoUri);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mVideoView.start();
            }
        });

        mTitleBar = findViewById(R.id.video_view_title_bar);
        mTitleBar.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mTitleBar.getRightGroup().setVisibility(View.GONE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        //jCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //jCameraView.onPause();
    }
}
