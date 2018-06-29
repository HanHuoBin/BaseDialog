package com.hb.dialog.dialog;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hb.dialog.R;


/**
 * fragment里使用的加载dialog
 * Created by Administrator on 2015/11/17.
 */
public class LoadingFragmentDialog extends DialogFragment {

    private String msg;
    private TextView tvMsg;

    public LoadingFragmentDialog(){
        setStyle(STYLE_NO_TITLE, R.style.CustomerDialogTheme);
    }

    public void setMessage(String msg){
        this.msg = msg;
        if(tvMsg != null && !TextUtils.isEmpty(msg)){
            tvMsg.setText(msg);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.dialog_loading_layout, null);
        tvMsg = (TextView) layout.findViewById(R.id.tv_msg);
        if(!TextUtils.isEmpty(msg)){
            tvMsg.setText(msg);
        }
        return layout;
    }
}
