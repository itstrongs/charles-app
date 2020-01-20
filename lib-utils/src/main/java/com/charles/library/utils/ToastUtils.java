package com.charles.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Charles on 2017/6/10.
 */
public class ToastUtils {

    public static void show(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}