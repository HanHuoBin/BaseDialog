package com.hb.dialog.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hb.dialog.R;


/**
 * created by mundane.
 * 连接中的dialog
 */
public class ConnectingDialog extends Dialog {
    private Context  context;
    private ImageView mIvAnim;
    private String mMsg;
    private TextView mTvMsg;

    public ConnectingDialog(Context context) {
        super(context, R.style.CustomerDialogTheme);
        this.context = context;
    }

    public void setMessage(String msg) {
        this.mMsg = msg;
        if (mTvMsg != null && !TextUtils.isEmpty(msg)) {
            mTvMsg.setText(msg);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        View view = View.inflate(context, R.layout.dialog_connecting, null);
        mIvAnim = view.findViewById(R.id.iv_anim);
        mTvMsg = view.findViewById(R.id.tv_msg);
        if (!TextUtils.isEmpty(mMsg)) {
            mTvMsg.setText(mMsg);
        }
        setContentView(view);
        startAnim(mIvAnim);
    }

    private void startAnim(ImageView ivAnim) {
        AnimationDrawable drawable = (AnimationDrawable) ivAnim.getDrawable();
        if (!drawable.isRunning()) {
            drawable.start();
        }
    }

    private void stopAnim(ImageView ivAnim) {
        AnimationDrawable drawable = (AnimationDrawable) ivAnim.getDrawable();
        if (drawable.isRunning()) {
            drawable.stop();
        }
    }

    @Override
    public void show() {
        if (mIvAnim != null) {
            startAnim(mIvAnim);
        }
        super.show();
    }

    @Override
    public void dismiss() {
        // 防止activity被finish之前,dialog还没dismiss而报错
        if (context != null && !((Activity) context).isFinishing()) {
            stopAnim(mIvAnim);
            super.dismiss();
        }
    }
}
