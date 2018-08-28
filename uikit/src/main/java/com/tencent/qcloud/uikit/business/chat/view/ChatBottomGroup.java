package com.tencent.qcloud.uikit.business.chat.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.ilivesdk.ILiveCallBack;
import com.tencent.qcloud.uikit.R;
import com.tencent.qcloud.uikit.business.chat.model.ILiveAudioMachine;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfo;
import com.tencent.qcloud.uikit.business.chat.model.MessageInfoUtil;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatActionsFragment;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatBottomAction;
import com.tencent.qcloud.uikit.common.ILiveConstants;
import com.tencent.qcloud.uikit.common.component.face.Emoji;
import com.tencent.qcloud.uikit.common.component.face.FaceFragment;
import com.tencent.qcloud.uikit.common.component.face.FaceManager;
import com.tencent.qcloud.uikit.common.component.picture.Matisse;
import com.tencent.qcloud.uikit.common.utils.SoftKeyBoardUtil;
import com.tencent.qcloud.uikit.common.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by valxehuang on 2018/7/18.
 */

public class ChatBottomGroup extends LinearLayout implements View.OnClickListener {

    private ImageView switchBtn, faceBtn, moreBtn;
    private Button voiceBtn;
    private EditText msgEditor;
    private View moreGroup;
    private ChatBottomHandler bottomHandler;
    private ChatActionsFragment actionsFragment;
    private FaceFragment faceFragment;
    private Activity activity;
    private FragmentManager fragmentManager;
    private List<ChatBottomAction> actions = new ArrayList<>();

    private static final int STATE_NONE_INPUT = -1;
    private static final int STATE_SOFT_INPUT = 0;
    private static final int STATE_VOICE_INPUT = 1;
    private static final int STATE_FACE_INPUT = 2;
    private static final int STATE_ACTION_INPUT = 3;
    private boolean sendAble;
    private int currentState;
    private float startRecordY;

    public ChatBottomGroup(Context context) {
        super(context);
        init();
    }

