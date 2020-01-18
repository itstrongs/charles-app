package com.charles.eden.helper;

import com.charles.eden.NotePlanApp;
import com.charles.eden.model.ConstantPool;
import com.charles.utils.SPHelper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Details:
 * @Author: liufengqiang
 * @Date: 2019/6/3
 */
public class RequestInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String Authorization = SPHelper.getString(NotePlanApp.getContext(), ConstantPool.SP_AUTHORIZATION);
        Request request = chain.request().newBuilder()
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("Access-Control-Allow-Origin", "*")
                .addHeader("Access-Control-Allow-Headers", "X-Requested-With")
                .addHeader("Vary", "Accept-Encoding")
                .addHeader("Authorization", Authorization)
                .build();
        return chain.proceed(request);
    }
}
