package com.charles.utils;

import android.content.Context;
import android.util.Log;

/**
 * Created by Charles on 2017/11/9.
 */
public class Logger {

    private static String TAG = "LOGGER_TAG";

    private static boolean IS_DEBUG_MODE = true;

    public static void setDebug(Context context) {
        IS_DEBUG_MODE = AppUtils.isDebug(context);
    }

    public static void setTag(String tag) {
        TAG = tag;
    }

    public static void d(String log) {
        if (IS_DEBUG_MODE) {
            Log.d(TAG, log);
        }
    }

    public static void i(String log) {
        if (IS_DEBUG_MODE) {
            Log.i(TAG, log);
        }
    }

    public static void v(String log) {
        if (IS_DEBUG_MODE) {
            Log.v(TAG, log);
        }
    }

    public static void w(String log) {
        if (IS_DEBUG_MODE) {
            Log.w(TAG, log);
        }
    }

    public static void e(String log) {
        if (IS_DEBUG_MODE) {
            Log.e(TAG, log);
        }
    }

    public static void e(Throwable e) {
        if (IS_DEBUG_MODE) {
            Log.e(TAG, e.getMessage());
        }
    }
}