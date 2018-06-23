package com.hb.dialog.myDialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Html;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.hb.dialog.R;


public class MyAlertInputDialog {
    private Context      context;
    private Dialog       dialog;
    private LinearLayout lLayout_bg;
    private TextView     txt_title;
    private TextView     txt_msg;
    private EditText     edittxt_result;
    private LinearLayout negLL;
    private LinearLayout posLL;
    private TextView     btn_neg;
    private TextView     btn_pos;
    private ImageView    img_line;
    private Display      display;
    private boolean      showTitle    = false;
    private boolean      showMsg      = false;
    private boolean      showEditText = false;
    private boolean      showPosBtn   = false;
    private boolean      showNegBtn   = false;

    public MyAlertInputDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public MyAlertInputDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.toast_view_input_dialog, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        txt_msg.setVisibility(View.GONE);
        edittxt_result = (EditText) view.findViewById(R.id.edittxt_result);
        edittxt_result.setVisibility(View.GONE);
        btn_neg = (TextView) view.findViewById(R.id.btn_neg);
        btn_pos = (TextView) view.findViewById(R.id.btn_pos);
        negLL = (LinearLayout) view.findViewById(R.id.ll_left);
        posLL = (LinearLayout) view.findViewById(R.id.ll_right);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85),
                LayoutParams.WRAP_CONTENT));

        return this;
    }

    public TextView getPositiveButton() {
        return btn_pos;
    }

    public TextView getNegativeButton() {
        return btn_neg;
    }

    public EditText getContentEditText() {
        return edittxt_result;
    }

    public MyAlertInputDialog setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText(context.getString(R.string.title));
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    public MyAlertInputDialog setEditText(String msg) {
        showEditText = true;
        if ("".equals(msg)) {
            edittxt_result.setHint(context.getString(R.string.contents));
        } else {
            edittxt_result.setHint(msg);
        }
        return this;
    }

    public MyAlertInputDialog setEditType(int editType) {
        edittxt_result.setInputType(editType);
        return this;
    }

    public String getResult() {
        return edittxt_result.getText().toString();
    }

    public MyAlertInputDialog setMsg(String msg) {
        showMsg = true;
        if ("".equals(msg)) {
            txt_msg.setText(context.getString(R.string.contents));
        } else {
            txt_msg.setText(Html.fromHtml(msg));
        }
        return this;
    }

    public MyAlertInputDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public MyAlertInputDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public MyAlertInputDialog setPositiveButton(String text, final OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText(context.getString(R.string.confirm));
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
            }
        });
        return this;
    }

    public MyAlertInputDialog setNegativeButton(String text, final OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_neg.setText(context.getString(R.string.cancel));
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText(context.getString(R.string.prompt));
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showEditText) {
            edittxt_result.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (!showPosBtn && !showNegBtn) {
            btn_pos.setText(context.getString(R.string.confirm));
            posLL.setVisibility(View.VISIBLE);
            btn_pos.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
        }

        if (showPosBtn && showNegBtn) {
            posLL.setVisibility(View.VISIBLE);
            negLL.setVisibility(View.VISIBLE);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            posLL.setVisibility(View.VISIBLE);
            negLL.setVisibility(View.GONE);
            img_line.setVisibility(View.GONE);
        }

        if (!showPosBtn && showNegBtn) {
            posLL.setVisibility(View.GONE);
            negLL.setVisibility(View.VISIBLE);
            img_line.setVisibility(View.GONE);
        }
    }

    public void show() {
        setLayout();
        dialog.show();
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
