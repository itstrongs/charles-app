package com.charles.credit.assist.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.library.utils.AppUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 版本页面
 *
 * @Author: liufengqiang
 * @Date: 2019/6/7
 */
public class VersionActivity extends BaseActivity {

    @BindView(R.id.text_version)
    TextView textVersion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        ButterKnife.bind(this);
        textVersion.setText(AppUtils.getVersionName(this) + "\n(已更新到最新版本)");
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        onBackPressed();
    }
}
