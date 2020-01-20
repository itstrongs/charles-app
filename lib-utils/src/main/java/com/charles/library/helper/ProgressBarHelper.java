package com.charles.library.helper;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

/**
 * Created by Charles on 2018/9/20 09:57 星期四
 */
public class ProgressBarHelper {

    private MyFrameLayout mFrameLayout;

    public void create(Activity activity) {
        mFrameLayout = new MyFrameLayout(activity);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        ProgressBar progressBar = new ProgressBar(activity);
        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams
                (ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params2.gravity = Gravity.CENTER;
        mFrameLayout.addView(progressBar, params2);
        activity.addContentView(mFrameLayout, params);
    }

    public void dismiss() {
        mFrameLayout.setVisibility(View.GONE);
        ((ViewGroup) mFrameLayout.getParent()).removeView(mFrameLayout);
    }

    class MyFrameLayout extends FrameLayout {

        public MyFrameLayout(@NonNull Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            return true;
        }
    }
}
