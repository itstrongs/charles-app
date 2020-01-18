package com.charles.eden.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.charles.eden.R;
import com.charles.eden.activity.LoginActivity;
import com.charles.eden.activity.MainActivity;
import com.charles.eden.model.ConstantPool;
import com.charles.utils.SPHelper;
import com.charles.utils.ToastUtils;
import com.charles.utils.base.BaseFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

import static com.charles.eden.model.ConstantPool.SP_IS_RELEASE;

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
    @BindView(R.id.switch_server)
    Switch switchServer;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SPHelper.getBoolean(mContext, ConstantPool.SP_IS_LOGIN)) {
            textUsername.setText(SPHelper.getString(mContext, ConstantPool.SP_NICKNAME));
            Picasso.with(mContext).load(SPHelper.getString(mContext, ConstantPool.SP_PORTRAIT)).into(imgPortrait);
        } else {
            startActivity(new Intent(mActivity, LoginActivity.class));
        }
        switchServer.setChecked(SPHelper.getBoolean(mContext, SP_IS_RELEASE));
        switchServer.setOnCheckedChangeListener((compoundButton, b) -> {
            SPHelper.putBoolean(mContext, SP_IS_RELEASE, b);
            compoundButton.setText(b ? "release" : "debug");
        });
    }

    @OnClick({R.id.text_switch_server, R.id.text_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_switch_server:
                SPHelper.putString(mContext, ConstantPool.SP_BASE_URL, "http://121.41.74.211/");
                ToastUtils.show(mContext, "切换成功");
                break;
            case R.id.text_logout:
                SPHelper.putBoolean(mContext, ConstantPool.SP_IS_LOGIN, false);
                SPHelper.putString(mContext, ConstantPool.SP_USERNAME, "");
                SPHelper.putLong(mContext, ConstantPool.SP_USER_ID, null);
                SPHelper.putString(mContext, ConstantPool.SP_AUTHORIZATION, "");
                ((MainActivity) mActivity).switchFragment(NoteFragment.class);
                break;
        }
    }
}