    public ChatBottomGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChatBottomGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        activity = (Activity) getContext();
        inflate(getContext(), R.layout.chat_bottom_group, this);
        moreGroup = findViewById(R.id.more_groups);
        voiceBtn = findViewById(R.id.chat_voice_input);
        switchBtn = findViewById(R.id.voice_input_switch);
        switchBtn.setOnClickListener(this);
        faceBtn = findViewById(R.id.face_btn);
        faceBtn.setOnClickListener(this);
        moreBtn = findViewById(R.id.more_btn);
        moreBtn.setOnClickListener(this);
        msgEditor = findViewById(R.id.chat_message_input);
        msgEditor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())) {
                    sendAble = true;
                    moreBtn.setImageResource(R.drawable.send_icon);
                } else {
                    sendAble = false;
                    moreBtn.setImageResource(R.drawable.bottom_action_more);
                }
            }
        });
        msgEditor.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                showSoftInput();
                return false;
            }
        });

        msgEditor.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return false;
            }
        });

        msgEditor.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return false;
            }
        });

        voiceBtn.setOnTouchListener(new OnTouchListener() {
            private long start;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    startRecordY = motionEvent.getY();
                    bottomHandler.startRecording();
                    start = System.currentTimeMillis();
                    ILiveAudioMachine.getInstance().startRecord();

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getY() - startRecordY < -100) {
                        UIUtils.toastLongMessage("已取消");
                    } else {
                        bottomHandler.sendMessage(MessageInfoUtil.buildAudioMessage(System.currentTimeMillis() - start));
                        UIUtils.toastLongMessage("已发送");
                    }
                    ILiveAudioMachine.getInstance().stopRecord();
                    bottomHandler.stopRecording();
                }
                return false;
            }
        });

        initDefaultActions();
    }


    private void initDefaultActions() {
        ChatBottomAction action = new ChatBottomAction();
        action.setIconResId(R.drawable.pic);
        action.setTitleId(R.string.pic);
        action.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Matisse.defaultFrom(activity, new ILiveCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        if (data instanceof Map) {
                            Map videoRes = (Map) data;
                            MessageInfo msg = MessageInfoUtil.buildVideoMessage((Uri) videoRes.get(ILiveConstants.CAMERA_IMAGE_PATH), (Uri) videoRes.get(ILiveConstants.CAMERA_VIDEO_PATH));
                            bottomHandler.sendMessage(msg);
                        } else {
                            List<Uri> uris = (List<Uri>) data;
                            for (int i = 0; i < uris.size(); i++) {
                                MessageInfo msg = MessageInfoUtil.buildImageMessage(uris.get(i));
                                bottomHandler.sendMessage(msg);
                            }
                        }
                    }

                    @Override
                    public void onError(String module, int errCode, String errMsg) {

                    }
                });
            }
        });
        actions.add(action);
        action = new ChatBottomAction();
        action.setIconResId(R.drawable.action_video_selector);
        action.setTitleId(R.string.action_file);
        action.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//设置类型，我这里是任意类型，任意后缀的可以这样写。
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                actionsFragment.setCallback(new ILiveCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        MessageInfo info = MessageInfoUtil.buildFileMessage((Uri) data);
                        bottomHandler.sendMessage(info);
                    }

                    @Override
                    public void onError(String module, int errCode, String errMsg) {
                        UIUtils.toastLongMessage(errMsg);
                    }
                });
                actionsFragment.startActivityForResult(intent, ChatActionsFragment.REQUEST_CODE_FILE);
            }
        });


        action = new ChatBottomAction();
        action.setIconResId(R.drawable.photo);
        action.setTitleId(R.string.photo);
        action.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.toastLongMessage("摄像");
            }
        });
        actions.add(action);

        action = new ChatBottomAction();
        action.setIconResId(R.drawable.video);
        action.setTitleId(R.string.video);
        action.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.toastLongMessage("视频");
            }
        });
        actions.add(action);

        action = new ChatBottomAction();
        action.setIconResId(R.drawable.file);
        action.setTitleId(R.string.file);
        action.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.toastLongMessage("文件");
            }
        });
        actions.add(action);
    }

    public void setBottomActions(List<ChatBottomAction> actions) {
        this.actions.addAll(actions);
    }


    public void setBottomAreaHandler(ChatBottomHandler handler) {
        this.bottomHandler = handler;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.voice_input_switch) {
            if (currentState == STATE_SOFT_INPUT)
                currentState = STATE_VOICE_INPUT;
            else
                currentState = STATE_SOFT_INPUT;
            if (currentState == STATE_VOICE_INPUT) {
                switchBtn.setImageResource(R.drawable.soft_input);
                voiceBtn.setVisibility(VISIBLE);
                msgEditor.setVisibility(GONE);
                faceBtn.setVisibility(View.GONE);
                hideSoftInput();
            } else {
                switchBtn.setImageResource(R.drawable.mic_icon);
                voiceBtn.setVisibility(GONE);
                msgEditor.setVisibility(VISIBLE);
                faceBtn.setVisibility(View.VISIBLE);
                showSoftInput();
            }

        } else if (view.getId() == R.id.face_btn) {
            if (currentState == STATE_FACE_INPUT) {
                currentState = STATE_NONE_INPUT;
                moreGroup.setVisibility(View.GONE);
            } else {
                showFaceViewGroup();
                currentState = STATE_FACE_INPUT;
            }
        } else if (view.getId() == R.id.more_btn) {
            if (sendAble) {
                bottomHandler.sendMessage(MessageInfoUtil.buildMessage(msgEditor.getText().toString()));
                msgEditor.setText("");
            } else {
                if (currentState == STATE_ACTION_INPUT) {
                    currentState = STATE_NONE_INPUT;
                    moreGroup.setVisibility(View.GONE);
                } else {
                    showActionsGroup();
                    currentState = STATE_ACTION_INPUT;
                }

            }

        }
    }

    private void showSoftInput() {
        hideActionsGroup();
     /*   if (moreGroup.getVisibility() == VISIBLE) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        } else {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }*/
        currentState = STATE_SOFT_INPUT;
        switchBtn.setImageResource(R.drawable.bottom_action_voice);
        faceBtn.setImageResource(R.drawable.bottom_action_face);
        msgEditor.requestFocus();
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(msgEditor, 0);
        if (bottomHandler != null)
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    bottomHandler.bottomAreaShow();
                }
            }, 200);

    }


    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(msgEditor.getWindowToken(), 0);
        msgEditor.clearFocus();
        if (bottomHandler != null)
            bottomHandler.bottomAreaHide();
    }

    private void showFaceViewGroup() {
        if (fragmentManager == null)
            fragmentManager = activity.getFragmentManager();
        if (faceFragment == null)
            faceFragment = new FaceFragment();
        hideSoftInput();
        postDelayed(new Runnable() {
            @Override
            public void run() {
                moreGroup.setVisibility(View.VISIBLE);
                msgEditor.requestFocus();
                faceFragment.setListener(new FaceFragment.OnEmojiClickListener() {
                    @Override
                    public void onEmojiDelete() {
                        int index = msgEditor.getSelectionStart();
                        Editable editable = msgEditor.getText();
                        String faceChar = editable.subSequence(index - 4, index).toString();
                        if (FaceManager.isFaceChar(faceChar)) {
                            editable.delete(index - 4, index);
                        } else {
                            editable.delete(index - 1, index);
                        }

                    }

                    @Override
                    public void onEmojiClick(Emoji emoji) {
                        int index = msgEditor.getSelectionStart();
                        Editable editable = msgEditor.getText();
                        editable.insert(index, emoji.getFilter());
                        FaceManager.handlerEmojiText(msgEditor, editable.toString());
                    }
                });
                fragmentManager.beginTransaction().replace(R.id.more_groups, faceFragment).commitAllowingStateLoss();
            }
        }, 100);

    }

    private void showActionsGroup() {
        if (fragmentManager == null)
            fragmentManager = activity.getFragmentManager();
        if (actionsFragment == null)
            actionsFragment = new ChatActionsFragment();

        actionsFragment.setActions(actions);
        hideSoftInput();
        moreGroup.setVisibility(View.VISIBLE);
        fragmentManager.beginTransaction().replace(R.id.more_groups, actionsFragment).commitAllowingStateLoss();
    }

    private void hideActionsGroup() {
        moreGroup.setVisibility(View.GONE);
    }

    public interface ChatBottomHandler {
        void bottomAreaShow();

        void bottomAreaHide();

        void sendMessage(MessageInfo messageInfo);

        void startRecording();

        void stopRecording();
    }
}
