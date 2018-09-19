package com.hb.dialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.hb.dialog.R;

public class UpdateDialog {

    private Context context;
    private Display display;
    private Dialog dialog;
    private TextView titleTv;
    private TextView msgTv;
    private TextView posTv;
    private TextView negTv;

    public UpdateDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public UpdateDialog builder() {
        View view = LayoutInflater.from(context).inflate(R.layout.update_view_dialog, null);
        titleTv = view.findViewById(R.id.tv_title);
        msgTv = view.findViewById(R.id.tv_msg);
        negTv = view.findViewById(R.id.btn_neg);
        posTv = view.findViewById(R.id.btn_pos);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        return this;
    }

    /**
     * 设置标题
     *
     * @param title
     * @return
     */
    public UpdateDialog setTitle(String title) {
        if ("".equals(title)) {
            titleTv.setText(context.getString(R.string.title));
        } else {
            titleTv.setText(title);
        }
        return this;
    }

    /**
     * 设置更新内容
     *
     * @param msg
     * @return
     */
    public UpdateDialog setMsg(String msg) {
        if ("".equals(msg)) {
            msgTv.setText(context.getString(R.string.contents));
        } else {
            msgTv.setText(Html.fromHtml(msg));
        }
        return this;
    }

    public UpdateDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public UpdateDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public UpdateDialog setPositiveButton(String text, final View.OnClickListener listener) {
        if ("".equals(text)) {
            posTv.setText(context.getString(R.string.confirm));
        } else {
            posTv.setText(text);
        }
        posTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public UpdateDialog setPositiveButton(String text, final View.OnClickListener listener, final boolean isDismiss) {
        if ("".equals(text)) {
            posTv.setText(context.getString(R.string.confirm));
        } else {
            posTv.setText(text);
        }
        posTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                if (isDismiss) {
                    dialog.dismiss();
                }
            }
        });
        return this;
    }

    public UpdateDialog setNegativeButton(String text, final View.OnClickListener listener) {
        if ("".equals(text)) {
            negTv.setText(context.getString(R.string.cancel));
        } else {
            negTv.setText(text);
        }
        negTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }


    public void show() {
        if (dialog != null) {
            dialog.show();
        }
    }

    public void dismiss() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            dialog = null;
        } catch (Exception e) {

        }
    }

}
