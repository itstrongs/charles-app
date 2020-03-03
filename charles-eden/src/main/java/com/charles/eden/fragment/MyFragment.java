package com.charles.eden.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.charles.eden.R;
import com.charles.eden.activity.MainActivity;
import com.charles.eden.model.ConstantPool;
import com.charles.utils.SPHelper;
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
        Picasso.with(mContext).load(SPHelper.getString(mContext, ConstantPool.SP_PORTRAIT)).into(imgPortrait);
    }

    @OnClick({R.id.text_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_logout:
                SPHelper.putBoolean(mContext, ConstantPool.SP_IS_LOGIN, false);
                SPHelper.putString(mContext, ConstantPool.SP_USERNAME, "");
                SPHelper.putLong(mContext, ConstantPool.SP_USER_ID, 0L);
                SPHelper.putString(mContext, ConstantPool.SP_AUTHORIZATION, "");
                ((MainActivity) mActivity).switchFragment(NoteFragment.class);
                break;
        }
    }
}
