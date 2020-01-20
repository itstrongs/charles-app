package com.charles.credit.assist.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.credit.assist.model.ConstantPool;
import com.charles.credit.assist.helper.DataCallback;
import com.charles.library.utils.SPHelper;
import com.charles.library.utils.StringUtils;
import com.charles.library.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页面
 *
 * @Author: liufengqiang
 * @Date: 2019/6/3
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_verify_code)
    EditText editVerifyCode;
    @BindView(R.id.text_get_verify_code)
    TextView textGetVerifyCode;

    private int mTimeCount;
    private Runnable mRunnable;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.text_get_verify_code, R.id.text_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.text_get_verify_code:
                getVerifyCode();
                break;
            case R.id.text_protocol:
                startActivity(new Intent(this, WebViewActivity.class));
                break;
        }
    }

    private void getVerifyCode() {
        final String telephone = editUsername.getText().toString().trim();
        if (StringUtils.isEmpty(telephone)) {
            ToastUtils.show(mContext, "手机号不能为空");
            return;
        }
        mPresenter.verifyCode(mActivity, telephone, new DataCallback() {
            @Override
            public void result(Object result) {
                textGetVerifyCode.setClickable(false);
                mTimeCount = 60;
                textGetVerifyCode.setText(String.valueOf(mTimeCount));
                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        mTimeCount -= 1;
                        if (mTimeCount < 0) {
                            textGetVerifyCode.setText("获取验证码");
                            textGetVerifyCode.setClickable(true);
                            mHandler.removeCallbacks(mRunnable);
                        } else {
                            textGetVerifyCode.setText(String.valueOf(mTimeCount));
                            mHandler.postDelayed(mRunnable, 1000);
                        }
                    }
                };
                mHandler.postDelayed(mRunnable, 1000);
            }
        });
    }

    private void login() {
        final String telephone = editUsername.getText().toString().trim();
        final String verifyCode = editVerifyCode.getText().toString().trim();
        if (StringUtils.isEmpty(telephone)) {
            ToastUtils.show(mContext, "手机号不能为空");
            return;
        }
        if (StringUtils.isEmpty(verifyCode)) {
            ToastUtils.show(mContext, "验证码不能为空");
            return;
        }
        mPresenter.login(mActivity, telephone, verifyCode);
    }

    @Override
    protected void onResume() {
        super.onResume();
        editUsername.setText(SPHelper.getString(mContext, ConstantPool.SP_PHONE));
    }
}
