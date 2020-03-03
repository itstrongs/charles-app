package com.charles.credit.assist.fragment;

import android.content.Intent;
import android.view.View;

import com.charles.credit.assist.R;
import com.charles.credit.assist.activity.ApplyActivity;
import com.charles.credit.assist.activity.BlacklistActivity;
import com.charles.credit.assist.activity.ContactsActivity;
import com.charles.credit.assist.activity.GradeActivity;
import com.charles.credit.assist.activity.SetMealActivity;
import com.charles.library.base.BaseFragment;
import com.charles.library.utils.ToastUtils;

import butterknife.OnClick;

/**
 * Created by 刘奉强 on 2019/6/img_info_bg 18:58
 * <p>
 * Describe：
 */
public class HomeFragment extends BaseFragment {

    @Override
    protected int setFragmentLayout() {
        return R.layout.fragment_tab_home;
    }

    @Override
    protected void initView() {
    }

    @OnClick({R.id.text_home_menu_0, R.id.text_home_menu_1, R.id.text_home_menu_2,
            R.id.text_contacts, R.id.text_detection, R.id.text_operator})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_detection:
                startActivity(new Intent(mContext, SetMealActivity.class));
                break;
            case R.id.text_home_menu_0:
                startActivity(new Intent(mContext, GradeActivity.class));
                break;
            case R.id.text_home_menu_1:
                startActivity(new Intent(mContext, BlacklistActivity.class));
                break;
            case R.id.text_home_menu_2:
                startActivity(new Intent(mContext, ApplyActivity.class));
                break;
            case R.id.text_contacts:
                startActivity(new Intent(mContext, ContactsActivity.class));
                break;
            case R.id.text_operator:
                ToastUtils.show(mContext, "暂未开放");
                break;
        }
    }
}
