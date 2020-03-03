package com.charles.credit.assist.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.charles.credit.assist.R;
import com.charles.utils.helper.ActivityHelper;

/**
 * Created by 刘奉强 on 2019/5/27 23:28
 * <p>
 * Describe：
 */
public class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Activity mActivity;
    public Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        mActivity = this;
        ActivityHelper.INSTANCE.add(this);
        mPresenter = new Presenter();
    }

    protected void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.color_white));
        }
        View decor = getWindow().getDecorView();
        if (true) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityHelper.INSTANCE.remove(this);
    }
}