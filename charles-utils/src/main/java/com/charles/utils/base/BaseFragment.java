package com.charles.utils.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 刘奉强 on 2019/6/img_info_bg 19:23
 * <p>
 * Describe：
 */
public abstract class BaseFragment<T> extends Fragment {

    protected Context mContext;
    protected Activity mActivity;
    protected T mBaseActivity;
    protected View mContentView;
    protected Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(setFragmentLayout(), container, false);
        return mContentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        mActivity = getActivity();
        mContext = getContext();
        initView();
        initData();
    }

    protected abstract int setFragmentLayout();

    protected void initView() {
    }

    protected void initData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
