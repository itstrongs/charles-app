package com.charles.eden.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.charles.eden.R;
import com.charles.eden.activity.LoginActivity;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelper;
import com.charles.eden.model.ConstantPool;
import com.charles.eden.model.bean.UserBo;
import com.charles.utils.SPHelper;
import com.charles.utils.StringUtils;
import com.charles.utils.base.BaseFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

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
    @BindView(R.id.text_signature)
    TextView textSignature;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_my;
    }

    @Override
    public void onResume() {
        super.onResume();
        RetrofitHelper.INSTANCE.post(mActivity, UserBo.class, new RetrofitHelper.RetrofitCallback<UserBo>() {
            @Override
            public Observable<JSONObject> getObservable(HttpService httpService) {
                return httpService.userInfo();
            }

            @Override
            public void onResult(String msg, UserBo userBo) {
                textUsername.setText(userBo.getNickname());
                if (StringUtils.isNotEmpty(userBo.getPortrait())) {
                    Picasso.with(mContext).load(userBo.getPortrait()).into(imgPortrait);
                }
                textSignature.setText(userBo.getSignature());
            }
        });

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
