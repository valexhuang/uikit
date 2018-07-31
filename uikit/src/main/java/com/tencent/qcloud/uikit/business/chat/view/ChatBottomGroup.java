package com.tencent.qcloud.uikit.business.chat.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
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
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatActionsFragment;
import com.tencent.qcloud.uikit.business.chat.view.widget.ChatBottomAction;
import com.tencent.qcloud.uikit.common.component.face.Emoji;
import com.tencent.qcloud.uikit.common.component.face.FaceFragment;
import com.tencent.qcloud.uikit.common.component.face.FaceManager;
import com.tencent.qcloud.uikit.common.component.picture.Matisse;
import com.tencent.qcloud.uikit.common.utils.ImageUtil;
import com.tencent.qcloud.uikit.common.utils.SoftKeyBoardUtil;
import com.tencent.qcloud.uikit.common.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

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
        inflate(getContext(), R.layout.chat_bottom_group, this);
        moreGroup = findViewById(R.id.more_groups);
        voiceBtn = findViewById(R.id.chat_voice_input);
        LayoutParams params = (LayoutParams) moreGroup.getLayoutParams();
        params.height = SoftKeyBoardUtil.getSoftKeyBoardHeight();
        moreGroup.setLayoutParams(params);
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
                    moreBtn.setImageResource(R.drawable.msg_send_selector);
                } else {
                    sendAble = false;
                    moreBtn.setImageResource(R.drawable.more_icon);
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
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    startRecordY = motionEvent.getY();
                    bottomHandler.startRecording();
                    ILiveAudioMachine.getInstance().startRecord();

                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    if (motionEvent.getY() - startRecordY < -100) {
                        UIUtils.toastLongMessage("已取消");
                    } else {
                        bottomHandler.sendMessage(buildAudioMessage());
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
        action.setIconResId(R.drawable.action_photo_selector);
        action.setTitleId(R.string.action_photo);
        action.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Matisse.defaultFrom(activity, new ILiveCallBack() {
                    @Override
                    public void onSuccess(Object data) {
                        List<Uri> uris = (List<Uri>) data;
                        for (int i = 0; i < uris.size(); i++) {
                            MessageInfo msg = buildImageMessage(uris.get(i));
                            bottomHandler.sendMessage(msg);
                        }
                    }

                    @Override
                    public void onError(String module, int errCode, String errMsg) {

                    }
                });
            }
        });
        actions.add(action);
    }

    public void setBottomActions(List<ChatBottomAction> actions) {
        this.actions.addAll(actions);
    }


    public void setActivity(Activity activity) {
        this.activity = activity;
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
                hideSoftInput();
            } else {
                switchBtn.setImageResource(R.drawable.mic_icon);
                voiceBtn.setVisibility(GONE);
                msgEditor.setVisibility(VISIBLE);
                showSoftInput();
            }

        } else if (view.getId() == R.id.face_btn) {
            if (currentState == STATE_FACE_INPUT)
                currentState = STATE_VOICE_INPUT;
            else
                currentState = STATE_FACE_INPUT;
            if (currentState == STATE_FACE_INPUT) {
                faceBtn.setImageResource(R.drawable.soft_input);
                showFaceViewGroup();
            } else {
                faceBtn.setImageResource(R.drawable.face_icon);
                showSoftInput();
            }


        } else if (view.getId() == R.id.more_btn) {
            if (sendAble) {
                bottomHandler.sendMessage(buildMessage());
            } else {
                if (actionsFragment != null && actionsFragment.isVisible()) {
                    showSoftInput();
                } else {
                    showActionsGroup();
                    currentState = STATE_ACTION_INPUT;
                }
            }

        }
    }

    private void showSoftInput() {
        if (moreGroup.getVisibility() == VISIBLE) {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        } else {
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
        currentState = STATE_SOFT_INPUT;
        switchBtn.setImageResource(R.drawable.mic_icon);
        faceBtn.setImageResource(R.drawable.face_icon);
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


    private void hideSoftInput() {
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

    private MessageInfo buildMessage() {
        MessageInfo info = new MessageInfo();
        info.setMsg(msgEditor.getText().toString());
        info.setSelf(true);
        return info;
    }


    private MessageInfo buildImageMessage(Uri uri) {
        MessageInfo info = new MessageInfo();
        info.setSelf(true);
        info.setMsgType(MessageInfo.MSG_TYPE_IMAGE);
        info.setMsgBitamp(ImageUtil.getBitmapFormUri(uri));
        return info;
    }

    private MessageInfo buildAudioMessage() {
        MessageInfo info = new MessageInfo();
        info.setDataPath(ILiveAudioMachine.getInstance().getRecordAudioPath());
        info.setSelf(true);
        info.setMsgType(MessageInfo.MSG_TYPE_AUDIO);
        return info;
    }

    public interface ChatBottomHandler {
        void bottomAreaShow();

        void bottomAreaHide();

        void sendMessage(MessageInfo messageInfo);

        void startRecording();

        void stopRecording();
    }
}
