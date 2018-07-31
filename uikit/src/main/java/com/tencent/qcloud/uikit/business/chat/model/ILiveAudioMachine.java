package com.tencent.qcloud.uikit.business.chat.model;

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

/**
 * Created by valexhuang on 2018/5/29.
 */

public class ILiveAudioMachine {

    public static boolean startMic, startPlay, forceStop;
    static final int frequency = 16000; //音频采样频率
    static final int channelConfiguration = AudioFormat.CHANNEL_IN_MONO; //音频的录制单声道
    static final int audioEncoding = AudioFormat.ENCODING_PCM_16BIT; //音频编码率


    public static String CURRENT_RECORD_FILE = ILiveConstants.RECORD_DIR + "/demo_auto_";
    private AudioHandleCallback mCallback, mLastCallback;
    private AudioTrack audioTrack;
    private String playAudioPath;
    private String recordAudioPath;
    private static ILiveAudioMachine instance = new ILiveAudioMachine();

    private ILiveAudioMachine() {

    }

    public static ILiveAudioMachine getInstance() {
        return instance;
    }


    public void startRecord() {
        startMic = true;
        new RecordThread().start();
    }

    public void stopRecord() {
        startMic = false;
    }


    public void playRecord(String filePath, AudioHandleCallback callback) {
        this.mCallback = callback;
        this.playAudioPath = filePath;
        if (startPlay) {
            forceStop = true;
        } else {
            startPlay = true;
            new PlayThread(filePath).start();
        }

    }

    public void forceStopReplay(String filePath) {

    }

    public void stopPlayRecord() {
        startPlay = false;
    }

    public void setAudioHandleCallback(AudioHandleCallback callback) {
        mCallback = callback;
    }


    public String getRecordAudioPath() {
        return recordAudioPath;
    }

    public interface AudioHandleCallback {
        void recordComplete();

        void playComplete();
    }


    private class RecordThread extends Thread {
        @Override
        public void run() {
            //根据采样参数获取每一次音频采样大小
            try {
                int recBufSize = AudioRecord.getMinBufferSize(frequency, channelConfiguration, audioEncoding) * 2;
                AudioRecord audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, frequency, channelConfiguration, audioEncoding, recBufSize);
                byte[] recBuf = new byte[recBufSize];
                audioRecord.startRecording();
                recordAudioPath = CURRENT_RECORD_FILE + System.currentTimeMillis();
                File recorderFile = new File(recordAudioPath);
                if (!recorderFile.getParentFile().exists())
                    recorderFile.getParentFile().mkdirs();
                recorderFile.createNewFile();
                OutputStream os = new FileOutputStream(recorderFile);
                while (startMic) {
                    int readLen = audioRecord.read(recBuf, 0, recBufSize);
                    os.write(recBuf, 0, readLen);
                }
                audioRecord.stop();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private class PlayThread extends Thread {
        String audioPath;

        PlayThread(String filePath) {
            audioPath = filePath;
        }

        public void run() {
            try {
                // 获取最小缓冲区
                int bufSize = AudioTrack.getMinBufferSize(frequency, AudioFormat.CHANNEL_OUT_MONO, audioEncoding);
                // 实例化AudioTrack(设置缓冲区为最小缓冲区的2倍，至少要等于最小缓冲区)
                audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, frequency, AudioFormat.CHANNEL_OUT_MONO,
                        audioEncoding, bufSize, AudioTrack.MODE_STREAM);
                audioTrack.play();

                File recorderFile = new File(audioPath);
                // 获取音乐文件输入流
                InputStream is = new FileInputStream(recorderFile);
                byte[] buffer = new byte[bufSize * 2];
                int readLen;
                while ((readLen = is.read(buffer, 0, buffer.length)) != -1 && startPlay && !forceStop) {
                    audioTrack.write(buffer, 0, readLen);
                }
                startPlay = false;
                is.close();
                audioTrack.stop();
                if (forceStop) {
                    forceStop = false;
                    if (mLastCallback != null) {
                        mLastCallback.playComplete();
                    }
                    playRecord(playAudioPath, mCallback);
                } else {
                    if (mCallback != null) {
                        mCallback.playComplete();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
