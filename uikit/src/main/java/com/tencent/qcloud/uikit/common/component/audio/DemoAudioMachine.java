package com.tencent.qcloud.uikit.common.component.audio;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;

import com.tencent.qcloud.uikit.common.ILiveConstants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by valexhuang on 2018/5/29.
 */

public class DemoAudioMachine {

    public static boolean startMic, startPlay, recordComplete, sendFinish;
    static final int frequency = 16000; //音频采样频率
    static final int channelConfiguration = AudioFormat.CHANNEL_IN_MONO; //音频的录制单声道
    static final int audioEncoding = AudioFormat.ENCODING_PCM_16BIT; //音频编码率


    public static String CURRENT_RECORD_FILE = ILiveConstants.APP_DIR + "/demo_auto.pcm";
    private ArrayList dataList = new ArrayList();


    AudioRecord audioRecord;
    int recBufSize = AudioRecord.getMinBufferSize(frequency, channelConfiguration, audioEncoding) * 2;


    private AudioHandleCallback mCallback;


    private static DemoAudioMachine instance = new DemoAudioMachine();

    private DemoAudioMachine() {

    }

    public static DemoAudioMachine getInstance() {
        return instance;
    }


    private class RecordThread extends Thread {
        @Override
        public void run() {

            try {
                //根据采样参数获取每一次音频采样大小
                audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, frequency, channelConfiguration, audioEncoding, recBufSize);
                audioRecord.startRecording();
                sendFinish = false;
                byte[] recBuf = new byte[recBufSize];
                File recorderFile = new File(CURRENT_RECORD_FILE);
                if (!recorderFile.getParentFile().exists())
                    recorderFile.getParentFile().mkdir();
                recorderFile.createNewFile();
                OutputStream os = new FileOutputStream(recorderFile);
                while (startMic) {
                    int readLen = audioRecord.read(recBuf, 0, recBufSize);
                    if (readLen != -1) {
                        byte readBts[] = new byte[readLen];
                        System.arraycopy(recBuf, 0, readBts, 0, readLen);
                        os.write(recBuf, 0, readLen);
                        synchronized (dataList) {
                            dataList.add(readBts);
                        }
                    }
                }
                audioRecord.stop();
                if (mCallback != null) {
                    mCallback.recordComplete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }


    private class PlayThread extends Thread {
        public void run() {
            try {
                // 获取最小缓冲区
                int bufSize = AudioTrack.getMinBufferSize(frequency, AudioFormat.CHANNEL_OUT_MONO, audioEncoding);
                // 实例化AudioTrack(设置缓冲区为最小缓冲区的2倍，至少要等于最小缓冲区)
                AudioTrack audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, frequency, AudioFormat.CHANNEL_OUT_MONO,
                        audioEncoding, bufSize, AudioTrack.MODE_STREAM);
                audioTrack.play();

                File recorderFile = new File(CURRENT_RECORD_FILE);
                // 获取音乐文件输入流
                InputStream is = new FileInputStream(recorderFile);
                byte[] buffer = new byte[bufSize * 2];
                int readLen;
                while ((readLen = is.read(buffer, 0, buffer.length)) != -1 && startPlay) {
                    audioTrack.write(buffer, 0, readLen);
                }
                if (mCallback != null) {
                    mCallback.playComplete();
                }
                startPlay = false;
                is.close();
                audioTrack.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void startRecord() {
        startMic = true;
        recordComplete = false;
        new RecordThread().start();
    }

    public void stopRecord() {
        startMic = false;
    }


    public void playRecord() {
        if (startPlay)
            return;
        startPlay = true;
        new PlayThread().start();
    }


    public void stopPlayRecord() {
        startPlay = false;
    }

    public interface AudioHandleCallback {
        void recordComplete();

        void playComplete();
    }

}
