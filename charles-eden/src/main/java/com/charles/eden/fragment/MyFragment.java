package com.charles.eden.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.charles.eden.R;
import com.charles.eden.activity.LoginActivity;
import com.charles.eden.model.ConstantPool;
import com.charles.utils.SPHelper;
import com.charles.utils.StringUtils;
import com.charles.utils.base.BaseFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 回忆录
 *
 * @Author liufengqiang <fq1781@163.com>
 * @Date 2019年08月02日 17:49
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.img_portrait)
    ImageView imgPortrait;
    @BindView(R.id.text_username)
    TextView textUsername;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void onResume() {
        super.onResume();
        textUsername.setText(SPHelper.getString(mContext, ConstantPool.SP_NICKNAME));
        String poetrait = SPHelper.getString(mContext, ConstantPool.SP_PORTRAIT);
        if (StringUtils.isNotEmpty(poetrait)) {
            Picasso.with(mContext).load(poetrait).into(imgPortrait);
        }
    }

    @OnClick({R.id.text_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_logout:
                SPHelper.putString(mContext, ConstantPool.SP_USERNAME, "");
                SPHelper.putLong(mContext, ConstantPool.SP_USER_ID, 0L);
                SPHelper.putString(mContext, ConstantPool.SP_AUTHORIZATION, "");
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
        }
    }
}
