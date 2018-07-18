package com.hb.dialog.myDialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hb.dialog.R;
import com.hb.dialog.inputView.PwdInputView;
import com.hb.dialog.widget.PasswordKeyboardView;


public class MyPwdInputDialog {
    private Context              context;
    private Dialog               dialog;
    private Display              display;

    private PasswordKeyboardView mKeyBoardView;
    private PwdInputView managerInputView;
    private StringBuilder        password = new StringBuilder();
    private TextView titleTv;

    public MyPwdInputDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
    }

    public MyPwdInputDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.toast_view_password_input, null);

        // 设置Dialog最小宽度为屏幕宽度
        view.setMinimumWidth(display.getWidth());

        managerInputView = (PwdInputView) view.findViewById(R.id.input_manager_password);
        mKeyBoardView = (PasswordKeyboardView) view.findViewById(R.id.password_key_board);
        titleTv = (TextView) view.findViewById(R.id.tv_title);

        initManagerInputView();

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);

        return this;
    }

    public MyPwdInputDialog setTitle(String  title){
        if(!TextUtils.isEmpty(title)){
            titleTv.setText(title);
        }
        return this;
    }

    /**
     * 管理员密码输入框，以及键盘监听
     */
    private void initManagerInputView() {
        managerInputView.setShadowPasswords(false);
        managerInputView.setNumTextColor(context.getResources().getColor(R.color.dialog_gray));
    }

    public MyPwdInputDialog setPasswordListener(final OnPasswordResultListener listener) {
        mKeyBoardView.setIOnKeyboardListener(new PasswordKeyboardView.IOnKeyboardListener() {
            @Override
            public void onInsertKeyEvent(String text) {
                //只能输入六位数密码
                if (password.length() < 6) {
                    password.append(text);
                    managerInputView.setText(password);
                }
                if (password.length() == 6) {
                    listener.onPasswordResult(password.toString());
                }
            }

            @Override
            public void onDeleteKeyEvent() {
                //删除密码
                if (password.length() > 0) {
                    password.delete(password.length() - 1, password.length());
                    managerInputView.setText(password);
                }
            }
        });
        return this;
    }

    public MyPwdInputDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public MyPwdInputDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public interface OnPasswordResultListener {
        void onPasswordResult(String password);
    }
}
