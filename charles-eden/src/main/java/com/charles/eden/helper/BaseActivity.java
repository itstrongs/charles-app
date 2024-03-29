package com.charles.eden.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.charles.utils.Logger;
import com.charles.utils.SPHelper;
import com.charles.utils.ToastUtils;
import com.charles.utils.helper.ActivityHelper;

import java.util.UUID;

import static com.charles.eden.model.ConstantPool.SP_ACTIVITY_UUID;

/**
 * Created by 刘奉强 on 2018/11/29 12:00
 * <p>
 * Describe：
 */
public class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Activity mActivity;

    private boolean mIsFirstBack;
    private String mActivityUuid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        mContext = getApplicationContext();
        mActivity = this;
        mActivityUuid = UUID.randomUUID().toString();
        ActivityHelper.INSTANCE.add(this);
    }

    protected void exitApp() {
        if (!mIsFirstBack) {
            mIsFirstBack = true;
            ToastUtils.show(mContext, "再按一次退出应用！");
            new Handler().postDelayed(() -> mIsFirstBack = false, 2000);
        } else {
            super.onBackPressed();
            System.exit(0);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.d("BaseActivity onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.d("BaseActivity onResume");
    }

    @Override
    protected void onDestroy() {
        Logger.d("activity被销毁");
        SPHelper.putString(mContext, SP_ACTIVITY_UUID, mActivityUuid);
        ActivityHelper.INSTANCE.remove(this);
        super.onDestroy();
    }
}
