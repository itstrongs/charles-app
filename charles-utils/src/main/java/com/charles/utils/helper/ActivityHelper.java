package com.charles.utils.helper;

import android.app.Activity;
import android.os.Handler;

import com.charles.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public enum ActivityHelper {

    INSTANCE;

    private List<Activity> mActivityList;
    private boolean mIsFirstBackEvent;

    ActivityHelper() {
        mActivityList = new ArrayList<>();
    }

    public void add(Activity activity) {
        mActivityList.add(activity);
    }

    public Activity getActivityTop() {
        return mActivityList.get(mActivityList.size() - 1);
    }

    public void remove(Activity activity) {
        mActivityList.remove(activity);
    }

    public void exit() {
        finishAllActivity();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void finishAllActivity() {
        for (Activity activity : mActivityList) {
            activity.finish();
        }
        mActivityList.clear();
    }

    public void finishOtherActivity() {
        for (int i = 0; i < mActivityList.size(); i++) {
            if (!mActivityList.get(i).getLocalClassName().contains("MainActivity")) {
                mActivityList.get(i).finish();
                mActivityList.remove(i);
            }
        }
    }

    /**
     * APP退出
     *
     * @param activity
     */
    public void quit(Activity activity) {
        if (!mIsFirstBackEvent) {
            mIsFirstBackEvent = true;
            ToastUtils.show(activity, "再按一次退出应用！");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIsFirstBackEvent = false;
                }
            }, 2000);
        } else {
            finishAllActivity();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }
}
