package com.charles.todo.http;

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
        Request request = chain.request().newBuilder()
                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
                .addHeader("Access-Control-Allow-Origin", "*")
                .addHeader("Access-Control-Allow-Headers", "X-Requested-With")
                .addHeader("Vary", "Accept-Encoding")
                .build();
        return chain.proceed(request);
    }
}
