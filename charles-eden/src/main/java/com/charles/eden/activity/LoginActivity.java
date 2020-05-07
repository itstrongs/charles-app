package com.charles.eden.activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.charles.eden.R;
import com.charles.eden.helper.BaseActivity;
import com.charles.eden.helper.HttpService;
import com.charles.eden.helper.RetrofitHelper;
import com.charles.eden.model.ConstantPool;
import com.charles.eden.model.bo.UserBo;
import com.charles.utils.SPHelper;
import com.charles.utils.StringUtils;
import com.charles.utils.ToastUtils;
import com.charles.utils.helper.ActivityHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * description
 *
 * @author liufengqiang <fq1781@163.com>
 * @date 2019-12-22 11:28
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.edit_username)
    EditText editUsername;
    @BindView(R.id.edit_password)
    EditText editPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        final String username = editUsername.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            ToastUtils.show(mContext, "用户名或密码不能为空");
            return;
        }

        RetrofitHelper.INSTANCE.post(this, UserBo.class, new RetrofitHelper.RetrofitCallback<UserBo>() {
            @Override
            public Observable<JSONObject> getObservable(HttpService httpService) {
                return httpService.userLogin(new UserBo(username, password));
            }

            @Override
            public void onResult(String msg, UserBo userBo) {
                ToastUtils.show(mContext, msg);
                SPHelper.putString(mContext, ConstantPool.SP_USERNAME, userBo.getMobileNo());
                SPHelper.putString(mContext, ConstantPool.SP_NICKNAME, userBo.getNickname());
                SPHelper.putString(mContext, ConstantPool.SP_PORTRAIT, userBo.getPortrait());
                SPHelper.putLong(mContext, ConstantPool.SP_USER_ID, userBo.getId());
                SPHelper.putString(mContext, ConstantPool.SP_AUTHORIZATION, userBo.getAuthorization());
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        ActivityHelper.INSTANCE.quit(this);
    }
}
