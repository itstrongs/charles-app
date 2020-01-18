package com.charles.utils.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by Charles on 2018/9/18 09:36 星期二
 */
public class PermissionsHelper {

    private static final String TAG = "PermissionsHelper";

    public static boolean hasPermissions(@NonNull Context context, @Size(min = 1) @NonNull String... perms) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            Log.w(TAG, "hasPermissions: API version < M, returning true by default");
            return true;
        }
        if (context == null) {
            throw new IllegalArgumentException("Can't check permissions for null context");
        }
        for (String perm : perms) {
            if (ContextCompat.checkSelfPermission(context, perm) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void requestPermissions(@NonNull Activity activity, int requestCode, @Size(min = 1) @NonNull String... perms) {
        if (hasPermissions(activity, perms)) {
            return;
        }
        ActivityCompat.requestPermissions(activity, perms, requestCode);
    }
}
