package com.charles.credit.assist.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;

import com.charles.credit.assist.R;
import com.charles.credit.assist.fragment.HomeFragment;
import com.charles.credit.assist.fragment.MyFragment;
import com.charles.credit.assist.fragment.WelfareFragment;
import com.charles.credit.assist.helper.BaseActivity;
import com.charles.credit.assist.model.ConstantPool;
import com.charles.credit.assist.view.NavigationView;
import com.charles.library.helper.ActivityHelper;
import com.charles.library.utils.SPHelper;
import com.charles.library.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    private boolean mIsFirstBack;
    private String mCurrentFragment;
    private Map<String, Fragment> mFragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigationView.setOnItemSelectedListener(new NavigationView.OnItemSelectedListener() {
            @Override
            public void onNavigationItemSelected(int resId) {
                if (resId == R.id.radio_btn_0) {
                    switchFragment(HomeFragment.class);
                } else if (resId == R.id.radio_btn_1) {
                    if (SPHelper.getBoolean(mContext, ConstantPool.SP_IS_LOGIN)) {
                        switchFragment(WelfareFragment.class);
                    } else {
                    }
                } else if (resId == R.id.radio_btn_2) {
                    if (SPHelper.getBoolean(mContext, ConstantPool.SP_IS_LOGIN)) {
                        switchFragment(MyFragment.class);
                    } else {
                    }
                }
            }
        });
        initFragment();
        String[] permissions = {Manifest.permission.READ_PHONE_STATE};
        if (ContextCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initFragment() {
        mFragmentMap = new HashMap<>();
        mCurrentFragment = HomeFragment.class.getSimpleName();
        mFragmentMap.put(mCurrentFragment, new HomeFragment());
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame_main_fragment, mFragmentMap.get(mCurrentFragment))
                .commit();
    }

    public void switchFragment(Class clazz) {
        String fragmentTag = clazz.getSimpleName();
        if (!fragmentTag.equals(mCurrentFragment)) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(mFragmentMap.get(mCurrentFragment));
            Fragment fragment = mFragmentMap.get(fragmentTag);
            if (fragment == null) {
                try {
                    fragment = (Fragment) clazz.newInstance();
                    mFragmentMap.put(fragmentTag, fragment);
                    transaction.add(R.id.frame_main_fragment, fragment, fragmentTag).commit();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                transaction.show(fragment).commit();
            }
            mCurrentFragment = fragmentTag;
        }
    }

    @Override
    public void onBackPressed() {
        if (!mIsFirstBack) {
            mIsFirstBack = true;
            ToastUtils.show(mContext, "再按一次退出应用！");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIsFirstBack = false;
                }
            }, 2000);
        } else {
            super.onBackPressed();
            ActivityHelper.INSTANCE.exit();
            System.exit(0);
        }
    }
}