package com.tencent.qcloud.uikit.common.component.video;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.common.ILiveConstants;
import com.tencent.qcloud.uikit.common.utils.ImageUtil;

public class VideoViewActivity extends AppCompatActivity {
    private JCameraView jCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_video_view);
        String imagePath = getIntent().getStringExtra(ILiveConstants.CAMERA_IMAGE_PATH);
        String videoPath = getIntent().getStringExtra(ILiveConstants.CAMERA_VIDEO_PATH);
        Bitmap firstFrame = ImageUtil.getBitmapFormUri(imagePath);
        jCameraView = findViewById(R.id.video_jcameraview);
        jCameraView.playVideo(firstFrame, videoPath);
    }


    @Override
    protected void onResume() {
        super.onResume();
        jCameraView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        jCameraView.onPause();
    }
}
