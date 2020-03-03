package com.charles.credit.assist.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 分享页面
 *
 * @Author: liufengqiang
 * @Date: 2019/6/6
 */
public class ShareActivity extends BaseActivity {

    @BindView(R.id.text_share)
    TextView textShare;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.text_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.text_share:
                break;
        }
    }
}
