package com.charles.yys;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.charles.utils.Logger;
import com.charles.utils.SPHelper;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by itstrongs on 2017/11/25.
 */
public class YYSService extends Service {

    private Runnable mRunnable;
    private Handler mHandler;
    private Random mRandom;

    @Override
    public void onCreate() {
        super.onCreate();
        mRandom = new Random();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int moduleType = SPHelper.getInt(getApplicationContext(), ConstantPool.SP_YYS_MODULE_TYPE, 0);
        final int defaultX;
        final int defaultY;
        if (moduleType == 0) {
            defaultX = SPHelper.getInt(getApplicationContext(), ConstantPool.SP_YYS_YH_X, 1700);
            defaultY = SPHelper.getInt(getApplicationContext(), ConstantPool.SP_YYS_YH_Y, 990);
        } else {
            defaultX = SPHelper.getInt(getApplicationContext(), ConstantPool.SP_YYS_BG_X, 1700);
            defaultY = SPHelper.getInt(getApplicationContext(), ConstantPool.SP_YYS_BG_Y, 990);
        }

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                int x;
                int y;
                if (moduleType == 0) {
                    x = defaultX + mRandom.nextInt(160) - 80;
                    y = defaultY - mRandom.nextInt(80);
                } else {
                    x = defaultX - mRandom.nextInt(200);
                    y = defaultY - mRandom.nextInt(200);
                }

                String cmd = "input tap " + x + " " + y;
                Logger.d("点击挑战:" + cmd);
                execShellCmd(cmd);

                if (moduleType == 0) {
                    mHandler.postDelayed(this, 2000);
                } else {
                    mHandler.postDelayed(this, 600);
                }
            }
        };
        mHandler.postDelayed(mRunnable, 2000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }

    private void execShellCmd(String cmd) {
        try {
            // 申请获取root权限，这一步很重要，不然会没有作用
            Process process = Runtime.getRuntime().exec("su");
            // 获取输出流
            OutputStream outputStream = process.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            dataOutputStream.writeBytes(cmd);
            dataOutputStream.flush();
            dataOutputStream.close();
            outputStream.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
