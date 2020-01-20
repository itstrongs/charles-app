package com.charles.myapp.fragment.blog;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.charles.library.base.BaseFragment;
import com.charles.myapp.R;
import com.charles.myapp.view.CustomWebView;

import butterknife.BindView;

import static com.charles.myapp.data.ConstantPool.URL_BLOG;

/**
 * Created by itstrongs on 2017/11/3.
 */
public class BlogFragment extends BaseFragment {

    @BindView(R.id.web_view)
    CustomWebView webView;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_blog;
    }

    @Override
    protected void initView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        webView.loadUrl(URL_BLOG);
    }
}
