package com.charles.eden;

import android.app.Application;
import android.content.Context;

import com.charles.utils.Logger;

/**
 * Created by 刘奉强 on 2018/12/16 18:20
 * <p>
 * Describe：
 */
public class NotePlanApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.setTag("NOTE_PLAN_TAG");
        this.mContext = getApplicationContext();
        Logger.d("onCreate");
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        Logger.d("我被清理了");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Logger.d("onLowMemory");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Logger.d("程序终止的时候执行");
    }

}
