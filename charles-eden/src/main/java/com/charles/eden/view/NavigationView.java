package com.charles.eden.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import com.charles.eden.R;

/**
 * Created by 刘奉强 on 2018/11/29 14:12
 * <p>
 * Describe：
 */
public class NavigationView extends RadioGroup implements View.OnClickListener {

    private OnItemSelectedListener mListener;

    public NavigationView(Context context) {
        this(context, null);
    }

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.navigation_view, this);
        findViewById(R.id.radio_menu_note).setOnClickListener(this);
        findViewById(R.id.radio_menu_todo).setOnClickListener(this);
        findViewById(R.id.radio_menu_sentence).setOnClickListener(this);
        findViewById(R.id.radio_menu_friends).setOnClickListener(this);
        findViewById(R.id.radio_menu_my).setOnClickListener(this);
    }

    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onClick(View view) {
        mListener.onNavigationItemSelected(view.getId());
    }

    public interface OnItemSelectedListener {

        void onNavigationItemSelected(int resId);
    }
}
