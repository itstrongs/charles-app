package com.charles.utils.view;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

/**
 * @Details:
 * @Author: liufengqiang
 * @Date: 2019/6/15
 */
public class ProgressFrameLayout extends FrameLayout {

    public ProgressFrameLayout(@NonNull Context context) {
        super(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

}
