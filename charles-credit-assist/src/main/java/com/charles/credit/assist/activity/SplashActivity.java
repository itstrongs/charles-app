package com.charles.credit.assist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.credit.assist.model.ConstantPool;
import com.charles.library.utils.SPHelper;

/**
 * 闪屏页面
 *
 * @Author: liufengqiang
 * @Date: 2019/6/6
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SPHelper.getBoolean(getApplicationContext(), ConstantPool.SP_IS_LOGIN)) {
                    startActivity(new Intent(mContext, MainActivity.class));
                } else {
                    startActivity(new Intent(mContext, LoginActivity.class));
                }
                finish();
            }
        }, 1500);
    }
}
