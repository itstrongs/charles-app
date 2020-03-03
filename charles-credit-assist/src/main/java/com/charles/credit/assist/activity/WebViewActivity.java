package com.charles.credit.assist.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.credit.assist.model.ConstantPool;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 隐私协议页面
 *
 * @Author: liufengqiang
 * @Date: 2019/6/12
 */
public class WebViewActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        initStatusBar();
        webView.loadUrl(ConstantPool.URL_PROTOCOL);
    }
}
