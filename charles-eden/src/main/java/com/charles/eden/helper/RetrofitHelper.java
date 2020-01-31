package com.charles.eden.helper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.charles.utils.Logger;
import com.charles.utils.ToastUtils;
import com.charles.utils.http.HttpResult;
import com.charles.utils.view.ProgressFrameLayout;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.charles.eden.model.ConstantPool.URL_BASE;

/**
 * Created by 刘奉强 on 2018/11/29 20:13
 * <p>
 * Describe：
 */
public enum RetrofitHelper {

    INSTANCE;

    private static final long DEFAULT_TIMEOUT = 5000;
    private Retrofit mRetrofit;

    @SuppressLint("CheckResult")
    public void post(final Activity activity, final RetrofitCallback callback) {
        final ProgressFrameLayout frameLayout = new ProgressFrameLayout(activity);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ProgressBar progressBar = new ProgressBar(activity);
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.gravity = Gravity.CENTER;
        frameLayout.addView(progressBar, params2);
        activity.addContentView(frameLayout, params);
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        HttpService httpService = mRetrofit.create(HttpService.class);
        callback.getObservable(httpService)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Logger.d("http completed");
                    frameLayout.setVisibility(View.GONE);
                    ((ViewGroup) frameLayout.getParent()).removeView(frameLayout);
                    Logger.i("http result ==> " + result.toString());
                    if (result.getCode() == 200) {
                        callback.onResult(result);
                    } else {
                        ToastUtils.show(activity, result.getMsg());
                    }
                }, throwable -> {
                    Logger.e("http error ==> " + throwable.getMessage());
                    ToastUtils.show(activity, "网络连接失败");
                    frameLayout.setVisibility(View.GONE);
                    ((ViewGroup) frameLayout.getParent()).removeView(frameLayout);
                }
                );
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new RequestInterceptor());
        return httpClientBuilder.build();
    }

    public interface RetrofitCallback {

        Observable<HttpResult> getObservable(HttpService httpService);

        void onResult(HttpResult result);
    }
}
