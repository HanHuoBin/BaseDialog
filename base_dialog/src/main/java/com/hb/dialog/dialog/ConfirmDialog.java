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
    private OnBtnClickListener listener;
    private ImageView noticeImg;
    private TextView tvMsg;

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
    public void setClickListener(OnBtnClickListener listener) {
        this.listener = listener;
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
     * 初始化dialog
     */
    private void init() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.dialog_confirm_layout, null);
        setContentView(layout);
        View btnOk = layout.findViewById(R.id.btn_ok);
        View btnCancel = layout.findViewById(R.id.btn_cancel);
        noticeImg = (ImageView) layout.findViewById(R.id.img_logo);
        tvMsg = (TextView) layout.findViewById(R.id.tv_msg);

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

}
