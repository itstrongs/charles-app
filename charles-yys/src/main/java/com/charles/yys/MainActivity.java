package com.charles.yys;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.charles.utils.SPHelper;
import com.charles.utils.ToastUtils;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edit_yh_x)
    EditText editYhX;
    @BindView(R.id.edit_yh_y)
    EditText editYhY;
    @BindView(R.id.edit_bg_x)
    EditText editBgX;
    @BindView(R.id.edit_bg_y)
    EditText editBgY;
    @BindView(R.id.text_point)
    TextView textPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        editYhX.setText(String.valueOf(SPHelper.getInt(this, ConstantPool.SP_YYS_YH_X, 1700)));
        editYhY.setText(String.valueOf(SPHelper.getInt(this, ConstantPool.SP_YYS_YH_Y, 990)));

        editBgX.setText(String.valueOf(SPHelper.getInt(this, ConstantPool.SP_YYS_BG_X, 1700)));
        editBgY.setText(String.valueOf(SPHelper.getInt(this, ConstantPool.SP_YYS_BG_Y, 990)));
    }

    @OnClick({R.id.btn_start_yh, R.id.btn_start_bg, R.id.btn_close, R.id.btn_root, R.id.btn_ok_yh, R.id.btn_ok_bg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_start_yh:
                SPHelper.putInt(this, ConstantPool.SP_YYS_MODULE_TYPE, 0);
                stopService(new Intent(this, YYSService.class));
                startService(new Intent(this, YYSService.class));
                ToastUtils.show(this, "服务已开启");
                break;
            case R.id.btn_start_bg:
                SPHelper.putInt(this, ConstantPool.SP_YYS_MODULE_TYPE, 1);
                stopService(new Intent(this, YYSService.class));
                startService(new Intent(this, YYSService.class));
                ToastUtils.show(this, "服务已开启");
                break;
            case R.id.btn_close:
                stopService(new Intent(this, YYSService.class));
                ToastUtils.show(this, "服务已关闭");
                break;
            case R.id.btn_root:
                try {
                    Runtime.getRuntime().exec("su");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_ok_yh:
                try {
                    SPHelper.putInt(this, ConstantPool.SP_YYS_YH_X, Integer.parseInt(editYhX.getText().toString()));
                    SPHelper.putInt(this, ConstantPool.SP_YYS_YH_Y, Integer.parseInt(editYhY.getText().toString()));
                } catch (Exception e) {
                    ToastUtils.show(this, "请输入数字");
                }
                break;
            case R.id.btn_ok_bg:
                try {
                    SPHelper.putInt(this, ConstantPool.SP_YYS_BG_X, Integer.parseInt(editYhX.getText().toString()));
                    SPHelper.putInt(this, ConstantPool.SP_YYS_BG_Y, Integer.parseInt(editYhY.getText().toString()));
                } catch (Exception e) {
                    ToastUtils.show(this, "请输入数字");
                }
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        try {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_MOVE:
                    textPoint.setText("当前触摸坐标：（" + x + ", " + y + "）");
                    break;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
