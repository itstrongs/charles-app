package com.charles.utils.http;//package com.jdangame.utils;

import android.app.Activity;

import com.charles.utils.Logger;
import com.charles.utils.ToastUtils;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by itstrongs on 2017/11/10.
 */
public class OkHttpHelper {

    private static OkHttpHelper mHelper;

    private OkHttpClient mClient;

    private OkHttpHelper() {
        mClient = new OkHttpClient();
    }

    public static OkHttpHelper getInstance() {
        if (mHelper == null) {
            mHelper = new OkHttpHelper();
        }
        return mHelper;
    }

    public void get(final Activity activity, final String url, final HttpCallback callback) {
        Logger.d("请求URL:" + url);
        Request request = new Request.Builder().url(url).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String result = response.body().string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Logger.d("请求结果:" + result);
                        callback.onSuccess(result);
                    }
                });
            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() { }
                });
            }
        });
    }

    public void post(final Activity activity, final String url, Map<String, String> map, final HttpCallback callback) {
        Logger.d("请求URL:" + url);
        FormBody.Builder builder = new FormBody.Builder();
        if (map != null) {
            Logger.d("请求参数:" + map);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (entry.getValue() == null) {
                    ToastUtils.show(activity, entry.getKey() + "不能为空");
                    return;
                }
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String result = response.body().string();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Logger.d("请求结果:" + result);
                        callback.onSuccess(result);
                    }
                });
            }

            @Override
            public void onFailure(Call arg0, IOException arg1) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Logger.d("请求失败");
                    }
                });
            }
        });
    }

    interface HttpCallback {

        void onSuccess(String result);
    }
}
