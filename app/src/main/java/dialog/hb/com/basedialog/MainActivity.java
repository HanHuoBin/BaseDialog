package dialog.hb.com.basedialog;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.hb.dialog.myDialog.ActionSheetDialog;
import com.hb.dialog.myDialog.MyAlertDialog;
import com.hb.dialog.myDialog.MyAlertInputDialog;
import com.hb.dialog.myDialog.MyImageMsgDialog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AnimationDrawable           connectAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.action_dialog, R.id.alert_dialog, R.id.alert_input_dialog, R.id.image_msg_dialog})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
                MyAlertDialog myAlertDialog = new MyAlertDialog(this).builder()
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
                MyAlertInputDialog myAlertInputDialog = new MyAlertInputDialog(this).builder()
                        .setTitle("请输入")
                        .setEditText("")
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
