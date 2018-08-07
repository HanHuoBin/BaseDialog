package com.hb.dialog.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hb.dialog.R;

/**
 * 确认dialog
 */
public class ConfirmDialog extends Dialog {

    private Context context;
    private ImageView noticeImg;
    private TextView tvMsg;
    private TextView btnOk;
    private TextView btnCancel;

    public interface OnBtnClickListener {
        public void ok();

        public void cancel();
    }

    public ConfirmDialog(Context context) {
        super(context, R.style.CustomerDialogTheme);
        this.context = context;
        init();
    }

    /**
     * 设置按钮监听
     *
     * @param listener
     */
    public void setClickListener(final OnBtnClickListener listener) {
        btnOk.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.ok();
                }
                dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.cancel();
                }
                dismiss();
            }
        });
    }

    /**
     * 设置提示的logo
     *
     * @param logo
     * @return
     */
    public ConfirmDialog setLogoImg(int logo) {
        if (noticeImg == null) {
            return this;
        }
        noticeImg.setVisibility(View.VISIBLE);
        noticeImg.setImageDrawable(context.getResources().getDrawable(logo));
        return this;
    }

    /**
     * 获取logo
     *
     * @return
     */
    public ImageView getLogoImg() {
        return noticeImg;
    }

    /**
     * 设置提示语
     *
     * @param msg
     * @return
     */
    public ConfirmDialog setMsg(String msg) {
        if (tvMsg == null) {
            return this;
        }
        tvMsg.setVisibility(View.VISIBLE);
        tvMsg.setText(msg);
        return this;
    }

    /**
     * 确定按钮
     *
     * @param listener
     * @return
     */
    public ConfirmDialog setPositiveBtn(View.OnClickListener listener) {
        if (btnOk != null) {
            btnOk.setVisibility(View.VISIBLE);
            btnOk.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 取消按钮
     *
     * @param listener
     * @return
     */
    public ConfirmDialog setNegativeBtn(View.OnClickListener listener) {
        if (btnCancel != null) {
            btnCancel.setVisibility(View.VISIBLE);
            btnCancel.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 初始化dialog
     */
    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_confirm_layout, null);
        setContentView(layout);
        btnOk = layout.findViewById(R.id.btn_ok);
        btnCancel = layout.findViewById(R.id.btn_cancel);
        noticeImg = (ImageView) layout.findViewById(R.id.img_logo);
        tvMsg = (TextView) layout.findViewById(R.id.tv_msg);
    }

}
