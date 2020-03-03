package com.charles.credit.assist.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.charles.credit.assist.R;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.credit.assist.model.ConstantPool;
import com.charles.library.utils.AppUtils;
import com.charles.library.utils.Logger;
import com.charles.library.utils.SPHelper;
import com.charles.library.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 邀请好友页面
 *
 * @Author: liufengqiang
 * @Date: 2019/6/29
 */
public class InviteActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.img_back, R.id.text_share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.text_share:
                Logger.d("SPHelper.getLong(mContext, ConstantPool.SP_USER_ID" + SPHelper.getLong(mContext, ConstantPool.SP_USER_ID));
                String url = ConstantPool.URL_SHARE + SPHelper.getLong(mContext, ConstantPool.SP_USER_ID);
                AppUtils.copyClipboard(mContext, url);
                ToastUtils.show(mContext, "链接已复制到剪贴板，快粘贴给好友分享吧~");
//                IntentUtils.openURLByBrowser(mContext, url);
                break;
        }
    }
}
