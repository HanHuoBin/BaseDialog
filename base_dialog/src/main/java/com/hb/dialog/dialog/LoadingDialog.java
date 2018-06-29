package com.hb.dialog.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.hb.dialog.R;


/**
 * 加载数据过程中展示窗口 Created by Administrator on 2015/12/21.
 */
public class LoadingDialog extends Dialog {
    private Context  context;
    private String   msg;
    private TextView tvMsg;

    public LoadingDialog(Context context) {
        super(context, R.style.CustomerDialogTheme);
        this.context = context;
    }

    public void setMessage(String msg) {
        this.msg = msg;
        if (tvMsg != null && !TextUtils.isEmpty(msg)) {
            tvMsg.setText(msg);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View layout = inflater.inflate(R.layout.dialog_loading_layout, null);
        View layout = View.inflate(context, R.layout.dialog_loading_layout, null);
        setContentView(layout);

        tvMsg = layout.findViewById(R.id.tv_msg);
        if (!TextUtils.isEmpty(msg)) {
            tvMsg.setText(msg);
        }
    }

    @Override
    public void dismiss() {
        //防止activity被finish之前,dialog还没dismiss而报错
        if (context != null && !((Activity) context).isFinishing()) {
            super.dismiss();
        }
    }
}
