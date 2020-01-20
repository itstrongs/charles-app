package com.charles.myapp.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.charles.library.utils.Logger;
import com.charles.library.utils.SPHelper;
import com.charles.myapp.data.ConstantPool;

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
        mHandler = new Handler();
        final int defaultX = SPHelper.getInt(getApplicationContext(), ConstantPool.SP_YYS_X, 850);
        final int defaultY = SPHelper.getInt(getApplicationContext(), ConstantPool.SP_YYS_Y, 450);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                int x = defaultX + mRandom.nextInt(50);
                int y = defaultY + mRandom.nextInt(50);
                String cmd = "input tap " + x + " " + y;
                Logger.d("点击挑战:" + cmd);
                execShellCmd(cmd);
                //创建一个画布背景
//                mPenBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
//                //用于操作的画布
//                mPenCanvas = new Canvas(mPenBitmap);
//                // 创建画笔
//                Paint p = new Paint();
//                p.setColor(Color.RED);// 设置红色
//                canvas.drawCircle(60, 20, 10, p);// 小圆
                mHandler.postDelayed(this, 2000);
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
