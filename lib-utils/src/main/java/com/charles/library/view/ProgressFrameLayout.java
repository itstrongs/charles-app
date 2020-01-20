package com.charles.library.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.widget.FrameLayout;

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
