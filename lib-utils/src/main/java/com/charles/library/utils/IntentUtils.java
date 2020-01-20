package com.charles.library.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Intent工具
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年07月21日 14:14
 */
public class IntentUtils {

    public static void openURLByBrowser(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
