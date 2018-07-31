package com.tencent.qcloud.uikit.common.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.qcloud.uikit.R;

/**
 * Created by valxehuang on 2018/7/30.
 */

public class PopWindowUtil {

    public static AlertDialog buildCustomDialog(Activity activity) {
        if (activity.isDestroyed())
            return null;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("");
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setDimAmount(0);
        dialog.setCanceledOnTouchOutside(true);
        return dialog;
    }


    public static AlertDialog buildFullScreenDialog(Activity activity) {
        if (activity.isDestroyed())
            return null;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("");
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setDimAmount(0);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(null);
        return dialog;
    }

    public static final AlertDialog buildEditorDialog(Activity context, String title, String content,
                                                      String cancel, String sure, final EnsureListener listener) {

        if (context.isDestroyed()) {
            return null;
        }

        final AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setCancelable(false);
        dialog = builder.create();

        dialog.show();
        View baseView = LayoutInflater.from(context).inflate(R.layout.layout_dialog, null);
        ((TextView) baseView.findViewById(R.id.dialog_title)).setText(title);
        ((TextView) baseView.findViewById(R.id.dialog_content)).setText(content);
        ((TextView) baseView.findViewById(R.id.dialog_cancel_btn)).setText(cancel);
        ((TextView) baseView.findViewById(R.id.dialog_sure_btn)).setText(sure);
        final EditText editText = baseView.findViewById(R.id.dialog_editor);

        baseView.findViewById(R.id.dialog_sure_btn).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.sure(editText.getText().toString());
                dialog.dismiss();
            }
        });

        baseView.findViewById(R.id.dialog_cancel_btn).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null)
                    listener.cancel();
                dialog.dismiss();
            }
        });
        dialog.setContentView(baseView);
        return dialog;
    }

    public interface EnsureListener {
        void sure(Object obj);

        void cancel();
    }
}
