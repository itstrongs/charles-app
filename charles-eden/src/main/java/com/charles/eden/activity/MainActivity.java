package com.charles.eden.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.charles.eden.R;
import com.charles.eden.fragment.FriendsFragment;
import com.charles.eden.fragment.MicroSentenceFragment;
import com.charles.eden.fragment.MyFragment;
import com.charles.eden.fragment.NoteFragment;
import com.charles.eden.fragment.PhotoStoryTabFragment;
import com.charles.eden.fragment.TodoTabFragment;
import com.charles.eden.helper.BaseActivity;
import com.charles.eden.model.ConstantPool;
import com.charles.eden.view.NavigationView;
import com.charles.utils.SPHelper;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    private String mCurrentFragment;
    private Map<String, Fragment> mFragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        navigationView.setOnItemSelectedListener(resId -> {
            if (resId == R.id.radio_menu_note) {
                switchFragment(NoteFragment.class);
            } else if (resId == R.id.radio_menu_todo) {
                switchFragment(TodoTabFragment.class);
            } else if (resId == R.id.radio_photo_story) {
                switchFragment(PhotoStoryTabFragment.class);
            } else if (resId == R.id.radio_menu_sentence) {
                switchFragment(MicroSentenceFragment.class);
            } else if (resId == R.id.radio_menu_friends) {
                switchFragment(FriendsFragment.class);
            } else if (resId == R.id.radio_menu_my) {
                if (SPHelper.getBoolean(mContext, ConstantPool.SP_IS_LOGIN)) {
                    switchFragment(MyFragment.class);
                } else {
                    startActivity(new Intent(mActivity, LoginActivity.class));
                }
            }
        });
        initFragment();
    }

    private void initFragment() {
        mFragmentMap = new HashMap<>();
        mCurrentFragment = NoteFragment.class.getSimpleName();
        mFragmentMap.put(mCurrentFragment, new NoteFragment());
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
        exitApp();
    }
}
