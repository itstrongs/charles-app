package com.charles.credit.assist.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.charles.credit.assist.R;
import com.charles.credit.assist.activity.CouponActivity;
import com.charles.credit.assist.activity.HistoryActivity;
import com.charles.credit.assist.activity.InviteActivity;
import com.charles.credit.assist.activity.LoginActivity;
import com.charles.credit.assist.activity.MainActivity;
import com.charles.credit.assist.activity.VersionActivity;
import com.charles.credit.assist.activity.WebViewActivity;
import com.charles.library.base.BaseFragment;
import com.charles.library.http.HttpResult;
import com.charles.credit.assist.model.ConstantPool;
import com.charles.credit.assist.http.HttpService;
import com.charles.credit.assist.http.RetrofitHelper;
import com.charles.library.utils.SPHelper;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * Created by 刘奉强 on 2019/6/img_info_bg 19:01
 * <p>
 * Describe：
 */
public class MyFragment extends BaseFragment<MainActivity> {

    @BindView(R.id.text_username)
    TextView textUsername;
    @BindView(R.id.text_wechat_code)
    TextView textWechatCode;

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_tab_my;
    }

    @Override
    protected void initView() {
    }

    @Override
    public void onResume() {
        super.onResume();
        textUsername.setText(SPHelper.getString(mContext, ConstantPool.SP_PHONE));
        getUserInfo();
    }

    private void getUserInfo() {
        RetrofitHelper.INSTANCE.post(getActivity(), new RetrofitHelper.RetrofitCallback() {
            @Override
            public Observable<HttpResult> getObservable(HttpService httpService) {
                return httpService.userInfo();
            }

            @Override
            public void onResult(HttpResult result) {
                JSONObject data = result.getJSONData();
                textWechatCode.setText(data.getString("wechatCode"));
            }
        });
    }

    @OnClick({R.id.text_logout, R.id.layout_share, R.id.text_version, R.id.text_protocol,
            R.id.layout_history_report, R.id.img_invite})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_logout:
                logout();
                break;
            case R.id.layout_share:
                startActivityForResult(new Intent(mContext, CouponActivity.class), 917);
                break;
            case R.id.text_version:
                startActivity(new Intent(mContext, VersionActivity.class));
                break;
            case R.id.text_protocol:
                startActivity(new Intent(mContext, WebViewActivity.class));
                break;
            case R.id.layout_history_report:
                startActivity(new Intent(mContext, HistoryActivity.class));
                break;
            case R.id.img_invite:
                startActivity(new Intent(mContext, InviteActivity.class));
                break;
        }
    }

    private void logout() {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(mContext);
        normalDialog.setMessage("确定要退出登录吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SPHelper.putBoolean(mContext, ConstantPool.SP_IS_LOGIN, false);
                        startActivity(new Intent(mContext, LoginActivity.class));
                        getActivity().finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        normalDialog.create().dismiss();
                    }
                });
        normalDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 917 && resultCode == 918) {
            mBaseActivity.switchFragment(HomeFragment.class);
        }
    }
}
