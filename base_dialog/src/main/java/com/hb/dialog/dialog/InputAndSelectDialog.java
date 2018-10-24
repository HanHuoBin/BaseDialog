package com.hb.dialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hb.dialog.R;

public class InputAndSelectDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private Display display;
    private TextView titleTv;
    private TextView cancelBtn, okBtn;
    private EditText contentEt;
    private RadioButton oneRb;
    private RadioButton twoRb;
    private int index = 0;

    public InputAndSelectDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public InputAndSelectDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.input_and_checkbox_dialog,
                null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        titleTv = (TextView) view.findViewById(R.id.tv_title);
        contentEt = (EditText) view.findViewById(R.id.et_content);
        oneRb = (RadioButton) view.findViewById(R.id.rb_one);
        twoRb = (RadioButton) view.findViewById(R.id.rb_two);
        oneRb.setButtonDrawable(context.getResources().getDrawable(R.drawable.selector_checkbox));
        twoRb.setButtonDrawable(context.getResources().getDrawable(R.drawable.selector_checkbox));
        cancelBtn = (TextView) view.findViewById(R.id.btn_cancel);
        okBtn = (TextView) view.findViewById(R.id.btn_ok);
        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.InputAndSelectDialogStyle);
        dialog.setContentView(view);
        oneRb.setChecked(true);
        oneRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    index = 0;
                }
            }
        });
        twoRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    index = 1;
                }
            }
        });
        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85),
                LinearLayout.LayoutParams.WRAP_CONTENT));
        return this;
    }

    public InputAndSelectDialog setRadioButtonText(String[] titles) {
        if (titles != null && titles.length == 2) {
            oneRb.setText(titles[0]);
            twoRb.setText(titles[1]);
        }
        return this;
    }

    public InputAndSelectDialog setContentHint(String hint) {
        if (!TextUtils.isEmpty(hint)) {
            contentEt.setHint(hint);
        }
        return this;
    }

    public InputAndSelectDialog setPositiveButton(String text, int color, final InputAndSelectListener listener) {
        if ("".equals(text)) {
            okBtn.setText(context.getString(R.string.confirm));
        } else {
            okBtn.setText(text);
        }
        if (color != 0) {
            okBtn.setTextColor(context.getResources().getColor(color));
        }
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = contentEt.getText().toString().trim();
                listener.onSelected(content, index);
                dialog.dismiss();
            }
        });
        return this;
    }

    public InputAndSelectDialog setNegativeButton(String text, final View.OnClickListener listener) {
        if ("".equals(text)) {
            cancelBtn.setText(context.getString(R.string.cancel));
        } else {
            cancelBtn.setText(text);
        }
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
                dialog.dismiss();
            }
        });
        return this;
    }

    public InputAndSelectDialog setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            titleTv.setText(title);
        } else {
            titleTv.setText("");
        }
        return this;
    }

    public void show() {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }
    }

    public void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public interface InputAndSelectListener {
        void onSelected(String content, int index);
    }
}
