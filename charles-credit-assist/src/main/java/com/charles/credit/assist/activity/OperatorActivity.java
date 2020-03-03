package com.charles.credit.assist.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 运营商认证
 *
 * @Author: liufengqiang
 * @Date: 2019/6/30
 */
public class OperatorActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.img_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.img_close:
                onBackPressed();
                break;
        }
    }
}
