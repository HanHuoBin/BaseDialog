package dialog.hb.com.basedialog;

import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hb.dialog.dialog.ConfirmDialog;
import com.hb.dialog.dialog.ConnectingDialog;
import com.hb.dialog.dialog.LoadingDialog;
import com.hb.dialog.dialog.LoadingFragmentDialog;
import com.hb.dialog.myDialog.ActionSheetDialog;
import com.hb.dialog.myDialog.MyAlertDialog;
import com.hb.dialog.myDialog.MyAlertInputDialog;
import com.hb.dialog.myDialog.MyImageMsgDialog;
import com.hb.dialog.myDialog.MyPayInputDialog;
import com.hb.dialog.myDialog.MyPwdInputDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AnimationDrawable connectAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.action_dialog, R.id.alert_dialog, R.id.alert_input_dialog, R.id.image_msg_dialog,
            R.id.confirm_dialog, R.id.connecting_dialog,
            R.id.loading_dialog, R.id.loading_fragment_dialog,
            R.id.pwd_dialog, R.id.pay_dialog,R.id.update_dialog})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.update_dialog:

                break;
            case R.id.action_dialog:
                ActionSheetDialog dialog = new ActionSheetDialog(this).builder().setTitle("请选择")
                        .addSheetItem("相册", null, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                showMsg("相册");
                            }
                        }).addSheetItem("拍照", null, new ActionSheetDialog.OnSheetItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                showMsg("拍照");
                            }
                        });
                dialog.show();
                break;
            case R.id.alert_dialog:
                final MyAlertDialog myAlertDialog = new MyAlertDialog(this).builder()
                        .setTitle("确认吗？")
                        .setMsg("删除内容")
                        .setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMsg("确认");
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMsg("取消");
                            }
                        });
                myAlertDialog.show();
                break;
            case R.id.alert_input_dialog:
                final MyAlertInputDialog myAlertInputDialog = new MyAlertInputDialog(this).builder()
                        .setTitle("请输入")
                        .setEditText("");
                        myAlertInputDialog.setPositiveButton("确认", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMsg(myAlertInputDialog.getResult());
                                myAlertInputDialog.dismiss();
                            }
                        }).setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showMsg("取消");
                                myAlertInputDialog.dismiss();
                            }
                        });
                myAlertInputDialog.show();
                break;
            case R.id.image_msg_dialog:
                MyImageMsgDialog myImageMsgDialog = new MyImageMsgDialog(this).builder()
                        .setImageLogo(getResources().getDrawable(R.mipmap.ic_launcher))
                        .setMsg("连接中...");
                ImageView logoImg = myImageMsgDialog.getLogoImg();
                logoImg.setImageResource(R.drawable.connect_animation);
                connectAnimation = (AnimationDrawable) logoImg.getDrawable();
                connectAnimation.start();
                myImageMsgDialog.show();
                break;
            case R.id.confirm_dialog:
                final ConfirmDialog confirmDialog = new ConfirmDialog(this);
                confirmDialog.setLogoImg(R.mipmap.dialog_notice).setMsg("提示");
                confirmDialog.setPositiveBtn(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        confirmDialog.dismiss();
                    }
                });
                confirmDialog.show();
                break;
            case R.id.connecting_dialog:
                ConnectingDialog connectingDialog = new ConnectingDialog(this);
                connectingDialog.setMessage("MSG");
                connectingDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {

                    }
                });
                connectingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        return false;
                    }
                });
                connectingDialog.show();
                break;
            case R.id.loading_dialog:
                LoadingDialog loadingDialog = new LoadingDialog(this);
                loadingDialog.setMessage("loading");
                loadingDialog.show();
                break;
            case R.id.loading_fragment_dialog:
                LoadingFragmentDialog loadingFragmentDialog = new LoadingFragmentDialog();
                loadingFragmentDialog.setMessage("loading");
                loadingFragmentDialog.show(getSupportFragmentManager(), "msg");
                break;
            case R.id.pwd_dialog:
                final MyPwdInputDialog pwdDialog = new MyPwdInputDialog(this)
                        .builder()
                        .setTitle("请输入密码");
                pwdDialog.setPasswordListener(new MyPwdInputDialog.OnPasswordResultListener() {
                    @Override
                    public void onPasswordResult(String password) {
                        showMsg("您的输入结果：" + password);
                        pwdDialog.dismiss();
                    }
                });
                pwdDialog.show();
                break;
            case R.id.pay_dialog:
                final MyPayInputDialog myPayInputDialog = new MyPayInputDialog(this).Builder();
                myPayInputDialog.setResultListener(new MyPayInputDialog.ResultListener() {
                    @Override
                    public void onResult(String result) {
                        showMsg("您的输入结果：" + result);
                        myPayInputDialog.dismiss();
                    }
                }).setTitle("支付");
                myPayInputDialog.show();
                break;
        }
    }

    private void showMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
