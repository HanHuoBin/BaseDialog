package com.hb.dialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hb.dialog.R;

public class NoticeDialog {

    private Context context;
    private TextView msgTv;
    private ImageView logoImg;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private Display display;

    public NoticeDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public NoticeDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.notice_dialog,
                null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        msgTv = (TextView) view.findViewById(R.id.tv_msg);
        logoImg = (ImageView) view.findViewById(R.id.img_logo);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.NoticeDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.3),
                LinearLayout.LayoutParams.WRAP_CONTENT));

        return this;
    }

    public NoticeDialog setMsg(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            msgTv.setText(msg);
        } else {
            msgTv.setText("");
        }
        return this;
    }

    public NoticeDialog setImageLogo(Drawable msg) {
        if (msg != null) {
            logoImg.setImageDrawable(msg);
        }
        return this;
    }

    public void show() {
        if(dialog!=null && !dialog.isShowing()){
            dialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                }
            },1500);

        }
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
