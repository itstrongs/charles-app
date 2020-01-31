package com.charles.eden;

import android.app.Application;
import android.content.Context;

import com.charles.utils.Logger;
import com.qiniu.android.common.FixedZone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

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
        Configuration config = new Configuration.Builder()
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
//                .recorder(recorder)           // recorder分片上传时，已上传片记录器。默认null
//                .recorder(recorder, keyGen)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                .zone(FixedZone.zone0)        // 设置区域，不指定会自动选择。指定不同区域的上传域名、备用域名、备用IP。
                .build();
        // 重用uploadManager。一般地，只需要创建一个uploadManager对象
        UploadManager uploadManager = new UploadManager(config, 3);//配置3个线程数并发上传；不配置默认为3，只针对file.size>4M生效。线程数建议不超过5，上传速度主要取决于上行带宽，带宽很小的情况单线程和多线程没有区别
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
