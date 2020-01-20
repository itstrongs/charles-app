package com.charles.credit.assist;

import android.app.Application;
import android.content.Context;

import com.charles.credit.assist.helper.MyUncaughtExceptionHandler;
import com.charles.library.utils.Logger;

/**
 * 说明
 *
 * @Author: liufengqiang
 * @Date: 2019/6/3
 */
public class CreditApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.setTag("CREDIT_ASSIST");
        this.mContext = getApplicationContext();
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
    }

    public static Context getContext() {
        return mContext;
    }
}
